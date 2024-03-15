package br.com.fiap.room.building;

import br.com.fiap.room.room.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BuildingTest {

    private Building building;

    @BeforeEach
    void setUp() {
        building = new Building("Building");
    }

    @Test
    void shouldAddRoomToBuilding() {
        Room room = new Room();
        building.addRoom(room);

        assertTrue(building.getRooms().contains(room));
    }

    @Test
    void shouldUpdateBuildingName() {
        BuildingUpdateRequest request = new BuildingUpdateRequest("New Building Name");
        building.update(request);

        assertEquals("New Building Name", building.getName());
    }
}