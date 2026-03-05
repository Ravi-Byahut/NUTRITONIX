package com.nutritonix.nutrition_service;

import com.nutritonix.nutrition_service.client.NutritionFeignClient;
import com.nutritonix.nutrition_service.controller.NutritionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NutritionControllerTest {

    @InjectMocks
    private NutritionController nutritionController;

    @Mock
    private NutritionFeignClient nutritionFeignClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNutrientInfo() {
        // Arrange
        Map<String, Object> request = new HashMap<>();
        request.put("query", "1 apple");

        Map<String, Object> response = new HashMap<>();
        response.put("food_name", "apple");

        when(nutritionFeignClient.getNutrientInfo(anyString(), anyString(), eq(request)))
                .thenReturn(ResponseEntity.ok(response));

        Map<String, Object> result = nutritionController.getNutrientInfo(request);

        assertEquals("apple", result.get("food_name"));
    }

    @Test
    void testInstantSearch() {
        String query = "banana";
        Map<String, Object> response = new HashMap<>();
        response.put("common", "banana");

        when(nutritionFeignClient.instantSearch(anyString(), anyString(), eq(query)))
                .thenReturn(ResponseEntity.ok(response));

        Map<String, Object> result = nutritionController.instantSearch(query);

        assertEquals("banana", result.get("common"));
    }

    @Test
    void testSearchItem() {
        String nixItemId = "123abc";
        Map<String, Object> response = new HashMap<>();
        response.put("item_name", "grapes");

        when(nutritionFeignClient.searchItem(anyString(), anyString(), eq(nixItemId)))
                .thenReturn(ResponseEntity.ok(response));

        Map<String, Object> result = nutritionController.searchItem(nixItemId);

        assertEquals("grapes", result.get("item_name"));
    }

    @Test
    void testGetExerciseInfo() {
        Map<String, Object> request = new HashMap<>();
        request.put("query", "ran 3 miles");

        Map<String, Object> response = new HashMap<>();
        response.put("exercise", "running");

        when(nutritionFeignClient.getExerciseInfo(anyString(), anyString(), eq(request)))
                .thenReturn(ResponseEntity.ok(response));

        Map<String, Object> result = nutritionController.getExerciseInfo(request);

        assertEquals("running", result.get("exercise"));
    }
}
