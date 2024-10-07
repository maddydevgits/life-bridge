package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodInventoryRepository extends JpaRepository<BloodInventoryModel, Long> {
    
    // No need to provide a body, Spring Data JPA will implement it automatically.
    List<BloodInventoryModel> findByBloodType(String bloodType);
}