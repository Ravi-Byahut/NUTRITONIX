package com.nutritionix.user_profile_service.repository;

import com.nutritionix.user_profile_service.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserCredential, Integer> {
    public UserCredential findByIdAndPassword(Integer id, String password);
}
