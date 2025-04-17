package dev.Zerpyhis.VibeEvents.controller;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryEntity> cadastrar(@RequestBody DataCategory data) {
        var categoria = categoryService.registerCategory(data);
        return ResponseEntity.ok(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryEntity> atualizar(@PathVariable("id") Long id, @RequestBody DataCategory data) {
        var categoriaAtualizada = categoryService.atualizationCategory(id, data);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> listarTodas() {
        var categorias = categoryService.ListAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> buscarPorId(@PathVariable("id") Long id) {
        var categoria = categoryService.listByid(id);
        return ResponseEntity.ok(categoria);
    }
}
