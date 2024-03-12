package com.example.demo.Hotels;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table
public class Hotel {


    @Id
    @SequenceGenerator(
            name = "hotel_sequence",
            sequenceName = "hotel_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hotel_sequence"
    )
    private Long id;
    private String name;
    private String country;

    private String city;

    private String address;

    private String type;

    private String AdditionalInfo;

    private double FoodPricePerPerson;

    private String cancellationPolicy;

    @ElementCollection
    @CollectionTable(name = "hotel_rules", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "rule")
    private List<String> rules;

    @ElementCollection
    @CollectionTable(name = "hotel_amenities", joinColumns = @JoinColumn(name = "hotel_id"))
    @Column(name = "amenity")
    private List<String> amenities;
    private String IBAN;

    public Hotel() {
    }

    public Hotel(Long id, String name, String country, String city, String address, String type, String additionalInfo, double foodPricePerPerson, String cancellationPolicy, List<String> rules, List<String> amenities, String IBAN) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.type = type;
        AdditionalInfo = additionalInfo;
        FoodPricePerPerson = foodPricePerPerson;
        this.cancellationPolicy = cancellationPolicy;
        this.rules = rules;
        this.amenities = amenities;
        this.IBAN = IBAN;
    }

    public Hotel(String name, String country, String city, String address, String type, String additionalInfo, double foodPricePerPerson, String cancellationPolicy, List<String> rules, List<String> amenities, String IBAN) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
        this.type = type;
        AdditionalInfo = additionalInfo;
        FoodPricePerPerson = foodPricePerPerson;
        this.cancellationPolicy = cancellationPolicy;
        this.rules = rules;
        this.amenities = amenities;
        this.IBAN = IBAN;
    }

    public List<String> getRules() {
        return rules;
    }

    public void setRules(List<String> rules) {
        this.rules = rules;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdditionalInfo() {
        return AdditionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        AdditionalInfo = additionalInfo;
    }

    public double getFoodPricePerPerson() {
        return FoodPricePerPerson;
    }

    public void setFoodPricePerPerson(double foodPricePerPerson) {
        FoodPricePerPerson = foodPricePerPerson;
    }

    public String getCancellationPolicy() {
        return cancellationPolicy;
    }

    public void setCancellationPolicy(String cancellationPolicy) {
        this.cancellationPolicy = cancellationPolicy;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", AdditionalInfo='" + AdditionalInfo + '\'' +
                ", FoodPricePerPerson=" + FoodPricePerPerson +
                ", Rules=" + rules +
                ", Amenities=" + amenities +
                ", cancellationPolicy='" + cancellationPolicy + '\'' +
                ", IBAN='" + IBAN + '\'' +
                '}';
    }
}
