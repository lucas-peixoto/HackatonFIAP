package br.com.fiap.users.user;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.ok(new UserResponse(user));
    }

    @GetMapping("/user")
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        Page<User> users = userService.findAll(pageable);
        return ResponseEntity.ok(users.map(UserResponse::new));
    }

    @PostMapping("/user/search")
    public ResponseEntity<Page<UserResponse>> search(@RequestBody UserResponse userResponse, Pageable pageable) {
        Page<User> rooms = userService.search(userResponse, pageable);
        return ResponseEntity.ok(rooms.map(UserResponse::new));
    }
}
