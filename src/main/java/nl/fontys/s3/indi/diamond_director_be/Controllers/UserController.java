package nl.fontys.s3.indi.diamond_director_be.Controllers;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.s3.indi.diamond_director_be.business.CreateUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.GetAllUsersUseCase;
import nl.fontys.s3.indi.diamond_director_be.business.GetUserUseCase;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserRequest;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.CreateUserResponse;
import nl.fontys.s3.indi.diamond_director_be.domain.GetAllUsersResponce;
import nl.fontys.s3.indi.diamond_director_be.domain.Auth.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5173/")
public class UserController {
    private CreateUserUseCase createUserUseCase;
    private GetAllUsersUseCase getAllUsersUseCase;
    private GetUserUseCase getUserUseCase;

    
    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        CreateUserResponse responce = createUserUseCase.CreateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responce);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping()
    public ResponseEntity<GetAllUsersResponce> getAllUser(){
        return ResponseEntity.ok(getAllUsersUseCase.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable(value = "id")final Long id){
        return ResponseEntity.ok(getUserUseCase.getUser(id));
    }
}
