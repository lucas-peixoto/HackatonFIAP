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

    public User create(UserRequest userRequest) {
        userValidator.validateForCreation(userRequest).throwIfInvalid();
        User user = new User(userRequest.name(), userRequest.country(), userRequest.cpf(), userRequest.passport(), userRequest.birthDate(), userRequest.address(), userRequest.phone(), userRequest.email());
        return userRepository.save(user);
    }

    public Page<User> findAll(Pageable pageable) {
       return   userRepository.findAll(pageable);
    }

    public Page<User> search(UserResponse userResponse, Pageable pageable) {
       return userRepository.search(userResponse.name(), userResponse.email(), userResponse.cpf(), userResponse.passport(), pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
