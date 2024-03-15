package br.com.fiap.users.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByPassport(String passport);
}
