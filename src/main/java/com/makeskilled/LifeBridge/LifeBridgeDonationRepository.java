package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LifeBridgeDonationRepository extends JpaRepository<LifeBridgeDonationModel, Long> {
    // Custom queries can be added here if needed
}
