package com.JoaquinDeLosada.spring_data.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "LoanToBorrower")
public class LoanToBorrower {
    @Id                                                     
    @Column                                                 
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private int id;

    @NotBlank
    @Column(name = "loanID")
    private int loanID;

    @NotBlank
    @Column(name = "borrowerID")
    private int borrowerID;

    public LoanToBorrower() {

    }

    public LoanToBorrower(int id, int loanID, int borrowerID) {
        this.id = id;
        this.loanID = loanID;
        this.borrowerID = borrowerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoanID() {
        return loanID;
    }

    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    public int getBorrowerID() {
        return borrowerID;
    }

    public void setborrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }
}
