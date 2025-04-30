package dev.Zerpyhis.VibeEvents.records.TicketsData;

import dev.Zerpyhis.VibeEvents.entitys.Ticket.StatusTicket;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.TypeTicket;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;

import java.time.LocalDate;

public record DataTicket(
        EventsEntity event,
        PersonEntity person,
        LocalDate dateBuy,
        Double pricePaid,
        StatusTicket status,
        TypeTicket typeTicket
) {
}
