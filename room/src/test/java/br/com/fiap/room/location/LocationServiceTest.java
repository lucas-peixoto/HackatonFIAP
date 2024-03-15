package br.com.fiap.room.location;

import br.com.fiap.room.address.Address;
import br.com.fiap.room.address.AddressRequest;
import br.com.fiap.room.exception.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LocationServiceTest {

    private LocationService locationService;
    private LocationRepository locationRepository;
    private LocationCreateRequest createRequest;
    private LocationUpdateRequest updateRequest;
    private AddressRequest addressRequest;
    private Map<Integer, String> amenities;

    @BeforeEach
    void setUp() {
        locationRepository = mock(LocationRepository.class);
        locationService = new LocationService(locationRepository);

        addressRequest = new AddressRequest("Street", "123", "City", "State", "12345");
        amenities = new HashMap<>();
        amenities.put(1, "Pool");

        createRequest = mock(LocationCreateRequest.class);
        when(createRequest.name()).thenReturn("Location Name");
        when(createRequest.address()).thenReturn(addressRequest);
        when(createRequest.amenities()).thenReturn(amenities);

        updateRequest = mock(LocationUpdateRequest.class);
        when(updateRequest.name()).thenReturn("New Location Name");
        when(updateRequest.address()).thenReturn(addressRequest);
        when(updateRequest.amenities()).thenReturn(amenities);
    }

    @Test
    void createLocationSuccessfully() {
        Location location = new Location(createRequest.name(), new Address(addressRequest.street(), addressRequest.number(), addressRequest.city(), addressRequest.state(), addressRequest.cep()), createRequest.amenities());
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location createdLocation = locationService.create(createRequest);

        assertEquals(location, createdLocation);
    }

    @Test
    void updateLocationSuccessfully() {
        Location location = new Location(createRequest.name(), new Address(addressRequest.street(), addressRequest.number(), addressRequest.city(), addressRequest.state(), addressRequest.cep()), createRequest.amenities());
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));
        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location updatedLocation = locationService.update(1L, updateRequest);

        assertEquals(location, updatedLocation);
    }

    @Test
    void updateLocationNotFound() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> locationService.update(1L, updateRequest));
    }

    @Test
    void findLocationByIdSuccessfully() {
        Location location = new Location(createRequest.name(), new Address(addressRequest.street(), addressRequest.number(), addressRequest.city(), addressRequest.state(), addressRequest.cep()), createRequest.amenities());
        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));

        Location foundLocation = locationService.findById(1L);

        assertEquals(location, foundLocation);
    }

    @Test
    void findLocationByIdNotFound() {
        when(locationRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> locationService.findById(1L));
    }

    @Test
    void findAllLocations() {
        Page<Location> page = Page.empty();
        when(locationRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<Location> foundPage = locationService.findAll(Pageable.unpaged());

        assertEquals(page, foundPage);
    }

    @Test
    void deleteLocationSuccessfully() {
        doNothing().when(locationRepository).deleteById(anyLong());

        locationService.delete(1L);

        verify(locationRepository).deleteById(anyLong());
    }
}