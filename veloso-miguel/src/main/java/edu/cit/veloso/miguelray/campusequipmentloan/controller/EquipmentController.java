package edu.cit.veloso.miguelray.campusequipmentloan.controller;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.Equipment;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.EquipmentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    // GET all available equipment
    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailabilityTrue();
    }

    // POST: create new equipment
    @PostMapping
    public Equipment createEquipment(@RequestBody Equipment equipment) {
        equipment.setAvailability(true); // default to available
        return equipmentRepository.save(equipment);
    }

    // PUT: update existing equipment
    @PutMapping("/{id}")
    public Equipment updateEquipment(@PathVariable Long id, @RequestBody Equipment updatedEquipment) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        equipment.setName(updatedEquipment.getName());
        equipment.setType(updatedEquipment.getType());
        equipment.setSerialNumber(updatedEquipment.getSerialNumber());
        equipment.setAvailability(updatedEquipment.isAvailability());

        return equipmentRepository.save(equipment);
    }
}

