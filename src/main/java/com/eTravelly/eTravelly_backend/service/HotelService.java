package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    List<HotelDTO> getAllHotels();
    HotelDTO getHotelById(Long id);
    HotelDTO createHotel(HotelDTO hotelDTO);
    HotelDTO updateHotel(Long id, HotelDTO hotelDTO);
    void deleteHotel(Long id);
}
