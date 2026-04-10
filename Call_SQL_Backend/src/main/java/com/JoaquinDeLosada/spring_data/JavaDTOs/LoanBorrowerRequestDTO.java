package com.JoaquinDeLosada.spring_data.JavaDTOs;

public class LoanBorrowerRequestDTO {
    private BorrowerRequestDTO borrower;
    private LoanRequestDTO loan;

    // getters + setters
    public BorrowerRequestDTO getBorrower() { return borrower; }
    public void setBorrower(BorrowerRequestDTO borrower) { this.borrower = borrower; }

    public LoanRequestDTO getLoan() { return loan; }
    public void setLoan(LoanRequestDTO loan) { this.loan = loan; }
}
