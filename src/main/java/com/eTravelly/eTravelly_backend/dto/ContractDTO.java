package com.eTravelly.eTravelly_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDTO {
    private Long id;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Long hotelId;
    private List<RoomTypeDTO> roomTypes;
}