package edu.cit.veloso.miguelray.campusequipmentloan.dto;

import java.time.LocalDate;

public class CreateLoanRequest {
    private Long studentId;
    private Long equipmentId;

    private LocalDate dueDate;

    // Getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
