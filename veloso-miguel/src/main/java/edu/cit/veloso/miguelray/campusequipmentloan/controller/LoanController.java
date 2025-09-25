package edu.cit.veloso.miguelray.campusequipmentloan.controller;

import edu.cit.veloso.miguelray.campusequipmentloan.dto.CreateLoanRequest;
import edu.cit.veloso.miguelray.campusequipmentloan.dto.LoanReturnResponse;
import edu.cit.veloso.miguelray.campusequipmentloan.service.LoanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public LoanReturnResponse createLoan(@RequestBody CreateLoanRequest request) {
        return loanService.createLoan(request);
    }

    @PostMapping("/{id}/return")
    public LoanReturnResponse returnLoan(@PathVariable Long id) {
        return loanService.returnLoan(id);
    }

    @GetMapping("/{id}")
    public LoanReturnResponse getLoanById(@PathVariable Long id) {
        return loanService.getLoanById(id);
    }

    @PutMapping("/{id}")
    public LoanReturnResponse updateLoan(@PathVariable Long id, @RequestBody CreateLoanRequest request) {
        return loanService.updateLoan(id, request);
    }

    @GetMapping
    public String testLoanEndpoint() {
        return "Loan API is working!";
    }


}

