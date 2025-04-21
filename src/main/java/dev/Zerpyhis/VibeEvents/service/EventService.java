package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventsRepository eventsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public DataEventsReponse createEvent(RegisterEvent data) {
        Optional<CategoryEntity> category = categoryRepository.findById(data.categoryId());


        EventsEntity event = new EventsEntity(new DataEvents(
                data.name(),
                data.location(),
                data.date(),
                category.orElse(null)
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
        Optional<EventsEntity> event = eventsRepository.findById(id)


        return new DataEventsReponse(event.get().getName(), event.get().getLocation(), event.get().getDate(), event.get().getCategory().getName());
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
        Optional<EventsEntity> existingEvent = eventsRepository.findById(eventId);

        Optional<CategoryEntity> category = categoryRepository.findById(data.categoryId());


        existingEvent.get().setName(data.name());
        existingEvent.get().setLocation(data.location());
        existingEvent.get().setDate(data.date());
        existingEvent.get().setCategory(category.orElse(null));

        Optional<EventsEntity> updated = eventsRepository.save(existingEvent);
        return new DataEventsReponse(updated.get().getName(), updated.get().getLocation(), updated.get().getDate(), updated.get().getCategory().getName());
    }
}
