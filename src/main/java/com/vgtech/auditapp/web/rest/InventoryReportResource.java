package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.InventoryReportRepository;
import com.vgtech.auditapp.service.InventoryReportQueryService;
import com.vgtech.auditapp.service.InventoryReportService;
import com.vgtech.auditapp.service.criteria.InventoryReportCriteria;
import com.vgtech.auditapp.service.dto.InventoryReport;
import com.vgtech.auditapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.vgtech.auditapp.domain.InventoryReport}.
 */
@RestController
@RequestMapping("/api")
public class InventoryReportResource {

  private final Logger log = LoggerFactory.getLogger(
    InventoryReportResource.class
  );

  private static final String ENTITY_NAME = "inventoryReport";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final InventoryReportService inventoryReportService;

  private final InventoryReportRepository inventoryReportRepository;

  private final InventoryReportQueryService inventoryReportQueryService;

  public InventoryReportResource(
    InventoryReportService inventoryReportService,
    InventoryReportRepository inventoryReportRepository,
    InventoryReportQueryService inventoryReportQueryService
  ) {
    this.inventoryReportService = inventoryReportService;
    this.inventoryReportRepository = inventoryReportRepository;
    this.inventoryReportQueryService = inventoryReportQueryService;
  }

  /**
   * {@code POST  /inventory-reports} : Create a new inventoryReport.
   *
   * @param inventoryReport the inventoryReport to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventoryReport, or with status {@code 400 (Bad Request)} if the inventoryReport has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/inventory-reports")
  public ResponseEntity<InventoryReport> createInventoryReport(
    @RequestBody InventoryReport inventoryReport
  ) throws URISyntaxException {
    log.debug("REST request to save InventoryReport : {}", inventoryReport);
    if (inventoryReport.getId() != null) {
      throw new BadRequestAlertException(
        "A new inventoryReport cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    InventoryReport result = inventoryReportService.save(inventoryReport);
    return ResponseEntity
      .created(new URI("/api/inventory-reports/" + result.getId()))
      .headers(
        HeaderUtil.createEntityCreationAlert(
          applicationName,
          true,
          ENTITY_NAME,
          result.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PUT  /inventory-reports/:id} : Updates an existing inventoryReport.
   *
   * @param id the id of the inventoryReport to save.
   * @param inventoryReport the inventoryReport to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryReport,
   * or with status {@code 400 (Bad Request)} if the inventoryReport is not valid,
   * or with status {@code 500 (Internal Server Error)} if the inventoryReport couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/inventory-reports/{id}")
  public ResponseEntity<InventoryReport> updateInventoryReport(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody InventoryReport inventoryReport
  ) throws URISyntaxException {
    log.debug(
      "REST request to update InventoryReport : {}, {}",
      id,
      inventoryReport
    );
    if (inventoryReport.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, inventoryReport.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!inventoryReportRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    InventoryReport result = inventoryReportService.save(inventoryReport);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          inventoryReport.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /inventory-reports/:id} : Partial updates given fields of an existing inventoryReport, field will ignore if it is null
   *
   * @param id the id of the inventoryReport to save.
   * @param inventoryReport the inventoryReport to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventoryReport,
   * or with status {@code 400 (Bad Request)} if the inventoryReport is not valid,
   * or with status {@code 404 (Not Found)} if the inventoryReport is not found,
   * or with status {@code 500 (Internal Server Error)} if the inventoryReport couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/inventory-reports/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<InventoryReport> partialUpdateInventoryReport(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody InventoryReport inventoryReport
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update InventoryReport partially : {}, {}",
      id,
      inventoryReport
    );
    if (inventoryReport.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, inventoryReport.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!inventoryReportRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<InventoryReport> result = inventoryReportService.partialUpdate(
      inventoryReport
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        inventoryReport.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /inventory-reports} : get all the inventoryReports.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventoryReports in body.
   */
  @GetMapping("/inventory-reports")
  public ResponseEntity<List<InventoryReport>> getAllInventoryReports(
    InventoryReportCriteria criteria,
    Pageable pageable
  ) {
    log.debug("REST request to get InventoryReports by criteria: {}", criteria);
    Page<InventoryReport> page = inventoryReportQueryService.findByCriteria(
      criteria,
      pageable
    );
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /inventory-reports/count} : count all the inventoryReports.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/inventory-reports/count")
  public ResponseEntity<Long> countInventoryReports(
    InventoryReportCriteria criteria
  ) {
    log.debug(
      "REST request to count InventoryReports by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(inventoryReportQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /inventory-reports/:id} : get the "id" inventoryReport.
   *
   * @param id the id of the inventoryReport to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventoryReport, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/inventory-reports/{id}")
  public ResponseEntity<InventoryReport> getInventoryReport(
    @PathVariable Long id
  ) {
    log.debug("REST request to get InventoryReport : {}", id);
    Optional<InventoryReport> inventoryReport = inventoryReportService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(inventoryReport);
  }

  /**
   * {@code DELETE  /inventory-reports/:id} : delete the "id" inventoryReport.
   *
   * @param id the id of the inventoryReport to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/inventory-reports/{id}")
  public ResponseEntity<Void> deleteInventoryReport(@PathVariable Long id) {
    log.debug("REST request to delete InventoryReport : {}", id);
    inventoryReportService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(
        HeaderUtil.createEntityDeletionAlert(
          applicationName,
          true,
          ENTITY_NAME,
          id.toString()
        )
      )
      .build();
  }
}
