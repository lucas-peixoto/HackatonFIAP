package br.com.fiap.room.location;

import br.com.fiap.room.address.Address;
import br.com.fiap.room.address.AddressRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocationTest {

    private Location location;
    private LocationUpdateRequest locationRequest;
    private Address newAddress;
    Map<Integer, String> amenities, newAmenities;

    @BeforeEach
    void setUp() {
        AddressRequest addressRequest = new AddressRequest("Street", "123", "City", "State", "12345");
        Address address = new Address("Street", "123", "City", "State", "12345");
        newAddress = new Address(addressRequest.street(), addressRequest.number(), addressRequest.city(), addressRequest.state(), addressRequest.cep());
        amenities = new HashMap<>();
        amenities.put(1, "Pool");
        newAmenities = new HashMap<>();
        newAmenities.put(1, "Cool Pool");

        location = new Location("Location Name", address, amenities);

        locationRequest = mock(LocationUpdateRequest.class);
        when(locationRequest.name()).thenReturn("New Location Name");
        when(locationRequest.address()).thenReturn(addressRequest);
        when(locationRequest.amenities()).thenReturn(newAmenities);
    }

    @Test
    void updateChangesRequiredFields() {
        location.update(locationRequest);

        assertEquals("New Location Name", location.getName());
        assertEquals(newAddress, location.getAddress());
        assertEquals(newAmenities, location.getAmenities());
    }
}