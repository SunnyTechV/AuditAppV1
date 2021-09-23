package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.FireElectricalAuditRepository;
import com.vgtech.auditapp.service.FireElectricalAuditQueryService;
import com.vgtech.auditapp.service.FireElectricalAuditService;
import com.vgtech.auditapp.service.criteria.FireElectricalAuditCriteria;
import com.vgtech.auditapp.service.dto.FireElectricalAudit;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.FireElectricalAudit}.
 */
@RestController
@RequestMapping("/api")
public class FireElectricalAuditResource {

  private final Logger log = LoggerFactory.getLogger(
    FireElectricalAuditResource.class
  );

  private static final String ENTITY_NAME = "fireElectricalAudit";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final FireElectricalAuditService fireElectricalAuditService;

  private final FireElectricalAuditRepository fireElectricalAuditRepository;

  private final FireElectricalAuditQueryService fireElectricalAuditQueryService;

  public FireElectricalAuditResource(
    FireElectricalAuditService fireElectricalAuditService,
    FireElectricalAuditRepository fireElectricalAuditRepository,
    FireElectricalAuditQueryService fireElectricalAuditQueryService
  ) {
    this.fireElectricalAuditService = fireElectricalAuditService;
    this.fireElectricalAuditRepository = fireElectricalAuditRepository;
    this.fireElectricalAuditQueryService = fireElectricalAuditQueryService;
  }

  /**
   * {@code POST  /fire-electrical-audits} : Create a new fireElectricalAudit.
   *
   * @param fireElectricalAudit the fireElectricalAudit to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fireElectricalAudit, or with status {@code 400 (Bad Request)} if the fireElectricalAudit has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/fire-electrical-audits")
  public ResponseEntity<FireElectricalAudit> createFireElectricalAudit(
    @RequestBody FireElectricalAudit fireElectricalAudit
  ) throws URISyntaxException {
    log.debug(
      "REST request to save FireElectricalAudit : {}",
      fireElectricalAudit
    );
    if (fireElectricalAudit.getId() != null) {
      throw new BadRequestAlertException(
        "A new fireElectricalAudit cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    FireElectricalAudit result = fireElectricalAuditService.save(
      fireElectricalAudit
    );
    return ResponseEntity
      .created(new URI("/api/fire-electrical-audits/" + result.getId()))
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
   * {@code PUT  /fire-electrical-audits/:id} : Updates an existing fireElectricalAudit.
   *
   * @param id the id of the fireElectricalAudit to save.
   * @param fireElectricalAudit the fireElectricalAudit to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fireElectricalAudit,
   * or with status {@code 400 (Bad Request)} if the fireElectricalAudit is not valid,
   * or with status {@code 500 (Internal Server Error)} if the fireElectricalAudit couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/fire-electrical-audits/{id}")
  public ResponseEntity<FireElectricalAudit> updateFireElectricalAudit(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody FireElectricalAudit fireElectricalAudit
  ) throws URISyntaxException {
    log.debug(
      "REST request to update FireElectricalAudit : {}, {}",
      id,
      fireElectricalAudit
    );
    if (fireElectricalAudit.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, fireElectricalAudit.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!fireElectricalAuditRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    FireElectricalAudit result = fireElectricalAuditService.save(
      fireElectricalAudit
    );
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          fireElectricalAudit.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /fire-electrical-audits/:id} : Partial updates given fields of an existing fireElectricalAudit, field will ignore if it is null
   *
   * @param id the id of the fireElectricalAudit to save.
   * @param fireElectricalAudit the fireElectricalAudit to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fireElectricalAudit,
   * or with status {@code 400 (Bad Request)} if the fireElectricalAudit is not valid,
   * or with status {@code 404 (Not Found)} if the fireElectricalAudit is not found,
   * or with status {@code 500 (Internal Server Error)} if the fireElectricalAudit couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/fire-electrical-audits/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<FireElectricalAudit> partialUpdateFireElectricalAudit(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody FireElectricalAudit fireElectricalAudit
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update FireElectricalAudit partially : {}, {}",
      id,
      fireElectricalAudit
    );
    if (fireElectricalAudit.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, fireElectricalAudit.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!fireElectricalAuditRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<FireElectricalAudit> result = fireElectricalAuditService.partialUpdate(
      fireElectricalAudit
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        fireElectricalAudit.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /fire-electrical-audits} : get all the fireElectricalAudits.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fireElectricalAudits in body.
   */
  @GetMapping("/fire-electrical-audits")
  public ResponseEntity<List<FireElectricalAudit>> getAllFireElectricalAudits(
    FireElectricalAuditCriteria criteria,
    Pageable pageable
  ) {
    log.debug(
      "REST request to get FireElectricalAudits by criteria: {}",
      criteria
    );
    Page<FireElectricalAudit> page = fireElectricalAuditQueryService.findByCriteria(
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
   * {@code GET  /fire-electrical-audits/count} : count all the fireElectricalAudits.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/fire-electrical-audits/count")
  public ResponseEntity<Long> countFireElectricalAudits(
    FireElectricalAuditCriteria criteria
  ) {
    log.debug(
      "REST request to count FireElectricalAudits by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(fireElectricalAuditQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /fire-electrical-audits/:id} : get the "id" fireElectricalAudit.
   *
   * @param id the id of the fireElectricalAudit to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fireElectricalAudit, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/fire-electrical-audits/{id}")
  public ResponseEntity<FireElectricalAudit> getFireElectricalAudit(
    @PathVariable Long id
  ) {
    log.debug("REST request to get FireElectricalAudit : {}", id);
    Optional<FireElectricalAudit> fireElectricalAudit = fireElectricalAuditService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(fireElectricalAudit);
  }

  /**
   * {@code DELETE  /fire-electrical-audits/:id} : delete the "id" fireElectricalAudit.
   *
   * @param id the id of the fireElectricalAudit to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/fire-electrical-audits/{id}")
  public ResponseEntity<Void> deleteFireElectricalAudit(@PathVariable Long id) {
    log.debug("REST request to delete FireElectricalAudit : {}", id);
    fireElectricalAuditService.delete(id);
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
