package br.com.fiap.users.users;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String cpf;
    private String passport;
    private LocalDate birthDate;
    private String address;
    private String phone;
    private String email;
}
