package br.com.fiap.users.user;

import br.com.fiap.users.exception.NotFoundException;
import br.com.fiap.users.exception.ValidationFieldsException;
import br.com.fiap.users.exception.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;
    private UserValidator userValidator;
    private User user;
    private UserCreateRequest userCreateRequest;
    private UserUpdateRequest userUpdateRequest;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userValidator = spy(new UserValidator(userRepository));
        user = spy(new User("Lucas", "Brasil", "12345678990", "CS265436", LocalDate.of(1998, 5, 15), "Rua ", "0000000000", "email@email.com"));

        userCreateRequest = spy(new UserCreateRequest("Lucas", "Brasil", "12345678990", "CS265436", LocalDate.of(1998, 5, 15), "Rua ", "0000000000", "email@email.com"));
        userUpdateRequest = spy(new UserUpdateRequest("Lucas Mendes", "Brasil", "12345678922", "CS265437", LocalDate.of(1998, 5, 18), "Rua ", "0000000000", "email@email2.com"));
        userService = new UserService(userRepository, userValidator);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    }

    @Test
    void create__should_create_an_user_when_email_is_new() {
        clearInvocations();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User createdUser = userService.create(userCreateRequest);

        assertThat(user).isEqualTo(createdUser);

        verify(userRepository).existsByEmail(user.getEmail());
    }

    @Test
    void update__should_not_update_when_user_does_not_exist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.update(userUpdateRequest, 1L));
        verify(userValidator).validateForUpdate(any(UserUpdateRequest.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void create_should_not_create_an_user_if_email_already_exists() {
        when(userRepository.existsByEmail(userCreateRequest.email())).thenReturn(true);
        when(userValidator.validateForCreation(userCreateRequest)).thenReturn(mock(ValidationResult.class));

        User nullUser = userService.create(userCreateRequest);

        assertThat(nullUser).isNull();

        verify(userRepository).existsByEmail(userCreateRequest.email());
        verify(userValidator).validateForCreation(any(UserCreateRequest.class));
    }

    @Test
    void update__should_update_when_user_exists() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.update(userUpdateRequest, 1L);

        assertThat(updatedUser).isEqualTo(user);

        verify(userValidator).validateForUpdate(any(UserUpdateRequest.class));
        verify(userRepository).save(any(User.class));
    }

    @Test
    void update__should_throw_validation_fields_exception_when_email_already_exists() {
        when(userRepository.existsByEmail(userUpdateRequest.email())).thenReturn(true);

        assertThrows(ValidationFieldsException.class, () -> userService.update(userUpdateRequest, 1L));

        verify(userValidator).validateForUpdate(any(UserUpdateRequest.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void update__should_throw_validation_fields_exception_when_cpf_already_exists() {
        when(userRepository.existsByCpf(userUpdateRequest.cpf())).thenReturn(true);

        assertThrows(ValidationFieldsException.class, () -> userService.update(userUpdateRequest, 1L));

        verify(userValidator).validateForUpdate(any(UserUpdateRequest.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void update__should_throw_validation_fields_exception_when_passport_already_exists() {
        when(userRepository.existsByCpf(userUpdateRequest.cpf())).thenReturn(true);

        assertThrows(ValidationFieldsException.class, () -> userService.update(userUpdateRequest, 1L));

        verify(userValidator).validateForUpdate(any(UserUpdateRequest.class));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findAll__should_return_an_user_page() {
        Page<User> page = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        when(userRepository.findAll(pageable)).thenReturn(page);

        Page<User> foundPage = userService.findAll(pageable);

        assertThat(foundPage).isNotNull();
        assertThat(foundPage).isEqualTo(page);
    }

    @Test
    void findById__should_return_an_user_from_id() {
        assertThat(userService.findById(1L)).isEqualTo(user);
    }
}