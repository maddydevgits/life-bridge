package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeBridgeEquipmentInventoryRepository extends JpaRepository<LifeBridgeEquipmentInventoryModel, Long> {
    // Custom queries can be added here if needed
}
