package com.example.demo.Model;
import jakarta.persistence.*;

@Entity
@Table
public class Flight {
    @Id
    @SequenceGenerator(
            name = "flight_sequence",
            sequenceName = "flight_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "flight_sequence"
    )
    private Long FlightId;
    @Column
    private String DepartureLocation;
    @Column
    private String ArrivalLocation;
    @Column
    private String TimeOfDep;
    @Column
    private String TimeOfArrival;
    @ManyToOne(
//            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "plane_id",
//            referencedColumnName = "id",
//            foreignKey = @ForeignKey(
//                    name = "plane_id_fk"
//            )
//    )
    private Plane Plane;
    @Column
    private float Distance;
    @Column
    private float FlightPrice;
    @Column
    private float InsurancePrice;
    @Column
    private float ExtraBaggagePrice;
    @Column
    private String DepAirport;
    @Column
    private String ArrivalAirPort;
    @Column
    private String DepDate;
    @Column
    private String ArrivalDate;
    public Flight() {
    }
    public Flight(Long flightId, String departureLocation, String arrivalLocation, String timeOfDep, String timeOfArrival, Plane plane, float distance, float flightPrice, float insurancePrice, float extraBaggagePrice, String depAirport, String arrivalAirPort, String depDate, String arrivalDate) {
        FlightId = flightId;
        DepartureLocation = departureLocation;
        ArrivalLocation = arrivalLocation;
        TimeOfDep = timeOfDep;
        TimeOfArrival = timeOfArrival;
        Plane = plane;
        Distance = distance;
        FlightPrice = flightPrice;
        InsurancePrice = insurancePrice;
        ExtraBaggagePrice = extraBaggagePrice;
        DepAirport = depAirport;
        ArrivalAirPort = arrivalAirPort;
        DepDate = depDate;
        ArrivalDate = arrivalDate;
    }

    public Flight(String departureLocation, String arrivalLocation, String timeOfDep, String timeOfArrival, Plane plane, float distance, float flightPrice, float insurancePrice, float extraBaggagePrice, String depAirport, String arrivalAirPort, String depDate, String arrivalDate) {
        DepartureLocation = departureLocation;
        ArrivalLocation = arrivalLocation;
        TimeOfDep = timeOfDep;
        TimeOfArrival = timeOfArrival;
        Plane = plane;
        Distance = distance;
        FlightPrice = flightPrice;
        InsurancePrice = insurancePrice;
        ExtraBaggagePrice = extraBaggagePrice;
        DepAirport = depAirport;
        ArrivalAirPort = arrivalAirPort;
        DepDate = depDate;
        ArrivalDate = arrivalDate;
    }
    public Long getFlightId() {
        return FlightId;
    }

    public void setFlightId(Long flightId) {
        FlightId = flightId;
    }

    public String getDepartureLocation() {
        return DepartureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        DepartureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return ArrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        ArrivalLocation = arrivalLocation;
    }

    public String getTimeOfDep() {
        return TimeOfDep;
    }

    public void setTimeOfDep(String timeOfDep) {
        TimeOfDep = timeOfDep;
    }

    public String getTimeOfArrival() {
        return TimeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        TimeOfArrival = timeOfArrival;
    }

    public Plane getPlane() {
        return Plane;
    }

    public void setPlane(Plane plane) {
        Plane = plane;
    }

    public float getDistance() {
        return Distance;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public float getFlightPrice() {
        return FlightPrice;
    }

    public void setFlightPrice(float flightPrice) {
        FlightPrice = flightPrice;
    }

    public float getInsurancePrice() {
        return InsurancePrice;
    }

    public void setInsurancePrice(float insurancePrice) {
        InsurancePrice = insurancePrice;
    }

    public float getExtraBaggagePrice() {
        return ExtraBaggagePrice;
    }

    public void setExtraBaggagePrice(float extraBaggagePrice) {
        ExtraBaggagePrice = extraBaggagePrice;
    }

    public String getDepAirport() {
        return DepAirport;
    }

    public void setDepAirport(String depAirport) {
        DepAirport = depAirport;
    }

    public String getArrivalAirPort() {
        return ArrivalAirPort;
    }

    public void setArrivalAirPort(String arrivalAirPort) {
        ArrivalAirPort = arrivalAirPort;
    }

    public String getDepDate() {
        return DepDate;
    }

    public void setDepDate(String depDate) {
        DepDate = depDate;
    }

    public String getArrivalDate() {
        return ArrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        ArrivalDate = arrivalDate;
    }
    @Override
    public String toString() {
        return "Flight{" +
                "FlightId=" + FlightId +
                ", DepartureLocation='" + DepartureLocation + '\'' +
                ", ArrivalLocation='" + ArrivalLocation + '\'' +
                ", TimeOfDep=" + TimeOfDep +
                ", TimeOfArrival=" + TimeOfArrival +
                ", PlaneId=" + Plane.getId() +
                ", Distance=" + Distance +
                ", FlightPrice=" + FlightPrice +
                ", InsurancePrice=" + InsurancePrice +
                ", ExtraBaggagePrice=" + ExtraBaggagePrice +
                ", DepAirport='" + DepAirport + '\'' +
                ", ArrivalAirPort='" + ArrivalAirPort + '\'' +
                ", DepDate=" + DepDate +
                ", ArrivalDate=" + ArrivalDate +
                '}';
    }

}
