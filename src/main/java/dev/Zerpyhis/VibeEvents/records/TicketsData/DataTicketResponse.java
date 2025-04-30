package dev.Zerpyhis.VibeEvents.records.TicketsData;

import java.time.LocalDate;

public record DataTicketResponse(
        String eventName,
        String personName,
        LocalDate dateBuy,
        Double pricePaid,
        String status,
        String typeTicket
) {
}
