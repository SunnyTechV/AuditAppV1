package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.InventorySupplierDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the InventorySupplierDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InventorySupplierDetailsRepository
  extends
    JpaRepository<InventorySupplierDetails, Long>,
    JpaSpecificationExecutor<InventorySupplierDetails> {}
