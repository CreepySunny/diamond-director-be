package nl.fontys.s3.indi.diamond_director_be.business.impl;

import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;

final class UserConverter {
    private UserConverter(){}

    public static User convert(UserEntity userEntity){
        return User.builder()
                .password(userEntity.getPassword())
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .role(userEntity.getRole())
                .build();
    }
}
