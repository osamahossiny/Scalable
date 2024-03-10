package com.example.demo.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public void addNewRoom(Room room) {
        Optional<Room> roomByRoomNumber = roomRepository.findRoomById(room.getId());
        if (roomByRoomNumber.isPresent()) {
            throw new IllegalStateException("A room with this room number already exists.");
        }
        roomRepository.save(room);
    }

    public void deleteRoom(Long roomId) {
        boolean exists = roomRepository.existsById(roomId);
        if (!exists) {
            throw new IllegalStateException("Room with id " + roomId + " does not exist.");
        }
        roomRepository.deleteById(roomId);
    }

    @Transactional
    public void updateRoom(Long id, Long hotelId, double roomPrice, int roomNumber, String roomType, double roomSpace, List<String> features, List<String> breakfast, double breakfastPrice) {
        Room room = roomRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Room with id " + id + "does not exist")
        );
        if (hotelId != null && !hotelId.equals(room.getHotelId())) {
            room.setHotelId(hotelId);
        }
        if (roomPrice != 0 && !(roomPrice == room.getRoomPrice())) {
            room.setRoomPrice(roomPrice);
        }
        if (roomNumber != 0 && !(roomNumber == room.getRoomNumber())) {
            room.setRoomNumber(roomNumber);
        }
        if (roomType != null && !roomType.equals(room.getRoomType())) {
            room.setRoomType(roomType);
        }
        if (roomSpace != 0 && !(roomSpace == room.getRoomSpace())) {
            room.setRoomSpace(roomSpace);
        }
        if (features != null && !features.equals(room.getFeatures())) {
            room.setFeatures(features);
        }
        if (breakfast != null && !breakfast.equals(room.getBreakfast())) {
            room.setBreakfast(breakfast);
        }
        if (breakfastPrice != 0 && !(breakfastPrice == room.getBreakfastPrice())) {
            room.setBreakfastPrice(breakfastPrice);
        }
    }
}
