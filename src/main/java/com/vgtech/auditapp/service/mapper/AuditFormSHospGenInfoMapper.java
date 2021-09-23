package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfo;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditFormSHospGenInfo} and its DTO {@link AuditFormSHospGenInfo}.
 */
@Mapper(componentModel = "spring", uses = { AuditMapper.class })
public interface AuditFormSHospGenInfoMapper
  extends EntityMapper<AuditFormSHospGenInfo, AuditFormSHospGenInfo> {
  @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
  AuditFormSHospGenInfo toDto(AuditFormSHospGenInfo s);
}
