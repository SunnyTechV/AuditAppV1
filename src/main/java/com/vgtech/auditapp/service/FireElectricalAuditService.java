package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.FireElectricalAudit;
import com.vgtech.auditapp.repository.FireElectricalAuditRepository;
import com.vgtech.auditapp.service.dto.FireElectricalAudit;
import com.vgtech.auditapp.service.mapper.FireElectricalAuditMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link FireElectricalAudit}.
 */
@Service
@Transactional
public class FireElectricalAuditService {

  private final Logger log = LoggerFactory.getLogger(
    FireElectricalAuditService.class
  );

  private final FireElectricalAuditRepository fireElectricalAuditRepository;

  private final FireElectricalAuditMapper fireElectricalAuditMapper;

  public FireElectricalAuditService(
    FireElectricalAuditRepository fireElectricalAuditRepository,
    FireElectricalAuditMapper fireElectricalAuditMapper
  ) {
    this.fireElectricalAuditRepository = fireElectricalAuditRepository;
    this.fireElectricalAuditMapper = fireElectricalAuditMapper;
  }

  /**
   * Save a fireElectricalAudit.
   *
   * @param fireElectricalAudit the entity to save.
   * @return the persisted entity.
   */
  public FireElectricalAudit save(FireElectricalAudit fireElectricalAudit) {
    log.debug("Request to save FireElectricalAudit : {}", fireElectricalAudit);
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toEntity(
      fireElectricalAudit
    );
    fireElectricalAudit =
      fireElectricalAuditRepository.save(fireElectricalAudit);
    return fireElectricalAuditMapper.toDto(fireElectricalAudit);
  }

  /**
   * Partially update a fireElectricalAudit.
   *
   * @param fireElectricalAudit the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<FireElectricalAudit> partialUpdate(
    FireElectricalAudit fireElectricalAudit
  ) {
    log.debug(
      "Request to partially update FireElectricalAudit : {}",
      fireElectricalAudit
    );

    return fireElectricalAuditRepository
      .findById(fireElectricalAudit.getId())
      .map(existingFireElectricalAudit -> {
        fireElectricalAuditMapper.partialUpdate(
          existingFireElectricalAudit,
          fireElectricalAudit
        );

        return existingFireElectricalAudit;
      })
      .map(fireElectricalAuditRepository::save)
      .map(fireElectricalAuditMapper::toDto);
  }

  /**
   * Get all the fireElectricalAudits.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<FireElectricalAudit> findAll(Pageable pageable) {
    log.debug("Request to get all FireElectricalAudits");
    return fireElectricalAuditRepository
      .findAll(pageable)
      .map(fireElectricalAuditMapper::toDto);
  }

  /**
   * Get one fireElectricalAudit by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<FireElectricalAudit> findOne(Long id) {
    log.debug("Request to get FireElectricalAudit : {}", id);
    return fireElectricalAuditRepository
      .findById(id)
      .map(fireElectricalAuditMapper::toDto);
  }

  /**
   * Delete the fireElectricalAudit by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete FireElectricalAudit : {}", id);
    fireElectricalAuditRepository.deleteById(id);
  }
}
