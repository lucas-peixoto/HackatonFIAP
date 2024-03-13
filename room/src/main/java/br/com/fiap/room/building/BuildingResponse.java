package br.com.fiap.room.building;

import br.com.fiap.room.room.RoomResponse;

import java.util.List;

public record BuildingResponse(Long id, String name, List<RoomResponse> rooms) {
    public BuildingResponse(Building building) {
        this(building.getId(), building.getName(), building.getRooms().stream().map(RoomResponse::new).toList());
    }
}