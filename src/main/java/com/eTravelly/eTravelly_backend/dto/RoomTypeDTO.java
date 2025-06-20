package com.eTravelly.eTravelly_backend.dto;

import lombok.Data;

@Data
public class RoomTypeDTO {
    private Long id;
    private String roomTypeName;
    private Double pricePerPersonPerNight;
    private Integer totalRooms;
    private Integer maxAdults;
    private Long contractId;

}
