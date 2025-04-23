package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.exceptions.CategoryNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.repositorys.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CategoryServiceTest {
    private CategoryRepository repository;
    private CategoryService service;

    @BeforeEach
    void setUp() {
        repository = mock(CategoryRepository.class);
        service = new CategoryService();
        service.repository = repository;
    }

    @Test
    void registerCategory_DeveSalvarCategoria() {
        DataCategory data = new DataCategory("Música");
        CategoryEntity entity = new CategoryEntity(data);

        when(repository.save(any())).thenReturn(entity);

        CategoryEntity result = service.registerCategory(data);

        assertEquals("Música", result.getName());
        verify(repository).save(any());
    }

    @Test
    void atualizationCategory_DeveAtualizarCategoriaExistente() {
        Long id = 1L;
        CategoryEntity category = new CategoryEntity(new DataCategory("Antigo"));
        when(repository.findById(id)).thenReturn(Optional.of(category));
        when(repository.save(any())).thenReturn(category);

        DataCategory dataAtualizada = new DataCategory("Atualizado");
        CategoryEntity result = service.atualizationCategory(id, dataAtualizada);

        assertEquals("Atualizado", result.getName());
        verify(repository).save(category);
    }

    @Test
    void atualizationCategory_QuandoCategoriaNaoExiste_DeveLancarExcecao() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () ->
                service.atualizationCategory(1L, new DataCategory("Inexistente")));
    }

    @Test
    void deleteCategory_DeveDeletarCategoria() {
        when(repository.existsById(1L)).thenReturn(true);

        service.deleteCategory(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deleteCategory_QuandoCategoriaNaoExiste_DeveLancarExcecao() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> service.deleteCategory(1L));
    }

    @Test
    void ListAll_DeveRetornarTodasCategorias() {
        List<CategoryEntity> categorias = List.of(new CategoryEntity(new DataCategory("Música")));
        when(repository.findAll()).thenReturn(categorias);

        List<CategoryEntity> result = service.ListAll();

        assertEquals(1, result.size());
    }

    @Test
    void listByid_DeveRetornarCategoriaPorId() {
        CategoryEntity categoria = new CategoryEntity(new DataCategory("Filmes"));
        when(repository.findById(1L)).thenReturn(Optional.of(categoria));

        CategoryEntity result = service.listByid(1L);

        assertEquals("Filmes", result.getName());
    }

    @Test
    void listByid_QuandoNaoEncontrada_DeveLancarExcecao() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> service.listByid(1L));
    }

}