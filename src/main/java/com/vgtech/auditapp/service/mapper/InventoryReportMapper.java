package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.InventoryReport;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InventoryReport} and its DTO {@link InventoryReport}.
 */
@Mapper(componentModel = "spring", uses = { AuditMapper.class })
public interface InventoryReportMapper
  extends EntityMapper<InventoryReport, InventoryReport> {
  @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
  InventoryReport toDto(InventoryReport s);
}
