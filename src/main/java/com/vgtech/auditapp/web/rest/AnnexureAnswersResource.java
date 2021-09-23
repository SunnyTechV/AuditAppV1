package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.AnnexureAnswersRepository;
import com.vgtech.auditapp.service.AnnexureAnswersQueryService;
import com.vgtech.auditapp.service.AnnexureAnswersService;
import com.vgtech.auditapp.service.criteria.AnnexureAnswersCriteria;
import com.vgtech.auditapp.service.dto.AnnexureAnswers;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.AnnexureAnswers}.
 */
@RestController
@RequestMapping("/api")
public class AnnexureAnswersResource {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureAnswersResource.class
  );

  private static final String ENTITY_NAME = "annexureAnswers";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AnnexureAnswersService annexureAnswersService;

  private final AnnexureAnswersRepository annexureAnswersRepository;

  private final AnnexureAnswersQueryService annexureAnswersQueryService;

  public AnnexureAnswersResource(
    AnnexureAnswersService annexureAnswersService,
    AnnexureAnswersRepository annexureAnswersRepository,
    AnnexureAnswersQueryService annexureAnswersQueryService
  ) {
    this.annexureAnswersService = annexureAnswersService;
    this.annexureAnswersRepository = annexureAnswersRepository;
    this.annexureAnswersQueryService = annexureAnswersQueryService;
  }

  /**
   * {@code POST  /annexure-answers} : Create a new annexureAnswers.
   *
   * @param annexureAnswers the annexureAnswers to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new annexureAnswers, or with status {@code 400 (Bad Request)} if the annexureAnswers has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/annexure-answers")
  public ResponseEntity<AnnexureAnswers> createAnnexureAnswers(
    @RequestBody AnnexureAnswers annexureAnswers
  ) throws URISyntaxException {
    log.debug("REST request to save AnnexureAnswers : {}", annexureAnswers);
    if (annexureAnswers.getId() != null) {
      throw new BadRequestAlertException(
        "A new annexureAnswers cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    AnnexureAnswers result = annexureAnswersService.save(annexureAnswers);
    return ResponseEntity
      .created(new URI("/api/annexure-answers/" + result.getId()))
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
   * {@code PUT  /annexure-answers/:id} : Updates an existing annexureAnswers.
   *
   * @param id the id of the annexureAnswers to save.
   * @param annexureAnswers the annexureAnswers to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annexureAnswers,
   * or with status {@code 400 (Bad Request)} if the annexureAnswers is not valid,
   * or with status {@code 500 (Internal Server Error)} if the annexureAnswers couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/annexure-answers/{id}")
  public ResponseEntity<AnnexureAnswers> updateAnnexureAnswers(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AnnexureAnswers annexureAnswers
  ) throws URISyntaxException {
    log.debug(
      "REST request to update AnnexureAnswers : {}, {}",
      id,
      annexureAnswers
    );
    if (annexureAnswers.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, annexureAnswers.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!annexureAnswersRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    AnnexureAnswers result = annexureAnswersService.save(annexureAnswers);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          annexureAnswers.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /annexure-answers/:id} : Partial updates given fields of an existing annexureAnswers, field will ignore if it is null
   *
   * @param id the id of the annexureAnswers to save.
   * @param annexureAnswers the annexureAnswers to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annexureAnswers,
   * or with status {@code 400 (Bad Request)} if the annexureAnswers is not valid,
   * or with status {@code 404 (Not Found)} if the annexureAnswers is not found,
   * or with status {@code 500 (Internal Server Error)} if the annexureAnswers couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/annexure-answers/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<AnnexureAnswers> partialUpdateAnnexureAnswers(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AnnexureAnswers annexureAnswers
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update AnnexureAnswers partially : {}, {}",
      id,
      annexureAnswers
    );
    if (annexureAnswers.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, annexureAnswers.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!annexureAnswersRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<AnnexureAnswers> result = annexureAnswersService.partialUpdate(
      annexureAnswers
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        annexureAnswers.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /annexure-answers} : get all the annexureAnswers.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of annexureAnswers in body.
   */
  @GetMapping("/annexure-answers")
  public ResponseEntity<List<AnnexureAnswers>> getAllAnnexureAnswers(
    AnnexureAnswersCriteria criteria,
    Pageable pageable
  ) {
    log.debug("REST request to get AnnexureAnswers by criteria: {}", criteria);
    Page<AnnexureAnswers> page = annexureAnswersQueryService.findByCriteria(
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
   * {@code GET  /annexure-answers/count} : count all the annexureAnswers.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/annexure-answers/count")
  public ResponseEntity<Long> countAnnexureAnswers(
    AnnexureAnswersCriteria criteria
  ) {
    log.debug(
      "REST request to count AnnexureAnswers by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(annexureAnswersQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /annexure-answers/:id} : get the "id" annexureAnswers.
   *
   * @param id the id of the annexureAnswers to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the annexureAnswers, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/annexure-answers/{id}")
  public ResponseEntity<AnnexureAnswers> getAnnexureAnswers(
    @PathVariable Long id
  ) {
    log.debug("REST request to get AnnexureAnswers : {}", id);
    Optional<AnnexureAnswers> annexureAnswers = annexureAnswersService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(annexureAnswers);
  }

  /**
   * {@code DELETE  /annexure-answers/:id} : delete the "id" annexureAnswers.
   *
   * @param id the id of the annexureAnswers to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/annexure-answers/{id}")
  public ResponseEntity<Void> deleteAnnexureAnswers(@PathVariable Long id) {
    log.debug("REST request to delete AnnexureAnswers : {}", id);
    annexureAnswersService.delete(id);
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
