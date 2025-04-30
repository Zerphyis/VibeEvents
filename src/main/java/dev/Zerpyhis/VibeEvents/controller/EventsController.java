package dev.Zerpyhis.VibeEvents.controller;

import dev.Zerpyhis.VibeEvents.records.EventsData.DataEventsReponse;
import dev.Zerpyhis.VibeEvents.records.EventsData.RegisterEvent;
import dev.Zerpyhis.VibeEvents.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventsController {
    @Autowired
    private EventService eventsService;

    @PostMapping
    public ResponseEntity<DataEventsReponse> createEvent(@RequestBody RegisterEvent data) {
        DataEventsReponse newEvent = eventsService.createEvent(data);
        return ResponseEntity.ok(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<DataEventsReponse>> getAllEvents() {
        List<DataEventsReponse> events = eventsService.findAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataEventsReponse> getEventById(@PathVariable("id") Long id) {
        DataEventsReponse event = eventsService.findEventById(id);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataEventsReponse> updateEvent(@PathVariable("id") Long id, @RequestBody RegisterEvent data) {
        DataEventsReponse updatedEvent = eventsService.updateEvent(id, data);
        return ResponseEntity.ok(updatedEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Long id) {
        eventsService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }
}
