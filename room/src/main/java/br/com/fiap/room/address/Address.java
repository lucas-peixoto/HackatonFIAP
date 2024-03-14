package br.com.fiap.room.address;

import jakarta.persistence.Embeddable;

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
}
