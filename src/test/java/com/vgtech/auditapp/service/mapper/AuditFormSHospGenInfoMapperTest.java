package com.vgtech.auditapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuditFormSHospGenInfoMapperTest {

  private AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper;

  @BeforeEach
  public void setUp() {
    auditFormSHospGenInfoMapper = new AuditFormSHospGenInfoMapperImpl();
  }
}
