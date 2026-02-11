package com.gatekeeper.DatabaseSetup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User , String> {


   boolean existsByRole(String role);

   Optional<User> findByUserId(String userId);
}
