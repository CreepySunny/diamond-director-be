package nl.fontys.s3.indi.diamond_director_be.configuration.db;

import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.persistance.Entities.UserEntity;
import nl.fontys.s3.indi.diamond_director_be.persistance.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DatabaseDataInitializer {

    private UserRepository userRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateDatabaseInitialDummyData() {
        if (userRepository.count() == 0) {
            userRepository.createUser(UserEntity.builder().role("Player").email("user@example.com").firstName("John").lastName("Smith").password("password").build());
            userRepository.createUser(UserEntity.builder().role("Coach").email("user@example.com").firstName("Sam").lastName("Dornald").password("password").build());
            userRepository.createUser(UserEntity.builder().role("Pitcher").email("user@example.com").firstName("Donald").lastName("Trump").password("password").build());
            userRepository.createUser(UserEntity.builder().role("3B").email("user@example.com").firstName("Barrack").lastName("Obama").password("password").build());
            userRepository.createUser(UserEntity.builder().role("2B").email("user@example.com").firstName("John").lastName("McClain").password("password").build());
        }
    }
}
