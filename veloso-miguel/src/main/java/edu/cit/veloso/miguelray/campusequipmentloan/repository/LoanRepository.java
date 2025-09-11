package edu.cit.veloso.miguelray.campusequipmentloan.repository;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.Loan;
import edu.cit.veloso.miguelray.campusequipmentloan.domain.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    long countByStudentIdAndStatus(Long studentId, LoanStatus status);
}
