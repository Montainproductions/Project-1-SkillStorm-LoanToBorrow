package com.JoaquinDeLosada.spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JoaquinDeLosada.spring_data.JavaDTOs.BorrowerRequestDTO;
import com.JoaquinDeLosada.spring_data.models.Borrower;
import com.JoaquinDeLosada.spring_data.repositories.BorrowerRepository;

@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    BorrowerService(BorrowerRepository repository) {
        this.borrowerRepository = repository;
    }

    public List<Borrower> GetAllBorrowers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Borrower> borrowerPage = borrowerRepository.findAll(pageable);
        List<Borrower> borrowerList = borrowerPage.getContent();
        return borrowerList;
    }

    public Borrower GetBorrowerById(int id) {

        if(id <= 0){
            return null;
        }

        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if(borrower.isPresent()) {
            return borrower.get();
        }
        return null;
    }
    

    @Transactional
    public Borrower SaveBorrower(BorrowerRequestDTO borrowerRequest) {
        int idOfBorrower = FindOrCreateBorrower(borrowerRequest);

        return GetBorrowerById(idOfBorrower);
    }

    public boolean DeleteBorrowerById(int id){
        if(GetBorrowerById(id) == null) {
            return false;
        }

        borrowerRepository.deleteById(id);
        return true;
    }

    public String StateName(int id){
        String nameOfState = "";

        //repository.GetState

        return nameOfState;
    }

    public int RiskyBorrower(int id){
        int riskValue = 0;
        Borrower currentBorrower = GetBorrowerById(id);

        if(currentBorrower.getCreditScore() <= 500 && currentBorrower.getPaymentsMissed() > 0){
            riskValue = 2;
        } else if((currentBorrower.getCreditScore() <= 450 && currentBorrower.getPaymentsMissed() == 0) || 
            (currentBorrower.getCreditScore() <= 800 && currentBorrower.getPaymentsMissed() > 0)){
            riskValue = 1;
        }

        return riskValue;
    }

    public int RiskyBorrower(int paymentMissed, int creditScore){
        int riskValue = 0;

        if(paymentMissed > 0 && creditScore <= 500){
            riskValue = 2;
        } else if((paymentMissed == 0 && creditScore <= 450) || (paymentMissed > 0 && creditScore <= 800)){
            riskValue = 1;
        }

        return riskValue;
    }

    public int FindOrCreateBorrower(BorrowerRequestDTO borrowerRequest) {
        Optional<Borrower> borrowerExists = borrowerRepository.findByEmailOrSsnLast4(borrowerRequest.GetEmail(), borrowerRequest.GetssnLast4());

        if (borrowerExists.isPresent()) {
            // Borrower found — return their existing ID
            return borrowerExists.get().getId();
        }

        // Step 2 — no match found, create a new borrower
        Borrower newBorrower = new Borrower();
        newBorrower.setFirstName(borrowerRequest.GetFirstName());
        newBorrower.setLastName(borrowerRequest.GetLastName());
        newBorrower.setEmail(borrowerRequest.GetEmail());
        newBorrower.setPhone(borrowerRequest.GetPhone());
        newBorrower.setssnLast4(borrowerRequest.GetssnLast4());
        newBorrower.setAddress(borrowerRequest.GetAddress());
        newBorrower.setCity(borrowerRequest.GetCity());
        newBorrower.setState(borrowerRequest.GetState());
        newBorrower.setZipCode(borrowerRequest.GetZipCode());
        newBorrower.setCreditScore(borrowerRequest.GetCreditScore());
        newBorrower.setMonthlyIncome(borrowerRequest.GetMonthlyIncome());
        newBorrower.setYearlyIncome(borrowerRequest.GetYearlyIncome());
        newBorrower.setPaymentsMissed(0);
        newBorrower.setRiskValue(RiskyBorrower(newBorrower.getPaymentsMissed(), newBorrower.getCreditScore()));

        Borrower newlySavedBorrower = borrowerRepository.save(newBorrower);

        return newlySavedBorrower.getId();
    }
}
