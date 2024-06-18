package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.Business.Authentication.UserLogInUseCase;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInRequest;
import nl.fontys.s3.indi.diamond_director_be.Domain.Auth.LogInResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class LogInController {
    private final UserLogInUseCase userLogInUseCase;

    @PostMapping
    public ResponseEntity<LogInResponse> login(@Valid @RequestBody LogInRequest logInRequest) {
        LogInResponse response = userLogInUseCase.userAuthenticate(logInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
