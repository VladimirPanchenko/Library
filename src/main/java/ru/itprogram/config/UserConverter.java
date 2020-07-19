package ru.itprogram.config;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.itprogram.domain.dto.User;
import ru.itprogram.domain.entity.UserEntity;

public class UserConverter extends CustomConverter<User, UserEntity> {

    @Override
    public UserEntity convert(User user, Type<? extends UserEntity> type, MappingContext mappingContext) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(user.getUserName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        return userEntity;
    }
}
