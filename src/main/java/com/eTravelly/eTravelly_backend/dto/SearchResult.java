package com.eTravelly.eTravelly_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    private String hotelName;
    private String roomTypeName;
    private double totalPrice;
    private String availability;

    public SearchResult(String hotelName, String roomTypeName, double totalPrice, String availability) {
        this.hotelName = hotelName;
        this.roomTypeName = roomTypeName;
        this.totalPrice = totalPrice;
        this.availability = availability;
    }
}