package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.exceptions.CategoryNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.EventsNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataEvents;
import dev.Zerpyhis.VibeEvents.records.DataEventsReponse;
import dev.Zerpyhis.VibeEvents.records.RegisterEvent;
import dev.Zerpyhis.VibeEvents.repositorys.CategoryRepository;
import dev.Zerpyhis.VibeEvents.repositorys.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public DataEventsReponse createEvent(RegisterEvent data) {
        CategoryEntity category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));

        EventsEntity event = new EventsEntity(new DataEvents(
                data.name(),
                data.description(),
                data.location(),
                data.date(),
                data.priceTicket(),
                data.quantityTicket(),
                category
        ));

        EventsEntity saved = eventsRepository.save(event);
        return new DataEventsReponse(saved.getName(), saved.getDescription(), saved.getLocation(), saved.getDate(), saved.getQuantiyTicket(), saved.getPriceTicket(), saved.getCategory().getName());
    }

    public List<DataEventsReponse> findAllEvents() {
        return eventsRepository.findAll()
                .stream()
                .map(e -> new DataEventsReponse(
                        e.getName(),
                        e.getDescription(),
                        e.getLocation(),
                        e.getDate(),
                        e.getQuantiyTicket(),
                        e.getPriceTicket(),
                        e.getCategory().getName()
                ))
                .collect(Collectors.toList());
    }

    public DataEventsReponse findEventById(Long id) {
        EventsEntity event = eventsRepository.findById(id)
                .orElseThrow(() -> new EventsNotFoundException("Evento não encontrado"));

        return new DataEventsReponse(event.getName(), event.getDescription(), event.getLocation(), event.getDate(),event.getQuantiyTicket(), event.getPriceTicket(),event.getCategory().getName());
    }

    @Transactional
    public void deleteEvent(Long id) {
        if (!eventsRepository.existsById(id)) {
            throw new EventsNotFoundException("Evento não encontrado");
        }
        eventsRepository.deleteById(id);
    }

    @Transactional
    public DataEventsReponse updateEvent(Long eventId, RegisterEvent data) {
        EventsEntity existingEvent = eventsRepository.findById(eventId)
                .orElseThrow(() -> new EventsNotFoundException("Evento não encontrado"));

        CategoryEntity category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada"));

        existingEvent.setName(data.name());
        existingEvent.setDescription(data.description());
        existingEvent.setLocation(data.location());
        existingEvent.setDate(data.date());
        existingEvent.setQuantiyTicket(data.quantityTicket());
        existingEvent.setCategory(category);
        existingEvent.setPriceTicket(data.priceTicket());

        EventsEntity updated = eventsRepository.save(existingEvent);
        return new DataEventsReponse(updated.getName(), updated.getDescription(), updated.getLocation(), updated.getDate(),updated.getQuantiyTicket(), updated.getPriceTicket(), updated.getCategory().getName());
    }
}
