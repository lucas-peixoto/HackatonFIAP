package br.com.fiap.users.user;

import br.com.fiap.users.exception.ValidationFieldsException;
import br.com.fiap.users.exception.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private UserValidator userValidator;
//    private User user;
    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userValidator = spy(new UserValidator(userRepository));
//        user = spy(new User("Lucas", "Brasil", "12345678990", "CS265436", LocalDate.of(1998, 5, 15), "Rua ", "0000000000", "email@email.com"));
//        when(userRepository.save(any())).thenReturn(user);


        userRequest = spy(new UserRequest("Lucas", "Brasil", "12345678990", "CS265436", LocalDate.of(1998, 5, 15), "Rua ", "0000000000", "email@email.com"));
        userService = new UserService(userRepository, userValidator);
    }

    @Test
    void create_should_create_an_user_when_email_is_new() {
        userService.create(userRequest);

        verify(userRepository).existsByEmail(userRequest.email());
        verify(userRepository).save(any(User.class));
        verify(userValidator).validateForCreation(any(UserRequest.class));
    }

    @Test
    void create_should_not_create_an_user_if_email_already_exists() {
        when(userRepository.existsByEmail(userRequest.email())).thenReturn(true);
        when(userValidator.validateForCreation(userRequest)).thenReturn(mock(ValidationResult.class));

        userService.create(userRequest);

        verify(userRepository).existsByEmail(userRequest.email());
        verify(userValidator).validateForCreation(any(UserRequest.class));
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }
}