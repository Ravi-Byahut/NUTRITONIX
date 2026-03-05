package com.nutritonix.wishlist_service;

import com.nutritonix.wishlist_service.client.NutritionFeignClient;
import com.nutritonix.wishlist_service.model.WishlistItem;
import com.nutritonix.wishlist_service.repository.WishlistRepository;
import com.nutritonix.wishlist_service.service.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class WishlistServiceTest {

    @InjectMocks
    private WishlistService wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @Mock
    private NutritionFeignClient nutritionFeignClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddToWishlist_Success() {
        Long userId = 1L;
        String tagId = "123";
        String query = "apple";

        when(wishlistRepository.findByUserIdAndTagId(String.valueOf(userId), tagId)).thenReturn(Optional.empty());

        Map<String, Object> foodItem = new HashMap<>();
        foodItem.put("tag_id", tagId);
        foodItem.put("food_name", "Apple");
        foodItem.put("serving_unit", "piece");
        foodItem.put("serving_qty", 1);
        foodItem.put("photo", Map.of("thumb", "image_url"));

        Map<String, Object> response = new HashMap<>();
        response.put("common", List.of(foodItem));

        when(nutritionFeignClient.searchFood(query, wishlistService.appId, wishlistService.appKey)).thenReturn(response);

        WishlistItem savedItem = new WishlistItem();
        savedItem.setUserId(String.valueOf(userId));
        savedItem.setTagId(tagId);
        savedItem.setFoodName("Apple");
        savedItem.setServingUnit("piece");
        savedItem.setServingQty(1);
        savedItem.setImageUrl("image_url");

        when(wishlistRepository.save(any(WishlistItem.class))).thenReturn(savedItem);

        WishlistItem result = wishlistService.addToWishlist(String.valueOf(userId), tagId, query);

        assertNotNull(result);
        assertEquals("Apple", result.getFoodName());
        verify(wishlistRepository, times(1)).save(any(WishlistItem.class));
    }

    @Test
    void testGetWishlist_ReturnsList() {
        Long userId = 1L;
        List<WishlistItem> mockList = List.of(new WishlistItem(), new WishlistItem());

        when(wishlistRepository.findByUserId(String.valueOf(userId))).thenReturn(mockList);

        List<WishlistItem> result = wishlistService.getWishlist(String.valueOf(userId));

        assertEquals(2, result.size());
    }

    @Test
    void testRemoveFromWishlist_DeletesItem() {
        Long userId = 1L;
        String tagId = "123";

        WishlistItem mockItem = new WishlistItem();
        mockItem.setId(1L);

        when(wishlistRepository.findByUserIdAndTagId(String.valueOf(userId), tagId)).thenReturn(Optional.of(mockItem));

        wishlistService.removeFromWishlist(String.valueOf(userId), tagId);

        verify(wishlistRepository, times(1)).deleteById(1L);
    }

}
