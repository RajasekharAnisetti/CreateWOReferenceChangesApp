package com.eshipper.service.impl;

import com.eshipper.service.LocationTypeService;
import com.eshipper.domain.LocationType;
import com.eshipper.repository.LocationTypeRepository;
import com.eshipper.service.dto.LocationTypeDTO;
import com.eshipper.service.mapper.LocationTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link LocationType}.
 */
@Service
@Transactional
public class LocationTypeServiceImpl implements LocationTypeService {

    private final Logger log = LoggerFactory.getLogger(LocationTypeServiceImpl.class);

    private final LocationTypeRepository locationTypeRepository;

    private final LocationTypeMapper locationTypeMapper;

    public LocationTypeServiceImpl(LocationTypeRepository locationTypeRepository, LocationTypeMapper locationTypeMapper) {
        this.locationTypeRepository = locationTypeRepository;
        this.locationTypeMapper = locationTypeMapper;
    }

    /**
     * Save a locationType.
     *
     * @param locationTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LocationTypeDTO save(LocationTypeDTO locationTypeDTO) {
        log.debug("Request to save LocationType : {}", locationTypeDTO);
        LocationType locationType = locationTypeMapper.toEntity(locationTypeDTO);
        locationType = locationTypeRepository.save(locationType);
        return locationTypeMapper.toDto(locationType);
    }

    /**
     * Get all the locationTypes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<LocationTypeDTO> findAll() {
        log.debug("Request to get all LocationTypes");
        return locationTypeRepository.findAll().stream()
            .map(locationTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one locationType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LocationTypeDTO> findOne(Long id) {
        log.debug("Request to get LocationType : {}", id);
        return locationTypeRepository.findById(id)
            .map(locationTypeMapper::toDto);
    }

    /**
     * Delete the locationType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LocationType : {}", id);
        locationTypeRepository.deleteById(id);
    }
}
