package com.vgtech.auditapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FireElectricalAuditTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(FireElectricalAudit.class);
    FireElectricalAudit fireElectricalAudit1 = new FireElectricalAudit();
    fireElectricalAudit1.setId(1L);
    FireElectricalAudit fireElectricalAudit2 = new FireElectricalAudit();
    assertThat(fireElectricalAudit1).isNotEqualTo(fireElectricalAudit2);
    fireElectricalAudit2.setId(fireElectricalAudit1.getId());
    assertThat(fireElectricalAudit1).isEqualTo(fireElectricalAudit2);
    fireElectricalAudit2.setId(2L);
    assertThat(fireElectricalAudit1).isNotEqualTo(fireElectricalAudit2);
    fireElectricalAudit1.setId(null);
    assertThat(fireElectricalAudit1).isNotEqualTo(fireElectricalAudit2);
  }
}
