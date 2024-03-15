package br.com.fiap.room.building;

import br.com.fiap.room.exception.NotFoundException;
import br.com.fiap.room.location.Location;
import br.com.fiap.room.location.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BuildingServiceTest {

    private BuildingRepository buildingRepository;
    private LocationRepository locationRepository;

    private BuildingService buildingService;

    @BeforeEach
    void setUp() {
        buildingRepository = mock(BuildingRepository.class);
        locationRepository = mock(LocationRepository.class);

        buildingService = new BuildingService(buildingRepository, locationRepository);
    }

    @Test
    void shouldCreateBuilding() {
        Long locationId = 1L;
        BuildingCreateRequest request = new BuildingCreateRequest("New Building");
        Location location = new Location();
        Building building = new Building(request.name());

        when(locationRepository.findById(locationId)).thenReturn(Optional.of(location));
        when(buildingRepository.save(any(Building.class))).thenReturn(building);

        Building result = buildingService.create(locationId, request);

        assertEquals(building, result);
        verify(locationRepository).findById(locationId);
        verify(buildingRepository).save(any(Building.class));
    }

    @Test
    void shouldThrowExceptionWhenLocationNotFoundOnCreate() {
        Long locationId = 1L;
        BuildingCreateRequest request = new BuildingCreateRequest("New Building");

        when(locationRepository.findById(locationId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> buildingService.create(locationId, request));
        verify(locationRepository).findById(locationId);
        verify(buildingRepository, times(0)).save(any(Building.class));
    }

    @Test
    void shouldUpdateBuilding() {
        Long buildingId = 1L;
        BuildingUpdateRequest request = new BuildingUpdateRequest("Updated Building");
        Building building = new Building("Old Building");

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(buildingRepository.save(any(Building.class))).thenReturn(building);

        Building result = buildingService.update(buildingId, request);

        assertEquals("Updated Building", result.getName());
        verify(buildingRepository).findById(buildingId);
        verify(buildingRepository).save(any(Building.class));
    }

    @Test
    void shouldThrowExceptionWhenBuildingNotFoundOnUpdate() {
        Long buildingId = 1L;
        BuildingUpdateRequest request = new BuildingUpdateRequest("Updated Building");

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> buildingService.update(buildingId, request));
        verify(buildingRepository).findById(buildingId);
        verify(buildingRepository, times(0)).save(any(Building.class));
    }
}