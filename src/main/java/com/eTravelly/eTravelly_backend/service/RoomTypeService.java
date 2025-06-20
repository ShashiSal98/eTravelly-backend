package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.RoomTypeDTO;

import java.util.List;

public interface RoomTypeService {
    List<RoomTypeDTO> getAllRoomTypes();
    RoomTypeDTO getRoomTypeById(Long id);
    RoomTypeDTO createRoomType(RoomTypeDTO roomTypeDTO);
    RoomTypeDTO updateRoomType(Long id, RoomTypeDTO roomTypeDTO);
    void deleteRoomType(Long id);
}
