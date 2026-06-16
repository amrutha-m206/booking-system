package com.booking_system.project.booking.controller;

import com.booking_system.project.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.booking_system.project.booking.entity.Booking;


@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking){
        return bookingService.createBooking(booking);
    }

    @DeleteMapping("/{id}")
    public String cancelBooking(@PathVariable Long id){
        bookingService.cancelBooking(id);
        return "Booking cancelled successfully";
    }
}
