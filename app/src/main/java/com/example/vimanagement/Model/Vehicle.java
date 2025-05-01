package com.example.vimanagement.Model;

public class Vehicle {
    private String ownerName;
    private String mobileNumber;
    private String countryCode;
    private String vehicleNumber;
    private String insuranceStart;
    private String insuranceExpiry;
    private String licenseExpiry;
    private String paymentAmount;
    private String pendingAmount;

    // Constructor

    public Vehicle(String ownerName, String mobileNumber, String countryCode, String vehicleNumber, String insuranceStart, String insuranceExpiry, String licenseExpiry, String paymentAmount, String pendingAmount) {
        this.ownerName = ownerName;
        this.mobileNumber = mobileNumber;
        this.countryCode = countryCode;
        this.vehicleNumber = vehicleNumber;
        this.insuranceStart = insuranceStart;
        this.insuranceExpiry = insuranceExpiry;
        this.licenseExpiry = licenseExpiry;
        this.paymentAmount = paymentAmount;
        this.pendingAmount = pendingAmount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getInsuranceStart() {
        return insuranceStart;
    }

    public void setInsuranceStart(String insuranceStart) {
        this.insuranceStart = insuranceStart;
    }

    public String getInsuranceExpiry() {
        return insuranceExpiry;
    }

    public void setInsuranceExpiry(String insuranceExpiry) {
        this.insuranceExpiry = insuranceExpiry;
    }

    public String getLicenseExpiry() {
        return licenseExpiry;
    }

    public void setLicenseExpiry(String licenseExpiry) {
        this.licenseExpiry = licenseExpiry;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        this.pendingAmount = pendingAmount;
    }
}