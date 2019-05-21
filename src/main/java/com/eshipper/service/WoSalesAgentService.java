package com.eshipper.service;

import com.eshipper.service.dto.WoSalesAgentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.WoSalesAgent}.
 */
public interface WoSalesAgentService {

    /**
     * Save a woSalesAgent.
     *
     * @param woSalesAgentDTO the entity to save.
     * @return the persisted entity.
     */
    WoSalesAgentDTO save(WoSalesAgentDTO woSalesAgentDTO);

    /**
     * Get all the woSalesAgents.
     *
     * @return the list of entities.
     */
    List<WoSalesAgentDTO> findAll();


    /**
     * Get the "id" woSalesAgent.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WoSalesAgentDTO> findOne(Long id);

    /**
     * Delete the "id" woSalesAgent.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
