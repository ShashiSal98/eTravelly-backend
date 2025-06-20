package com.eTravelly.eTravelly_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SearchRequest {
    private LocalDate checkInDate;
    private int nights;
    private List<RoomRequest> rooms;
}
