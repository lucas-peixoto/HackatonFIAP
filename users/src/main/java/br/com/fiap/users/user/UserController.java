package br.com.fiap.users.user;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserCreateRequest request) {
        User user = userService.create(request);

        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);

        return ResponseEntity.ok(users.map(UserResponse::new));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> update(@Valid @RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        User user = userService.update(userUpdateRequest, id);

        return ResponseEntity.ok(new UserResponse(user));
    }
}
