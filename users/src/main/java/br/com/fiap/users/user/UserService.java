package br.com.fiap.users.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public User create(UserRequest userRequest) {
        userValidator.validateForCreation(userRequest).throwIfInvalid();
        User user = new User(userRequest.name(), userRequest.country(), userRequest.cpf(), userRequest.passport(), userRequest.birthDate(), userRequest.address(), userRequest.phone(), userRequest.email());
        return userRepository.save(user);
    }
}
