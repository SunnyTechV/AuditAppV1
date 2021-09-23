package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.InventorySupplierDetails;
import com.vgtech.auditapp.repository.InventorySupplierDetailsRepository;
import com.vgtech.auditapp.service.dto.InventorySupplierDetails;
import com.vgtech.auditapp.service.mapper.InventorySupplierDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link InventorySupplierDetails}.
 */
@Service
@Transactional
public class InventorySupplierDetailsService {

  private final Logger log = LoggerFactory.getLogger(
    InventorySupplierDetailsService.class
  );

  private final InventorySupplierDetailsRepository inventorySupplierDetailsRepository;

  private final InventorySupplierDetailsMapper inventorySupplierDetailsMapper;

  public InventorySupplierDetailsService(
    InventorySupplierDetailsRepository inventorySupplierDetailsRepository,
    InventorySupplierDetailsMapper inventorySupplierDetailsMapper
  ) {
    this.inventorySupplierDetailsRepository =
      inventorySupplierDetailsRepository;
    this.inventorySupplierDetailsMapper = inventorySupplierDetailsMapper;
  }

  /**
   * Save a inventorySupplierDetails.
   *
   * @param inventorySupplierDetails the entity to save.
   * @return the persisted entity.
   */
  public InventorySupplierDetails save(
    InventorySupplierDetails inventorySupplierDetails
  ) {
    log.debug(
      "Request to save InventorySupplierDetails : {}",
      inventorySupplierDetails
    );
    InventorySupplierDetails inventorySupplierDetails = inventorySupplierDetailsMapper.toEntity(
      inventorySupplierDetails
    );
    inventorySupplierDetails =
      inventorySupplierDetailsRepository.save(inventorySupplierDetails);
    return inventorySupplierDetailsMapper.toDto(inventorySupplierDetails);
  }

  /**
   * Partially update a inventorySupplierDetails.
   *
   * @param inventorySupplierDetails the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<InventorySupplierDetails> partialUpdate(
    InventorySupplierDetails inventorySupplierDetails
  ) {
    log.debug(
      "Request to partially update InventorySupplierDetails : {}",
      inventorySupplierDetails
    );

    return inventorySupplierDetailsRepository
      .findById(inventorySupplierDetails.getId())
      .map(existingInventorySupplierDetails -> {
        inventorySupplierDetailsMapper.partialUpdate(
          existingInventorySupplierDetails,
          inventorySupplierDetails
        );

        return existingInventorySupplierDetails;
      })
      .map(inventorySupplierDetailsRepository::save)
      .map(inventorySupplierDetailsMapper::toDto);
  }

  /**
   * Get all the inventorySupplierDetails.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<InventorySupplierDetails> findAll(Pageable pageable) {
    log.debug("Request to get all InventorySupplierDetails");
    return inventorySupplierDetailsRepository
      .findAll(pageable)
      .map(inventorySupplierDetailsMapper::toDto);
  }

  /**
   * Get one inventorySupplierDetails by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<InventorySupplierDetails> findOne(Long id) {
    log.debug("Request to get InventorySupplierDetails : {}", id);
    return inventorySupplierDetailsRepository
      .findById(id)
      .map(inventorySupplierDetailsMapper::toDto);
  }

  /**
   * Delete the inventorySupplierDetails by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete InventorySupplierDetails : {}", id);
    inventorySupplierDetailsRepository.deleteById(id);
  }
}
