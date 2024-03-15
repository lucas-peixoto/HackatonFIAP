package br.com.fiap.room.building;

import br.com.fiap.room.exception.NotFoundException;
import br.com.fiap.room.location.Location;
import br.com.fiap.room.location.LocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final LocationRepository locationRepository;

    public BuildingService(BuildingRepository buildingRepository, LocationRepository locationRepository) {
        this.buildingRepository = buildingRepository;
        this.locationRepository = locationRepository;
    }

    public Building create(Long locationId, BuildingCreateRequest buildingRequest) {
        Location location = locationRepository.findById(locationId).orElseThrow(NotFoundException::new);
        Building building = new Building(buildingRequest.name());

        location.addBuilding(building);
        return buildingRepository.save(building);
    }

    public Building update(Long id, BuildingUpdateRequest buildingRequest) {
        Building building = buildingRepository.findById(id).orElseThrow(NotFoundException::new);
        building.update(buildingRequest);
        return buildingRepository.save(building);
    }

    public Building findById(Long id) {
        return buildingRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Building> findAll(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }

    public void delete(Long id) {
        buildingRepository.deleteById(id);
    }
}
