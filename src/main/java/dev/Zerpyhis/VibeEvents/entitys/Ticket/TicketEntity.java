package dev.Zerpyhis.VibeEvents.entitys.Ticket;

import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.records.TicketsData.DataTicket;
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


    public TicketEntity(){

    }

    public TicketEntity(DataTicket data) {
        this.events = data.event();
        this.person = data.person();
        this.dateBuy = data.dateBuy();
        this.pricePaid = data.pricePaid();
        this.status = data.status();
        this.typeTicket = data.typeTicket();
    }

    public EventsEntity getEvents() {
        return events;
    }

    public void setEvents(EventsEntity events) {
        this.events = events;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public LocalDate getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(LocalDate dateBuy) {
        this.dateBuy = dateBuy;
    }

    public Double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(Double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public TypeTicket getTypeTicket() {
        return typeTicket;
    }

    public void setTypeTicket(TypeTicket typeTicket) {
        this.typeTicket = typeTicket;
    }
}
