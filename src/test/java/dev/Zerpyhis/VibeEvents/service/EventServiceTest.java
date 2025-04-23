package dev.Zerpyhis.VibeEvents.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.entitys.events.EventsEntity;
import dev.Zerpyhis.VibeEvents.exceptions.CategoryNotFoundException;
import dev.Zerpyhis.VibeEvents.exceptions.EventsNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.records.DataEvents;
import dev.Zerpyhis.VibeEvents.records.DataEventsReponse;
import dev.Zerpyhis.VibeEvents.records.RegisterEvent;
import dev.Zerpyhis.VibeEvents.repositorys.CategoryRepository;
import dev.Zerpyhis.VibeEvents.repositorys.EventsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class EventServiceTest {


    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void testCreateEvent() {
        RegisterEvent data = new RegisterEvent("Event 1", "Description", "Location", LocalDate.now(), LocalDate.now().plusDays(1), 100.0, 1L, 500);
        CategoryEntity category = new CategoryEntity(new DataCategory("Category 1"));
        EventsEntity event = new EventsEntity(new DataEvents("Event 1", "Description", "Location", LocalDate.now(), LocalDate.now().plusDays(1), 100.0, 500, category));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(eventsRepository.save(any(EventsEntity.class))).thenReturn(event);

        DataEventsReponse response = eventService.createEvent(data);

        assertNotNull(response);
        assertEquals("Event 1", response.name());
        assertEquals("Category 1", response.categoryName());
    }

    @Test
    public void testCreateEventCategoryNotFound() {
        RegisterEvent data = new RegisterEvent("Event 1", "Description", "Location", LocalDate.now(), LocalDate.now().plusDays(1), 100.0, 1L, 500);

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            eventService.createEvent(data);
        });
    }

    @Test
    public void testFindEventById() {
        EventsEntity event = new EventsEntity(new DataEvents("Event 1", "Description", "Location", LocalDate.now(), LocalDate.now().plusDays(1), 100.0, 500, new CategoryEntity(new DataCategory("Category 1"))));

        when(eventsRepository.findById(1L)).thenReturn(Optional.of(event));

        DataEventsReponse response = eventService.findEventById(1L);

        assertNotNull(response);
        assertEquals("Event 1", response.name());
    }

    @Test
    public void testFindEventByIdNotFound() {
        when(eventsRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EventsNotFoundException.class, () -> {
            eventService.findEventById(1L);
        });
    }
}
