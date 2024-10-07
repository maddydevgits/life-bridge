package com.makeskilled.LifeBridge;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface LifeBridgeRepository extends JpaRepository<LifeBridgeModel,Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<LifeBridgeModel> findByUsername(String username);
}
