package dev.Zerpyhis.VibeEvents.controller;
import dev.Zerpyhis.VibeEvents.records.EventsData.DataEventsReponse;
import dev.Zerpyhis.VibeEvents.records.EventsData.RegisterEvent;
import dev.Zerpyhis.VibeEvents.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class EventsControllerTest {

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventsController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        // Dados para o teste
        RegisterEvent request = new RegisterEvent(
                "Evento de Teste",
                "Descrição do evento",
                "Local do evento",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 2),
                50.0,
                1L,
                100
        );

        DataEventsReponse response = new DataEventsReponse(
                "Evento de Teste",
                "Descrição do evento",
                "Local do evento",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 2),
                100,
                50.0,
                "Categoria Teste"
        );

        when(eventService.createEvent(request)).thenReturn(response);

        ResponseEntity<DataEventsReponse> result = controller.createEvent(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(eventService).createEvent(request);
    }

    @Test
    void testGetAllEvents() {
        List<DataEventsReponse> list = List.of(
                new DataEventsReponse(
                        "Evento 1",
                        "Descrição 1",
                        "Local 1",
                        LocalDate.of(2025, 5, 1),
                        LocalDate.of(2025, 5, 2),
                        100,
                        50.0,
                        "Categoria 1"
                ),
                new DataEventsReponse(
                        "Evento 2",
                        "Descrição 2",
                        "Local 2",
                        LocalDate.of(2025, 6, 1),
                        LocalDate.of(2025, 6, 2),
                        200,
                        75.0,
                        "Categoria 2"
                )
        );

        when(eventService.findAllEvents()).thenReturn(list);

        ResponseEntity<List<DataEventsReponse>> result = controller.getAllEvents();

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(list, result.getBody());
        verify(eventService).findAllEvents();
    }

    @Test
    void testGetEventById() {
        Long id = 1L;
        DataEventsReponse response = new DataEventsReponse(
                "Evento 1",
                "Descrição 1",
                "Local 1",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 2),
                100,
                50.0,
                "Categoria 1"
        );

        when(eventService.findEventById(id)).thenReturn(response);

        ResponseEntity<DataEventsReponse> result = controller.getEventById(id);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(eventService).findEventById(id);
    }

    @Test
    void testUpdateEvent() {
        Long id = 1L;
        RegisterEvent request = new RegisterEvent(
                "Evento Atualizado",
                "Descrição atualizada",
                "Novo Local",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 2),
                60.0,
                2L,
                150
        );

        DataEventsReponse response = new DataEventsReponse(
                "Evento Atualizado",
                "Descrição atualizada",
                "Novo Local",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 2),
                150,
                60.0,
                "Categoria Atualizada"
        );

        when(eventService.updateEvent(id, request)).thenReturn(response);

        ResponseEntity<DataEventsReponse> result = controller.updateEvent(id, request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());
        verify(eventService).updateEvent(id, request);
    }

    @Test
    void testDeleteEvent() {
        Long id = 1L;

        ResponseEntity<Void> result = controller.deleteEvent(id);

        assertEquals(204, result.getStatusCodeValue());
        verify(eventService).deleteEvent(id);
    }
    }


