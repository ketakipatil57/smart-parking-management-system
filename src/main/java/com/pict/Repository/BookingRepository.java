package com.pict.Repository;

import com.pict.Entity.Booking;
import com.pict.Entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Declaring methods inside the interfaces
    // Jpa will implement these methods
    List<Booking> findByParkingSlotId(Long slotId);

    List<Booking> findByBookingStatus(BookingStatus bookingStatus);

    Optional<Booking> findByParkingSlotIdAndBookingStatus(Long slotId, BookingStatus status);

}
