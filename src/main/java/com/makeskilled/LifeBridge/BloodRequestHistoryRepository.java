package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodRequestHistoryRepository extends JpaRepository<BloodRequestHistory, Long> {
    
    // Retrieve history of accepted requests by a specific user
    List<BloodRequestHistory> findByAcceptedBy(String acceptedBy);
}
