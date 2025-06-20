package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.*;
import com.eTravelly.eTravelly_backend.model.*;
import com.eTravelly.eTravelly_backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final HotelRepository hotelRepository;

    @Override
    public List<SearchResult> searchAvailableRooms(SearchRequest request) {
        List<SearchResult> results = new ArrayList<>();
        LocalDate checkIn = request.getCheckInDate();
        LocalDate checkOut = checkIn.plusDays(request.getNights());

        List<Hotel> hotels = hotelRepository.findAll();

        for (Hotel hotel : hotels) {
            if (hotel.getContracts() == null) continue;

            for (Contract contract : hotel.getContracts()) {
                if (contract == null || contract.getValidFrom() == null || contract.getValidTo() == null)
                    continue;

                if (checkIn.isBefore(contract.getValidFrom()) || checkOut.isAfter(contract.getValidTo())) {
                    continue;
                }

                if (contract.getRoomTypes() == null) continue;

                for (RoomType room : contract.getRoomTypes()) {
                    int totalAdults = request.getRooms().stream()
                            .mapToInt(RoomRequest::getNumberOfAdults)
                            .sum();

                    boolean fits = totalAdults <= (room.getMaxAdults() * room.getTotalRooms());

                    String availability = fits ? "Available" : "Not Available";
                    double totalPrice = room.getPricePerPersonPerNight() * totalAdults * request.getNights();

                    results.add(new SearchResult(
                            hotel.getHotelName(),
                            room.getRoomTypeName(),
                            totalPrice,
                            availability
                    ));
                }
            }
        }

        return results;
    }
}
