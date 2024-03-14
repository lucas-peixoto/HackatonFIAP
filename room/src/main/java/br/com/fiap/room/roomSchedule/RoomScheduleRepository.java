package br.com.fiap.room.roomSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {

    boolean existsByRoom_IdAndCheckInBetweenAndCheckOutBetween(Long id, LocalDate checkInStart, LocalDate checkInEnd, LocalDate checkOutStart, LocalDate checkOutEnd);

    default boolean isAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return !existsByRoom_IdAndCheckInBetweenAndCheckOutBetween(roomId, checkIn, checkOut, checkIn, checkOut);
    }
}