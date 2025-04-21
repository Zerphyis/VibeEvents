package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.exceptions.PersonNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataPerson;
import dev.Zerpyhis.VibeEvents.repositorys.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    @Transactional
    public PersonEntity registerPerson(DataPerson data) {
        var newPerson = new PersonEntity(data);
        return repository.save(newPerson);
    }

    @Transactional
    public PersonEntity atualizationPerson(Long id, DataPerson data) {
        Optional<PersonEntity> person = repository.findById(id);
        if (person.isPresent()) {
            PersonEntity attPerson = person.get();
            attPerson.setName(data.name());
            attPerson.setDocument(data.document());
            attPerson.setEmail(data.email());
            attPerson.setPhone(data.phone());

            return repository.save(attPerson);
        } else {
            throw new PersonNotFoundException("Pessoa com id não encontrado");
        }
    }

    @Transactional
    public void deletePerson(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new PersonNotFoundException("Pessoa não encontrada com o ID");
        }
    }

    @Transactional

    public List<PersonEntity> ListAll() {
        return repository.findAll();
    }

    @Transactional
    public PersonEntity listByid(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException("Pessoa com ID " + id + " não encontrada"));
    }
}
