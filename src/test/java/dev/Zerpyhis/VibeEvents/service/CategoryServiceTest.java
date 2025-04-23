package dev.Zerpyhis.VibeEvents.service;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.exceptions.CategoryNotFoundException;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.repositorys.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testRegisterCategory() {
        DataCategory data = new DataCategory("Category 1");
        CategoryEntity category = new CategoryEntity(data);

        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(category);

        CategoryEntity savedCategory = categoryService.registerCategory(data);

        assertNotNull(savedCategory);
        assertEquals("Category 1", savedCategory.getName());
    }

    @Test
    public void testAtualizationCategory() {
        DataCategory data = new DataCategory("Updated Category");
        CategoryEntity existingCategory = new CategoryEntity(new DataCategory("Old Category"));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(any(CategoryEntity.class))).thenReturn(existingCategory);

        CategoryEntity updatedCategory = categoryService.atualizationCategory(1L, data);

        assertEquals("Updated Category", updatedCategory.getName());
    }

    @Test
    public void testAtualizationCategoryNotFound() {
        DataCategory data = new DataCategory("Updated Category");

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.atualizationCategory(1L, data);
        });
    }

    @Test
    public void testDeleteCategory() {
        when(categoryRepository.existsById(1L)).thenReturn(true);

        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteCategoryNotFound() {
        when(categoryRepository.existsById(1L)).thenReturn(false);

        assertThrows(CategoryNotFoundException.class, () -> {
            categoryService.deleteCategory(1L);
        });
    }

}