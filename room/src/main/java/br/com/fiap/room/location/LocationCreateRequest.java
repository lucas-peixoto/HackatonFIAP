package br.com.fiap.room.location;

import br.com.fiap.room.address.AddressRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record LocationCreateRequest(@NotBlank String name, @NotNull AddressRequest address,
                                    @NotNull Map<Integer, String> amenities) {
}