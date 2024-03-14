package br.com.fiap.users.user;

public record UserSearchRequest(Long id, String name, String email, String cpf, String passport) {
}
