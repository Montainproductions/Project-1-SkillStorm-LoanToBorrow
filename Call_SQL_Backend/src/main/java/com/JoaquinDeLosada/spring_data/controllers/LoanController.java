package com.JoaquinDeLosada.spring_data.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JoaquinDeLosada.spring_data.JavaDTOs.LoanBorrowerRequestDTO;
import com.JoaquinDeLosada.spring_data.models.Loan;
import com.JoaquinDeLosada.spring_data.services.LoanService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/loan")
@CrossOrigin({"localhost:5500/*"})
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity<List<Loan>> GetAllLoans(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "4") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ) {

        List<Loan> loans = loanService.GetAllLoans(page, size, sortBy);

        if(loans == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> GetLoanbyId(@PathVariable int id) {

        Loan loan = loanService.GetLoanById(id);
        
        if(loan == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Loan> CreateLoan(@Valid @RequestBody LoanBorrowerRequestDTO newLoan) {
        Loan loan = loanService.SaveLoan(newLoan, true);
        
        if(loan == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loan, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Loan> UpdateLoan(@PathVariable int id, @Valid @RequestBody LoanBorrowerRequestDTO loanToUpdate) {
        Loan loan = loanService.SaveLoan(loanToUpdate, false);
        if(loan == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(loan, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteLoan(@PathVariable int id) {
        boolean isLoanDeleted = loanService.DeleteLoan(id);
        if(isLoanDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       return ResponseEntity.notFound().build();
    }
}
