package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.GetAllUsersUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.GetUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.CreateUserResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.GetAllUsersResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class UserController {
    private CreateUserUseCase createUserUseCase;
    private GetAllUsersUseCase getAllUsersUseCase;
    private GetUserUseCase getUserUseCase;

    @PostMapping()
    public ResponseEntity<CreateUserResponce> createUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponce responce = createUserUseCase.CreateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responce);
    }

    @GetMapping()
    public ResponseEntity<GetAllUsersResponce> getAllUser(){
        return ResponseEntity.ok(getAllUsersUseCase.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id")final Long id){
        return ResponseEntity.ok(getUserUseCase.getUser(id));
    }
}
