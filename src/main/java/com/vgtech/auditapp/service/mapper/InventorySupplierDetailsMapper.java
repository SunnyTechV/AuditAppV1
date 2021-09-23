package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.InventorySupplierDetails;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InventorySupplierDetails} and its DTO {@link InventorySupplierDetails}.
 */
@Mapper(componentModel = "spring", uses = { AuditMapper.class })
public interface InventorySupplierDetailsMapper
  extends EntityMapper<InventorySupplierDetails, InventorySupplierDetails> {
  @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
  InventorySupplierDetails toDto(InventorySupplierDetails s);
}
