package br.com.fiap.booking.booking;

import br.com.fiap.booking.room.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @PostMapping("/rooms/search")
    public ResponseEntity<Page<RoomResponse>> searchRooms(@Valid RoomSearchRequest roomSearchRequest) {
        Page<Room> roomsAvailable = roomService.search(roomSearchRequest);
        return ResponseEntity.ok(roomsAvailable.map(RoomResponse::new));
    }

//    @PostMapping("/booking/start")
//    public ResponseEntity<BookingResponse> startBooking(@Valid BookingStartRequest bookingStartRequest) {
//        Booking booking = bookingService.start(bookingStartRequest);
//        return ResponseEntity.ok(new BookingStartResponse(booking));
//    }
}
