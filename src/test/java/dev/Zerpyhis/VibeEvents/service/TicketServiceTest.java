package dev.Zerpyhis.VibeEvents.service;

import static org.junit.jupiter.api.Assertions.*;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.StatusTicket;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.TicketEntity;
import dev.Zerpyhis.VibeEvents.entitys.Ticket.TypeTicket;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.exceptions.EventsNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.PersonNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.TicketNotFoundException;
import dev.Zerpyhis.VibeEvents.records.TicketsData.DataRegisterTicket;
import dev.Zerpyhis.VibeEvents.records.TicketsData.DataTicketResponse;
import dev.Zerpyhis.VibeEvents.repositorys.EventsRepository;
import dev.Zerpyhis.VibeEvents.repositorys.PersonRepository;
import dev.Zerpyhis.VibeEvents.repositorys.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest

class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testCreateTicket() {
        DataRegisterTicket data = new DataRegisterTicket(1L, 1L, LocalDate.now(), 100.0, "PENDENTE", "VIP");

        EventsEntity event = new EventsEntity();
        event.setId(1L);
        event.setName("Evento Teste");

        PersonEntity person = new PersonEntity();
        person.setId(1L);
        person.setName("Pessoa Teste");

        when(eventsRepository.findById(1L)).thenReturn(Optional.of(event));
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        TicketEntity ticketMock = new TicketEntity();
        ticketMock.setEvents(event);
        ticketMock.setPerson(person);
        ticketMock.setPricePaid(100.0);
        ticketMock.setDateBuy(LocalDate.now());
        ticketMock.setStatus(StatusTicket.PENDENTE);
        ticketMock.setTypeTicket(TypeTicket.VIP);

        when(ticketRepository.save(any(TicketEntity.class))).thenReturn(ticketMock);

        DataTicketResponse response = ticketService.createTicket(data);

        assertNotNull(response);
        assertEquals("PENDENTE", response.status());
        assertEquals("VIP", response.typeTicket());
        assertEquals("Evento Teste", response.eventName());
        assertEquals("Pessoa Teste", response.personName());
    }

    @Test
    public void testCreateTicketEventNotFound() {
        // Dados de entrada para o teste
        DataRegisterTicket data = new DataRegisterTicket(1L, 1L, LocalDate.now(), 100.0, "PENDENTE", "VIP");

        // Simulando o evento não encontrado
        when(eventsRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificando se a exceção é lançada quando o evento não é encontrado
        assertThrows(EventsNotFoundException.class, () -> ticketService.createTicket(data));
    }

    @Test
    public void testCreateTicketPersonNotFound() {
        // Dados de entrada para o teste
        DataRegisterTicket data = new DataRegisterTicket(1L, 1L, LocalDate.now(), 100.0, "PENDENTE", "VIP");

        // Criando evento mockado
        EventsEntity event = new EventsEntity();
        event.setId(1L);

        // Simulando o evento encontrado e a pessoa não encontrada
        when(eventsRepository.findById(1L)).thenReturn(Optional.of(event));
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificando se a exceção é lançada quando a pessoa não é encontrada
        assertThrows(PersonNotFoundException.class, () -> ticketService.createTicket(data));
    }

    @Test
    public void testFindTicketById() {
        // Criando o TicketEntity mockado
        EventsEntity event = new EventsEntity();
        event.setId(1L);

        PersonEntity person = new PersonEntity();
        person.setId(1L);

        TicketEntity ticket = new TicketEntity();
        ticket.setId(1L);
        ticket.setEvents(event);
        ticket.setPerson(person);
        ticket.setPricePaid(100.0);
        ticket.setDateBuy(LocalDate.now());
        ticket.setStatus(StatusTicket.PENDENTE);
        ticket.setTypeTicket(TypeTicket.VIP);

        // Simulando a busca do ticket no repositório
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Chamando o método que será testado
        DataTicketResponse response = ticketService.findTicketById(1L);

        // Verificando se a resposta não é nula e se os valores estão corretos
        assertNotNull(response);
        assertEquals("PENDENTE", response.status());
        assertEquals("VIP", response.typeTicket());
    }

    @Test
    public void testFindTicketByIdNotFound() {
        // Simulando a busca de um ticket não encontrado
        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificando se a exceção é lançada quando o ticket não é encontrado
        assertThrows(TicketNotFoundException.class, () -> ticketService.findTicketById(1L));
    }

    @Test
    public void testDeleteTicket() {
        // Simulando a existência do ticket no repositório
        when(ticketRepository.existsById(1L)).thenReturn(true);

        // Chamando o método que será testado
        ticketService.deleteTicket(1L);

        // Verificando se o método deleteById foi chamado uma vez
        verify(ticketRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteTicketNotFound() {
        // Simulando que o ticket não existe no repositório
        when(ticketRepository.existsById(1L)).thenReturn(false);

        // Verificando se a exceção é lançada quando o ticket não é encontrado
        assertThrows(TicketNotFoundException.class, () -> ticketService.deleteTicket(1L));
    }
}