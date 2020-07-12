package ru.itprogram.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.Loggable;
import ru.itprogram.domain.dto.User;
import ru.itprogram.domain.entity.RoleEntity;
import ru.itprogram.domain.entity.UserEntity;
import ru.itprogram.exception.EntityNotFoundException;
import ru.itprogram.repository.RoleRepository;
import ru.itprogram.repository.UserRepository;

import java.util.Collections;
import java.util.List;

import static ru.itprogram.utils.MessageLog.WRITE_USER_DB;

@Data
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final MapperFacade mapperFacade;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserById(Long userId) {
        return mapperFacade.
                map(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new), User.class);
    }

    public List<User> allUsers() {
        return mapperFacade.mapAsList(userRepository.findAll(), User.class);
    }

    @Loggable
    public boolean saveUser(User user) {
        log.info(WRITE_USER_DB, user);
        UserEntity userFromDB = userRepository.findByUsername(user.getUserName());

        if (userFromDB != null) {
            return false;
        }

        RoleEntity role = new RoleEntity();
        role.setName("ROLE_USER");

        UserEntity newUser = new UserEntity();
        newUser.setRoles(Collections.singleton(role));
        newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return true;
    }
}
