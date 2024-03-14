package br.com.fiap.room.building;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping("/location/{id}/buildings")
    public ResponseEntity<BuildingResponse> create(@PathVariable Long id, @Valid @RequestBody BuildingCreateRequest buildingRequest) {
        Building building = buildingService.create(id, buildingRequest);
        return ResponseEntity.ok(new BuildingResponse(building));
    }

    @PutMapping("/buildings/{id}")
    public ResponseEntity<BuildingResponse> update(@PathVariable Long id, @Valid @RequestBody BuildingUpdateRequest buildingRequest) {
        Building building = buildingService.update(id, buildingRequest);
        return ResponseEntity.ok(new BuildingResponse(building));
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<BuildingResponse> findById(@PathVariable Long id) {
        Building building = buildingService.findById(id);
        return ResponseEntity.ok(new BuildingResponse(building));
    }

    @GetMapping("/buildings")
    public ResponseEntity<Page<BuildingResponse>> findAll(Pageable pageable) {
        Page<Building> buildings = buildingService.findAll(pageable);
        return ResponseEntity.ok(buildings.map(BuildingResponse::new));
    }

    @DeleteMapping("/buildings/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        buildingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
