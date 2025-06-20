package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.ContractDTO;
import com.eTravelly.eTravelly_backend.dto.HotelDTO;
import com.eTravelly.eTravelly_backend.dto.RoomTypeDTO;
import com.eTravelly.eTravelly_backend.model.Contract;
import com.eTravelly.eTravelly_backend.model.Hotel;
import com.eTravelly.eTravelly_backend.model.RoomType;
import com.eTravelly.eTravelly_backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {
        Hotel hotel = toEntity(hotelDTO);
        Hotel savedHotel = hotelRepository.save(hotel);
        return toDTO(savedHotel);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel not found with id " + id));
        return toDTO(hotel);
    }

    @Override
    public HotelDTO updateHotel(Long id, HotelDTO hotelDTO) {
        Hotel existingHotel = hotelRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel not found with id " + id));

        existingHotel.setHotelName(hotelDTO.getHotelName());

        // Update contracts: For simplicity, remove old contracts and add new ones
        existingHotel.getContracts().clear();

        List<Contract> updatedContracts = Optional.ofNullable(hotelDTO.getContracts())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        existingHotel.getContracts().addAll(updatedContracts);

        Hotel savedHotel = hotelRepository.save(existingHotel);
        return toDTO(savedHotel);
    }

    @Override
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new NoSuchElementException("Hotel not found with id " + id);
        }
        hotelRepository.deleteById(id);
    }

    // Conversion methods

    private HotelDTO toDTO(Hotel hotel) {
        HotelDTO hotelDTO = new HotelDTO();
        hotelDTO.setId(hotel.getId());
        hotelDTO.setHotelName(hotel.getHotelName());

        List<ContractDTO> contractDTOs = Optional.ofNullable(hotel.getContracts())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        hotelDTO.setContracts(contractDTOs);
        return hotelDTO;
    }

    private ContractDTO toDTO(Contract contract) {
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setId(contract.getId());
        contractDTO.setValidFrom(contract.getValidFrom());
        contractDTO.setValidTo(contract.getValidTo());

        List<RoomTypeDTO> roomTypeDTOs = Optional.ofNullable(contract.getRoomTypes())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        contractDTO.setRoomTypes(roomTypeDTOs);
        return contractDTO;
    }

    private RoomTypeDTO toDTO(RoomType roomType) {
        RoomTypeDTO roomTypeDTO = new RoomTypeDTO();
        roomTypeDTO.setId(roomType.getId());
        roomTypeDTO.setRoomTypeName(roomType.getRoomTypeName());
        roomTypeDTO.setPricePerPersonPerNight(roomType.getPricePerPersonPerNight());
        roomTypeDTO.setTotalRooms(roomType.getTotalRooms());
        roomTypeDTO.setMaxAdults(roomType.getMaxAdults());
        return roomTypeDTO;
    }

    private Hotel toEntity(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelDTO.getId());
        hotel.setHotelName(hotelDTO.getHotelName());

        List<Contract> contracts = Optional.ofNullable(hotelDTO.getContracts())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        hotel.setContracts(new ArrayList<>(contracts));
        return hotel;
    }

    private Contract toEntity(ContractDTO contractDTO) {
        Contract contract = new Contract();
        contract.setId(contractDTO.getId());
        contract.setValidFrom(contractDTO.getValidFrom());
        contract.setValidTo(contractDTO.getValidTo());

        List<RoomType> roomTypes = Optional.ofNullable(contractDTO.getRoomTypes())
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        contract.setRoomTypes(new ArrayList<>(roomTypes));
        return contract;
    }

    private RoomType toEntity(RoomTypeDTO roomTypeDTO) {
        RoomType roomType = new RoomType();
        roomType.setId(roomTypeDTO.getId());
        roomType.setRoomTypeName(roomTypeDTO.getRoomTypeName());
        roomType.setPricePerPersonPerNight(roomTypeDTO.getPricePerPersonPerNight());
        roomType.setTotalRooms(roomTypeDTO.getTotalRooms());
        roomType.setMaxAdults(roomTypeDTO.getMaxAdults());
        return roomType;
    }
}
