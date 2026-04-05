package com.pict.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.JoinColumnOrFormula;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Contact Number is required")
    @Column(nullable = false)
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Mobile number must be 10 digits"
    )
    private String contactNumber;

    @NotNull(message = "Parking slot is required")
    @ManyToOne
    @JoinColumn(name = "slot_id")
    private ParkingSlot parkingSlot;

    private LocalDate bookingDate;
    private LocalTime inTime;
    private LocalTime outTime;


    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus = BookingStatus.ACTIVE;

    @PrePersist
    public void prePersist() {
        this.bookingDate = LocalDate.now();
        this.inTime = LocalTime.now();
    }


    public Booking(){

    }

    // Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }

    public void setParkingSlot(ParkingSlot parkingSlot){
        this.parkingSlot = parkingSlot;
    }

    public void setBookingDate(LocalDate bookingDate){
        this.bookingDate = bookingDate;
    }

    public void setOutTime(LocalTime outTime){
        this.outTime = outTime;
    }

    public void setBookingStatus(BookingStatus bookingStatus){
        this.bookingStatus = bookingStatus;
    }


    // Getters

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getContactNumber(){
        return contactNumber;
    }

    public ParkingSlot getParkingSlot(){
        return parkingSlot;
    }

    public LocalTime getInTime(){
        return inTime;
    }

    public LocalTime getOutTime(){
        return outTime;
    }

    public BookingStatus getBookingStatus(){
        return bookingStatus;
    }
}
