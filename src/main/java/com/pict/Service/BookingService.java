package com.pict.Service;

import com.pict.Entity.Booking;
import com.pict.Entity.BookingStatus;
import com.pict.Entity.ParkingSlot;
import com.pict.Entity.SlotStatus;
import com.pict.Repository.BookingRepository;
import com.pict.Repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private final BookingRepository bookingRepository;
    private final ParkingRepository parkingRepository;

    public BookingService(BookingRepository bookingRepository, ParkingRepository parkingRepository){
        this.bookingRepository = bookingRepository;
        this.parkingRepository = parkingRepository;
    }



    public Booking bookSlot(String slotNumber, Booking booking) {


        ParkingSlot slot = parkingRepository.findBySlotNumber(slotNumber)
                .orElseThrow(() ->
                        new RuntimeException("Slot not found: " + slotNumber));

        if(slot.getStatus() != SlotStatus.AVAILABLE) {
            throw new RuntimeException("Slot is not Available!");
        }

        booking.setParkingSlot(slot);
        slot.setStatus(SlotStatus.RESERVED);
        parkingRepository.save(slot);

        return bookingRepository.save(booking);
    }

    public Booking checkout(Long bookingId){

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Booking not found"));

        if(booking.getBookingStatus() == BookingStatus.COMPLETED){
            throw new RuntimeException("Already checked out");
        }

        booking.setOutTime(LocalTime.now());

        booking.setBookingStatus(BookingStatus.COMPLETED);

        ParkingSlot slot = booking.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        parkingRepository.save(slot);

        return bookingRepository.save(booking);
    }

    public Booking cancelBooking(Long bookingId){

        Booking booking = bookingRepository.findById(bookingId).orElseThrow(()-> new RuntimeException("Slot not found"));

        if(booking.getBookingStatus() == BookingStatus.CANCELLED){
            throw new RuntimeException("Slot Already canceled ");
        }

        booking.setBookingStatus(BookingStatus.CANCELLED);

        ParkingSlot slot = booking.getParkingSlot();
        slot.setStatus(SlotStatus.AVAILABLE);
        parkingRepository.save(slot);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }


}
