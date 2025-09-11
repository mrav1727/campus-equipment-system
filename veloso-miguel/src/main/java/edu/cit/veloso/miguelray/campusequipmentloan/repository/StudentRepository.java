package edu.cit.veloso.miguelray.campusequipmentloan.repository;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {}
