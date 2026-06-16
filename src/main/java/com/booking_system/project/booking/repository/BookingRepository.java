package com.booking_system.project.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.booking_system.project.booking.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByResourceId(Long resourceId);
}
