package br.com.fiap.users.user;

import br.com.fiap.users.exception.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidationResult validateForCreation(UserCreateRequest userCreateRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (userRepository.existsByEmail(userCreateRequest.email())) {
            validationResult.addError("email", "User email '%s' already exists".formatted(userCreateRequest.email()));
        }

        return validationResult;
    }

    public ValidationResult validateForUpdate(UserUpdateRequest userUpdateRequest) {
        ValidationResult validationResult = new ValidationResult();

        if (userRepository.existsByEmail(userUpdateRequest.email())) {
            validationResult.addError("email", "User email '%s' already exists".formatted(userUpdateRequest.email()));
        }

        if (userRepository.existsByCpf(userUpdateRequest.cpf())) {
            validationResult.addError("email", "User cpf '%s' already exists".formatted(userUpdateRequest.email()));
        }

        if (userRepository.existsByCpf(userUpdateRequest.passport())) {
            validationResult.addError("email", "User passport '%s' already exists".formatted(userUpdateRequest.email()));
        }

        return validationResult;
    }
}
