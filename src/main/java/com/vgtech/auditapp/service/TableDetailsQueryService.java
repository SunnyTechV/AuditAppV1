package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.TableDetails;
import com.vgtech.auditapp.repository.TableDetailsRepository;
import com.vgtech.auditapp.service.criteria.TableDetailsCriteria;
import com.vgtech.auditapp.service.dto.TableDetails;
import com.vgtech.auditapp.service.mapper.TableDetailsMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link TableDetails} entities in the database.
 * The main input is a {@link TableDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TableDetails} or a {@link Page} of {@link TableDetails} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TableDetailsQueryService extends QueryService<TableDetails> {

  private final Logger log = LoggerFactory.getLogger(
    TableDetailsQueryService.class
  );

  private final TableDetailsRepository tableDetailsRepository;

  private final TableDetailsMapper tableDetailsMapper;

  public TableDetailsQueryService(
    TableDetailsRepository tableDetailsRepository,
    TableDetailsMapper tableDetailsMapper
  ) {
    this.tableDetailsRepository = tableDetailsRepository;
    this.tableDetailsMapper = tableDetailsMapper;
  }

  /**
   * Return a {@link List} of {@link TableDetails} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<TableDetails> findByCriteria(TableDetailsCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<TableDetails> specification = createSpecification(
      criteria
    );
    return tableDetailsMapper.toDto(
      tableDetailsRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link TableDetails} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<TableDetails> findByCriteria(
    TableDetailsCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<TableDetails> specification = createSpecification(
      criteria
    );
    return tableDetailsRepository
      .findAll(specification, page)
      .map(tableDetailsMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(TableDetailsCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<TableDetails> specification = createSpecification(
      criteria
    );
    return tableDetailsRepository.count(specification);
  }

  /**
   * Function to convert {@link TableDetailsCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<TableDetails> createSpecification(
    TableDetailsCriteria criteria
  ) {
    Specification<TableDetails> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), TableDetails_.id)
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              TableDetails_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getType(), TableDetails_.type)
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              TableDetails_.subType
            )
          );
      }
      if (criteria.getTableName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTableName(),
              TableDetails_.tableName
            )
          );
      }
      if (criteria.getDescription() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDescription(),
              TableDetails_.description
            )
          );
      }
      if (criteria.getDescriptionParameter() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDescriptionParameter(),
              TableDetails_.descriptionParameter
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              TableDetails_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              TableDetails_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              TableDetails_.freeField3
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              TableDetails_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              TableDetails_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              TableDetails_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              TableDetails_.lastModifiedBy
            )
          );
      }
      if (criteria.getOxygenConsumptionDataId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getOxygenConsumptionDataId(),
              root ->
                root
                  .join(TableDetails_.oxygenConsumptionData, JoinType.LEFT)
                  .get(OxygenConsumptionData_.id)
            )
          );
      }
    }
    return specification;
  }
}
