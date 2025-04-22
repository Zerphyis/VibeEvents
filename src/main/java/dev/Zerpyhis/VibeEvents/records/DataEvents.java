package dev.Zerpyhis.VibeEvents.records;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;

import java.time.LocalDate;

public record DataEvents(String name,
                         String location,
                         LocalDate date,
                         Integer quantityTicket,
                         CategoryEntity category) {
}
