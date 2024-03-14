package br.com.fiap.room.location;

import br.com.fiap.room.address.AddressResponse;
import br.com.fiap.room.building.BuildingResponse;

import java.util.List;
import java.util.Map;

/**
 * DTO for {@link Location}
 */
public record LocationResponse(Long id, String name, AddressResponse address, Map<Integer, String> amenities,
                               List<BuildingResponse> buildings) {
    public LocationResponse(Location location) {
        this(location.getId(), location.getName(), new AddressResponse(location.getAddress()), location.getAmenities(),
                location.getBuildings().stream().map(BuildingResponse::new).toList());
    }
}