package com.JoaquinDeLosada.spring_data.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JoaquinDeLosada.spring_data.models.Loan;
import com.JoaquinDeLosada.spring_data.models.LoanToBorrower;
import com.JoaquinDeLosada.spring_data.services.LoanService;
import com.JoaquinDeLosada.spring_data.services.LoanToBorrowerService;

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
@RequestMapping("/api/v1/loanToBorrowerController")
@CrossOrigin({"localhost:5500/*"})
public class LoanToBorrowerController {
    private final LoanToBorrowerService loanToBorrowerService;

    public LoanToBorrowerController(LoanToBorrowerService loanToBorrowerService) {
        this.loanToBorrowerService = loanToBorrowerService;
    }

    @GetMapping
    public ResponseEntity<List<LoanToBorrower>> GetAllLoanToBorrower(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "4") int size,
        @RequestParam(defaultValue = "id") String sortBy
    ) {

        List<LoanToBorrower> loansToBorrowers = loanToBorrowerService.GetAllLoansToBorrowers(page, size, sortBy);

        if(loansToBorrowers == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loansToBorrowers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanToBorrower> GetLoanToBorrowerById(@PathVariable int id) {

        LoanToBorrower loansToBorrower = loanToBorrowerService.GetLoanToBorrowerById(id);
        
        if(loansToBorrower == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loansToBorrower, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoanToBorrower> CreateLoanToBorrower(@Valid @RequestBody LoanToBorrower newLoanToBorrower) {
        LoanToBorrower loanToBorrower = loanToBorrowerService.SaveLoanToBorrower(newLoanToBorrower);
        
        if(loanToBorrower == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(loanToBorrower, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<LoanToBorrower> UpdateLoanToBorrower(@PathVariable int id, @Valid @RequestBody LoanToBorrower loanToBorrowerToUpdate) {
        LoanToBorrower loanToBorrower = loanToBorrowerService.SaveLoanToBorrower(loanToBorrowerToUpdate);
        if(loanToBorrower == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(loanToBorrower, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteLoanToBorrower(@PathVariable int id) {
        boolean isLoanToBorrowerDeleted = loanToBorrowerService.DeleteLoanToBorrower(id);
        if(isLoanToBorrowerDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       return ResponseEntity.notFound().build();
    }
}
