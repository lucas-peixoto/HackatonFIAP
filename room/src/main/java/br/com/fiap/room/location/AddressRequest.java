package br.com.fiap.room.location;

public record AddressRequest(String street, String number, String city, String state,
                             String cep) {
}