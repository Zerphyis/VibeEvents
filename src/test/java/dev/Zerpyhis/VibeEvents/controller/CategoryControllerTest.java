package dev.Zerpyhis.VibeEvents.controller;

import dev.Zerpyhis.VibeEvents.entitys.category.CategoryEntity;
import dev.Zerpyhis.VibeEvents.records.DataCategory;
import dev.Zerpyhis.VibeEvents.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {
        @Mock
        private CategoryService categoryService;

        @InjectMocks
        private CategoryController categoryController;

        private CategoryEntity mockCategory;
        private DataCategory mockDataCategory;

        @BeforeEach
        public void setup() {
            MockitoAnnotations.openMocks(this);
            mockDataCategory = new DataCategory("Categoria Teste");
            mockCategory = new CategoryEntity(mockDataCategory);
        }

        @Test
        public void testCadastrar() {
            when(categoryService.registerCategory(mockDataCategory)).thenReturn(mockCategory);

            ResponseEntity<CategoryEntity> response = categoryController.cadastrar(mockDataCategory);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(mockCategory.getName(), response.getBody().getName());
        }

        @Test
        public void testAtualizar() {
            when(categoryService.atualizationCategory(1L, mockDataCategory)).thenReturn(mockCategory);

            ResponseEntity<CategoryEntity> response = categoryController.atualizar(1L, mockDataCategory);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(mockCategory.getName(), response.getBody().getName());
        }

        @Test
        public void testDeletar() {
            doNothing().when(categoryService).deleteCategory(1L);

            ResponseEntity<Void> response = categoryController.deletar(1L);

            assertEquals(204, response.getStatusCodeValue());
            assertNull(response.getBody());
        }

        @Test
        public void testListarTodas() {
            List<CategoryEntity> mockList = Arrays.asList(mockCategory);
            when(categoryService.ListAll()).thenReturn(mockList);

            ResponseEntity<List<CategoryEntity>> response = categoryController.listarTodas();

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(mockList.size(), response.getBody().size());
            assertEquals(mockCategory.getName(), response.getBody().get(0).getName());
        }

        @Test
        public void testBuscarPorId() {
            when(categoryService.listByid(1L)).thenReturn(mockCategory);

            ResponseEntity<CategoryEntity> response = categoryController.buscarPorId(1L);

            assertEquals(200, response.getStatusCodeValue());
            assertEquals(mockCategory.getName(), response.getBody().getName());
        }
    }