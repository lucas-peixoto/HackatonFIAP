package br.com.fiap.users.user;

import java.time.LocalDate;

public record UserResponse(String name, String country, String cpf, String passport, LocalDate birthDate, String address, String phone, String email) {
    public UserResponse(User user) {
        this(user.getName(), user.getCountry(), user.getCpf(), user.getPassport(), user.getBirthDate(), user.getAddress(), user.getPhone(), user.getEmail());
    }
}
