package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.exceptions.CategoryNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.repositorys.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;

    @Transactional
    public CategoryEntity registerCategory(DataCategory data) {
        var newCategory = new CategoryEntity(data);
        return repository.save(newCategory);
    }

    @Transactional
    public CategoryEntity atualizationCategory(Long id, DataCategory data) {
        Optional<CategoryEntity> category = repository.findById(id);
        if (category.isPresent()) {
            CategoryEntity attCategory = category.get();
            attCategory.setName(data.name());
            return repository.save(attCategory);
        } else {
            throw new CategoryNotFoundException("Categoria com id não encontrado");
        }
    }

    @Transactional
    public void deleteCategory(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new CategoryNotFoundException("Categoria não encontrada com o ID");
        }
    }

    @Transactional
    public List<CategoryEntity> ListAll() {
        return repository.findAll();
    }

    @Transactional
    public CategoryEntity listByid(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria com ID " + id + " não encontrada"));
    }
}
