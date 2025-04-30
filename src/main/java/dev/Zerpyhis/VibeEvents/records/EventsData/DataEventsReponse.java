package dev.Zerpyhis.VibeEvents.records.EventsData;


import java.time.LocalDate;

public record DataEventsReponse(
        String name,
        String description,
        String location,
        LocalDate date,
        LocalDate dateEnd,
        Integer quantityTicket,
        Double priceTicket,
        String categoryName) {
}
