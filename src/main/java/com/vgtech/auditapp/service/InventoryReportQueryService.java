package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.InventoryReport;
import com.vgtech.auditapp.repository.InventoryReportRepository;
import com.vgtech.auditapp.service.criteria.InventoryReportCriteria;
import com.vgtech.auditapp.service.dto.InventoryReport;
import com.vgtech.auditapp.service.mapper.InventoryReportMapper;
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
 * Service for executing complex queries for {@link InventoryReport} entities in the database.
 * The main input is a {@link InventoryReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InventoryReport} or a {@link Page} of {@link InventoryReport} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InventoryReportQueryService extends QueryService<InventoryReport> {

  private final Logger log = LoggerFactory.getLogger(
    InventoryReportQueryService.class
  );

  private final InventoryReportRepository inventoryReportRepository;

  private final InventoryReportMapper inventoryReportMapper;

  public InventoryReportQueryService(
    InventoryReportRepository inventoryReportRepository,
    InventoryReportMapper inventoryReportMapper
  ) {
    this.inventoryReportRepository = inventoryReportRepository;
    this.inventoryReportMapper = inventoryReportMapper;
  }

  /**
   * Return a {@link List} of {@link InventoryReport} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<InventoryReport> findByCriteria(
    InventoryReportCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<InventoryReport> specification = createSpecification(
      criteria
    );
    return inventoryReportMapper.toDto(
      inventoryReportRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link InventoryReport} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<InventoryReport> findByCriteria(
    InventoryReportCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<InventoryReport> specification = createSpecification(
      criteria
    );
    return inventoryReportRepository
      .findAll(specification, page)
      .map(inventoryReportMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(InventoryReportCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<InventoryReport> specification = createSpecification(
      criteria
    );
    return inventoryReportRepository.count(specification);
  }

  /**
   * Function to convert {@link InventoryReportCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<InventoryReport> createSpecification(
    InventoryReportCriteria criteria
  ) {
    Specification<InventoryReport> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), InventoryReport_.id)
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              InventoryReport_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getType(), InventoryReport_.type)
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              InventoryReport_.subType
            )
          );
      }
      if (criteria.getTableName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTableName(),
              InventoryReport_.tableName
            )
          );
      }
      if (criteria.getDescription() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDescription(),
              InventoryReport_.description
            )
          );
      }
      if (criteria.getDescriptionParameter() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDescriptionParameter(),
              InventoryReport_.descriptionParameter
            )
          );
      }
      if (criteria.getActualAvailable() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getActualAvailable(),
              InventoryReport_.actualAvailable
            )
          );
      }
      if (criteria.getRemark() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getRemark(),
              InventoryReport_.remark
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              InventoryReport_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              InventoryReport_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              InventoryReport_.freeField3
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              InventoryReport_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              InventoryReport_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              InventoryReport_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              InventoryReport_.lastModifiedBy
            )
          );
      }
      if (criteria.getAuditId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAuditId(),
              root ->
                root.join(InventoryReport_.audit, JoinType.LEFT).get(Audit_.id)
            )
          );
      }
    }
    return specification;
  }
}
