package com.example.demo.VillaReservation;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

public class VillaReservation {

    private Long Userid;
    private Long Villaid;
    private String From;
    private String To;
    private Integer Price;
    private Integer NumOfPersons;

    public VillaReservation(){

    }
    public VillaReservation(Long userid, Long villaid, String from, String to, Integer price, Integer numOfPersons) {
        Userid = userid;
        Villaid = villaid;
        From = from;
        To = to;
        Price = price;
        NumOfPersons = numOfPersons;
    }

    public VillaReservation(String from, String to, Integer price, Integer numOfPersons) {
        From = from;
        To = to;
        Price = price;
        NumOfPersons = numOfPersons;
    }

    public Long getUserid() {
        return Userid;
    }

    public void setUserid(Long userid) {
        Userid = userid;
    }

    public Long getVillaid() {
        return Villaid;
    }

    public void setVillaid(Long villaid) {
        Villaid = villaid;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String to) {
        To = to;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getNumOfPersons() {
        return NumOfPersons;
    }

    public void setNumOfPersons(Integer numOfPersons) {
        NumOfPersons = numOfPersons;
    }

    @Override
    public String toString() {
        return "VillaReservation{" +
                "Userid=" + Userid +
                ", Villaid=" + Villaid +
                ", From='" + From + '\'' +
                ", To='" + To + '\'' +
                ", Price=" + Price +
                ", NumOfPersons=" + NumOfPersons +
                '}';
    }
}
