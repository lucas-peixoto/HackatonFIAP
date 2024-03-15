package br.com.fiap.users.user;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {

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
    @Column(unique = true, nullable = false)
    private String email;

    @Deprecated
    public User() {
    }

    public User(String name, String country, String cpf, String passport, LocalDate birthDate, String address, String phone, String email) {
        this.name = name;
        this.country = country;
        this.cpf = cpf;
        this.passport = passport;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassport() {
        return passport;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void update(UserRequest userRequest) {
        this.name = userRequest.name();
        this.country = userRequest.country();
        this.cpf = userRequest.cpf();
        this.passport = userRequest.passport();
        this.birthDate = userRequest.birthDate();
        this.address = userRequest.address();
        this.phone = userRequest.phone();
        this.email = userRequest.email();
    }
}
