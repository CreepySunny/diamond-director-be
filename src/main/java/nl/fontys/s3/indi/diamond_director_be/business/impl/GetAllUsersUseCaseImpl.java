package nl.fontys.s3.indi.diamond_director_be.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.GetAllUsersUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.GetAllUsersResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.User;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {
    private UserRepository userRepository;

    @Override
    public GetAllUsersResponce getAllUsers() {
        List<User> userList = new ArrayList<>();

        userRepository.findAll().forEach(e -> {
            userList.add(User.builder()
                    .email(e.getEmail())
                    .firstName(e.getFirstName())
                    .lastName(e.getLastName())
                    .id(e.getId())
                    .password(e.getPassword())
                    .role(e.getRole())
                    .build());
        });

        return GetAllUsersResponce.builder().allUsers(userList).build();
    }
}
