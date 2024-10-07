package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LifeBridgeRepository extends JpaRepository<LifeBridgeModel,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    LifeBridgeModel findByUsername(String username);
}
