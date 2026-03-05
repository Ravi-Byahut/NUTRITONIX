package com.nutritonix.wishlist_service.repository;


import com.nutritonix.wishlist_service.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByUserId(String userId);
    void deleteByUserIdAndTagId(String userId, String tagId);

    Optional<WishlistItem> findByUserIdAndTagId(String userId, String tagId);
}
