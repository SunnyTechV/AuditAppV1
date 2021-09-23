package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.AuditFormSHospGenInfo;
import com.vgtech.auditapp.repository.AuditFormSHospGenInfoRepository;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfo;
import com.vgtech.auditapp.service.mapper.AuditFormSHospGenInfoMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AuditFormSHospGenInfo}.
 */
@Service
@Transactional
public class AuditFormSHospGenInfoService {

  private final Logger log = LoggerFactory.getLogger(
    AuditFormSHospGenInfoService.class
  );

  private final AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository;

  private final AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper;

  public AuditFormSHospGenInfoService(
    AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository,
    AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper
  ) {
    this.auditFormSHospGenInfoRepository = auditFormSHospGenInfoRepository;
    this.auditFormSHospGenInfoMapper = auditFormSHospGenInfoMapper;
  }

  /**
   * Save a auditFormSHospGenInfo.
   *
   * @param auditFormSHospGenInfo the entity to save.
   * @return the persisted entity.
   */
  public AuditFormSHospGenInfo save(
    AuditFormSHospGenInfo auditFormSHospGenInfo
  ) {
    log.debug(
      "Request to save AuditFormSHospGenInfo : {}",
      auditFormSHospGenInfo
    );
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toEntity(
      auditFormSHospGenInfo
    );
    auditFormSHospGenInfo =
      auditFormSHospGenInfoRepository.save(auditFormSHospGenInfo);
    return auditFormSHospGenInfoMapper.toDto(auditFormSHospGenInfo);
  }

  /**
   * Partially update a auditFormSHospGenInfo.
   *
   * @param auditFormSHospGenInfo the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<AuditFormSHospGenInfo> partialUpdate(
    AuditFormSHospGenInfo auditFormSHospGenInfo
  ) {
    log.debug(
      "Request to partially update AuditFormSHospGenInfo : {}",
      auditFormSHospGenInfo
    );

    return auditFormSHospGenInfoRepository
      .findById(auditFormSHospGenInfo.getId())
      .map(existingAuditFormSHospGenInfo -> {
        auditFormSHospGenInfoMapper.partialUpdate(
          existingAuditFormSHospGenInfo,
          auditFormSHospGenInfo
        );

        return existingAuditFormSHospGenInfo;
      })
      .map(auditFormSHospGenInfoRepository::save)
      .map(auditFormSHospGenInfoMapper::toDto);
  }

  /**
   * Get all the auditFormSHospGenInfos.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<AuditFormSHospGenInfo> findAll(Pageable pageable) {
    log.debug("Request to get all AuditFormSHospGenInfos");
    return auditFormSHospGenInfoRepository
      .findAll(pageable)
      .map(auditFormSHospGenInfoMapper::toDto);
  }

  /**
   * Get one auditFormSHospGenInfo by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<AuditFormSHospGenInfo> findOne(Long id) {
    log.debug("Request to get AuditFormSHospGenInfo : {}", id);
    return auditFormSHospGenInfoRepository
      .findById(id)
      .map(auditFormSHospGenInfoMapper::toDto);
  }

  /**
   * Delete the auditFormSHospGenInfo by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete AuditFormSHospGenInfo : {}", id);
    auditFormSHospGenInfoRepository.deleteById(id);
  }
}
