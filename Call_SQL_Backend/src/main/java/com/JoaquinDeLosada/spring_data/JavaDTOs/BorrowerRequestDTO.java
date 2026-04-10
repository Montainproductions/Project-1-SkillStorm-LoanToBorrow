package com.JoaquinDeLosada.spring_data.JavaDTOs;

public class BorrowerRequestDTO {
    private String firstName;
    private String lastName;
    private int employmentType;
    private String email;
    private String phone;
    private int ssnLast4;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private double monthlyIncome;
    private double yearlyIncome;
    private int creditScore;

    public String GetFirstName(){
        return firstName;
    }

    public void SetFirstName(String firstName){
        this.firstName = firstName;
    }

    public String GetLastName(){
        return lastName;
    }

    public void SetLastName(String lastName){
        this.lastName = lastName;
    }

    public int GetEmploymentType(){
        return employmentType;
    }

    public void SetEmploymentType(int employmentType){
        this.employmentType = employmentType;
    }

    public int GetCreditScore(){
        return creditScore;
    }

    public void SetCreditScore(int creditScore){
        this.creditScore = creditScore;
    }

    public String GetEmail(){
        return email;
    }

    public void SetEmail(String email){
        this.email = email;
    }

    public String GetPhone(){
        return phone;
    }

    public void SetPhone(String phone){
        this.phone = phone;
    }

    public int GetssnLast4(){
        return ssnLast4;
    }

    public void SetssnLast4(int ssnLast4){
        this.ssnLast4 = ssnLast4;
    }

    public String GetAddress(){
        return address;
    }

    public void SetAddress(String address){
        this.address = address;
    }

        public String GetCity(){
        return city;
    }

    public void SetCity(String city){
        this.city = city;
    }

    public String GetState(){
        return state;
    }

    public void SetState(String state){
        this.state = state;
    }

    public int GetZipCode(){
        return zipCode;
    }

    public void SetZipCode(int zipCode){
        this.zipCode = zipCode;
    }

    public double GetMonthlyIncome(){
        return monthlyIncome;
    }

    public void SetMonthlyIncome(double monthlyIncome){
        this.monthlyIncome = monthlyIncome;
    }

    public double GetYearlyIncome(){
        return yearlyIncome;
    }

    public void SetYearlyIncome(double yearlyIncome){
        this.yearlyIncome = yearlyIncome;
    }
}
