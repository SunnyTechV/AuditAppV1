package com.vgtech.auditapp.web.rest;

import com.vgtech.auditapp.repository.AnnexureQuestionsRepository;
import com.vgtech.auditapp.service.AnnexureQuestionsQueryService;
import com.vgtech.auditapp.service.AnnexureQuestionsService;
import com.vgtech.auditapp.service.criteria.AnnexureQuestionsCriteria;
import com.vgtech.auditapp.service.dto.AnnexureQuestions;
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
 * REST controller for managing {@link com.vgtech.auditapp.domain.AnnexureQuestions}.
 */
@RestController
@RequestMapping("/api")
public class AnnexureQuestionsResource {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureQuestionsResource.class
  );

  private static final String ENTITY_NAME = "annexureQuestions";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final AnnexureQuestionsService annexureQuestionsService;

  private final AnnexureQuestionsRepository annexureQuestionsRepository;

  private final AnnexureQuestionsQueryService annexureQuestionsQueryService;

  public AnnexureQuestionsResource(
    AnnexureQuestionsService annexureQuestionsService,
    AnnexureQuestionsRepository annexureQuestionsRepository,
    AnnexureQuestionsQueryService annexureQuestionsQueryService
  ) {
    this.annexureQuestionsService = annexureQuestionsService;
    this.annexureQuestionsRepository = annexureQuestionsRepository;
    this.annexureQuestionsQueryService = annexureQuestionsQueryService;
  }

  /**
   * {@code POST  /annexure-questions} : Create a new annexureQuestions.
   *
   * @param annexureQuestions the annexureQuestions to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new annexureQuestions, or with status {@code 400 (Bad Request)} if the annexureQuestions has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/annexure-questions")
  public ResponseEntity<AnnexureQuestions> createAnnexureQuestions(
    @RequestBody AnnexureQuestions annexureQuestions
  ) throws URISyntaxException {
    log.debug("REST request to save AnnexureQuestions : {}", annexureQuestions);
    if (annexureQuestions.getId() != null) {
      throw new BadRequestAlertException(
        "A new annexureQuestions cannot already have an ID",
        ENTITY_NAME,
        "idexists"
      );
    }
    AnnexureQuestions result = annexureQuestionsService.save(annexureQuestions);
    return ResponseEntity
      .created(new URI("/api/annexure-questions/" + result.getId()))
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
   * {@code PUT  /annexure-questions/:id} : Updates an existing annexureQuestions.
   *
   * @param id the id of the annexureQuestions to save.
   * @param annexureQuestions the annexureQuestions to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annexureQuestions,
   * or with status {@code 400 (Bad Request)} if the annexureQuestions is not valid,
   * or with status {@code 500 (Internal Server Error)} if the annexureQuestions couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/annexure-questions/{id}")
  public ResponseEntity<AnnexureQuestions> updateAnnexureQuestions(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AnnexureQuestions annexureQuestions
  ) throws URISyntaxException {
    log.debug(
      "REST request to update AnnexureQuestions : {}, {}",
      id,
      annexureQuestions
    );
    if (annexureQuestions.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, annexureQuestions.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!annexureQuestionsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    AnnexureQuestions result = annexureQuestionsService.save(annexureQuestions);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          applicationName,
          true,
          ENTITY_NAME,
          annexureQuestions.getId().toString()
        )
      )
      .body(result);
  }

  /**
   * {@code PATCH  /annexure-questions/:id} : Partial updates given fields of an existing annexureQuestions, field will ignore if it is null
   *
   * @param id the id of the annexureQuestions to save.
   * @param annexureQuestions the annexureQuestions to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated annexureQuestions,
   * or with status {@code 400 (Bad Request)} if the annexureQuestions is not valid,
   * or with status {@code 404 (Not Found)} if the annexureQuestions is not found,
   * or with status {@code 500 (Internal Server Error)} if the annexureQuestions couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(
    value = "/annexure-questions/{id}",
    consumes = { "application/json", "application/merge-patch+json" }
  )
  public ResponseEntity<AnnexureQuestions> partialUpdateAnnexureQuestions(
    @PathVariable(value = "id", required = false) final Long id,
    @RequestBody AnnexureQuestions annexureQuestions
  ) throws URISyntaxException {
    log.debug(
      "REST request to partial update AnnexureQuestions partially : {}, {}",
      id,
      annexureQuestions
    );
    if (annexureQuestions.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    if (!Objects.equals(id, annexureQuestions.getId())) {
      throw new BadRequestAlertException(
        "Invalid ID",
        ENTITY_NAME,
        "idinvalid"
      );
    }

    if (!annexureQuestionsRepository.existsById(id)) {
      throw new BadRequestAlertException(
        "Entity not found",
        ENTITY_NAME,
        "idnotfound"
      );
    }

    Optional<AnnexureQuestions> result = annexureQuestionsService.partialUpdate(
      annexureQuestions
    );

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(
        applicationName,
        true,
        ENTITY_NAME,
        annexureQuestions.getId().toString()
      )
    );
  }

  /**
   * {@code GET  /annexure-questions} : get all the annexureQuestions.
   *
   * @param pageable the pagination information.
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of annexureQuestions in body.
   */
  @GetMapping("/annexure-questions")
  public ResponseEntity<List<AnnexureQuestions>> getAllAnnexureQuestions(
    AnnexureQuestionsCriteria criteria,
    Pageable pageable
  ) {
    log.debug(
      "REST request to get AnnexureQuestions by criteria: {}",
      criteria
    );
    Page<AnnexureQuestions> page = annexureQuestionsQueryService.findByCriteria(
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
   * {@code GET  /annexure-questions/count} : count all the annexureQuestions.
   *
   * @param criteria the criteria which the requested entities should match.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
   */
  @GetMapping("/annexure-questions/count")
  public ResponseEntity<Long> countAnnexureQuestions(
    AnnexureQuestionsCriteria criteria
  ) {
    log.debug(
      "REST request to count AnnexureQuestions by criteria: {}",
      criteria
    );
    return ResponseEntity
      .ok()
      .body(annexureQuestionsQueryService.countByCriteria(criteria));
  }

  /**
   * {@code GET  /annexure-questions/:id} : get the "id" annexureQuestions.
   *
   * @param id the id of the annexureQuestions to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the annexureQuestions, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/annexure-questions/{id}")
  public ResponseEntity<AnnexureQuestions> getAnnexureQuestions(
    @PathVariable Long id
  ) {
    log.debug("REST request to get AnnexureQuestions : {}", id);
    Optional<AnnexureQuestions> annexureQuestions = annexureQuestionsService.findOne(
      id
    );
    return ResponseUtil.wrapOrNotFound(annexureQuestions);
  }

  /**
   * {@code DELETE  /annexure-questions/:id} : delete the "id" annexureQuestions.
   *
   * @param id the id of the annexureQuestions to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/annexure-questions/{id}")
  public ResponseEntity<Void> deleteAnnexureQuestions(@PathVariable Long id) {
    log.debug("REST request to delete AnnexureQuestions : {}", id);
    annexureQuestionsService.delete(id);
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
