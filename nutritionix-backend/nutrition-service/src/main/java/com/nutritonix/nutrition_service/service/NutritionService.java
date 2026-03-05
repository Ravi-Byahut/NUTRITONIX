//package com.nutritonix.nutrition_service.service;
//
//
//import com.nutritonix.nutrition_service.model.FoodItem;
//import com.nutritonix.nutrition_service.repository.FoodItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class NutritionService {
//
//    @Autowired
//    private FoodItemRepository foodItemRepository;
//
//    public List<FoodItem> searchFood(String query) {
//        return foodItemRepository.findByNameContainingIgnoreCase(query);
//    }
//}
