package dev.Zerpyhis.VibeEvents.controller;


import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.records.DataPerson;
import dev.Zerpyhis.VibeEvents.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrar() {
        DataPerson dataPerson = new DataPerson("John", "123456789", "john.doe@example.com", "123-456-7890");
        PersonEntity expectedPerson = new PersonEntity(dataPerson);
        expectedPerson.setId(1L);  // Simulando que o ID foi gerado

        when(personService.registerPerson(dataPerson)).thenReturn(expectedPerson);

        ResponseEntity<PersonEntity> response = personController.cadastrar(dataPerson);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedPerson, response.getBody());
    }

    @Test
    void testAtualizar() {
        Long personId = 1L;
        DataPerson dataPerson = new DataPerson("John", "987654321", "john.doe@updated.com", "987-654-3210");
        PersonEntity updatedPerson = new PersonEntity(dataPerson);
        updatedPerson.setId(personId);

        when(personService.atualizationPerson(personId, dataPerson)).thenReturn(updatedPerson);

        ResponseEntity<PersonEntity> response = personController.atualizar(personId, dataPerson);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(updatedPerson, response.getBody());
    }

    @Test
    void testDeletar() {
        Long personId = 1L;

        doNothing().when(personService).deletePerson(personId);

        ResponseEntity<Void> response = personController.deletar(personId);

        assertEquals(204, response.getStatusCodeValue());
        verify(personService, times(1)).deletePerson(personId);
    }

    @Test
    void testListarTodas() {
        List<PersonEntity> persons = Collections.singletonList(new PersonEntity(new DataPerson("John", "123456789", "john.doe@example.com", "123-456-7890")));
        persons.get(0).setId(1L);
        when(personService.ListAll()).thenReturn(persons);

        ResponseEntity<List<PersonEntity>> response = personController.listarTodas();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(persons, response.getBody());
    }

    @Test
    void testBuscarPorId() {
        Long personId = 1L;
        PersonEntity person = new PersonEntity(new DataPerson("John", "123456789", "john.doe@example.com", "123-456-7890"));
        person.setId(personId);

        when(personService.listByid(personId)).thenReturn(person);

        ResponseEntity<PersonEntity> response = personController.buscarPorId(personId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(person, response.getBody());
    }
}