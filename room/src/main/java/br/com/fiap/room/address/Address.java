package br.com.fiap.room.address;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Address {

    private String street;
    private String number;
    private String city;
    private String state;
    private String cep;

    public Address() {
    }

    public Address(String street, String number, String city, String state, String cep) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCep() {
        return cep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(cep, address.cep);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, state, cep);
    }
}
