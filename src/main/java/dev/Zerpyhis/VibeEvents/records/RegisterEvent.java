package dev.Zerpyhis.VibeEvents.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;

public record RegisterEvent(@NotBlank String name,
                            @NotBlank String description,
                            @NotBlank String location,
                            @NotNull LocalDate date,
                            @NotNull Double priceTicket,
                            @NotNull Long categoryId,
                            @NotNull Integer quantityTicket
) {
}
