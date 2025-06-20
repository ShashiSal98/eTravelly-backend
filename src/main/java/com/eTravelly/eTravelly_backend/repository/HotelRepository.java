package com.eTravelly.eTravelly_backend.repository;

import com.eTravelly.eTravelly_backend.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository <Hotel, Long> {
    List<Hotel> findByHotelNameContainingIgnoreCase(String name);
}