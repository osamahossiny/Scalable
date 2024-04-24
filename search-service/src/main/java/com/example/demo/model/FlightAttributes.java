package com.example.demo.model;

public class FlightAttributes {
    String From;
    String To;
    String DepDate;
    String TravelClass;
    String Number;
    public FlightAttributes(String from, String to, String depDate, String aClass, String number) {
        From = from;
        To = to;
        DepDate = depDate;
        TravelClass = aClass;
        Number = number;
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

    public String getDepDate() {
        return DepDate;
    }

    public void setDepDate(String depDate) {
        DepDate = depDate;
    }

    public String getTravelClass() {
        return TravelClass;
    }

    public void setTravelClass(String travelClass) {
        TravelClass = travelClass;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
