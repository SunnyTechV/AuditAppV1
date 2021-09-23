package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.OxygenConsumptionData;
import com.vgtech.auditapp.repository.OxygenConsumptionDataRepository;
import com.vgtech.auditapp.service.dto.OxygenConsumptionData;
import com.vgtech.auditapp.service.mapper.OxygenConsumptionDataMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OxygenConsumptionData}.
 */
@Service
@Transactional
public class OxygenConsumptionDataService {

  private final Logger log = LoggerFactory.getLogger(
    OxygenConsumptionDataService.class
  );

  private final OxygenConsumptionDataRepository oxygenConsumptionDataRepository;

  private final OxygenConsumptionDataMapper oxygenConsumptionDataMapper;

  public OxygenConsumptionDataService(
    OxygenConsumptionDataRepository oxygenConsumptionDataRepository,
    OxygenConsumptionDataMapper oxygenConsumptionDataMapper
  ) {
    this.oxygenConsumptionDataRepository = oxygenConsumptionDataRepository;
    this.oxygenConsumptionDataMapper = oxygenConsumptionDataMapper;
  }

  /**
   * Save a oxygenConsumptionData.
   *
   * @param oxygenConsumptionData the entity to save.
   * @return the persisted entity.
   */
  public OxygenConsumptionData save(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    log.debug(
      "Request to save OxygenConsumptionData : {}",
      oxygenConsumptionData
    );
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toEntity(
      oxygenConsumptionData
    );
    oxygenConsumptionData =
      oxygenConsumptionDataRepository.save(oxygenConsumptionData);
    return oxygenConsumptionDataMapper.toDto(oxygenConsumptionData);
  }

  /**
   * Partially update a oxygenConsumptionData.
   *
   * @param oxygenConsumptionData the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<OxygenConsumptionData> partialUpdate(
    OxygenConsumptionData oxygenConsumptionData
  ) {
    log.debug(
      "Request to partially update OxygenConsumptionData : {}",
      oxygenConsumptionData
    );

    return oxygenConsumptionDataRepository
      .findById(oxygenConsumptionData.getId())
      .map(existingOxygenConsumptionData -> {
        oxygenConsumptionDataMapper.partialUpdate(
          existingOxygenConsumptionData,
          oxygenConsumptionData
        );

        return existingOxygenConsumptionData;
      })
      .map(oxygenConsumptionDataRepository::save)
      .map(oxygenConsumptionDataMapper::toDto);
  }

  /**
   * Get all the oxygenConsumptionData.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<OxygenConsumptionData> findAll(Pageable pageable) {
    log.debug("Request to get all OxygenConsumptionData");
    return oxygenConsumptionDataRepository
      .findAll(pageable)
      .map(oxygenConsumptionDataMapper::toDto);
  }

  /**
   * Get one oxygenConsumptionData by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<OxygenConsumptionData> findOne(Long id) {
    log.debug("Request to get OxygenConsumptionData : {}", id);
    return oxygenConsumptionDataRepository
      .findById(id)
      .map(oxygenConsumptionDataMapper::toDto);
  }

  /**
   * Delete the oxygenConsumptionData by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete OxygenConsumptionData : {}", id);
    oxygenConsumptionDataRepository.deleteById(id);
  }
}
