package com.eTravelly.eTravelly_backend.repository;

import com.eTravelly.eTravelly_backend.model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    List<RoomType> findByContract_Id(Long contractId);

}
