package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.AnnexureQuestions;
import com.vgtech.auditapp.repository.AnnexureQuestionsRepository;
import com.vgtech.auditapp.service.dto.AnnexureQuestions;
import com.vgtech.auditapp.service.mapper.AnnexureQuestionsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnnexureQuestions}.
 */
@Service
@Transactional
public class AnnexureQuestionsService {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureQuestionsService.class
  );

  private final AnnexureQuestionsRepository annexureQuestionsRepository;

  private final AnnexureQuestionsMapper annexureQuestionsMapper;

  public AnnexureQuestionsService(
    AnnexureQuestionsRepository annexureQuestionsRepository,
    AnnexureQuestionsMapper annexureQuestionsMapper
  ) {
    this.annexureQuestionsRepository = annexureQuestionsRepository;
    this.annexureQuestionsMapper = annexureQuestionsMapper;
  }

  /**
   * Save a annexureQuestions.
   *
   * @param annexureQuestions the entity to save.
   * @return the persisted entity.
   */
  public AnnexureQuestions save(AnnexureQuestions annexureQuestions) {
    log.debug("Request to save AnnexureQuestions : {}", annexureQuestions);
    AnnexureQuestions annexureQuestions = annexureQuestionsMapper.toEntity(
      annexureQuestions
    );
    annexureQuestions = annexureQuestionsRepository.save(annexureQuestions);
    return annexureQuestionsMapper.toDto(annexureQuestions);
  }

  /**
   * Partially update a annexureQuestions.
   *
   * @param annexureQuestions the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<AnnexureQuestions> partialUpdate(
    AnnexureQuestions annexureQuestions
  ) {
    log.debug(
      "Request to partially update AnnexureQuestions : {}",
      annexureQuestions
    );

    return annexureQuestionsRepository
      .findById(annexureQuestions.getId())
      .map(existingAnnexureQuestions -> {
        annexureQuestionsMapper.partialUpdate(
          existingAnnexureQuestions,
          annexureQuestions
        );

        return existingAnnexureQuestions;
      })
      .map(annexureQuestionsRepository::save)
      .map(annexureQuestionsMapper::toDto);
  }

  /**
   * Get all the annexureQuestions.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<AnnexureQuestions> findAll(Pageable pageable) {
    log.debug("Request to get all AnnexureQuestions");
    return annexureQuestionsRepository
      .findAll(pageable)
      .map(annexureQuestionsMapper::toDto);
  }

  /**
   * Get one annexureQuestions by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<AnnexureQuestions> findOne(Long id) {
    log.debug("Request to get AnnexureQuestions : {}", id);
    return annexureQuestionsRepository
      .findById(id)
      .map(annexureQuestionsMapper::toDto);
  }

  /**
   * Delete the annexureQuestions by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete AnnexureQuestions : {}", id);
    annexureQuestionsRepository.deleteById(id);
  }
}
