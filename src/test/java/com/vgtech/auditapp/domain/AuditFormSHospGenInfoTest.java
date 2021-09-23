package com.vgtech.auditapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AuditFormSHospGenInfoTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(AuditFormSHospGenInfo.class);
    AuditFormSHospGenInfo auditFormSHospGenInfo1 = new AuditFormSHospGenInfo();
    auditFormSHospGenInfo1.setId(1L);
    AuditFormSHospGenInfo auditFormSHospGenInfo2 = new AuditFormSHospGenInfo();
    auditFormSHospGenInfo2.setId(auditFormSHospGenInfo1.getId());
    assertThat(auditFormSHospGenInfo1).isEqualTo(auditFormSHospGenInfo2);
    auditFormSHospGenInfo2.setId(2L);
    assertThat(auditFormSHospGenInfo1).isNotEqualTo(auditFormSHospGenInfo2);
    auditFormSHospGenInfo1.setId(null);
    assertThat(auditFormSHospGenInfo1).isNotEqualTo(auditFormSHospGenInfo2);
  }
}
