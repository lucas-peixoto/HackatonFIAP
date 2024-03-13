package br.com.fiap.room.location;

import br.com.fiap.room.address.Address;

import java.io.Serializable;

/**
 * DTO for {@link br.com.fiap.room.address.Address}
 */
public record AddressResponse(String street, String number, String city, String state,
                              String cep) implements Serializable {
    public AddressResponse(Address address) {
        this(address.getStreet(), address.getNumber(), address.getCity(), address.getState(), address.getCep());
    }
}