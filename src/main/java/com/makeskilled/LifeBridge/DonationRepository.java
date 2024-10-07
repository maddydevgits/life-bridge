package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<DonationModel, Long> {
    // Custom queries can be added here if needed
}
