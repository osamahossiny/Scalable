package com.example.demo.Villa;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.util.ArrayList;

public class Villa {

    private Long Villaid;
    private String VillaName;
    private String Country;
    private String City;
    private Integer Address;
    private VillaType Type;
    private ArrayList<String> Features;
    private Integer MaxNumberOfGuests;
    private Integer Price;
    private Integer PriceWithBreakfast;
    private ArrayList<String> BreakfastFeatures;
    private ArrayList<String> Amenities;
    private ArrayList<String> Rules;
    private boolean CanRefund;
    private Integer CancellationFee;
    private String IBAN;

    public Villa(){

    }
    public Villa(Long villaid, String villaName, String country, String city, Integer address, VillaType type, ArrayList<String> features,
                 Integer maxNumberOfGuests, Integer price, Integer priceWithBreakfast, ArrayList<String> breakfastFeatures, ArrayList<String> amenities,
                 ArrayList<String> rules, boolean canRefund, Integer cancellationFee, String IBAN) {
        Villaid = villaid;
        VillaName = villaName;
        Country = country;
        City = city;
        Address = address;
        Type = type;
        Features = features;
        MaxNumberOfGuests = maxNumberOfGuests;
        Price = price;
        PriceWithBreakfast = priceWithBreakfast;
        BreakfastFeatures = breakfastFeatures;
        Amenities = amenities;
        Rules = rules;
        CanRefund = canRefund;
        CancellationFee = cancellationFee;
        this.IBAN = IBAN;
    }

    public Villa(String villaName, String country, String city, Integer address, VillaType type, ArrayList<String> features, Integer maxNumberOfGuests,
                 Integer price, Integer priceWithBreakfast, ArrayList<String> breakfastFeatures, ArrayList<String> amenities, ArrayList<String> rules,
                 boolean canRefund, Integer cancellationFee, String IBAN) {
        VillaName = villaName;
        Country = country;
        City = city;
        Address = address;
        Type = type;
        Features = features;
        MaxNumberOfGuests = maxNumberOfGuests;
        Price = price;
        PriceWithBreakfast = priceWithBreakfast;
        BreakfastFeatures = breakfastFeatures;
        Amenities = amenities;
        Rules = rules;
        CanRefund = canRefund;
        CancellationFee = cancellationFee;
        this.IBAN = IBAN;
    }

    public Long getVillaid() {
        return Villaid;
    }

    public void setVillaid(Long villaid) {
        Villaid = villaid;
    }

    public String getVillaName() {
        return VillaName;
    }

    public void setVillaName(String villaName) {
        VillaName = villaName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public Integer getAddress() {
        return Address;
    }

    public void setAddress(Integer address) {
        Address = address;
    }

    public VillaType getType() {
        return Type;
    }

    public void setType(VillaType type) {
        Type = type;
    }

    public ArrayList<String> getFeatures() {
        return Features;
    }

    public void setFeatures(ArrayList<String> features) {
        Features = features;
    }

    public Integer getMaxNumberOfGuests() {
        return MaxNumberOfGuests;
    }

    public void setMaxNumberOfGuests(Integer maxNumberOfGuests) {
        MaxNumberOfGuests = maxNumberOfGuests;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getPriceWithBreakfast() {
        return PriceWithBreakfast;
    }

    public void setPriceWithBreakfast(Integer priceWithBreakfast) {
        PriceWithBreakfast = priceWithBreakfast;
    }

    public ArrayList<String> getBreakfastFeatures() {
        return BreakfastFeatures;
    }

    public void setBreakfastFeatures(ArrayList<String> breakfastFeatures) {
        BreakfastFeatures = breakfastFeatures;
    }

    public ArrayList<String> getAmenities() {
        return Amenities;
    }

    public void setAmenities(ArrayList<String> amenities) {
        Amenities = amenities;
    }

    public ArrayList<String> getRules() {
        return Rules;
    }

    public void setRules(ArrayList<String> rules) {
        Rules = rules;
    }

    public boolean isCanRefund() {
        return CanRefund;
    }

    public void setCanRefund(boolean canRefund) {
        CanRefund = canRefund;
    }

    public Integer getCancellationFee() {
        return CancellationFee;
    }

    public void setCancellationFee(Integer cancellationFee) {
        CancellationFee = cancellationFee;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "Villaid=" + Villaid +
                ", VillaName='" + VillaName + '\'' +
                ", Country='" + Country + '\'' +
                ", City='" + City + '\'' +
                ", Address=" + Address +
                ", Type=" + Type +
                ", Features=" + Features +
                ", MaxNumberOfGuests=" + MaxNumberOfGuests +
                ", Price=" + Price +
                ", PriceWithBreakfast=" + PriceWithBreakfast +
                ", BreakfastFeatures=" + BreakfastFeatures +
                ", Amenities=" + Amenities +
                ", Rules=" + Rules +
                ", CanRefund=" + CanRefund +
                ", CancellationFee=" + CancellationFee +
                ", IBAN='" + IBAN + '\'' +
                '}';
    }
}
