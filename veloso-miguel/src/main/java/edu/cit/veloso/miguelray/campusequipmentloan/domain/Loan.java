package edu.cit.veloso.miguelray.campusequipmentloan.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Equipment equipment;

    @ManyToOne
    private Student student;

    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private double penalty;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Equipment getEquipment() { return equipment; }
    public void setEquipment(Equipment equipment) { this.equipment = equipment; }

    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }

    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }
}
