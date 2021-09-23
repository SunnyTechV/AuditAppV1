package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.OxygenConsumptionData;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OxygenConsumptionData} and its DTO {@link OxygenConsumptionData}.
 */
@Mapper(
  componentModel = "spring",
  uses = { AuditMapper.class, TableDetailsMapper.class }
)
public interface OxygenConsumptionDataMapper
  extends EntityMapper<OxygenConsumptionData, OxygenConsumptionData> {
  @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
  @Mapping(
    target = "tableDetails",
    source = "tableDetails",
    qualifiedByName = "id"
  )
  OxygenConsumptionData toDto(OxygenConsumptionData s);
}
