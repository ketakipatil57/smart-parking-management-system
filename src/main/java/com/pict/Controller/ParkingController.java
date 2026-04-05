package com.pict.Controller;

import com.pict.Entity.ParkingSlot;
import com.pict.Entity.SlotStatus;
import com.pict.Service.ParkingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Parking")
public class ParkingController {


    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService){
        this.parkingService = parkingService;
    }

    @GetMapping("/parkingslot")
    public ResponseEntity<List<ParkingSlot>> getAllSlots(){
        return ResponseEntity.ok(parkingService.getAllSlots());
    }

    @GetMapping("/parkingslot/available")
    public ResponseEntity<List<ParkingSlot>> getAvailableSlots(){
      return ResponseEntity.ok(parkingService.getAvailableSlots());
    }

    @GetMapping("/parkingslot/{id}")
    public ResponseEntity<ParkingSlot> getSlotById(@PathVariable Long id) {
        return ResponseEntity.ok(parkingService.findSlotById(id));
    }
    @PostMapping("/parkingslot")
    public ResponseEntity<ParkingSlot> addSlot(@Valid @RequestBody ParkingSlot parkingSlot) {
       return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.addParkingSlot(parkingSlot));
    }

    @PutMapping("/parkingslot/{id}/status")
    public ResponseEntity<Void> updateSlotStatus(@PathVariable Long id, @RequestParam SlotStatus status){
       parkingService.updateSlotStatus(id, status);
       return ResponseEntity.noContent().build();
    }
}

