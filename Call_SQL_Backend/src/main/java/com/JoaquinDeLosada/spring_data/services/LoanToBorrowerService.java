package com.JoaquinDeLosada.spring_data.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.JoaquinDeLosada.spring_data.models.LoanToBorrower;
import com.JoaquinDeLosada.spring_data.repositories.LoanToBorrowerRepository;

@Service
public class LoanToBorrowerService {

    private final LoanToBorrowerRepository loanToBorrowerRepository;

    public LoanToBorrowerService(LoanToBorrowerRepository loanToBorrowerRepository) {
        this.loanToBorrowerRepository = loanToBorrowerRepository;
    }

    public List<LoanToBorrower> GetAllLoansToBorrowers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<LoanToBorrower> loanToBorrowerPage = loanToBorrowerRepository.findAll(pageable);
        List<LoanToBorrower> loanToBorrowerList = loanToBorrowerPage.getContent();

        return loanToBorrowerList;
    }

    public LoanToBorrower GetLoanToBorrowerById(int id) {

        if(id < 0) {
            return (LoanToBorrower)loanToBorrowerRepository.findAll(); //if no id given then return all.
        }
        
        Optional<LoanToBorrower> loanToBorrower = loanToBorrowerRepository.findById(id);
        
        if(loanToBorrower.isPresent()) {
            return loanToBorrower.get();
        }

        // no loan found
        return null;
    }


    @Transactional
    public LoanToBorrower SaveLoanToBorrower(LoanToBorrower loanToBorrower) {
        return loanToBorrowerRepository.save(loanToBorrower);
    }

    @Transactional
    public boolean DeleteLoanToBorrower(int id) {
        if(GetLoanToBorrowerById(id) == null) {
            return false;
        }

        loanToBorrowerRepository.deleteById(id);
        return true;
    }
}
