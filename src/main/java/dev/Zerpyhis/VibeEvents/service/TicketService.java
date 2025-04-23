package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.Ticket.StatusTicket;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.TicketEntity;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.TypeTicket;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.exceptions.EventsNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.PersonNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.TicketNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataRegisterTicket;
import dev.Zerpyhis.VibeEvents.records.DataTicket;
import dev.Zerpyhis.VibeEvents.records.DataTicketResponse;
import dev.Zerpyhis.VibeEvents.repositorys.EventsRepository;
import dev.Zerpyhis.VibeEvents.repositorys.PersonRepository;
import dev.Zerpyhis.VibeEvents.repositorys.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private PersonRepository personRepository;

    public DataTicketResponse createTicket(DataRegisterTicket data) {
        EventsEntity event = eventsRepository.findById(data.eventId())
                .orElseThrow(() -> new EventsNotFoundException("Evento não encontrado"));
        PersonEntity person = personRepository.findById(data.personId())
                .orElseThrow(() -> new PersonNotFoundException("Pessoa não encontrada"));

        TicketEntity ticket = new TicketEntity(
                new DataTicket(
                        event,
                        person,
                        data.dateBuy(),
                        data.pricePaid(),
                        StatusTicket.valueOf(data.status()),
                        TypeTicket.valueOf(data.typeTicket())
                )
        );

        TicketEntity saved = ticketRepository.save(ticket);

        return new DataTicketResponse(
                saved.getEvents().getName(),
                saved.getPerson().getName(),
                saved.getDateBuy(),
                saved.getPricePaid(),
                saved.getStatus().name(),
                saved.getTypeTicket().name()
        );
    }

    public List<DataTicketResponse> findAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(t -> new DataTicketResponse(
                        t.getEvents().getName(),
                        t.getPerson().getName(),
                        t.getDateBuy(),
                        t.getPricePaid(),
                        t.getStatus().name(),
                        t.getTypeTicket().name()
                ))
                .collect(Collectors.toList());
    }

    public DataTicketResponse findTicketById(Long id) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado"));

        return new DataTicketResponse(
                ticket.getEvents().getName(),
                ticket.getPerson().getName(),
                ticket.getDateBuy(),
                ticket.getPricePaid(),
                ticket.getStatus().name(),
                ticket.getTypeTicket().name()
        );
    }

    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException("Ticket não encontrado");
        }
        ticketRepository.deleteById(id);
    }
}
