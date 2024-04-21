package com.example.UberReviewService.repositories;

import com.example.UberReviewService.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Optional<Driver> findByIdAndLicenseNumber(Long id, String licenseNumber);

    List<Driver> findAllByIdIn(List<Long> drivers);

    @Query(nativeQuery = true, value = "SELECT * FROM Driver WHERE id = :id AND license_number = :license") // RAW mysql query , error is thrown at runtime is query has issue
    Optional<Driver> rawFindByIdAndLicenseNumber(Long id, String license);

    @Query("From Driver as d where d.id = :id AND d.licenseNumber = :l") // Hibernate query, error is thrown at compile time
    Optional<Driver> hqlFindByIdAndLicense(Long id, String l);
}
