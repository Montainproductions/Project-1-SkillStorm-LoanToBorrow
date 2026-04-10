package com.JoaquinDeLosada.spring_data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JoaquinDeLosada.spring_data.models.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {
    @Modifying
    @Transactional
    @Query("update Loan m set m.loanName = :loanName where id = :id")
    public String UpdateLoanTitle(@Param("id") int id, @Param("loanName") String loanName);
    
    @Modifying
    @Transactional
    @Query("update Loan u set u.loanType = :loanType where id = :id")
    public String UpdateLoadType(@Param("id") int id, @Param("loanType") String loanType);

    @Modifying
    @Transactional
    @Query("update Loan u set u.lengthOfLoanMonths = :lengthOfLoanMonths where id = :id")
    public int UpdateLengthOfLoan(@Param("id") int id, @Param("loanType") int loanType);

    @Modifying
    @Transactional
    @Query("update Loan u set u.loanCurrentMonth = :loanCurrentMonth where id = :id")
    public int UpdateCurrentMonth(
        @Param("id") int id, @Param("loanCurrentMonth") int loanCurrentMonth);

    @Modifying
    @Transactional
    @Query("update Loan u set u.initialLoanAmount = :initialLoanAmount where id = :id")
    public int UpdateInitialLoanAmount(
        @Param("id") int id, @Param("initialLoanAmount") int initialLoanAmount);

    @Modifying
    @Transactional
    @Query("update Loan m set m.currentInterestRate = :currentInterestRate where id = :id")
    public String UpdateCurrentInterestRate(
        @Param("id") int id, @Param("currentInterestRate") float loanName);
    
    @Modifying
    @Transactional
    @Query("update Loan u set u.isAdjustableRate = :isAdjustableRate where id = :id")
    public String UpdateIsAdjustableRate(
        @Param("id") int id, @Param("isAdjustableRate") boolean isAdjustableRate);

    @Modifying
    @Transactional
    @Query("update Loan u set u.initialInterestRate = :initialInterestRate where id = :id")
    public int UpdateInitialInterestRate(@Param("id") int id, @Param("initialInterestRate") float initialInterestRate);

    @Modifying
    @Transactional
    @Query("update Loan u set u.adjustedInterestRate = :adjustedInterestRate where id = :id")
    public int UpdateAdjustedInterestRate(
        @Param("id") int id, @Param("adjustedInterestRate") float adjustedInterestRate);

    @Modifying
    @Transactional
    @Query("update Loan u set u.monthAtInterestRateChange = :monthAtInterestRateChange where id = :id")
    public int UpdateMonthAtInterestRateChange(@Param("id") int id, @Param("monthAtInterestRateChange") int monthAtInterestRateChange);
    
    @Modifying
    @Transactional
    @Query("update Loan u set u.downpaymentAmount = :downpaymentAmount where id = :id")
    public String UpdateDownpaymentAmount(
        @Param("id") int id, @Param("downpaymentAmount") float downpaymentAmount);

    @Modifying
    @Transactional
    @Query("update Loan u set u.amountOfMissedPayments = :amountOfMissedPayments where id = :id")
    public int UpdateAmountOfMissedPayments(
        @Param("id") int id, @Param("amountOfMissedPayments") float amountOfMissedPayments);

    @Modifying
    @Transactional
    @Query("update Loan u set u.paymentDate = :paymentDate where id = :id")
    public int UpdatePaymentDate(@Param("id") int id, @Param("paymentDate") String paymentDate);
}
