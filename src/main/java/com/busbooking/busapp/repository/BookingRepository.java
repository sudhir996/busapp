package com.busbooking.busapp.repository;

import com.busbooking.busapp.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    // Optionally, you can add custom query methods based on specific criteria
    // For example:
    // List<Booking> findByCustomerId(Long customerId);
    // List<Booking> findBySourceAndDestination(String source, String destination);
}
