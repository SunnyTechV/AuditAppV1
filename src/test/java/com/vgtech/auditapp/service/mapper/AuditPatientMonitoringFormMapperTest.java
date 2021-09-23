package com.vgtech.auditapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditPatientMonitoringFormMapperTest {

  private AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper;

  @BeforeEach
  public void setUp() {
    auditPatientMonitoringFormMapper =
      new AuditPatientMonitoringFormMapperImpl();
  }
}
