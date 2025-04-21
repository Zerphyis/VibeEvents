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
                data.location(),
                data.date(),
                category
        ));

        EventsEntity saved = eventsRepository.save(event);
        return new DataEventsReponse(saved.getName(), saved.getLocation(), saved.getDate(), saved.getCategory().getName());
    }

    public List<DataEventsReponse> findAllEvents() {
        return eventsRepository.findAll()
                .stream()
                .map(e -> new DataEventsReponse(
                        e.getName(),
                        e.getLocation(),
                        e.getDate(),
                        e.getCategory().getName()
                ))
                .collect(Collectors.toList());
    }

    public DataEventsReponse findEventById(Long id) {
        EventsEntity event = eventsRepository.findById(id)
                .orElseThrow(() -> new EventsNotFoundException("Evento não encontrado"));

        return new DataEventsReponse(event.getName(), event.getLocation(), event.getDate(), event.getCategory().getName());
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
        existingEvent.setLocation(data.location());
        existingEvent.setDate(data.date());
        existingEvent.setCategory(category);

        EventsEntity updated = eventsRepository.save(existingEvent);
        return new DataEventsReponse(updated.getName(), updated.getLocation(), updated.getDate(), updated.getCategory().getName());
    }
}
