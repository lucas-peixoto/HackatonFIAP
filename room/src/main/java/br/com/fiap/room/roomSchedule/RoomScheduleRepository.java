package br.com.fiap.room.roomSchedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface RoomScheduleRepository extends JpaRepository<RoomSchedule, Long> {
}