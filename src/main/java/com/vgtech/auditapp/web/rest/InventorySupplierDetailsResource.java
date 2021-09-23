package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.InventorySupplierDetailsRepository;
import com.vgtech.auditapp.service.InventorySupplierDetailsQueryService;
import com.vgtech.auditapp.service.InventorySupplierDetailsService;
import com.vgtech.auditapp.service.criteria.InventorySupplierDetailsCriteria;
import com.vgtech.auditapp.service.dto.InventorySupplierDetails;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.InventorySupplierDetails}.
 */
@RestController
@RequestMapping("/api")
public class InventorySupplierDetailsResource {

  private final Logger log = LoggerFactory.getLogger(
    InventorySupplierDetailsResource.class
  );

  private static final String ENTITY_NAME = "inventorySupplierDetails";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final InventorySupplierDetailsService inventorySupplierDetailsService;

  private final InventorySupplierDetailsRepository inventorySupplierDetailsRepository;

  private final InventorySupplierDetailsQueryService inventorySupplierDetailsQueryService;

  public InventorySupplierDetailsResource(
    InventorySupplierDetailsService inventorySupplierDetailsService,
    InventorySupplierDetailsRepository inventorySupplierDetailsRepository,
    InventorySupplierDetailsQueryService inventorySupplierDetailsQueryService
  ) {
    this.inventorySupplierDetailsService = inventorySupplierDetailsService;
    this.inventorySupplierDetailsRepository =
      inventorySupplierDetailsRepository;
    this.inventorySupplierDetailsQueryService =
      inventorySupplierDetailsQueryService;
  }

  /**
   * {@code POST  /inventory-supplier-details} : Create a new inventorySupplierDetails.
   *
   * @param inventorySupplierDetails the inventorySupplierDetails to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventorySupplierDetails, or with status {@code 400 (Bad Request)} if the inventorySupplierDetails has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/inventory-supplier-details")
  public ResponseEntity<InventorySupplierDetails> createInventorySupplierDetails(
    @RequestBody InventorySupplierDetails inventorySupplierDetails
  ) throws URISyntaxException {
    log.debug(
      "REST request to save InventorySupplierDetails : {}",
      inventorySupplierDetails
    );
    if (inventorySupplierDetails.getId() != null) {
      throw new BadRequestAlertException(
        "A new inventorySupplierDetails cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    InventorySupplierDetails result = inventorySupplierDetailsService.save(
      inventorySupplierDetails
    );
    return ResponseEntity
      .created(new URI("/api/inventory-supplier-details/" + result.getId()))
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
   * {@code PUT  /inventory-supplier-details/:id} : Updates an existing inventorySupplierDetails.
   *
   * @param id the id of the inventorySupplierDetails to save.
   * @param inventorySupplierDetails the inventorySupplierDetails to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventorySupplierDetails,
   * or with status {@code 400 (Bad Request)} if the inventorySupplierDetails is not valid,
   * or with status {@code 500 (Internal Server Error)} if the inventorySupplierDetails couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/inventory-supplier-details/{id}")
  public ResponseEntity<InventorySupplierDetails> updateInventorySupplierDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody InventorySupplierDetails inventorySupplierDetails
  ) throws URISyntaxException {
    log.debug(
      "REST request to update InventorySupplierDetails : {}, {}",
      id,
      inventorySupplierDetails
    );
    if (inventorySupplierDetails.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, inventorySupplierDetails.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!inventorySupplierDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    InventorySupplierDetails result = inventorySupplierDetailsService.save(
      inventorySupplierDetails
    );
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          inventorySupplierDetails.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /inventory-supplier-details/:id} : Partial updates given fields of an existing inventorySupplierDetails, field will ignore if it is null
   *
   * @param id the id of the inventorySupplierDetails to save.
   * @param inventorySupplierDetails the inventorySupplierDetails to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventorySupplierDetails,
   * or with status {@code 400 (Bad Request)} if the inventorySupplierDetails is not valid,
   * or with status {@code 404 (Not Found)} if the inventorySupplierDetails is not found,
   * or with status {@code 500 (Internal Server Error)} if the inventorySupplierDetails couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/inventory-supplier-details/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<InventorySupplierDetails> partialUpdateInventorySupplierDetails(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody InventorySupplierDetails inventorySupplierDetails
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update InventorySupplierDetails partially : {}, {}",
      id,
      inventorySupplierDetails
    );
    if (inventorySupplierDetails.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, inventorySupplierDetails.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!inventorySupplierDetailsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<InventorySupplierDetails> result = inventorySupplierDetailsService.partialUpdate(
      inventorySupplierDetails
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        inventorySupplierDetails.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /inventory-supplier-details} : get all the inventorySupplierDetails.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventorySupplierDetails in body.
   */
  @GetMapping("/inventory-supplier-details")
  public ResponseEntity<List<InventorySupplierDetails>> getAllInventorySupplierDetails(
    InventorySupplierDetailsCriteria criteria,
    Pageable pageable
  ) {
    log.debug(
      "REST request to get InventorySupplierDetails by criteria: {}",
      criteria
    );
    Page<InventorySupplierDetails> page = inventorySupplierDetailsQueryService.findByCriteria(
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
   * {@code GET  /inventory-supplier-details/count} : count all the inventorySupplierDetails.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/inventory-supplier-details/count")
  public ResponseEntity<Long> countInventorySupplierDetails(
    InventorySupplierDetailsCriteria criteria
  ) {
    log.debug(
      "REST request to count InventorySupplierDetails by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(inventorySupplierDetailsQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /inventory-supplier-details/:id} : get the "id" inventorySupplierDetails.
   *
   * @param id the id of the inventorySupplierDetails to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventorySupplierDetails, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/inventory-supplier-details/{id}")
  public ResponseEntity<InventorySupplierDetails> getInventorySupplierDetails(
    @PathVariable Long id
  ) {
    log.debug("REST request to get InventorySupplierDetails : {}", id);
    Optional<InventorySupplierDetails> inventorySupplierDetails = inventorySupplierDetailsService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(inventorySupplierDetails);
  }

  /**
   * {@code DELETE  /inventory-supplier-details/:id} : delete the "id" inventorySupplierDetails.
   *
   * @param id the id of the inventorySupplierDetails to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/inventory-supplier-details/{id}")
  public ResponseEntity<Void> deleteInventorySupplierDetails(
    @PathVariable Long id
  ) {
    log.debug("REST request to delete InventorySupplierDetails : {}", id);
    inventorySupplierDetailsService.delete(id);
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
