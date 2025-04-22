package dev.Zerpyhis.VibeEvents.records;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;

import java.time.LocalDate;

public record DataEvents(String name,
                         String description,
                         String location,
                         LocalDate date,
                         Double priceTicket,
                         Integer quantityTicket,
                         CategoryEntity category) {
}
