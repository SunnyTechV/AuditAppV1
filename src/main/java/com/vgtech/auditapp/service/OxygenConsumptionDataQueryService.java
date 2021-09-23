package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.OxygenConsumptionData;
import com.vgtech.auditapp.repository.OxygenConsumptionDataRepository;
import com.vgtech.auditapp.service.criteria.OxygenConsumptionDataCriteria;
import com.vgtech.auditapp.service.dto.OxygenConsumptionData;
import com.vgtech.auditapp.service.mapper.OxygenConsumptionDataMapper;
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
 * Service for executing complex queries for {@link OxygenConsumptionData} entities in the database.
 * The main input is a {@link OxygenConsumptionDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OxygenConsumptionData} or a {@link Page} of {@link OxygenConsumptionData} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OxygenConsumptionDataQueryService
  extends QueryService<OxygenConsumptionData> {

  private final Logger log = LoggerFactory.getLogger(
    OxygenConsumptionDataQueryService.class
  );

  private final OxygenConsumptionDataRepository oxygenConsumptionDataRepository;

  private final OxygenConsumptionDataMapper oxygenConsumptionDataMapper;

  public OxygenConsumptionDataQueryService(
    OxygenConsumptionDataRepository oxygenConsumptionDataRepository,
    OxygenConsumptionDataMapper oxygenConsumptionDataMapper
  ) {
    this.oxygenConsumptionDataRepository = oxygenConsumptionDataRepository;
    this.oxygenConsumptionDataMapper = oxygenConsumptionDataMapper;
  }

  /**
   * Return a {@link List} of {@link OxygenConsumptionData} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<OxygenConsumptionData> findByCriteria(
    OxygenConsumptionDataCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<OxygenConsumptionData> specification = createSpecification(
      criteria
    );
    return oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionDataRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link OxygenConsumptionData} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<OxygenConsumptionData> findByCriteria(
    OxygenConsumptionDataCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<OxygenConsumptionData> specification = createSpecification(
      criteria
    );
    return oxygenConsumptionDataRepository
      .findAll(specification, page)
      .map(oxygenConsumptionDataMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(OxygenConsumptionDataCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<OxygenConsumptionData> specification = createSpecification(
      criteria
    );
    return oxygenConsumptionDataRepository.count(specification);
  }

  /**
   * Function to convert {@link OxygenConsumptionDataCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<OxygenConsumptionData> createSpecification(
    OxygenConsumptionDataCriteria criteria
  ) {
    Specification<OxygenConsumptionData> specification = Specification.where(
      null
    );
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), OxygenConsumptionData_.id)
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              OxygenConsumptionData_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getType(),
              OxygenConsumptionData_.type
            )
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              OxygenConsumptionData_.subType
            )
          );
      }
      if (criteria.getTableName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTableName(),
              OxygenConsumptionData_.tableName
            )
          );
      }
      if (criteria.getNoofPatients() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getNoofPatients(),
              OxygenConsumptionData_.noofPatients
            )
          );
      }
      if (criteria.getConsumptionLitperMin() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getConsumptionLitperMin(),
              OxygenConsumptionData_.consumptionLitperMin
            )
          );
      }
      if (criteria.getConsumptionLitperDay() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getConsumptionLitperDay(),
              OxygenConsumptionData_.consumptionLitperDay
            )
          );
      }
      if (criteria.getConsumptionKLitperDay() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getConsumptionKLitperDay(),
              OxygenConsumptionData_.consumptionKLitperDay
            )
          );
      }
      if (criteria.getConsumptionTotal() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getConsumptionTotal(),
              OxygenConsumptionData_.consumptionTotal
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              OxygenConsumptionData_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              OxygenConsumptionData_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              OxygenConsumptionData_.freeField3
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              OxygenConsumptionData_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              OxygenConsumptionData_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              OxygenConsumptionData_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              OxygenConsumptionData_.lastModifiedBy
            )
          );
      }
      if (criteria.getAuditId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAuditId(),
              root ->
                root
                  .join(OxygenConsumptionData_.audit, JoinType.LEFT)
                  .get(Audit_.id)
            )
          );
      }
      if (criteria.getTableDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTableDetailsId(),
              root ->
                root
                  .join(OxygenConsumptionData_.tableDetails, JoinType.LEFT)
                  .get(TableDetails_.id)
            )
          );
      }
    }
    return specification;
  }
}
