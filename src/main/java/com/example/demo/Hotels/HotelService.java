package com.example.demo.Hotels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class HotelService {


    private final HotelRepository hotelRepository;
    @Autowired
    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    public void addNewHotel(Hotel hotel){
        Optional<Hotel> hotelByName = hotelRepository.findHotelByName(hotel.getName());
        if(hotelByName.isPresent()){
            throw new IllegalStateException("An hotel with this name already exists.");
        }
        hotelRepository.save(hotel);
    }

    public void deleteHotel(Long hotelId) {
        boolean exists = hotelRepository.existsById(hotelId);
        if (!exists) {
            throw new IllegalStateException("Hotel with id "+ hotelId + " does not exist.");
        }
        hotelRepository.deleteById(hotelId);
    }

    @Transactional
        public void updateHotel(Long id, String name, String country, String city, String address, String type, String additionalInfo, double foodPricePerPerson, String cancellationPolicy, List<String> rules, List<String> amenities, String IBAN) {
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()->
                new IllegalStateException("Hotel with id " + id + "does not exist")
        );
        if(name != null && !name.equals(hotel.getName())){
            hotel.setName(name);
        }
        if(country != null && !country.equals(hotel.getCountry())){
            hotel.setCountry(country);
        }
        if(city != null && !city.equals(hotel.getCity())){
            hotel.setCity(city);
        }
        if(address != null && !address.equals(hotel.getAddress())){
            hotel.setAddress(address);
        }
        if(type != null && !type.equals(hotel.getType())){
            hotel.setType(type);
        }
        if(additionalInfo != null && !additionalInfo.equals(hotel.getAdditionalInfo())){
            hotel.setAdditionalInfo(additionalInfo);
        }
        if(foodPricePerPerson != 0 && !(foodPricePerPerson == hotel.getFoodPricePerPerson())){
            hotel.setFoodPricePerPerson(foodPricePerPerson);
        }
        if(cancellationPolicy != null && !cancellationPolicy.equals(hotel.getCancellationPolicy())){
            hotel.setCancellationPolicy(cancellationPolicy);
        }
        if(rules != null && !rules.equals(hotel.getRules())){
            hotel.setRules(rules);
        }
        if(amenities != null && !amenities.equals(hotel.getAmenities())){
            hotel.setAmenities(amenities);
        }
        if(IBAN != null && !IBAN.equals(hotel.getIBAN())){
            hotel.setIBAN(IBAN);
        }
    }

}
