package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.AuditPatientMonitoringForm;
import com.vgtech.auditapp.repository.AuditPatientMonitoringFormRepository;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringForm;
import com.vgtech.auditapp.service.mapper.AuditPatientMonitoringFormMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AuditPatientMonitoringForm}.
 */
@Service
@Transactional
public class AuditPatientMonitoringFormService {

  private final Logger log = LoggerFactory.getLogger(
    AuditPatientMonitoringFormService.class
  );

  private final AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository;

  private final AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper;

  public AuditPatientMonitoringFormService(
    AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository,
    AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper
  ) {
    this.auditPatientMonitoringFormRepository =
      auditPatientMonitoringFormRepository;
    this.auditPatientMonitoringFormMapper = auditPatientMonitoringFormMapper;
  }

  /**
   * Save a auditPatientMonitoringForm.
   *
   * @param auditPatientMonitoringForm the entity to save.
   * @return the persisted entity.
   */
  public AuditPatientMonitoringForm save(
    AuditPatientMonitoringForm auditPatientMonitoringForm
  ) {
    log.debug(
      "Request to save AuditPatientMonitoringForm : {}",
      auditPatientMonitoringForm
    );
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toEntity(
      auditPatientMonitoringForm
    );
    auditPatientMonitoringForm =
      auditPatientMonitoringFormRepository.save(auditPatientMonitoringForm);
    return auditPatientMonitoringFormMapper.toDto(auditPatientMonitoringForm);
  }

  /**
   * Partially update a auditPatientMonitoringForm.
   *
   * @param auditPatientMonitoringForm the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<AuditPatientMonitoringForm> partialUpdate(
    AuditPatientMonitoringForm auditPatientMonitoringForm
  ) {
    log.debug(
      "Request to partially update AuditPatientMonitoringForm : {}",
      auditPatientMonitoringForm
    );

    return auditPatientMonitoringFormRepository
      .findById(auditPatientMonitoringForm.getId())
      .map(existingAuditPatientMonitoringForm -> {
        auditPatientMonitoringFormMapper.partialUpdate(
          existingAuditPatientMonitoringForm,
          auditPatientMonitoringForm
        );

        return existingAuditPatientMonitoringForm;
      })
      .map(auditPatientMonitoringFormRepository::save)
      .map(auditPatientMonitoringFormMapper::toDto);
  }

  /**
   * Get all the auditPatientMonitoringForms.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<AuditPatientMonitoringForm> findAll(Pageable pageable) {
    log.debug("Request to get all AuditPatientMonitoringForms");
    return auditPatientMonitoringFormRepository
      .findAll(pageable)
      .map(auditPatientMonitoringFormMapper::toDto);
  }

  /**
   *  Get all the auditPatientMonitoringForms where Audit is {@code null}.
   *  @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<AuditPatientMonitoringForm> findAllWhereAuditIsNull() {
    log.debug(
      "Request to get all auditPatientMonitoringForms where Audit is null"
    );
    return StreamSupport
      .stream(
        auditPatientMonitoringFormRepository.findAll().spliterator(),
        false
      )
      .filter(auditPatientMonitoringForm ->
        auditPatientMonitoringForm.getAudit() == null
      )
      .map(auditPatientMonitoringFormMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one auditPatientMonitoringForm by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<AuditPatientMonitoringForm> findOne(Long id) {
    log.debug("Request to get AuditPatientMonitoringForm : {}", id);
    return auditPatientMonitoringFormRepository
      .findById(id)
      .map(auditPatientMonitoringFormMapper::toDto);
  }

  /**
   * Delete the auditPatientMonitoringForm by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete AuditPatientMonitoringForm : {}", id);
    auditPatientMonitoringFormRepository.deleteById(id);
  }
}
