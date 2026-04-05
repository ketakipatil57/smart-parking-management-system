package com.pict.Repository;

import com.pict.Entity.ParkingSlot;
import com.pict.Entity.SlotStatus;
import com.pict.Entity.SlotType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingRepository extends JpaRepository<ParkingSlot, Long> {

    List<ParkingSlot> findByStatus(SlotStatus status);
    List<ParkingSlot> findBySlotType(SlotType slotType);

    Optional<ParkingSlot> findBySlotNumber(String slotNumber);
}