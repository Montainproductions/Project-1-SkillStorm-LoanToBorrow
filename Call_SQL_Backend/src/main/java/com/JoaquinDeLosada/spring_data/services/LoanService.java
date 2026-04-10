package com.JoaquinDeLosada.spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JoaquinDeLosada.spring_data.JavaDTOs.LoanBorrowerRequestDTO;
import com.JoaquinDeLosada.spring_data.JavaDTOs.LoanRequestDTO;
import com.JoaquinDeLosada.spring_data.models.Loan;
import com.JoaquinDeLosada.spring_data.models.LoanToBorrower;
import com.JoaquinDeLosada.spring_data.repositories.LoanRepository;
import com.JoaquinDeLosada.spring_data.repositories.LoanToBorrowerRepository;

@Service
public class LoanService {
    private LoanRepository loanRepository;
    private BorrowerService borrowerService;
    private LoanToBorrowerRepository loanToBorrowerRepository;


    public LoanService(LoanRepository loanRepository, BorrowerService borrowerService, LoanToBorrowerRepository loanToBorrowerRepository) {
        this.loanRepository = loanRepository;
        this.borrowerService = borrowerService;
        this.loanToBorrowerRepository = loanToBorrowerRepository;
    }

    public List<Loan> GetAllLoans(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Loan> loanPage = loanRepository.findAll(pageable);
        List<Loan> loanList = loanPage.getContent();

        return loanList;
    }

    public Loan GetLoanById(int id) {

        if(id < 0) {
            return (Loan)loanRepository.findAll(); //if no id given then return all.
        }
        
        Optional<Loan> loan = loanRepository.findById(id);
        
        if(loan.isPresent()) {
            return loan.get();
        }

        // no loan found
        return null;
    }


    @Transactional
    public Loan SaveLoan(LoanBorrowerRequestDTO loanborrowerRequest, boolean isNewLoan) {
        Loan newLoanAsObject = TurnIntoLoanObjectDT(loanborrowerRequest.getLoan(), isNewLoan);
        
        //If there isnt suficient data then 
        if(newLoanAsObject == null){
            return null;
        }

        Loan savedLoan = loanRepository.save(newLoanAsObject);

        /* Create connection entry for the interlink table */
        //For creating the link between borrower and loan
        int borrowerId = borrowerService.FindOrCreateBorrower(loanborrowerRequest.getBorrower());

        LoanToBorrower createBorrowerLoan = new LoanToBorrower();
        createBorrowerLoan.setborrowerID(borrowerId);
        createBorrowerLoan.setLoanID(savedLoan.getId());
        loanToBorrowerRepository.save(createBorrowerLoan);
        
        return savedLoan;
    }

    @Transactional
    public boolean DeleteLoan(int id) {
        if(GetLoanById(id) == null) {
            return false;
        }

        loanRepository.deleteById(id);
        return true;
    }

    /* Actual Buisness logic */
    public Loan TurnIntoLoanObjectDT(LoanRequestDTO loanDTO, boolean isNewLoan){
        Loan newLoan = new Loan();

        newLoan.setLoanName(loanDTO.getLoanName());
        newLoan.setLoanType(loanDTO.getLoanType());
        newLoan.setInitialLoanAmount(loanDTO.getLoanPrincipal());
        newLoan.setInitialInterestRate(loanDTO.getInitialInterestRate());
        newLoan.setLengthOfLoanMonth(loanDTO.getLoanLengthMonths());
        newLoan.setPaymentDate(loanDTO.getPaymentDay());
        newLoan.setIsAdjustableRate(loanDTO.getIsAdjustableInterestRate());
        
        if(loanDTO.getIsAdjustableInterestRate()){
            if((loanDTO.getAdjustedinterestRate() == loanDTO.getInitialInterestRate())|| (loanDTO.getMonthInterestRateChanges() < 1)){
                return null;
            }
            newLoan.setAdjustedInterestRate(loanDTO.getAdjustedinterestRate());
            newLoan.setMonthAtInterestRateChange(loanDTO.getMonthInterestRateChanges());
        }

        int currentMonth;

        if(isNewLoan){
            newLoan.setLoanCurrentMonth(0);
            
            currentMonth = 0;
        }else{
            currentMonth = newLoan.getLoanCurrentMonth();
        }

        double monthlyPayment = AmountMonthlyPayment(newLoan, currentMonth, loanDTO.getLoanLengthMonths(), isNewLoan);

        if(monthlyPayment < 10){
            return null;
        }
        newLoan.setMonthlyPayment(monthlyPayment);

        newLoan.setAmountOfMissedPayments(0);
        newLoan.setPaymentDate(loanDTO.getPaymentDay());

        return newLoan;
    }

    public double AmountMonthlyPayment(Loan loanToCalculate, int currentMonth, int paymentsLeft, boolean isNewLoan){
        double payment = 
        RemainingLoanAmount(loanToCalculate.getInitialLoanAmount(), paymentsLeft, loanToCalculate.getMonthlyPayment(), isNewLoan)
        *((loanToCalculate.getCurrentInterestRate() * InterestpaymentCalculation(loanToCalculate.getCurrentInterestRate(), paymentsLeft))) /
        (InterestpaymentCalculation(loanToCalculate.getCurrentInterestRate(), paymentsLeft)-1);
        
        return payment;
    }

    public double InterestpaymentCalculation(double interestRate, int paymentsLeft){
        return Math.pow(1+interestRate, paymentsLeft);
    }

    public double RemainingLoanAmount(double loanRemaining, int monthsLeft, double monthlyPayment, boolean isNewLoan){
        if(!isNewLoan && monthlyPayment != 0){
            loanRemaining = loanRemaining - monthsLeft * monthlyPayment;
        }

        return loanRemaining;
    }
}
