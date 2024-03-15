package br.com.fiap.room.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Map;

public record RoomUpdateRequest(@NotNull @Min(1) Integer capacity, @NotNull @Min(0L) BigDecimal dailyRate,
                                @NotNull Map<Integer, String> beds,
                                @NotNull Map<Integer, String> furniture) {
}