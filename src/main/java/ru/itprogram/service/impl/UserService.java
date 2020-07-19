package ru.itprogram.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.LoggableAfterReturning;
import ru.itprogram.aspect.LoggableBefore;
import ru.itprogram.aspect.LoggableException;
import ru.itprogram.domain.dto.User;
import ru.itprogram.domain.entity.RoleEntity;
import ru.itprogram.domain.entity.UserEntity;
import ru.itprogram.exception.EntityNotFoundException;
import ru.itprogram.exception.UserAlreadyExistsException;
import ru.itprogram.repository.RoleRepository;
import ru.itprogram.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static ru.itprogram.utils.MessageLog.WRITE_USER_DB;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MapperFacade mapperFacade;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @LoggableException
    @LoggableAfterReturning
    public User findUserById(Long userId) {
        return mapperFacade.
                map(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new), User.class);
    }

    public List<User> allUsers() {
        return mapperFacade.mapAsList(userRepository.findAll(), User.class);
    }

    @LoggableBefore
    public void saveUser(User user) {
        log.info(WRITE_USER_DB, user);

        if (userExists(user)) {
            throw new UserAlreadyExistsException();
        }

        RoleEntity roleUser = roleRepository.getOne(1l);
        addNewUser(user, roleUser);
    }

    private boolean userExists(User user) {
        return Objects.nonNull(userRepository.findByUsername(user.getUserName()));
    }

    private void addNewUser(User user, RoleEntity defaultRole) {
        UserEntity newUser = mapperFacade.map(user, UserEntity.class);
        newUser.setRoles(Collections.singleton(defaultRole));
        userRepository.save(newUser);
    }
}
