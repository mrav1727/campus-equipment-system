package edu.cit.veloso.miguelray.campusequipmentloan.service.impl;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.Equipment;
import edu.cit.veloso.miguelray.campusequipmentloan.domain.Loan;
import edu.cit.veloso.miguelray.campusequipmentloan.domain.LoanStatus;
import edu.cit.veloso.miguelray.campusequipmentloan.domain.Student;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.LoanReturnResponse;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.LoanRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.StudentRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.service.LoanService;
import edu.cit.veloso.miguelray.campusequipmentloan.service.penalty.PenaltyStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final EquipmentRepository equipmentRepository;
    private final StudentRepository studentRepository;
    private final PenaltyStrategy penaltyStrategy;

    public LoanServiceImpl(LoanRepository loanRepository, EquipmentRepository equipmentRepository,
                           StudentRepository studentRepository, PenaltyStrategy penaltyStrategy) {
        this.loanRepository = loanRepository;
        this.equipmentRepository = equipmentRepository;
        this.studentRepository = studentRepository;
        this.penaltyStrategy = penaltyStrategy;
    }

    @Override
    public LoanReturnResponse createLoan(CreateLoanRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Equipment equipment = equipmentRepository.findById(request.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found"));

        // Limit active loans per student
        long activeLoans = loanRepository.countByStudentIdAndStatus(student.getId(), LoanStatus.ACTIVE);
        if (activeLoans >= 2) {
            throw new RuntimeException("Student already has 2 active loans");
        }

        LocalDate startDate = LocalDate.now();
        LocalDate dueDate = startDate.plusDays(7);

        Loan loan = new Loan();
        loan.setStudent(student);
        loan.setEquipment(equipment);
        loan.setStartDate(startDate);
        loan.setDueDate(dueDate);
        loan.setStatus(LoanStatus.ACTIVE);

        // Set equipment unavailable
        equipment.setAvailability(false);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);

        return new LoanReturnResponse(
                loan.getId(),
                "Loan created successfully",
                0,
                loan.getStatus().name(),
                loan.getStartDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                student.getId(),
                student.getName(),
                equipment.getId(),
                equipment.getName()
        );
    }

    @Override
    public LoanReturnResponse updateLoan(Long loanId, CreateLoanRequest request) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        // Update student if provided
        if (request.getStudentId() != null) {
            Student student = studentRepository.findById(request.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            loan.setStudent(student);
        }

        // Update equipment if provided
        if (request.getEquipmentId() != null) {
            Equipment equipment = equipmentRepository.findById(request.getEquipmentId())
                    .orElseThrow(() -> new RuntimeException("Equipment not found"));

            // Make previous equipment available
            loan.getEquipment().setAvailability(true);
            equipmentRepository.save(loan.getEquipment());

            // Assign new equipment and set availability
            loan.setEquipment(equipment);
            equipment.setAvailability(false);
            equipmentRepository.save(equipment);
        }

        loanRepository.save(loan);

        return new LoanReturnResponse(
                loan.getId(),
                "Loan updated successfully",
                loan.getPenalty(),
                loan.getStatus().name(),
                loan.getStartDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStudent().getId(),
                loan.getStudent().getName(),
                loan.getEquipment().getId(),
                loan.getEquipment().getName()
        );
    }

    @Override
    public LoanReturnResponse returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());

        double penalty = 0;
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus(LoanStatus.OVERDUE);
            penalty = penaltyStrategy.calculatePenalty(loan.getDueDate(), loan.getReturnDate());
            loan.setPenalty(penalty);
        } else {
            loan.setStatus(LoanStatus.RETURNED);
        }

        // Make equipment available again
        Equipment equipment = loan.getEquipment();
        equipment.setAvailability(true);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);

        return new LoanReturnResponse(
                loan.getId(),
                "Loan returned successfully",
                penalty,
                loan.getStatus().name(),
                loan.getStartDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStudent().getId(),
                loan.getStudent().getName(),
                equipment.getId(),
                equipment.getName()
        );
    }

    @Override
    public LoanReturnResponse getLoanById(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        return new LoanReturnResponse(
                loan.getId(),
                "Loan fetched successfully",
                loan.getPenalty(),
                loan.getStatus().name(),
                loan.getStartDate(),
                loan.getDueDate(),
                loan.getReturnDate(),
                loan.getStudent().getId(),
                loan.getStudent().getName(),
                loan.getEquipment().getId(),
                loan.getEquipment().getName()
        );
    }
}
