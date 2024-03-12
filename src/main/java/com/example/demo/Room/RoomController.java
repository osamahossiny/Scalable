package com.example.demo.Room;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getRooms() {
        return this.roomService.getRooms();
    }

    @PostMapping
    public void addNewRoom(@RequestBody Room room) {
        roomService.addNewRoom(room);
    }

    @DeleteMapping(path = "{roomId}")
    public void deleteRoom(@PathVariable("roomId") Long roomId) {
        roomService.deleteRoom(roomId);
    }

    @PutMapping(path = "{roomId}")
    public void updateRoom(@PathVariable("roomId") Long roomId, @RequestParam(required = false, name = "hotelId") Long hotelId, @RequestParam(required = false, name = "roomPrice") double roomPrice, @RequestParam(required = false, name = "roomNumber") int roomNumber, @RequestParam(required = false, name = "roomType") String roomType, @RequestParam(required = false, name = "roomSpace") double roomSpace, @RequestParam(required = false, name = "features") List<String> features, @RequestParam(required = false, name = "breakfast") List<String> breakfast, @RequestParam(required = false, name = "breakfastPrice") double breakfastPrice) {
        roomService.updateRoom(roomId, hotelId, roomPrice, roomNumber, roomType, roomSpace, features, breakfast, breakfastPrice);
    }
}
