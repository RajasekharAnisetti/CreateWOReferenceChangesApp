package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.InsuranceTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceType} and its DTO {@link InsuranceTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InsuranceTypeMapper extends EntityMapper<InsuranceTypeDTO, InsuranceType> {


    @Mapping(target = "woWorkOrders", ignore = true)
    InsuranceType toEntity(InsuranceTypeDTO insuranceTypeDTO);

    default InsuranceType fromId(Long id) {
        if (id == null) {
            return null;
        }
        InsuranceType insuranceType = new InsuranceType();
        insuranceType.setId(id);
        return insuranceType;
    }
}
