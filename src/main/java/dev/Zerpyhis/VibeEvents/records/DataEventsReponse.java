package dev.Zerpyhis.VibeEvents.records;


import java.time.LocalDate;

public record DataEventsReponse(   String name,
        String location,
        LocalDate date,
        String categoryName) {
}
