package edu.cit.veloso.miguelray.campusequipmentloan.dto;

public class CreateLoanRequest {
    private Long studentId;
    private Long equipmentId;

    // Getters and setters
    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }

    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }
}
