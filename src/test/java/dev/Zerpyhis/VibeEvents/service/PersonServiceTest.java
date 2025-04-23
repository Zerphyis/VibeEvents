package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.exceptions.PersonNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataPerson;
import dev.Zerpyhis.VibeEvents.repositorys.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void testRegisterPerson() {
        DataPerson data = new DataPerson("John Doe", "123456789", "john.doe@example.com", "123-456-7890");
        PersonEntity person = new PersonEntity(data);

        when(personRepository.save(any(PersonEntity.class))).thenReturn(person);

        PersonEntity savedPerson = personService.registerPerson(data);

        assertNotNull(savedPerson);
        assertEquals("John Doe", savedPerson.getName());
    }

    @Test
    public void testAtualizationPerson() {
        DataPerson data = new DataPerson("Jane Doe", "987654321", "jane.doe@example.com", "098-765-4321");
        PersonEntity existingPerson = new PersonEntity(new DataPerson("John Doe", "123456789", "john.doe@example.com", "123-456-7890"));

        when(personRepository.findById(1L)).thenReturn(Optional.of(existingPerson));
        when(personRepository.save(any(PersonEntity.class))).thenReturn(existingPerson);

        PersonEntity updatedPerson = personService.atualizationPerson(1L, data);

        assertEquals("Jane Doe", updatedPerson.getName());
    }

    @Test
    public void testAtualizationPersonNotFound() {
        DataPerson data = new DataPerson("Jane Doe", "987654321", "jane.doe@example.com", "098-765-4321");

        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PersonNotFoundException.class, () -> {
            personService.atualizationPerson(1L, data);
        });
    }

    @Test
    public void testDeletePerson() {
        when(personRepository.existsById(1L)).thenReturn(true);

        personService.deletePerson(1L);

        verify(personRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeletePersonNotFound() {
        when(personRepository.existsById(1L)).thenReturn(false);

        assertThrows(PersonNotFoundException.class, () -> {
            personService.deletePerson(1L);
        });
    }

}