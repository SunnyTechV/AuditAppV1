package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.AuditFormSHospGenInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AuditFormSHospGenInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditFormSHospGenInfoRepository
  extends
    JpaRepository<AuditFormSHospGenInfo, Long>,
    JpaSpecificationExecutor<AuditFormSHospGenInfo> {}
