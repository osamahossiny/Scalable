package com.example.demo.HotelReservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class HotelReservationService {


    private  final HotelReservationRepository hotelReservationRepository;

    @Autowired
    public HotelReservationService(HotelReservationRepository hotelReservationRepository) {
        this.hotelReservationRepository = hotelReservationRepository;
    }

    public List<HotelReservation> getHotelRating(){
        return hotelReservationRepository.findAll();
    }

    public void addNewHotelReservation(HotelReservation hotelReservation) {
        Optional<HotelReservation> hotelReservationByRoomId = hotelReservationRepository.findHotelReservationByRoomId(hotelReservation.getRoomID());
        if(hotelReservationByRoomId.isPresent()){
            throw new IllegalStateException("A reservation for this room already exists.");
        }
        hotelReservationRepository.save(hotelReservation);
    }

    public void deleteHotelReservation(Long hotelReservationId) {
        boolean exists = hotelReservationRepository.existsById(hotelReservationId);

        if (!exists) {
            throw new IllegalStateException("Hotel Reservation with id "+ hotelReservationId + " does not exist.");
        }
        hotelReservationRepository.deleteById(hotelReservationId);
    }

    @Transactional
    public void updateHotelReservation(Long hotelReservationId, Long userId, Long hotelId, Long roomId, LocalDate checkIn, LocalDate checkOut) {
        HotelReservation hotelReservation = hotelReservationRepository.findById(hotelReservationId).orElseThrow(()->
                new IllegalStateException("Hotel Reservation with id " + hotelReservationId + "does not exist")
        );
        if(userId != null && !userId.equals(hotelReservation.getUserId())){
            hotelReservation.setUserId(userId);
        }
        if(hotelId != null && !hotelId.equals(hotelReservation.getHotelId())){
            hotelReservation.setHotelId(hotelId);
        }
        if(roomId != null && !roomId.equals(hotelReservation.getRoomID())){
            hotelReservation.setRoomID(roomId);
        }
        if(checkIn != null  && !hotelReservation.getCheckIn().equals(checkIn)){
            hotelReservation.setCheckIn(checkIn);
        }
        if(checkOut != null  && !hotelReservation.getCheckOut().equals(checkOut)){
            hotelReservation.setCheckOut(checkOut);
        }
    }
}
