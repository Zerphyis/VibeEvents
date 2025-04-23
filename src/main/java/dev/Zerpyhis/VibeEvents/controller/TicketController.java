package dev.Zerpyhis.VibeEvents.controller;

import dev.Zerpyhis.VibeEvents.records.DataRegisterTicket;
import dev.Zerpyhis.VibeEvents.records.DataTicketResponse;
import dev.Zerpyhis.VibeEvents.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<DataTicketResponse> createTicket(@RequestBody DataRegisterTicket data) {
        var response = ticketService.createTicket(data);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DataTicketResponse>> getAllTickets() {
        var tickets = ticketService.findAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataTicketResponse> getTicketById(@PathVariable Long id) {
        var ticket = ticketService.findTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
