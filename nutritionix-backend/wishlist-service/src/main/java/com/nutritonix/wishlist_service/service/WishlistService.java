package com.nutritonix.wishlist_service.service;


import com.nutritonix.wishlist_service.client.NutritionFeignClient;
import com.nutritonix.wishlist_service.model.WishlistItem;
import com.nutritonix.wishlist_service.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private NutritionFeignClient nutritionFeignClient;

    public final String appId = "c7103db4";  // Replace with actual App ID
    public final String appKey = "bd081bbed92179ebc63433157f22457b";

    public WishlistItem addToWishlist(String userId, String tagId, String query) {
        Optional<WishlistItem> existingItem = wishlistRepository.findByUserIdAndTagId(userId, tagId);
        if (existingItem.isPresent()) {
            throw new RuntimeException("Item is already in the wishlist!");
        }

        Map<String, Object> response = nutritionFeignClient.searchFood(query, appId, appKey);
        System.out.println(response);

        List<Map<String, Object>> commonFoods = (List<Map<String, Object>>) response.get("common");

        if (commonFoods != null && !commonFoods.isEmpty()) {
            for (Map<String, Object> foodItem : commonFoods) {
                if (tagId.equals(String.valueOf(foodItem.get("tag_id")))) {
                    WishlistItem item = new WishlistItem();
                    item.setUserId(userId);
                    item.setTagId(tagId);
                    item.setFoodName((String) foodItem.get("food_name"));
                    item.setServingUnit((String) foodItem.get("serving_unit"));
                    item.setServingQty((Integer) foodItem.get("serving_qty"));

                    Map<String, Object> photo = (Map<String, Object>) foodItem.get("photo");
                    if (photo != null) {
                        item.setImageUrl((String) photo.get("thumb"));
                    }

                    return wishlistRepository.save(item);
                }
            }
        }

        throw new RuntimeException("Food item with tagId " + tagId + " not found in common list!");
    }




    public List<WishlistItem> getWishlist(String userId) {
        return wishlistRepository.findByUserId(userId);
    }

    public void removeFromWishlist(String userId, String tagId) {
        Optional<WishlistItem> item= wishlistRepository.findByUserIdAndTagId(userId,tagId);
        item.ifPresent(wishlistItem -> wishlistRepository.deleteById(wishlistItem.getId()));

    }
}
