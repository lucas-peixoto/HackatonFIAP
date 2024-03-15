package br.com.fiap.users.user;

import br.com.fiap.users.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    public User create(UserCreateRequest userCreateRequest) {
        userValidator.validateForCreation(userCreateRequest).throwIfInvalid();
        User user = new User(userCreateRequest.name(), userCreateRequest.country(), userCreateRequest.cpf(), userCreateRequest.passport(), userCreateRequest.birthDate(), userCreateRequest.address(), userCreateRequest.phone(), userCreateRequest.email());
        return userRepository.save(user);
    }

    public User update(UserUpdateRequest userUpdateRequest, Long id) {
        userValidator.validateForUpdate(userUpdateRequest).throwIfInvalid();
        User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        user.update(userUpdateRequest);
        return userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
       return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
