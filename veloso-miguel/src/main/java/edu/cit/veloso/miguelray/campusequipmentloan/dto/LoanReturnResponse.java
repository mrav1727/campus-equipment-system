package edu.cit.veloso.miguelray.campusequipmentloan.dto;

public class LoanReturnResponse {
    private Long loanId;
    private String message;
    private double penalty;

    public LoanReturnResponse(Long loanId, String message, double penalty) {
        this.loanId = loanId;
        this.message = message;
        this.penalty = penalty;
    }

    // Getters and setters
    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }
}
