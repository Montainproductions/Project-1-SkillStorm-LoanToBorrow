package com.JoaquinDeLosada.spring_data.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.JoaquinDeLosada.spring_data.models.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Integer> {
    @Modifying
    @Transactional
    @Query("update Borrower u set u.firstName = :firstName where id = :id")
    public String updateFirstName(@Param("id") int id, @Param("firstName") String firstName);
    
    @Modifying
    @Transactional
    @Query("update Borrower u set u.lastName = :lastName where id = :id")
    public String updateLastName(@Param("id") int id, @Param("lastName") String lastName);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.employmentType = :employmentType where id = :id")
    public int updateEmploymentType(@Param("id") int id, @Param("employmentType") int employmentType);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.creditScore = :creditScore where id = :id")
    public int updateCreditScore(
        @Param("id") int id, @Param("creditScore") int creditScore);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.email = :email where id = :id")
    public int updateEmail(
        @Param("id") int id, @Param("email") String email);

    @Modifying
    @Transactional
    @Query("update Borrower m set m.mainPhone = :mainPhone where id = :id")
    public String updateMainPhone(
        @Param("id") int id, @Param("mainPhone") float mainPhone);
    
    @Modifying
    @Transactional
    @Query("update Borrower u set u.address = :address where id = :id")
    public String updateAddress(
        @Param("id") int id, @Param("address") String address);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.zipCode = :zipCode where id = :id")
    public int updateZipCode(@Param("id") int id, @Param("zipCode") int zipCode);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.state = :state where id = :id")
    public int updateState(
        @Param("id") int id, @Param("state") int state);

    @Modifying
    @Transactional
    @Query("update Borrower u set u.ssnLast4 = :ssnLast4 where id = :id")
    public int updateSsnLast4(@Param("id") int id, @Param("ssnLast4") int ssnLast4);

    @Modifying
    @Transactional
    @Query("update Borrower m set m.monthlyIncome = :monthlyIncome where id = :id")
    public String updateMonthlyIncome(
        @Param("loan-id") int id, @Param("monthlyIncome") float monthlyIncome);
    
    @Modifying
    @Transactional
    @Query("update Borrower u set u.yearlyIncome = :yearlyIncome where id = :id")
    public String updateYearlyIncome(
        @Param("id") int id, @Param("yearlyIncome") float yearlyIncome);

    @Query("SELECT b FROM Borrower b " + "WHERE b.email = :email " + "OR b.ssnLast4 = :ssnLast4")
    Optional<Borrower> findByEmailOrSsnLast4(
        @Param("email") String email, @Param("ssnLast4") int ssnLast4
    );
}
