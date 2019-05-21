package com.eshipper.service;

import com.eshipper.service.dto.LocationTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.LocationType}.
 */
public interface LocationTypeService {

    /**
     * Save a locationType.
     *
     * @param locationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    LocationTypeDTO save(LocationTypeDTO locationTypeDTO);

    /**
     * Get all the locationTypes.
     *
     * @return the list of entities.
     */
    List<LocationTypeDTO> findAll();


    /**
     * Get the "id" locationType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LocationTypeDTO> findOne(Long id);

    /**
     * Delete the "id" locationType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
