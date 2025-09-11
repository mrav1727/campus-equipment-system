package edu.cit.veloso.miguelray.campusequipmentloan.service.penalty;

import java.time.LocalDate;

public interface PenaltyStrategy {
    double calculatePenalty(LocalDate dueDate, LocalDate returnDate);
}
