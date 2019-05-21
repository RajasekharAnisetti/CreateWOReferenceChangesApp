package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.LocationTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LocationType} and its DTO {@link LocationTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocationTypeMapper extends EntityMapper<LocationTypeDTO, LocationType> {



    default LocationType fromId(Long id) {
        if (id == null) {
            return null;
        }
        LocationType locationType = new LocationType();
        locationType.setId(id);
        return locationType;
    }
}
