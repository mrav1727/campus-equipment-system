package edu.cit.veloso.miguelray.campusequipmentloan.dto;

import java.time.LocalDate;

public class LoanReturnResponse {
    private Long loanId;
    private String message;
    private double penalty;
    private String status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Long studentId;
    private String studentName;
    private Long equipmentId;
    private String equipmentName;

    public LoanReturnResponse(Long loanId, String message, double penalty,
                              String status, LocalDate startDate, LocalDate dueDate,
                              LocalDate returnDate, Long studentId, String studentName,
                              Long equipmentId, String equipmentName) {
        this.loanId = loanId;
        this.message = message;
        this.penalty = penalty;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.studentId = studentId;
        this.studentName = studentName;
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
    }

    // Getters and setters
    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }

    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
}
