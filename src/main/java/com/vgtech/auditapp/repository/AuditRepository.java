package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.Audit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Audit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditRepository
  extends JpaRepository<Audit, Long>, JpaSpecificationExecutor<Audit> {}
