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

    @GetMapping("/available")
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findByAvailabilityTrue();
    }
}
