package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.Audit;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Audit} and its DTO {@link Audit}.
 */
@Mapper(
  componentModel = "spring",
  uses = { AuditPatientMonitoringFormMapper.class }
)
public interface AuditMapper extends EntityMapper<Audit, Audit> {
  @Mapping(
    target = "auditPatientMonitoringForm",
    source = "auditPatientMonitoringForm",
    qualifiedByName = "id"
  )
  Audit toDto(Audit s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Audit toDtoId(Audit audit);
}
