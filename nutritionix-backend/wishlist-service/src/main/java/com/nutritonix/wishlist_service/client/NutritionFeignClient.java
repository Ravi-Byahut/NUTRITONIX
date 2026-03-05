package com.nutritonix.wishlist_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "NUTRITION-SERVICE", url = "https://trackapi.nutritionix.com")
public interface NutritionFeignClient {

     // Replace with actual App Key

    @GetMapping("/v2/search/instant")
    Map<String, Object> searchFood(
            @RequestParam("query") String query,
            @RequestHeader("x-app-id") String appId,
            @RequestHeader("x-app-key") String appKey
    );
}
