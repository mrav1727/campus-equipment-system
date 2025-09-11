package edu.cit.veloso.miguelray.campusequipmentloan.repository;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    List<Equipment> findByAvailabilityTrue();
}
