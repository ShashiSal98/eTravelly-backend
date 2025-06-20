package com.eTravelly.eTravelly_backend.service;

import com.eTravelly.eTravelly_backend.dto.ContractDTO;

import java.util.List;

public interface ContractService {
    List<ContractDTO> getAllContracts();
    ContractDTO getContractById(Long id);
    ContractDTO createContract(ContractDTO contractDTO);
    ContractDTO updateContract(Long id, ContractDTO contractDTO);
    void deleteContract(Long id);
}
