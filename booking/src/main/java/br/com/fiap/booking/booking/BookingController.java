package br.com.fiap.booking.booking;

import br.com.fiap.booking.room.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookingController {

    private final BookingService bookingService;
    private final RoomService roomService;

    public BookingController(BookingService bookingService, RoomService roomService) {
        this.bookingService = bookingService;
        this.roomService = roomService;
    }

    @PostMapping("/rooms/search")
    public ResponseEntity<Page<RoomResponse>> searchRooms(@Valid @RequestBody RoomSearchRequest roomSearchRequest) {
        Page<RoomClientResponse> roomsAvailable = roomService.search(roomSearchRequest);
        return ResponseEntity.ok(roomsAvailable.map(RoomResponse::new));
    }

    @PostMapping("/booking/start")
    public ResponseEntity<BookingResponse> startBooking(@Valid @RequestBody BookingStartRequest bookingStartRequest) {
        Booking booking = bookingService.start(bookingStartRequest);
        return ResponseEntity.ok(new BookingResponse(booking));
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable Long id) {
        Booking booking = bookingService.findById(id);
        return ResponseEntity.ok(new BookingResponse(booking));
    }

    @PostMapping("/booking/{id}/confirm")
    public ResponseEntity<BookingResponse> confirm(@PathVariable Long id) {
        Booking booking = bookingService.confirm(id);
        return ResponseEntity.ok(new BookingResponse(booking));
    }
}
