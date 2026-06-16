package com.booking_system.project.booking.service;

import com.booking_system.project.booking.entity.Booking;
import com.booking_system.project.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Booking booking){
        List<Booking> existing=bookingRepository.findByResourceId((booking.getResourceId()));
        for(Booking b: existing){
            if(isOverlapping(booking,b)){
                throw new RuntimeException(("Resource already booked for this time slot"));

            }
        }
        booking.setStatus("BOOKED");
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long id){
        Booking booking= bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);
    }

    private boolean isOverlapping(Booking a,Booking b){
        return a.getStartTime().isBefore(b.getEndTime()) && a.getEndTime().isAfter(b.getStartTime());
    }

}
