package com.nutritonix.wishlist_service.controller;


import com.nutritonix.wishlist_service.model.WishlistItem;
import com.nutritonix.wishlist_service.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add/{userId}/{tagId}")
    public ResponseEntity<WishlistItem> addToWishlist(
            @PathVariable String userId,
            @PathVariable String tagId,
            @RequestBody Map<String, String> requestBody) {

        String query = requestBody.get("query");
        WishlistItem item = wishlistService.addToWishlist(userId, tagId, query);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }


    @GetMapping("/{userId}")
    public List<WishlistItem> getWishlist(@PathVariable String userId) {
        return wishlistService.getWishlist(userId);
    }

    @DeleteMapping("/remove/{userId}/{tagId}")
    public void removeFromWishlist(@PathVariable String userId, @PathVariable String tagId) {
        wishlistService.removeFromWishlist(userId, tagId);
    }
}
