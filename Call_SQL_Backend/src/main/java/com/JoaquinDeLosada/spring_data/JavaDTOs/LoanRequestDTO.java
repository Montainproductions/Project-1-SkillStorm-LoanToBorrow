package com.JoaquinDeLosada.spring_data.JavaDTOs;

public class LoanRequestDTO {
    private String loanName;
    private String loanType;
    private double loanPrincipal;
    private double initialInterestRate;
    private int loanLengthMonths;
    private int paymentDay;
    private boolean isAdjustableInterestRate;
    private double adjustedinterestRate;
    private int monthInterestRateChanges;

    public String getLoanName(){
        return loanName;
    }

    public void setLoanName(String loanName){
        this.loanName = loanName;
    }

    public String getLoanType(){
        return loanType;
    }

    public void setLoanType(String loanType){
        this.loanType = loanType;
    }

    public double getLoanPrincipal(){
        return loanPrincipal;
    }

    public void setLoanPrincipal(double loanPrincipal){
        this.loanPrincipal = loanPrincipal;
    }

    public double getInitialInterestRate(){
        return initialInterestRate;
    }

    public void setInitialInterestRate(double initialInterestRate){
        this.initialInterestRate = initialInterestRate;
    }

    public int getLoanLengthMonths(){
        return loanLengthMonths;
    }

    public void setLoanLengthMonths(int loanLengthMonths){
        this.loanLengthMonths = loanLengthMonths;
    }

    public int getPaymentDay(){
        return paymentDay;
    }

    public void setPaymentDay(int paymentDay){
        this.paymentDay = paymentDay;
    }

    public boolean getIsAdjustableInterestRate(){
        return isAdjustableInterestRate;
    }

    public void setIsAdjustableInterestRate(boolean isAdjustableInterestRate){
        this.isAdjustableInterestRate = isAdjustableInterestRate;
    }

    public double getAdjustedinterestRate(){
        return adjustedinterestRate;
    }

    public void setAdjustedinterestRate(double adjustedinterestRate){
        this.adjustedinterestRate = adjustedinterestRate;
    }

    public int getMonthInterestRateChanges(){
        return monthInterestRateChanges;
    }

    public void setMonthInterestRateChanges(int monthInterestRateChanges){
        this.monthInterestRateChanges = monthInterestRateChanges;
    }
}
