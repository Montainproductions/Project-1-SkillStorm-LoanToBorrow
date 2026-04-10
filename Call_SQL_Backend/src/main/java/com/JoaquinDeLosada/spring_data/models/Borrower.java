package com.JoaquinDeLosada.spring_data.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Borrower")
public class Borrower {

    //@OneToMany(targetEntity = otherControllerName.class, mappedBy = "classname")
    @Id                                                     
    @Column                                                 
    @GeneratedValue(strategy = GenerationType.IDENTITY)     
    private int id;

    @NotBlank
    @Column(name = "firstName")
    private String firstName;

    @NotBlank
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "employmentType")
    private int employmentType;

    //@Min(value = 300)
    //@Max(value = 850)
    @Column(name = "creditScore")
    private int creditScore;

    @Column(name = "email")
    private String email;

    //@Min(value = 0000000000)
    @Size
    //@Max(value = 9999999999)
    @Column(name = "mainPhone")
    private String mainPhone;

    @Column(name = "borrowerAddress")
    private String address;

    //@Min(value = 1)
    //@Max(value = 10)
    @Column(name = "zipCode")
    private int zipCode;
    
    //@Min(value = 1)
    //@Max(value = 10)
    @Column(name = "usState")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "ssnFourDigits")
    private int ssnLast4;

    @Column(name = "incomeMonthly")
    private double monthlyIncome;

    @Column(name = "incomeYearly")
    private double yearlyIncome;
    
    @Column(name = "paymentsMissed")
    private int paymentsMissed;

    @Column(name = "riskValue")
    private int riskValue;

    public Borrower() {

    }

    public Borrower(int id, String borrowerFirstName, String lastName, int employmentType, 
        int creditScore, String email, String mainPhone, String address, int zipCode, String state, String city, 
        int ssnLast4, double monthlyIncome, double yearlyIncome, int paymentsMissed, int riskValue) {
        this.id = id;
        this.firstName = borrowerFirstName;
        this.lastName = lastName;
        this.employmentType = employmentType;
        this.creditScore = creditScore;
        this.email = email;
        this.mainPhone = mainPhone;
        this.address = address;
        this.zipCode = zipCode;
        this.state = state;
        this.city = city;
        this.ssnLast4 = ssnLast4;
        this.monthlyIncome = monthlyIncome;
        this.yearlyIncome = yearlyIncome;
        this.paymentsMissed = paymentsMissed;
        this.riskValue = riskValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String borrowerFirstName) {
        this.firstName = borrowerFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String borrowerLastName) {
        this.lastName = borrowerLastName;
    }

    public int getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(int employmentType) {
        this.employmentType = employmentType;
    }

    public int getCreditScore(){
        return creditScore;
    }

    public void setCreditScore(int creditScore){
        this.creditScore = creditScore;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return mainPhone;
    }

    public void setPhone(String mainPhone){
        this.mainPhone = mainPhone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public int getZipCode(){
        return zipCode;
    }

    public void setZipCode(int zipCode){
        this.zipCode = zipCode;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public int getssnLast4(){
        return ssnLast4;
    }

    public void setssnLast4(int ssnLast4){
        this.ssnLast4 = ssnLast4;
    }

    public double getMonthlyIncome(){
        return monthlyIncome;
    }

    public void setMonthlyIncome(double monthlyIncome){
        this.monthlyIncome = monthlyIncome;
    }

    public double getYearlyIncome(){
        return yearlyIncome;
    }

    public void setYearlyIncome(double yearlyIncome){
        this.yearlyIncome = yearlyIncome;
    }

    public int getPaymentsMissed(){
        return paymentsMissed;
    }

    public void setPaymentsMissed(int paymentsMissed){
        this.paymentsMissed = paymentsMissed;
    }

    public int getEiskValue(){
        return riskValue;
    }

    public void setRiskValue(int riskValue){
        this.riskValue = riskValue;
    }
}
