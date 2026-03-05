package com.nutritonix.nutrition_service.controller;

import com.nutritonix.nutrition_service.client.NutritionFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private NutritionFeignClient nutritionixApiClient;

    private final String appId = "c7103db4";  // Replace with actual App ID
    private final String appKey = "bd081bbed92179ebc63433157f22457b"; // Replace with actual App Key

    // 1️⃣ Get Nutrients from Natural Language Text
    @PostMapping("/natural/nutrients")
    public Map<String, Object> getNutrientInfo(@RequestBody Map<String, Object> requestBody) {
        return nutritionixApiClient.getNutrientInfo(appId, appKey, requestBody).getBody();
    }


    @GetMapping("/search/instant")
    public Map<String, Object> instantSearch(@RequestParam String query) {
        return nutritionixApiClient.instantSearch(appId, appKey, query).getBody();
    }


    @GetMapping("/search/item")
    public Map<String, Object> searchItem(@RequestParam String nixItemId) {
        return nutritionixApiClient.searchItem(appId, appKey, nixItemId).getBody();
    }


    @PostMapping("/natural/exercise")
    public Map<String, Object> getExerciseInfo(@RequestBody Map<String, Object> requestBody) {
        return nutritionixApiClient.getExerciseInfo(appId, appKey, requestBody).getBody();
    }
}