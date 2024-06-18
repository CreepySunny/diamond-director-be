package nl.fontys.s3.indi.diamond_director_be.Business.Authentication.Converters;

import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.User;
import nl.fontys.s3.indi.diamond_director_be.Persistance.Entities.UserEntity;

public final class UserConverter {
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
