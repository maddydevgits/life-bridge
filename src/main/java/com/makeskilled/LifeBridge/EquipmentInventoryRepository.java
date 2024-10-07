package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentInventoryRepository extends JpaRepository<EquipmentInventoryModel, Long> {
    // Custom queries can be added here if needed
}
