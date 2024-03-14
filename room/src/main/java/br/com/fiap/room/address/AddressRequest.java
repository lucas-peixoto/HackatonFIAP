package br.com.fiap.room.address;

public record AddressRequest(String street, String number, String city, String state,
                             String cep) {
}