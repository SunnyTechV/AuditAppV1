package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.OxygenConsumptionData;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OxygenConsumptionData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OxygenConsumptionDataRepository
  extends
    JpaRepository<OxygenConsumptionData, Long>,
    JpaSpecificationExecutor<OxygenConsumptionData> {}
