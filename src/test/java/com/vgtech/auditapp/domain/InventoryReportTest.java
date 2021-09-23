package com.vgtech.auditapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventoryReportTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(InventoryReport.class);
    InventoryReport inventoryReport1 = new InventoryReport();
    inventoryReport1.setId(1L);
    InventoryReport inventoryReport2 = new InventoryReport();
    inventoryReport2.setId(inventoryReport1.getId());
    assertThat(inventoryReport1).isEqualTo(inventoryReport2);
    inventoryReport2.setId(2L);
    assertThat(inventoryReport1).isNotEqualTo(inventoryReport2);
    inventoryReport1.setId(null);
    assertThat(inventoryReport1).isNotEqualTo(inventoryReport2);
  }
}
