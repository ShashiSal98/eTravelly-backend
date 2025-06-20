package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.ContractDTO;
import com.eTravelly.eTravelly_backend.dto.RoomTypeDTO;
import com.eTravelly.eTravelly_backend.model.Contract;
import com.eTravelly.eTravelly_backend.model.Hotel;
import com.eTravelly.eTravelly_backend.model.RoomType;
import com.eTravelly.eTravelly_backend.repository.ContractRepository;
import com.eTravelly.eTravelly_backend.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final HotelRepository hotelRepository;

    @Override
    public List<ContractDTO> getAllContracts() {
        return contractRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ContractDTO getContractById(Long id) {
        Contract contract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
        return toDTO(contract);
    }

    @Override
    public ContractDTO createContract(ContractDTO dto) {
        Contract contract = toEntity(dto);
        return toDTO(contractRepository.save(contract));
    }

    @Override
    public ContractDTO updateContract(Long id, ContractDTO dto) {
        Contract existing = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));

        existing.setValidFrom(dto.getValidFrom());
        existing.setValidTo(dto.getValidTo());

        if (dto.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(dto.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + dto.getHotelId()));
            existing.setHotel(hotel);
        }

        // Optional: update room types logic
        return toDTO(contractRepository.save(existing));
    }

    @Override
    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    // ---------- Mapping ----------

    private ContractDTO toDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setValidFrom(contract.getValidFrom());
        dto.setValidTo(contract.getValidTo());
        dto.setHotelId(contract.getHotel() != null ? contract.getHotel().getId() : null);

        if (contract.getRoomTypes() != null) {
            List<RoomTypeDTO> roomTypeDTOs = contract.getRoomTypes().stream().map(roomType -> {
                RoomTypeDTO rtDto = new RoomTypeDTO();
                rtDto.setId(roomType.getId());
                rtDto.setRoomTypeName(roomType.getRoomTypeName());
                rtDto.setPricePerPersonPerNight(roomType.getPricePerPersonPerNight());
                rtDto.setTotalRooms(roomType.getTotalRooms());
                rtDto.setMaxAdults(roomType.getMaxAdults());
                rtDto.setContractId(contract.getId());
                return rtDto;
            }).collect(Collectors.toList());
            dto.setRoomTypes(roomTypeDTOs);
        }

        return dto;
    }

    private Contract toEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setValidFrom(dto.getValidFrom());
        contract.setValidTo(dto.getValidTo());

        if (dto.getHotelId() != null) {
            Hotel hotel = hotelRepository.findById(dto.getHotelId())
                    .orElseThrow(() -> new RuntimeException("Hotel not found with id: " + dto.getHotelId()));
            contract.setHotel(hotel);
        }

        if (dto.getRoomTypes() != null) {
            List<RoomType> roomTypes = dto.getRoomTypes().stream().map(rtDto -> {
                RoomType roomType = new RoomType();
                roomType.setId(rtDto.getId());
                roomType.setRoomTypeName(rtDto.getRoomTypeName());
                roomType.setPricePerPersonPerNight(rtDto.getPricePerPersonPerNight());
                roomType.setTotalRooms(rtDto.getTotalRooms());
                roomType.setMaxAdults(rtDto.getMaxAdults());
                roomType.setContract(contract);
                return roomType;
            }).collect(Collectors.toList());
            contract.setRoomTypes(roomTypes);
        } else {
            contract.setRoomTypes(new ArrayList<>());
        }

        return contract;
    }
}
