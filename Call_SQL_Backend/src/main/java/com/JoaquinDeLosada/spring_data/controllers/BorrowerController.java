package com.JoaquinDeLosada.spring_data.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JoaquinDeLosada.spring_data.JavaDTOs.BorrowerRequestDTO;
import com.JoaquinDeLosada.spring_data.models.Borrower;
import com.JoaquinDeLosada.spring_data.models.Loan;
import com.JoaquinDeLosada.spring_data.services.BorrowerService;

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


@RestController
@RequestMapping("/api/v1/BorrowerList")
@CrossOrigin({"localhost:5500/*"})
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    public ResponseEntity<List<Borrower>> GetAllBorrower(
        @RequestParam(defaultValue = "4") int size,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "id") String sortBy
    ) {
        return new ResponseEntity<>(borrowerService.GetAllBorrowers(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> GetBorrowerById(@PathVariable int id) {
        Borrower borrower = borrowerService.GetBorrowerById(id);
        
        if(borrower == null) {
            return ResponseEntity.notFound().build();
        }
        
        return new ResponseEntity<>(borrower, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Borrower> CreateBorrower(@Valid @RequestBody BorrowerRequestDTO newBorrower) {
        Borrower borrower = borrowerService.SaveBorrower(newBorrower);
        if(borrower == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(borrower, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteBorrower(@PathVariable int id) {
        borrowerService.DeleteBorrowerById(id);
        return ResponseEntity.noContent().build();
    }
    
}
