package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.AnnexureAnswers;
import com.vgtech.auditapp.repository.AnnexureAnswersRepository;
import com.vgtech.auditapp.service.dto.AnnexureAnswers;
import com.vgtech.auditapp.service.mapper.AnnexureAnswersMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AnnexureAnswers}.
 */
@Service
@Transactional
public class AnnexureAnswersService {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureAnswersService.class
  );

  private final AnnexureAnswersRepository annexureAnswersRepository;

  private final AnnexureAnswersMapper annexureAnswersMapper;

  public AnnexureAnswersService(
    AnnexureAnswersRepository annexureAnswersRepository,
    AnnexureAnswersMapper annexureAnswersMapper
  ) {
    this.annexureAnswersRepository = annexureAnswersRepository;
    this.annexureAnswersMapper = annexureAnswersMapper;
  }

  /**
   * Save a annexureAnswers.
   *
   * @param annexureAnswers the entity to save.
   * @return the persisted entity.
   */
  public AnnexureAnswers save(AnnexureAnswers annexureAnswers) {
    log.debug("Request to save AnnexureAnswers : {}", annexureAnswers);
    AnnexureAnswers annexureAnswers = annexureAnswersMapper.toEntity(
      annexureAnswers
    );
    annexureAnswers = annexureAnswersRepository.save(annexureAnswers);
    return annexureAnswersMapper.toDto(annexureAnswers);
  }

  /**
   * Partially update a annexureAnswers.
   *
   * @param annexureAnswers the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<AnnexureAnswers> partialUpdate(
    AnnexureAnswers annexureAnswers
  ) {
    log.debug(
      "Request to partially update AnnexureAnswers : {}",
      annexureAnswers
    );

    return annexureAnswersRepository
      .findById(annexureAnswers.getId())
      .map(existingAnnexureAnswers -> {
        annexureAnswersMapper.partialUpdate(
          existingAnnexureAnswers,
          annexureAnswers
        );

        return existingAnnexureAnswers;
      })
      .map(annexureAnswersRepository::save)
      .map(annexureAnswersMapper::toDto);
  }

  /**
   * Get all the annexureAnswers.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<AnnexureAnswers> findAll(Pageable pageable) {
    log.debug("Request to get all AnnexureAnswers");
    return annexureAnswersRepository
      .findAll(pageable)
      .map(annexureAnswersMapper::toDto);
  }

  /**
   * Get one annexureAnswers by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<AnnexureAnswers> findOne(Long id) {
    log.debug("Request to get AnnexureAnswers : {}", id);
    return annexureAnswersRepository
      .findById(id)
      .map(annexureAnswersMapper::toDto);
  }

  /**
   * Delete the annexureAnswers by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete AnnexureAnswers : {}", id);
    annexureAnswersRepository.deleteById(id);
  }
}
