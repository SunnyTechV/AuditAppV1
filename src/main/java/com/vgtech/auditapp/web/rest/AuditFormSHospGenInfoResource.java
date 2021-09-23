package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.AuditFormSHospGenInfoRepository;
import com.vgtech.auditapp.service.AuditFormSHospGenInfoQueryService;
import com.vgtech.auditapp.service.AuditFormSHospGenInfoService;
import com.vgtech.auditapp.service.criteria.AuditFormSHospGenInfoCriteria;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfo;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.AuditFormSHospGenInfo}.
 */
@RestController
@RequestMapping("/api")
public class AuditFormSHospGenInfoResource {

  private final Logger log = LoggerFactory.getLogger(
    AuditFormSHospGenInfoResource.class
  );

  private static final String ENTITY_NAME = "auditFormSHospGenInfo";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AuditFormSHospGenInfoService auditFormSHospGenInfoService;

  private final AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository;

  private final AuditFormSHospGenInfoQueryService auditFormSHospGenInfoQueryService;

  public AuditFormSHospGenInfoResource(
    AuditFormSHospGenInfoService auditFormSHospGenInfoService,
    AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository,
    AuditFormSHospGenInfoQueryService auditFormSHospGenInfoQueryService
  ) {
    this.auditFormSHospGenInfoService = auditFormSHospGenInfoService;
    this.auditFormSHospGenInfoRepository = auditFormSHospGenInfoRepository;
    this.auditFormSHospGenInfoQueryService = auditFormSHospGenInfoQueryService;
  }

  /**
   * {@code POST  /audit-form-s-hosp-gen-infos} : Create a new auditFormSHospGenInfo.
   *
   * @param auditFormSHospGenInfo the auditFormSHospGenInfo to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditFormSHospGenInfo, or with status {@code 400 (Bad Request)} if the auditFormSHospGenInfo has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/audit-form-s-hosp-gen-infos")
  public ResponseEntity<AuditFormSHospGenInfo> createAuditFormSHospGenInfo(
    @RequestBody AuditFormSHospGenInfo auditFormSHospGenInfo
  ) throws URISyntaxException {
    log.debug(
      "REST request to save AuditFormSHospGenInfo : {}",
      auditFormSHospGenInfo
    );
    if (auditFormSHospGenInfo.getId() != null) {
      throw new BadRequestAlertException(
        "A new auditFormSHospGenInfo cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    AuditFormSHospGenInfo result = auditFormSHospGenInfoService.save(
      auditFormSHospGenInfo
    );
    return ResponseEntity
      .created(new URI("/api/audit-form-s-hosp-gen-infos/" + result.getId()))
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
   * {@code PUT  /audit-form-s-hosp-gen-infos/:id} : Updates an existing auditFormSHospGenInfo.
   *
   * @param id the id of the auditFormSHospGenInfo to save.
   * @param auditFormSHospGenInfo the auditFormSHospGenInfo to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditFormSHospGenInfo,
   * or with status {@code 400 (Bad Request)} if the auditFormSHospGenInfo is not valid,
   * or with status {@code 500 (Internal Server Error)} if the auditFormSHospGenInfo couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/audit-form-s-hosp-gen-infos/{id}")
  public ResponseEntity<AuditFormSHospGenInfo> updateAuditFormSHospGenInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AuditFormSHospGenInfo auditFormSHospGenInfo
  ) throws URISyntaxException {
    log.debug(
      "REST request to update AuditFormSHospGenInfo : {}, {}",
      id,
      auditFormSHospGenInfo
    );
    if (auditFormSHospGenInfo.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, auditFormSHospGenInfo.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!auditFormSHospGenInfoRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    AuditFormSHospGenInfo result = auditFormSHospGenInfoService.save(
      auditFormSHospGenInfo
    );
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          auditFormSHospGenInfo.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /audit-form-s-hosp-gen-infos/:id} : Partial updates given fields of an existing auditFormSHospGenInfo, field will ignore if it is null
   *
   * @param id the id of the auditFormSHospGenInfo to save.
   * @param auditFormSHospGenInfo the auditFormSHospGenInfo to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditFormSHospGenInfo,
   * or with status {@code 400 (Bad Request)} if the auditFormSHospGenInfo is not valid,
   * or with status {@code 404 (Not Found)} if the auditFormSHospGenInfo is not found,
   * or with status {@code 500 (Internal Server Error)} if the auditFormSHospGenInfo couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/audit-form-s-hosp-gen-infos/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<AuditFormSHospGenInfo> partialUpdateAuditFormSHospGenInfo(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AuditFormSHospGenInfo auditFormSHospGenInfo
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update AuditFormSHospGenInfo partially : {}, {}",
      id,
      auditFormSHospGenInfo
    );
    if (auditFormSHospGenInfo.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, auditFormSHospGenInfo.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!auditFormSHospGenInfoRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<AuditFormSHospGenInfo> result = auditFormSHospGenInfoService.partialUpdate(
      auditFormSHospGenInfo
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        auditFormSHospGenInfo.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /audit-form-s-hosp-gen-infos} : get all the auditFormSHospGenInfos.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditFormSHospGenInfos in body.
   */
  @GetMapping("/audit-form-s-hosp-gen-infos")
  public ResponseEntity<List<AuditFormSHospGenInfo>> getAllAuditFormSHospGenInfos(
    AuditFormSHospGenInfoCriteria criteria,
    Pageable pageable
  ) {
    log.debug(
      "REST request to get AuditFormSHospGenInfos by criteria: {}",
      criteria
    );
    Page<AuditFormSHospGenInfo> page = auditFormSHospGenInfoQueryService.findByCriteria(
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
   * {@code GET  /audit-form-s-hosp-gen-infos/count} : count all the auditFormSHospGenInfos.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/audit-form-s-hosp-gen-infos/count")
  public ResponseEntity<Long> countAuditFormSHospGenInfos(
    AuditFormSHospGenInfoCriteria criteria
  ) {
    log.debug(
      "REST request to count AuditFormSHospGenInfos by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(auditFormSHospGenInfoQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /audit-form-s-hosp-gen-infos/:id} : get the "id" auditFormSHospGenInfo.
   *
   * @param id the id of the auditFormSHospGenInfo to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditFormSHospGenInfo, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/audit-form-s-hosp-gen-infos/{id}")
  public ResponseEntity<AuditFormSHospGenInfo> getAuditFormSHospGenInfo(
    @PathVariable Long id
  ) {
    log.debug("REST request to get AuditFormSHospGenInfo : {}", id);
    Optional<AuditFormSHospGenInfo> auditFormSHospGenInfo = auditFormSHospGenInfoService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(auditFormSHospGenInfo);
  }

  /**
   * {@code DELETE  /audit-form-s-hosp-gen-infos/:id} : delete the "id" auditFormSHospGenInfo.
   *
   * @param id the id of the auditFormSHospGenInfo to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/audit-form-s-hosp-gen-infos/{id}")
  public ResponseEntity<Void> deleteAuditFormSHospGenInfo(
    @PathVariable Long id
  ) {
    log.debug("REST request to delete AuditFormSHospGenInfo : {}", id);
    auditFormSHospGenInfoService.delete(id);
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
