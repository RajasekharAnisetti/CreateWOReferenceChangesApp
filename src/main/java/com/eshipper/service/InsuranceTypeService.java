package com.eshipper.service;

import com.eshipper.service.dto.InsuranceTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.InsuranceType}.
 */
public interface InsuranceTypeService {

    /**
     * Save a insuranceType.
     *
     * @param insuranceTypeDTO the entity to save.
     * @return the persisted entity.
     */
    InsuranceTypeDTO save(InsuranceTypeDTO insuranceTypeDTO);

    /**
     * Get all the insuranceTypes.
     *
     * @return the list of entities.
     */
    List<InsuranceTypeDTO> findAll();


    /**
     * Get the "id" insuranceType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InsuranceTypeDTO> findOne(Long id);

    /**
     * Delete the "id" insuranceType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
