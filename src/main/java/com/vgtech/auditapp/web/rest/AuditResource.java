package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.AuditRepository;
import com.vgtech.auditapp.service.AuditQueryService;
import com.vgtech.auditapp.service.AuditService;
import com.vgtech.auditapp.service.criteria.AuditCriteria;
import com.vgtech.auditapp.service.dto.Audit;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.Audit}.
 */
@RestController
@RequestMapping("/api")
public class AuditResource {

  private final Logger log = LoggerFactory.getLogger(AuditResource.class);

  private static final String ENTITY_NAME = "audit";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AuditService auditService;

  private final AuditRepository auditRepository;

  private final AuditQueryService auditQueryService;

  public AuditResource(
    AuditService auditService,
    AuditRepository auditRepository,
    AuditQueryService auditQueryService
  ) {
    this.auditService = auditService;
    this.auditRepository = auditRepository;
    this.auditQueryService = auditQueryService;
  }

  /**
   * {@code POST  /audits} : Create a new audit.
   *
   * @param audit the audit to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new audit, or with status {@code 400 (Bad Request)} if the audit has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/audits")
  public ResponseEntity<Audit> createAudit(@RequestBody Audit audit)
    throws URISyntaxException {
    log.debug("REST request to save Audit : {}", audit);
    if (audit.getId() != null) {
      throw new BadRequestAlertException(
        "A new audit cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    Audit result = auditService.save(audit);
    return ResponseEntity
      .created(new URI("/api/audits/" + result.getId()))
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
   * {@code PUT  /audits/:id} : Updates an existing audit.
   *
   * @param id the id of the audit to save.
   * @param audit the audit to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated audit,
   * or with status {@code 400 (Bad Request)} if the audit is not valid,
   * or with status {@code 500 (Internal Server Error)} if the audit couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/audits/{id}")
  public ResponseEntity<Audit> updateAudit(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody Audit audit
  ) throws URISyntaxException {
    log.debug("REST request to update Audit : {}, {}", id, audit);
    if (audit.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, audit.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!auditRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Audit result = auditService.save(audit);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          audit.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /audits/:id} : Partial updates given fields of an existing audit, field will ignore if it is null
   *
   * @param id the id of the audit to save.
   * @param audit the audit to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated audit,
   * or with status {@code 400 (Bad Request)} if the audit is not valid,
   * or with status {@code 404 (Not Found)} if the audit is not found,
   * or with status {@code 500 (Internal Server Error)} if the audit couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/audits/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<Audit> partialUpdateAudit(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody Audit audit
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update Audit partially : {}, {}",
      id,
      audit
    );
    if (audit.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, audit.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!auditRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<Audit> result = auditService.partialUpdate(audit);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        audit.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /audits} : get all the audits.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of audits in body.
   */
  @GetMapping("/audits")
  public ResponseEntity<List<Audit>> getAllAudits(
    AuditCriteria criteria,
    Pageable pageable
  ) {
    log.debug("REST request to get Audits by criteria: {}", criteria);
    Page<Audit> page = auditQueryService.findByCriteria(criteria, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(
      ServletUriComponentsBuilder.fromCurrentRequest(),
      page
    );
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /audits/count} : count all the audits.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/audits/count")
  public ResponseEntity<Long> countAudits(AuditCriteria criteria) {
    log.debug("REST request to count Audits by criteria: {}", criteria);
    return ResponseEntity
      .ok()
      .body(auditQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /audits/:id} : get the "id" audit.
   *
   * @param id the id of the audit to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the audit, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/audits/{id}")
  public ResponseEntity<Audit> getAudit(@PathVariable Long id) {
    log.debug("REST request to get Audit : {}", id);
    Optional<Audit> audit = auditService.findOne(id);
    return ResponseUtil.wrapOrNotFound(audit);
  }

  /**
   * {@code DELETE  /audits/:id} : delete the "id" audit.
   *
   * @param id the id of the audit to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/audits/{id}")
  public ResponseEntity<Void> deleteAudit(@PathVariable Long id) {
    log.debug("REST request to delete Audit : {}", id);
    auditService.delete(id);
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
