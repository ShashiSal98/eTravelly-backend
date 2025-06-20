package com.eTravelly.eTravelly_backend.repository;

import com.eTravelly.eTravelly_backend.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
}
