package dev.Zerpyhis.VibeEvents.controller;

import static org.junit.jupiter.api.Assertions.*;
import dev.Zerpyhis.VibeEvents.records.TicketsData.DataRegisterTicket;
import dev.Zerpyhis.VibeEvents.records.TicketsData.DataTicketResponse;
import dev.Zerpyhis.VibeEvents.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class TicketControllerTest {
    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    private DataRegisterTicket dataRegisterTicket;
    private DataTicketResponse dataTicketResponse;

    @BeforeEach
    void setUp() {
        dataRegisterTicket = new DataRegisterTicket(
                1L,
                1L,
                LocalDate.now(),
                100.0,
                "Active",
                "VIP"
        );

        dataTicketResponse = new DataTicketResponse(
                "Concert",
                "John Doe",
                LocalDate.now(),
                100.0,
                "Active",
                "VIP"
        );
    }

    @Test
    void testCreateTicket() {
        when(ticketService.createTicket(Mockito.any(DataRegisterTicket.class))).thenReturn(dataTicketResponse);

        ResponseEntity<DataTicketResponse> response = ticketController.createTicket(dataRegisterTicket);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dataTicketResponse, response.getBody());
    }

    @Test
    void testGetAllTickets() {
        when(ticketService.findAllTickets()).thenReturn(Collections.singletonList(dataTicketResponse));

        ResponseEntity<List<DataTicketResponse>> response = ticketController.getAllTickets();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals(dataTicketResponse, response.getBody().get(0));
    }

    @Test
    void testGetTicketById() {
        when(ticketService.findTicketById(Mockito.anyLong())).thenReturn(dataTicketResponse);

        ResponseEntity<DataTicketResponse> response = ticketController.getTicketById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(dataTicketResponse, response.getBody());
    }

    @Test
    void testDeleteTicket() {
        Mockito.doNothing().when(ticketService).deleteTicket(Mockito.anyLong());

        ResponseEntity<Void> response = ticketController.deleteTicket(1L);

        assertEquals(204, response.getStatusCodeValue());
    }
}