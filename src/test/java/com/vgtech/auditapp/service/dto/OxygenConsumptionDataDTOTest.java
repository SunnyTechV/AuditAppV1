package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OxygenConsumptionDataTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(OxygenConsumptionData.class);
    OxygenConsumptionData oxygenConsumptionData1 = new OxygenConsumptionData();
    oxygenConsumptionData1.setId(1L);
    OxygenConsumptionData oxygenConsumptionData2 = new OxygenConsumptionData();
    assertThat(oxygenConsumptionData1).isNotEqualTo(oxygenConsumptionData2);
    oxygenConsumptionData2.setId(oxygenConsumptionData1.getId());
    assertThat(oxygenConsumptionData1).isEqualTo(oxygenConsumptionData2);
    oxygenConsumptionData2.setId(2L);
    assertThat(oxygenConsumptionData1).isNotEqualTo(oxygenConsumptionData2);
    oxygenConsumptionData1.setId(null);
    assertThat(oxygenConsumptionData1).isNotEqualTo(oxygenConsumptionData2);
  }
}
