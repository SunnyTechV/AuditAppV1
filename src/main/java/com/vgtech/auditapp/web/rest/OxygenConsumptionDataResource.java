package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.OxygenConsumptionDataRepository;
import com.vgtech.auditapp.service.OxygenConsumptionDataQueryService;
import com.vgtech.auditapp.service.OxygenConsumptionDataService;
import com.vgtech.auditapp.service.criteria.OxygenConsumptionDataCriteria;
import com.vgtech.auditapp.service.dto.OxygenConsumptionData;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.OxygenConsumptionData}.
 */
@RestController
@RequestMapping("/api")
public class OxygenConsumptionDataResource {

  private final Logger log = LoggerFactory.getLogger(
    OxygenConsumptionDataResource.class
  );

  private static final String ENTITY_NAME = "oxygenConsumptionData";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final OxygenConsumptionDataService oxygenConsumptionDataService;

  private final OxygenConsumptionDataRepository oxygenConsumptionDataRepository;

  private final OxygenConsumptionDataQueryService oxygenConsumptionDataQueryService;

  public OxygenConsumptionDataResource(
    OxygenConsumptionDataService oxygenConsumptionDataService,
    OxygenConsumptionDataRepository oxygenConsumptionDataRepository,
    OxygenConsumptionDataQueryService oxygenConsumptionDataQueryService
  ) {
    this.oxygenConsumptionDataService = oxygenConsumptionDataService;
    this.oxygenConsumptionDataRepository = oxygenConsumptionDataRepository;
    this.oxygenConsumptionDataQueryService = oxygenConsumptionDataQueryService;
  }

  /**
   * {@code POST  /oxygen-consumption-data} : Create a new oxygenConsumptionData.
   *
   * @param oxygenConsumptionData the oxygenConsumptionData to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new oxygenConsumptionData, or with status {@code 400 (Bad Request)} if the oxygenConsumptionData has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/oxygen-consumption-data")
  public ResponseEntity<OxygenConsumptionData> createOxygenConsumptionData(
    @RequestBody OxygenConsumptionData oxygenConsumptionData
  ) throws URISyntaxException {
    log.debug(
      "REST request to save OxygenConsumptionData : {}",
      oxygenConsumptionData
    );
    if (oxygenConsumptionData.getId() != null) {
      throw new BadRequestAlertException(
        "A new oxygenConsumptionData cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    OxygenConsumptionData result = oxygenConsumptionDataService.save(
      oxygenConsumptionData
    );
    return ResponseEntity
      .created(new URI("/api/oxygen-consumption-data/" + result.getId()))
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
   * {@code PUT  /oxygen-consumption-data/:id} : Updates an existing oxygenConsumptionData.
   *
   * @param id the id of the oxygenConsumptionData to save.
   * @param oxygenConsumptionData the oxygenConsumptionData to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oxygenConsumptionData,
   * or with status {@code 400 (Bad Request)} if the oxygenConsumptionData is not valid,
   * or with status {@code 500 (Internal Server Error)} if the oxygenConsumptionData couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/oxygen-consumption-data/{id}")
  public ResponseEntity<OxygenConsumptionData> updateOxygenConsumptionData(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody OxygenConsumptionData oxygenConsumptionData
  ) throws URISyntaxException {
    log.debug(
      "REST request to update OxygenConsumptionData : {}, {}",
      id,
      oxygenConsumptionData
    );
    if (oxygenConsumptionData.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, oxygenConsumptionData.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!oxygenConsumptionDataRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    OxygenConsumptionData result = oxygenConsumptionDataService.save(
      oxygenConsumptionData
    );
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          oxygenConsumptionData.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /oxygen-consumption-data/:id} : Partial updates given fields of an existing oxygenConsumptionData, field will ignore if it is null
   *
   * @param id the id of the oxygenConsumptionData to save.
   * @param oxygenConsumptionData the oxygenConsumptionData to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated oxygenConsumptionData,
   * or with status {@code 400 (Bad Request)} if the oxygenConsumptionData is not valid,
   * or with status {@code 404 (Not Found)} if the oxygenConsumptionData is not found,
   * or with status {@code 500 (Internal Server Error)} if the oxygenConsumptionData couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/oxygen-consumption-data/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<OxygenConsumptionData> partialUpdateOxygenConsumptionData(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody OxygenConsumptionData oxygenConsumptionData
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update OxygenConsumptionData partially : {}, {}",
      id,
      oxygenConsumptionData
    );
    if (oxygenConsumptionData.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, oxygenConsumptionData.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!oxygenConsumptionDataRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<OxygenConsumptionData> result = oxygenConsumptionDataService.partialUpdate(
      oxygenConsumptionData
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        oxygenConsumptionData.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /oxygen-consumption-data} : get all the oxygenConsumptionData.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of oxygenConsumptionData in body.
   */
  @GetMapping("/oxygen-consumption-data")
  public ResponseEntity<List<OxygenConsumptionData>> getAllOxygenConsumptionData(
    OxygenConsumptionDataCriteria criteria,
    Pageable pageable
  ) {
    log.debug(
      "REST request to get OxygenConsumptionData by criteria: {}",
      criteria
    );
    Page<OxygenConsumptionData> page = oxygenConsumptionDataQueryService.findByCriteria(
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
   * {@code GET  /oxygen-consumption-data/count} : count all the oxygenConsumptionData.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/oxygen-consumption-data/count")
  public ResponseEntity<Long> countOxygenConsumptionData(
    OxygenConsumptionDataCriteria criteria
  ) {
    log.debug(
      "REST request to count OxygenConsumptionData by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(oxygenConsumptionDataQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /oxygen-consumption-data/:id} : get the "id" oxygenConsumptionData.
   *
   * @param id the id of the oxygenConsumptionData to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the oxygenConsumptionData, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/oxygen-consumption-data/{id}")
  public ResponseEntity<OxygenConsumptionData> getOxygenConsumptionData(
    @PathVariable Long id
  ) {
    log.debug("REST request to get OxygenConsumptionData : {}", id);
    Optional<OxygenConsumptionData> oxygenConsumptionData = oxygenConsumptionDataService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(oxygenConsumptionData);
  }

  /**
   * {@code DELETE  /oxygen-consumption-data/:id} : delete the "id" oxygenConsumptionData.
   *
   * @param id the id of the oxygenConsumptionData to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/oxygen-consumption-data/{id}")
  public ResponseEntity<Void> deleteOxygenConsumptionData(
    @PathVariable Long id
  ) {
    log.debug("REST request to delete OxygenConsumptionData : {}", id);
    oxygenConsumptionDataService.delete(id);
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
