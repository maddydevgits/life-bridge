package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequestModel, Long> {
    List<BloodRequestModel> findByAcceptedFalse();  // Retrieve all requests that haven't been accepted

    List<BloodRequestModel> findByUsername(String username);
    List<BloodRequestModel> findByUsernameNotAndAcceptedFalse(String username);
}