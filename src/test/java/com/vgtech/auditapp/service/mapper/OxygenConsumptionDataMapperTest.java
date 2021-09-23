package com.vgtech.auditapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OxygenConsumptionDataMapperTest {

  private OxygenConsumptionDataMapper oxygenConsumptionDataMapper;

  @BeforeEach
  public void setUp() {
    oxygenConsumptionDataMapper = new OxygenConsumptionDataMapperImpl();
  }
}
