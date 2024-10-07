package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LifeBridgeBloodInventoryRepository extends JpaRepository<LifeBridgeBloodInventoryModel, Long> {
    
    // No need to provide a body, Spring Data JPA will implement it automatically.
    List<LifeBridgeBloodInventoryModel> findByBloodType(String bloodType);
}