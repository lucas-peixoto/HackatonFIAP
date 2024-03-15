package br.com.fiap.room.room;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r NOT IN (SELECT rs.room FROM RoomSchedule rs WHERE rs.checkIn BETWEEN ?1 AND ?2 OR rs.checkOut BETWEEN ?1 AND ?2)")
    Page<Room> findAllAvailable(LocalDate checkIn, LocalDate checkOut, Pageable pageable);
}