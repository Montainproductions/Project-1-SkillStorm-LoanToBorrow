package com.JoaquinDeLosada.spring_data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class Loan {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "loanName")
    private String loanName;

    @Column(name = "loanType")
    private String loanType;

    @Column(name = "lenghtOfLoanMonths")
    private int lengthOfLoanMonths;

    @Column(name = "currentMonth")
    private int loanCurrentMonth;

    @Column(name = "initialLoanAmount")
    private double initialLoanAmount;
    
    @Column(name = "currentInterestRate")
    private double currentInterestRate;

    @Column(name = "isAdjustableRate")
    private boolean isAdjustableRate;

    @Column(name = "initialInterestRate")
    private double initialInterestRate;

    @Column(name = "adjustedInterestRate")
    private double adjustedInterestRate;

    @Column(name = "monthAtInterestRateChange")
    private int monthAtInterestRateChange;

    @Column(name = "loanMonthlyPayment")
    private double monthlyPayment;

    @Column(name = "amountOfMissedPayments")
    private int amountOfMissedPayments;

    @Column(name = "paymentDate")
    private int paymentDate;

    public Loan() {
    }

    public Loan(int id, String loanName, String loanType, int lengthOfLoanMonths, int loanCurrentMonth,
        double initialLoanAmount, boolean isAdjustableRate, double initialInterestRate,
        double adjustedInterestRate, int monthAtInterestRateChange, float monthlyPayment, int amountOfMissedPayments, int paymentDate) {
        this.id = id;
        this.loanName = loanName;
        this.loanType = loanType;
        this.lengthOfLoanMonths = lengthOfLoanMonths;
        this.loanCurrentMonth = loanCurrentMonth;
        this.initialLoanAmount = initialLoanAmount;
        this.currentInterestRate = initialInterestRate;
        this.isAdjustableRate = isAdjustableRate;
        this.initialInterestRate = initialInterestRate;
        this.adjustedInterestRate = adjustedInterestRate;
        this.monthAtInterestRateChange = monthAtInterestRateChange;
        this.monthlyPayment = monthlyPayment;
        this.amountOfMissedPayments = amountOfMissedPayments;
        this.paymentDate = paymentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoanName() {
        return loanName;
    }

    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getLengthOfLoanMonths() {
        return lengthOfLoanMonths;
    }

    public void setLengthOfLoanMonth(int lengthOfLoanMonths) {
        this.lengthOfLoanMonths = lengthOfLoanMonths;
    }

    public int getLoanCurrentMonth() {
        return loanCurrentMonth;
    }

    public void setLoanCurrentMonth(int loanCurrentMonth) {
        this.loanCurrentMonth = loanCurrentMonth;
    }

    public double getInitialLoanAmount() {
        return initialLoanAmount;
    }

    public void setInitialLoanAmount(double initialLoanAmount) {
        this.initialLoanAmount = initialLoanAmount;
    }

    public double getCurrentInterestRate() {
        return currentInterestRate;
    }

    public void setcurrentInterestRate(double currentInterestRate) {
        this.currentInterestRate = currentInterestRate;
    }

    public boolean getIsAdjustableRate() {
        return isAdjustableRate;
    }

    public void setIsAdjustableRate(boolean isAdjustableRate) {
        this.isAdjustableRate = isAdjustableRate;
    }

    public double getInitialInterestRate() {
        return initialInterestRate;
    }

    public void setInitialInterestRate(double initialInterestRate) {
        this.initialInterestRate = initialInterestRate;
    }

    public double getAdjustedInterestRate() {
        return adjustedInterestRate;
    }

    public void setAdjustedInterestRate(double adjustedInterestRate) {
        this.adjustedInterestRate = adjustedInterestRate;
    }
    
    public int getMonthAtInterestRateChange() {
        return monthAtInterestRateChange;
    }

    public void setMonthAtInterestRateChange(int monthAtInterestRateChange) {
        this.monthAtInterestRateChange = monthAtInterestRateChange;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getamountOfMissedPayments() {
        return amountOfMissedPayments;
    }

    public void setAmountOfMissedPayments(int amountOfMissedPayments) {
        this.amountOfMissedPayments = amountOfMissedPayments;
    }

    public int getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(int paymentDate) {
        this.paymentDate = paymentDate;
    }
}
