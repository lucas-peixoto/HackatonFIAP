package br.com.fiap.users.user;

import br.com.fiap.users.exception.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidationResult validateForCreation(UserRequest userRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (userRepository.existsByEmail(userRequest.email())) {
            validationResult.addError("email", "User email '%s' already exists".formatted(userRequest.email()));
        }

        return validationResult;
    }
}
