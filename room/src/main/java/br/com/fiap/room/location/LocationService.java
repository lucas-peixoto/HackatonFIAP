package br.com.fiap.room.location;

import br.com.fiap.room.address.Address;
import br.com.fiap.room.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location create(LocationCreateRequest locationRequest) {
        Address address = new Address(locationRequest.address().street(), locationRequest.address().number(),
                locationRequest.address().city(), locationRequest.address().state(), locationRequest.address().cep());
        Location location = new Location(locationRequest.name(), address, locationRequest.amenities());

        return locationRepository.save(location);
    }

    public Location update(Long id, LocationUpdateRequest locationRequest) {
        Location location = locationRepository.findById(id).orElseThrow(NotFoundException::new);
        location.update(locationRequest);

        return locationRepository.save(location);
    }

    public Location findById(Long id) {
        return locationRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
