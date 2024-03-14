package br.com.fiap.users.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    @Query("""
    SELECT u FROM User u WHERE u.name = ':name'
    """)
    Page<User> search(@PathVariable("name") String name, @PathVariable("email") String email, @PathVariable("cpf") String cpf, @PathVariable("passport") String passport, Pageable pageable);
}
