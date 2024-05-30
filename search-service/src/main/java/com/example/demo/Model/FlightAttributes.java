package com.example.demo.Model;

public class FlightAttributes {
    String From;
    String To;
    String DepDate;
    String TravelClass;
    String Number;
    String ReturnDate;
    int Duration;
    String DepAirport;
    String ArrAirport;
    int Price;
    Long Airline;

//    public FlightAttributes(String from, String to, String depDate, String travelClass, String number, String returnDate, String duration, String depAirport, String arrAirport, int price, Long airline) {
//        From = from;
//        To = to;
//        DepDate = depDate;
//        TravelClass = travelClass;
//        Number = number;
//        ReturnDate = returnDate;
//        Duration = duration;
//        DepAirport = depAirport;
//        ArrAirport = arrAirport;
//        Price = price;
//        Airline = airline;
//    }

    public FlightAttributes(String from, String to, String depDate, String aClass, String number) {
        From = from;
        To = to;
        DepDate = depDate;
        TravelClass = aClass;
        Number = number;
    }

    public FlightAttributes() {
    }

    //    public FlightAttributes(String from, String to, String depDate, String aClass, String number,String returnDate) {
//        From = from;
//        To = to;
//        DepDate = depDate;
//        TravelClass = aClass;
//        Number = number;
//        ReturnDate=returnDate;
//    }
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

    public String getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(String returnDate) {
        ReturnDate = returnDate;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getDepAirport() {
        return DepAirport;
    }

    public void setDepAirport(String depAirport) {
        DepAirport = depAirport;
    }

    public String getArrAirport() {
        return ArrAirport;
    }

    public void setArrAirport(String arrAirport) {
        ArrAirport = arrAirport;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Long getAirline() {
        return Airline;
    }

    public void setAirline(Long airline) {
        Airline = airline;
    }
}
