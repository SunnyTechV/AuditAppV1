package com.vgtech.auditapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TableDetailsMapperTest {

  private TableDetailsMapper tableDetailsMapper;

  @BeforeEach
  public void setUp() {
    tableDetailsMapper = new TableDetailsMapperImpl();
  }
}
