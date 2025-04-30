package dev.Zerpyhis.VibeEvents.records.EventsData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterEvent(@NotBlank String name,
                            @NotBlank String description,
                            @NotBlank String location,
                            @NotNull LocalDate date,
                            @NotNull LocalDate dateEnd,
                            @NotNull Double priceTicket,
                            @NotNull Long categoryId,
                            @NotNull Integer quantityTicket
) {
}
