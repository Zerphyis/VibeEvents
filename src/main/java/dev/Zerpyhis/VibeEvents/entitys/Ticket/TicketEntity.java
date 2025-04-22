package dev.Zerpyhis.VibeEvents.entitys.Ticket;

import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "Ticket")

public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @OneToOne
    @JoinColumn(name = "evento_id")
    private EventsEntity events;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private PersonEntity person;

    @NotNull
    private LocalDate dateBuy;

    @NotNull
    private  Double pricePaid;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusTicket status;

    @NotNull
    @Enumerated(EnumType.STRING)
    private  TypeTicket typeTicket;
}
