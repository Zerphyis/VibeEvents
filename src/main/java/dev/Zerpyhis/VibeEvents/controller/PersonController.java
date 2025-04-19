package dev.Zerpyhis.VibeEvents.controller;

import dev.Zerpyhis.VibeEvents.entitys.person.PersonEntity;
import dev.Zerpyhis.VibeEvents.records.DataPerson;
import dev.Zerpyhis.VibeEvents.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonEntity> cadastrar(@RequestBody DataPerson data) {
        var pessoa = personService.registerPerson(data);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonEntity> atualizar(@PathVariable("id") Long id, @RequestBody DataPerson data) {
        var pessoaAtualizada = personService.atualizationPerson(id, data);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PersonEntity>> listarTodas() {
        var pessoas = personService.ListAll();
        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonEntity> buscarPorId(@PathVariable("id") Long id) {
        var pessoa = personService.listByid(id);
        return ResponseEntity.ok(pessoa);
    }
}
