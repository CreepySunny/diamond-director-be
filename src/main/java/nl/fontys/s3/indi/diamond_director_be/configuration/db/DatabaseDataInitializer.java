package nl.fontys.s3.indi.diamond_director_be.configuration.db;

import lombok.AllArgsConstructor;
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

        }
    }
}
