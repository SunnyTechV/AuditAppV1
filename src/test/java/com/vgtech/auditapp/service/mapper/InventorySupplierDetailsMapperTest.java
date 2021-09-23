package com.vgtech.auditapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InventorySupplierDetailsMapperTest {

  private InventorySupplierDetailsMapper inventorySupplierDetailsMapper;

  @BeforeEach
  public void setUp() {
    inventorySupplierDetailsMapper = new InventorySupplierDetailsMapperImpl();
  }
}
