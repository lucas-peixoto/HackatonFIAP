package br.com.fiap.room.location;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/locations")
    public ResponseEntity<LocationResponse> create(@Valid LocationCreateRequest locationRequest) {
        Location location = locationService.create(locationRequest);
        return ResponseEntity.ok(new LocationResponse(location));
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<LocationResponse> update(@PathVariable Long id, @Valid LocationUpdateRequest locationRequest) {
        Location location = locationService.update(id, locationRequest);
        return ResponseEntity.ok(new LocationResponse(location));
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<LocationResponse> findById(@PathVariable Long id) {
        Location location = locationService.findById(id);
        return ResponseEntity.ok(new LocationResponse(location));
    }

    @GetMapping("/locations")
    public ResponseEntity<Page<LocationResponse>> findAll(Pageable pageable) {
        Page<Location> locations = locationService.findAll(pageable);
        return ResponseEntity.ok(locations.map(LocationResponse::new));
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        locationService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
