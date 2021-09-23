package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventorySupplierDetailsTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(InventorySupplierDetails.class);
    InventorySupplierDetails inventorySupplierDetails1 = new InventorySupplierDetails();
    inventorySupplierDetails1.setId(1L);
    InventorySupplierDetails inventorySupplierDetails2 = new InventorySupplierDetails();
    assertThat(inventorySupplierDetails1)
      .isNotEqualTo(inventorySupplierDetails2);
    inventorySupplierDetails2.setId(inventorySupplierDetails1.getId());
    assertThat(inventorySupplierDetails1).isEqualTo(inventorySupplierDetails2);
    inventorySupplierDetails2.setId(2L);
    assertThat(inventorySupplierDetails1)
      .isNotEqualTo(inventorySupplierDetails2);
    inventorySupplierDetails1.setId(null);
    assertThat(inventorySupplierDetails1)
      .isNotEqualTo(inventorySupplierDetails2);
  }
}
