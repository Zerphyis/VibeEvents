package dev.Zerpyhis.VibeEvents.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RegisterEvent(@NotBlank String name,
                            @NotBlank String location,
                            @NotNull LocalDate date,
                            @NotNull Long categoryId) {
}
