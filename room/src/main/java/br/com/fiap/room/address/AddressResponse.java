package br.com.fiap.room.address;

public record AddressResponse(String street, String number, String city, String state,
                              String cep) {
    public AddressResponse(Address address) {
        this(address.getStreet(), address.getNumber(), address.getCity(), address.getState(), address.getCep());
    }
}