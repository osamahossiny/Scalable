package com.example.demo.Service;
import com.example.demo.Repository.FlightRepository;
import com.example.demo.Model.Flight;
import com.example.demo.Model.FlightAttributes;
import com.example.demo.Model.Plane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class FlightService {
    private static final String CACHE_PREFIX = "flight::";
    private  final FlightRepository flightRepository;
    @Autowired
    private RedisTemplate<String, Flight> redisTemplate;
    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getFlights(){
        return flightRepository.findAll();
    }
    public Flight getFlightId(Long id) {
        System.out.println("GET");
        Flight cached = redisTemplate.opsForValue().get(CACHE_PREFIX+ id);
        if (cached != null) {
            System.out.println("CASHED");
            return cached;
        } else {
            System.out.println("DB");
            Flight db = flightRepository.findById(id).orElse(null);
            if (db != null) {
                redisTemplate.opsForValue().set(CACHE_PREFIX + id, db, 10, TimeUnit.MINUTES);
            }
            return db;
        }
    }
    public List<Flight> getDirectFlights(FlightAttributes attributes){
        return flightRepository.findbyAttributes(attributes.getFrom(), attributes.getTo(), attributes.getDepDate(), attributes.getTravelClass(), attributes.getNumber());
    }
    public List<Object[]> getTwoWay(FlightAttributes attributes){
        return flightRepository.findTwoWay(attributes.getFrom(), attributes.getTo(), attributes.getDepDate(), attributes.getTravelClass(), attributes.getNumber());
    }
    public List<Object[]> getRoundTrip(FlightAttributes attributes){
        List<Flight> t1= flightRepository.findbyAttributes(attributes.getFrom(), attributes.getTo(), attributes.getDepDate(), attributes.getTravelClass(),attributes.getNumber());
        List<Flight> t2= flightRepository.findbyAttributes(attributes.getTo(), attributes.getFrom(), attributes.getReturnDate(), attributes.getTravelClass(),attributes.getNumber());
        List<Object[]> res= new LinkedList<>();
        for(int i=0;i<t1.size();i++)
            for(int j=0;j<t2.size();j++)
                if(t1.get(i).getArrivalDate().compareTo(t2.get(j).getDepDate())<0){
                    Object[] roundTrip={t1.get(i),t2.get(j)};
                    res.add(roundTrip);
                }
        return res;
    }
    public List<Flight> getFiltered(FlightAttributes attributes){
        List<Flight> l=flightRepository.findbyAttributes(attributes.getFrom(), attributes.getTo(), attributes.getDepDate(), attributes.getTravelClass(), attributes.getNumber());
        for(int i=0;i<l.size();i++){
            if(attributes.getPrice()>0&&l.get(i).getFlightPrice()>attributes.getPrice()) {
                l.remove(i);
                i--;
                continue;
            }
            if(attributes.getDuration()>0){
                int hours=Integer.parseInt(l.get(i).getTimeOfArrival().substring(0,2))-Integer.parseInt(l.get(i).getTimeOfDep().substring(0,2));
                if(hours<0)
                    hours+=24;
                int mins=Integer.parseInt(l.get(i).getTimeOfArrival().substring(3,5))-Integer.parseInt(l.get(i).getTimeOfDep().substring(3,5));
                if(mins<0) {
                    hours-=1;
                    mins += 60;
                }
                int seconds=Integer.parseInt(l.get(i).getTimeOfArrival().substring(6,8))-Integer.parseInt(l.get(i).getTimeOfDep().substring(6,8));
                if(seconds<0) {
                    mins-=1;
                    seconds += 60;
                    if(mins<0) {
                        hours-=1;
                        mins += 60;
                    }
                }
                int actualDuration=hours*3600+mins*60+seconds;
                if(actualDuration>attributes.getDuration()) {
                    l.remove(i);
                    i--;
                    continue;
                }
            }
            if(attributes.getArrAirport()!=null&&!attributes.getArrAirport().isEmpty()&&!attributes.getArrAirport().equals(l.get(i).getArrivalAirPort())){
                l.remove(i);
                i--;
                continue;
            }
            if(attributes.getDepAirport()!=null&&!attributes.getDepAirport().isEmpty()&&!attributes.getDepAirport().equals(l.get(i).getDepAirport())){
                l.remove(i);
                i--;
                continue;
            }
            if(attributes.getAirline()!=null && attributes.getAirline()!=0&&(l.get(i).getPlane()==null || l.get(i).getPlane().getAirline()==null || !attributes.getAirline().equals(l.get(i).getPlane().getAirline().getId()))) {
                l.remove(i);
                i--;
                continue;
            }
        }
        return l;
    }
    public void addNewFlight(Flight flight) {
        Optional<Flight> flightByFlightId = flightRepository.findById(flight.getFlightId());
        if (flightByFlightId.isPresent()){
            throw new IllegalStateException("A Flight with this id already exists.");
        }
        Flight saved=flightRepository.save(flight);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }

    public void deleteFlight(Long flightId) {
        boolean exists = flightRepository.existsById(flightId);
        if (!exists) {
            throw new IllegalStateException("Flight with id "+ flightId + " does not exist.");
        }
        flightRepository.deleteById(flightId);
        redisTemplate.delete(CACHE_PREFIX + flightId);

    }

    @Transactional
    public void updateFlight(Long flightId, String departureLocation, String arrivalLocation, String timeOfDep, String timeOfArrival, Plane plane, float distance, float flightPrice, float insurancePrice, float extraBaggagePrice, String depAirport, String arrivalAirPort, String depDate, String arrivalDate) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() ->
                new IllegalStateException("flight with id " + flightId + " does not exist")
        );

        if (!flight.getFlightId().equals(flightId)) {
            Optional<Flight> flightOptional = flightRepository.findById(flightId);
            if (flightOptional.isPresent()){
                throw new IllegalStateException("This id is already used.");
            }
            flight.setFlightId(flightId);
        }
        if (departureLocation != null && !departureLocation.isEmpty() && !flight.getDepartureLocation().equals(departureLocation)) {
            flight.setDepartureLocation(departureLocation);
        }
        if (arrivalLocation != null && !arrivalLocation.isEmpty() && !flight.getArrivalLocation().equals(arrivalLocation)) {
            flight.setArrivalLocation(arrivalLocation);
        }
        if (timeOfDep != null&& !timeOfDep.isEmpty() && !flight.getTimeOfDep().equals(timeOfDep)) {
            flight.setTimeOfDep(timeOfDep);
        }
        if (timeOfArrival != null && !timeOfArrival.isEmpty() && !flight.getTimeOfArrival().equals(timeOfArrival)) {
            flight.setTimeOfArrival(timeOfArrival);
        }
        if (plane != null && !flight.getPlane().getId().equals(plane.getId())) {
            flight.setPlane(plane);
        }
        if (distance != 0 && flight.getDistance()!=distance) {
            flight.setDistance(distance);
        }

        if (insurancePrice != 0 && flight.getInsurancePrice()!=insurancePrice) {
            flight.setInsurancePrice(insurancePrice);
        }
        if (flightPrice != 0 && flight.getFlightPrice()!=flightPrice) {
            flight.setFlightPrice(flightPrice);
        }
        if (extraBaggagePrice != 0 && flight.getExtraBaggagePrice()!=extraBaggagePrice) {
            flight.setExtraBaggagePrice(extraBaggagePrice);
        }
        if (depAirport != null && !depAirport.isEmpty() && !flight.getDepAirport().equals(depAirport)) {
            flight.setDepAirport(depAirport);
        }
        if (arrivalAirPort != null && !arrivalAirPort.isEmpty() && !flight.getArrivalAirPort().equals(arrivalAirPort)) {
            flight.setArrivalAirPort(arrivalAirPort);
        }
        if (depDate != null && !arrivalDate.isEmpty()&& !flight.getDepDate().equals(depDate)) {
            flight.setDepDate(depDate);
        }
        if (arrivalDate != null && !arrivalDate.isEmpty()&& !flight.getArrivalDate().equals(arrivalDate)) {
            flight.setArrivalDate(arrivalDate);
        }
        Flight saved=flightRepository.save(flight);
        redisTemplate.opsForValue().set(CACHE_PREFIX + saved.getId(), saved, 10, TimeUnit.MINUTES);
    }
}
