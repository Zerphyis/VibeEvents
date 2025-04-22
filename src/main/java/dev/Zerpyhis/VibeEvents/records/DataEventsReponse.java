package dev.Zerpyhis.VibeEvents.records;


import java.time.LocalDate;

public record DataEventsReponse(   String name,
        String location,
        LocalDate date,
        Integer quantityTicket, Double priceTicket,
        String categoryName) {
}
