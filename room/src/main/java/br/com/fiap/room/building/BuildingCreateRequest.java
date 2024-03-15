package br.com.fiap.room.building;

import jakarta.validation.constraints.NotBlank;

public record BuildingCreateRequest(@NotBlank String name) {
}