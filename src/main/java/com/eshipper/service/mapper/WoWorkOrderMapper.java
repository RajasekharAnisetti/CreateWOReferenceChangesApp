package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.WoWorkOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WoWorkOrder} and its DTO {@link WoWorkOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {LocationTypeMapper.class, WoSalesAgentMapper.class, InsuranceTypeMapper.class})
public interface WoWorkOrderMapper extends EntityMapper<WoWorkOrderDTO, WoWorkOrder> {

    @Mapping(source = "fromLocation.id", target = "fromLocationId")
    @Mapping(source = "toLocation.id", target = "toLocationId")
    @Mapping(source = "woSalesAgent.id", target = "woSalesAgentId")
    @Mapping(source = "insuranceType.id", target = "insuranceTypeId")
    WoWorkOrderDTO toDto(WoWorkOrder woWorkOrder);

    @Mapping(source = "fromLocationId", target = "fromLocation")
    @Mapping(source = "toLocationId", target = "toLocation")
    @Mapping(source = "woSalesAgentId", target = "woSalesAgent")
    @Mapping(source = "insuranceTypeId", target = "insuranceType")
    WoWorkOrder toEntity(WoWorkOrderDTO woWorkOrderDTO);

    default WoWorkOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        WoWorkOrder woWorkOrder = new WoWorkOrder();
        woWorkOrder.setId(id);
        return woWorkOrder;
    }
}
