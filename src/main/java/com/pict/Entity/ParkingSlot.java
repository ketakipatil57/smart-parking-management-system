package com.pict.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Slot Number is required")
    @Column(nullable = false, unique = true)
    private String slotNumber;

    @NotNull(message = "Slot type is required")
    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    @Enumerated(EnumType.STRING)
    private SlotStatus status = SlotStatus.AVAILABLE;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    // default constructor
    public ParkingSlot(){}

    // Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setSlotNumber(String slotNumber){
        this.slotNumber = slotNumber;
    }

    public void setSlotType(SlotType slotType){
        this.slotType = slotType;
    }

    public void  setStatus(SlotStatus status){
        this.status = status;
    }

    public void setLocation(String location){
        this.location = location;
    }


    // Getters
    public Long getId(){
        return id;
    }

    public String getSlotNumber(){
        return slotNumber;
    }
    public SlotType getSlotType(){
        return slotType;
    }

    public SlotStatus getStatus(){
        return status;
    }

    public String getLocation(){
        return location;
    }
}
