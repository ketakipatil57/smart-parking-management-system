package com.pict.Service;

import com.pict.Entity.ParkingSlot;
import com.pict.Entity.SlotStatus;
import com.pict.Repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository){
        this.parkingRepository = parkingRepository;
    }

    public List<ParkingSlot> getAllSlots(){
        return parkingRepository.findAll();
    }

    public List<ParkingSlot> getAvailableSlots(){
        return parkingRepository.findByStatus(SlotStatus.AVAILABLE);
    }

    public ParkingSlot addParkingSlot(ParkingSlot parkingSlot){
        parkingSlot.setStatus(SlotStatus.AVAILABLE);
        return parkingRepository.save(parkingSlot);
    }

    public ParkingSlot findSlotById(Long id){
        return parkingRepository.findById(id).orElseThrow(()-> new RuntimeException("Slot not found with id :" + id));
    }

    public void updateSlotStatus(Long id, SlotStatus status){
        ParkingSlot slot = findSlotById(id);
        slot.setStatus(status);
        parkingRepository.save(slot);
    }

}
