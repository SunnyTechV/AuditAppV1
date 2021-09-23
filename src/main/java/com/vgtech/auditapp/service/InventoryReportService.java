package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.InventoryReport;
import com.vgtech.auditapp.repository.InventoryReportRepository;
import com.vgtech.auditapp.service.dto.InventoryReport;
import com.vgtech.auditapp.service.mapper.InventoryReportMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InventoryReport}.
 */
@Service
@Transactional
public class InventoryReportService {

  private final Logger log = LoggerFactory.getLogger(
    InventoryReportService.class
  );

  private final InventoryReportRepository inventoryReportRepository;

  private final InventoryReportMapper inventoryReportMapper;

  public InventoryReportService(
    InventoryReportRepository inventoryReportRepository,
    InventoryReportMapper inventoryReportMapper
  ) {
    this.inventoryReportRepository = inventoryReportRepository;
    this.inventoryReportMapper = inventoryReportMapper;
  }

  /**
   * Save a inventoryReport.
   *
   * @param inventoryReport the entity to save.
   * @return the persisted entity.
   */
  public InventoryReport save(InventoryReport inventoryReport) {
    log.debug("Request to save InventoryReport : {}", inventoryReport);
    InventoryReport inventoryReport = inventoryReportMapper.toEntity(
      inventoryReport
    );
    inventoryReport = inventoryReportRepository.save(inventoryReport);
    return inventoryReportMapper.toDto(inventoryReport);
  }

  /**
   * Partially update a inventoryReport.
   *
   * @param inventoryReport the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<InventoryReport> partialUpdate(
    InventoryReport inventoryReport
  ) {
    log.debug(
      "Request to partially update InventoryReport : {}",
      inventoryReport
    );

    return inventoryReportRepository
      .findById(inventoryReport.getId())
      .map(existingInventoryReport -> {
        inventoryReportMapper.partialUpdate(
          existingInventoryReport,
          inventoryReport
        );

        return existingInventoryReport;
      })
      .map(inventoryReportRepository::save)
      .map(inventoryReportMapper::toDto);
  }

  /**
   * Get all the inventoryReports.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<InventoryReport> findAll(Pageable pageable) {
    log.debug("Request to get all InventoryReports");
    return inventoryReportRepository
      .findAll(pageable)
      .map(inventoryReportMapper::toDto);
  }

  /**
   * Get one inventoryReport by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<InventoryReport> findOne(Long id) {
    log.debug("Request to get InventoryReport : {}", id);
    return inventoryReportRepository
      .findById(id)
      .map(inventoryReportMapper::toDto);
  }

  /**
   * Delete the inventoryReport by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete InventoryReport : {}", id);
    inventoryReportRepository.deleteById(id);
  }
}
