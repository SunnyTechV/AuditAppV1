package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringForm;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditPatientMonitoringForm} and its DTO {@link AuditPatientMonitoringForm}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AuditPatientMonitoringFormMapper
  extends EntityMapper<AuditPatientMonitoringForm, AuditPatientMonitoringForm> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  AuditPatientMonitoringForm toDtoId(
    AuditPatientMonitoringForm auditPatientMonitoringForm
  );
}
