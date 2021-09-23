package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.AuditPatientMonitoringForm;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AuditPatientMonitoringForm entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditPatientMonitoringFormRepository
  extends
    JpaRepository<AuditPatientMonitoringForm, Long>,
    JpaSpecificationExecutor<AuditPatientMonitoringForm> {}
