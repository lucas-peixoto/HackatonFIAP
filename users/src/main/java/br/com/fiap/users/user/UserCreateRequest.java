package br.com.fiap.users.user;

import java.time.LocalDate;

public record UserCreateRequest(String name, String country, String cpf, String passport, LocalDate birthDate, String address, String phone, String email) {
}
