package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.TableDetailsRepository;
import com.vgtech.auditapp.service.TableDetailsQueryService;
import com.vgtech.auditapp.service.TableDetailsService;
import com.vgtech.auditapp.service.criteria.TableDetailsCriteria;
import com.vgtech.auditapp.service.dto.TableDetails;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.TableDetails}.
 */
@RestController
@RequestMapping("/api")
public class TableDetailsResource {

  private final Logger log = LoggerFactory.getLogger(
    TableDetailsResource.class
  );

  private static final String ENTITY_NAME = "tableDetails";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final TableDetailsService tableDetailsService;

  private final TableDetailsRepository tableDetailsRepository;

  private final TableDetailsQueryService tableDetailsQueryService;

  public TableDetailsResource(
    TableDetailsService tableDetailsService,
    TableDetailsRepository tableDetailsRepository,
    TableDetailsQueryService tableDetailsQueryService
  ) {
    this.tableDetailsService = tableDetailsService;
    this.tableDetailsRepository = tableDetailsRepository;
    this.tableDetailsQueryService = tableDetailsQueryService;
  }

  /**
   * {@code POST  /table-details} : Create a new tableDetails.
   *
   * @param tableDetails the tableDetails to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tableDetails, or with status {@code 400 (Bad Request)} if the tableDetails has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/table-details")
  public ResponseEntity<TableDetails> createTableDetails(
    @RequestBody TableDetails tableDetails
  ) throws URISyntaxException {
    log.debug("REST request to save TableDetails : {}", tableDetails);
    if (tableDetails.getId() != null) {
      throw new BadRequestAlertException(
        "A new tableDetails cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    TableDetails result = tableDetailsService.save(tableDetails);
    return ResponseEntity
      .created(new URI("/api/table-details/" + result.getId()))
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
   * {@code PUT  /table-details/:id} : Updates an existing tableDetails.
   *
   * @param id the id of the tableDetails to save.
   * @param tableDetails the tableDetails to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableDetails,
   * or with status {@code 400 (Bad Request)} if the tableDetails is not valid,
   * or with status {@code 500 (Internal Server Error)} if the tableDetails couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/table-details/{id}")
  public ResponseEntity<TableDetails> updateTableDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TableDetails tableDetails
  ) throws URISyntaxException {
    log.debug("REST request to update TableDetails : {}, {}", id, tableDetails);
    if (tableDetails.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, tableDetails.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!tableDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    TableDetails result = tableDetailsService.save(tableDetails);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          tableDetails.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /table-details/:id} : Partial updates given fields of an existing tableDetails, field will ignore if it is null
   *
   * @param id the id of the tableDetails to save.
   * @param tableDetails the tableDetails to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tableDetails,
   * or with status {@code 400 (Bad Request)} if the tableDetails is not valid,
   * or with status {@code 404 (Not Found)} if the tableDetails is not found,
   * or with status {@code 500 (Internal Server Error)} if the tableDetails couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/table-details/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<TableDetails> partialUpdateTableDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody TableDetails tableDetails
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update TableDetails partially : {}, {}",
      id,
      tableDetails
    );
    if (tableDetails.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, tableDetails.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!tableDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<TableDetails> result = tableDetailsService.partialUpdate(
      tableDetails
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        tableDetails.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /table-details} : get all the tableDetails.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tableDetails in body.
   */
  @GetMapping("/table-details")
  public ResponseEntity<List<TableDetails>> getAllTableDetails(
    TableDetailsCriteria criteria,
    Pageable pageable
  ) {
    log.debug("REST request to get TableDetails by criteria: {}", criteria);
    Page<TableDetails> page = tableDetailsQueryService.findByCriteria(
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
   * {@code GET  /table-details/count} : count all the tableDetails.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/table-details/count")
  public ResponseEntity<Long> countTableDetails(TableDetailsCriteria criteria) {
    log.debug("REST request to count TableDetails by criteria: {}", criteria);
    return ResponseEntity
      .ok()
      .body(tableDetailsQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /table-details/:id} : get the "id" tableDetails.
   *
   * @param id the id of the tableDetails to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tableDetails, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/table-details/{id}")
  public ResponseEntity<TableDetails> getTableDetails(@PathVariable Long id) {
    log.debug("REST request to get TableDetails : {}", id);
    Optional<TableDetails> tableDetails = tableDetailsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(tableDetails);
  }

  /**
   * {@code DELETE  /table-details/:id} : delete the "id" tableDetails.
   *
   * @param id the id of the tableDetails to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/table-details/{id}")
  public ResponseEntity<Void> deleteTableDetails(@PathVariable Long id) {
    log.debug("REST request to delete TableDetails : {}", id);
    tableDetailsService.delete(id);
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
