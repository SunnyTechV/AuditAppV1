package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.repository.AuditRepository;
import com.vgtech.auditapp.service.dto.Audit;
import com.vgtech.auditapp.service.mapper.AuditMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Audit}.
 */
@Service
@Transactional
public class AuditService {

  private final Logger log = LoggerFactory.getLogger(AuditService.class);

  private final AuditRepository auditRepository;

  private final AuditMapper auditMapper;

  public AuditService(
    AuditRepository auditRepository,
    AuditMapper auditMapper
  ) {
    this.auditRepository = auditRepository;
    this.auditMapper = auditMapper;
  }

  /**
   * Save a audit.
   *
   * @param audit the entity to save.
   * @return the persisted entity.
   */
  public Audit save(Audit audit) {
    log.debug("Request to save Audit : {}", audit);
    Audit audit = auditMapper.toEntity(audit);
    audit = auditRepository.save(audit);
    return auditMapper.toDto(audit);
  }

  /**
   * Partially update a audit.
   *
   * @param audit the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<Audit> partialUpdate(Audit audit) {
    log.debug("Request to partially update Audit : {}", audit);

    return auditRepository
      .findById(audit.getId())
      .map(existingAudit -> {
        auditMapper.partialUpdate(existingAudit, audit);

        return existingAudit;
      })
      .map(auditRepository::save)
      .map(auditMapper::toDto);
  }

  /**
   * Get all the audits.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<Audit> findAll(Pageable pageable) {
    log.debug("Request to get all Audits");
    return auditRepository.findAll(pageable).map(auditMapper::toDto);
  }

  /**
   * Get one audit by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<Audit> findOne(Long id) {
    log.debug("Request to get Audit : {}", id);
    return auditRepository.findById(id).map(auditMapper::toDto);
  }

  /**
   * Delete the audit by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete Audit : {}", id);
    auditRepository.deleteById(id);
  }
}
