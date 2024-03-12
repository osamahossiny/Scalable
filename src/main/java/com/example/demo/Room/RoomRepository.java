package com.example.demo.Room;


import com.example.demo.Airline.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long> {

    @Query("select a from Room a where  a.id = ?1")
    Optional<Room> findRoomById(Long id);
}
