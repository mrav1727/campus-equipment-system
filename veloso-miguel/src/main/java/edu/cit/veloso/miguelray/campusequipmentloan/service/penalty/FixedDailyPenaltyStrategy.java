package edu.cit.veloso.miguelray.campusequipmentloan.service.penalty;

import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class FixedDailyPenaltyStrategy implements PenaltyStrategy {

    private static final double DAILY_PENALTY = 50.0;

    @Override
    public double calculatePenalty(LocalDate dueDate, LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        return daysLate > 0 ? daysLate * DAILY_PENALTY : 0;
    }
}
