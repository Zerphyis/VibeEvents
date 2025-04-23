package dev.Zerpyhis.VibeEvents.records;

import java.time.LocalDate;

public record DataRegisterTicket(
        Long eventId,
        Long personId,
        LocalDate dateBuy,
        Double pricePaid,
        String status,
        String typeTicket
) {
}
