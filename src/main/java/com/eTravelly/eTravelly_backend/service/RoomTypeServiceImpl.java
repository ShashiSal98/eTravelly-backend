package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.RoomTypeDTO;
import com.eTravelly.eTravelly_backend.model.Contract;
import com.eTravelly.eTravelly_backend.model.RoomType;
import com.eTravelly.eTravelly_backend.repository.ContractRepository;
import com.eTravelly.eTravelly_backend.repository.RoomTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;
    private final ContractRepository contractRepository;

    @Override
    public List<RoomTypeDTO> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RoomTypeDTO getRoomTypeById(Long id) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room type not found with id: " + id));
        return toDTO(roomType);
    }

    @Override
    public RoomTypeDTO createRoomType(RoomTypeDTO dto) {
        Contract contract = contractRepository.findById(dto.getContractId())
                .orElseThrow(() -> new EntityNotFoundException("Contract not found with id: " + dto.getContractId()));

        RoomType roomType = new RoomType();
        roomType.setRoomTypeName(dto.getRoomTypeName());
        roomType.setPricePerPersonPerNight(dto.getPricePerPersonPerNight());
        roomType.setTotalRooms(dto.getTotalRooms());
        roomType.setMaxAdults(dto.getMaxAdults());
        roomType.setContract(contract);

        RoomType saved = roomTypeRepository.save(roomType);
        return toDTO(saved);
    }

    @Override
    public RoomTypeDTO updateRoomType(Long id, RoomTypeDTO dto) {
        RoomType roomType = roomTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room type not found with id: " + id));

        roomType.setRoomTypeName(dto.getRoomTypeName());
        roomType.setPricePerPersonPerNight(dto.getPricePerPersonPerNight());
        roomType.setTotalRooms(dto.getTotalRooms());
        roomType.setMaxAdults(dto.getMaxAdults());

        if (dto.getContractId() != null) {
            Contract contract = contractRepository.findById(dto.getContractId())
                    .orElseThrow(() -> new EntityNotFoundException("Contract not found with id: " + dto.getContractId()));
            roomType.setContract(contract);
        }

        RoomType updated = roomTypeRepository.save(roomType);
        return toDTO(updated);
    }

    @Override
    public void deleteRoomType(Long id) {
        if (!roomTypeRepository.existsById(id)) {
            throw new EntityNotFoundException("Room type not found with id: " + id);
        }
        roomTypeRepository.deleteById(id);
    }

    private RoomTypeDTO toDTO(RoomType entity) {
        RoomTypeDTO dto = new RoomTypeDTO();
        dto.setId(entity.getId());
        dto.setRoomTypeName(entity.getRoomTypeName());
        dto.setPricePerPersonPerNight(entity.getPricePerPersonPerNight());
        dto.setTotalRooms(entity.getTotalRooms());
        dto.setMaxAdults(entity.getMaxAdults());
        dto.setContractId(entity.getContract() != null ? entity.getContract().getId() : null);
        return dto;
    }
}
