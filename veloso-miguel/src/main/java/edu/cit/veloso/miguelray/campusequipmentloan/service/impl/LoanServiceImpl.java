package edu.cit.veloso.miguelray.campusequipmentloan.service.impl;

import edu.cit.veloso.miguelray.campusequipmentloan.domain.*;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.LoanReturnResponse;
import edu.cit.veloso.miguelray.campusequipmentloan.service.penalty.PenaltyStrategy;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.EquipmentRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.LoanRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.repository.StudentRepository;
import edu.cit.veloso.miguelray.campusequipmentloan.service.LoanService;
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

        equipment.setAvailability(false);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);

        return new LoanReturnResponse(loan.getId(), "Loan created successfully", 0);
    }

    @Override
    public LoanReturnResponse returnLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        loan.setReturnDate(LocalDate.now());
        loan.setStatus(LoanStatus.RETURNED);

        double penalty = 0;
        if (loan.getReturnDate().isAfter(loan.getDueDate())) {
            loan.setStatus(LoanStatus.OVERDUE);
            penalty = penaltyStrategy.calculatePenalty(loan.getDueDate(), loan.getReturnDate());
            loan.setPenalty(penalty);
        }

        Equipment equipment = loan.getEquipment();
        equipment.setAvailability(true);
        equipmentRepository.save(equipment);

        loanRepository.save(loan);

        return new LoanReturnResponse(loan.getId(), "Loan returned successfully", penalty);
    }
}
