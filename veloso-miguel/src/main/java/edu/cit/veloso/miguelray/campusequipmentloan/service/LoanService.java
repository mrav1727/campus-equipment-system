package edu.cit.veloso.miguelray.campusequipmentloan.service;

import edu.cit.veloso.miguelray.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.LoanReturnResponse;

public interface LoanService {
    LoanReturnResponse createLoan(CreateLoanRequest request);
    LoanReturnResponse returnLoan(Long loanId);

    // Added method
    LoanReturnResponse getLoanById(Long loanId);

    LoanReturnResponse updateLoan(Long loanId, CreateLoanRequest request);
}
