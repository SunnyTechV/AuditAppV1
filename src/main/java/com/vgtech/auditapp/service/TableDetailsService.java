package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.TableDetails;
import com.vgtech.auditapp.repository.TableDetailsRepository;
import com.vgtech.auditapp.service.dto.TableDetails;
import com.vgtech.auditapp.service.mapper.TableDetailsMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TableDetails}.
 */
@Service
@Transactional
public class TableDetailsService {

  private final Logger log = LoggerFactory.getLogger(TableDetailsService.class);

  private final TableDetailsRepository tableDetailsRepository;

  private final TableDetailsMapper tableDetailsMapper;

  public TableDetailsService(
    TableDetailsRepository tableDetailsRepository,
    TableDetailsMapper tableDetailsMapper
  ) {
    this.tableDetailsRepository = tableDetailsRepository;
    this.tableDetailsMapper = tableDetailsMapper;
  }

  /**
   * Save a tableDetails.
   *
   * @param tableDetails the entity to save.
   * @return the persisted entity.
   */
  public TableDetails save(TableDetails tableDetails) {
    log.debug("Request to save TableDetails : {}", tableDetails);
    TableDetails tableDetails = tableDetailsMapper.toEntity(tableDetails);
    tableDetails = tableDetailsRepository.save(tableDetails);
    return tableDetailsMapper.toDto(tableDetails);
  }

  /**
   * Partially update a tableDetails.
   *
   * @param tableDetails the entity to update partially.
   * @return the persisted entity.
   */
  public Optional<TableDetails> partialUpdate(TableDetails tableDetails) {
    log.debug("Request to partially update TableDetails : {}", tableDetails);

    return tableDetailsRepository
      .findById(tableDetails.getId())
      .map(existingTableDetails -> {
        tableDetailsMapper.partialUpdate(existingTableDetails, tableDetails);

        return existingTableDetails;
      })
      .map(tableDetailsRepository::save)
      .map(tableDetailsMapper::toDto);
  }

  /**
   * Get all the tableDetails.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public Page<TableDetails> findAll(Pageable pageable) {
    log.debug("Request to get all TableDetails");
    return tableDetailsRepository
      .findAll(pageable)
      .map(tableDetailsMapper::toDto);
  }

  /**
   * Get one tableDetails by id.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  @Transactional(readOnly = true)
  public Optional<TableDetails> findOne(Long id) {
    log.debug("Request to get TableDetails : {}", id);
    return tableDetailsRepository.findById(id).map(tableDetailsMapper::toDto);
  }

  /**
   * Delete the tableDetails by id.
   *
   * @param id the id of the entity.
   */
  public void delete(Long id) {
    log.debug("Request to delete TableDetails : {}", id);
    tableDetailsRepository.deleteById(id);
  }
}
