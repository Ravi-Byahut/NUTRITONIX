package com.nutritonix.nutrition_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@FeignClient(name = "nutritionix-api", url = "https://trackapi.nutritionix.com")
public interface NutritionFeignClient {

    @PostMapping("/v2/natural/nutrients")
    ResponseEntity<Map<String, Object>> getNutrientInfo(
            @RequestHeader("x-app-id") String appId,
            @RequestHeader("x-app-key") String appKey,
            @RequestBody Map<String, Object> requestBody
    );

    @GetMapping("/v2/search/instant")
    ResponseEntity<Map<String, Object>> instantSearch(
            @RequestHeader("x-app-id") String appId,
            @RequestHeader("x-app-key") String appKey,
            @RequestParam("query") String query
    );

    @GetMapping("/v2/search/item")
    ResponseEntity<Map<String, Object>> searchItem(
            @RequestHeader("x-app-id") String appId,
            @RequestHeader("x-app-key") String appKey,
            @RequestParam("nix_item_id") String nixItemId
    );

    @PostMapping("/v2/natural/exercise")
    ResponseEntity<Map<String, Object>> getExerciseInfo(
            @RequestHeader("x-app-id") String appId,
            @RequestHeader("x-app-key") String appKey,
            @RequestBody Map<String, Object> requestBody
    );
}