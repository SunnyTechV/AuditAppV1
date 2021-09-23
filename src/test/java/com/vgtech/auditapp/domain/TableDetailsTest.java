package com.vgtech.auditapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TableDetailsTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(TableDetails.class);
    TableDetails tableDetails1 = new TableDetails();
    tableDetails1.setId(1L);
    TableDetails tableDetails2 = new TableDetails();
    tableDetails2.setId(tableDetails1.getId());
    assertThat(tableDetails1).isEqualTo(tableDetails2);
    tableDetails2.setId(2L);
    assertThat(tableDetails1).isNotEqualTo(tableDetails2);
    tableDetails1.setId(null);
    assertThat(tableDetails1).isNotEqualTo(tableDetails2);
  }
}
