package com.chrisjhkim.sweethome.location.repository;

import com.chrisjhkim.sweethome.location.entity.Place;
import com.chrisjhkim.sweethome.location.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
