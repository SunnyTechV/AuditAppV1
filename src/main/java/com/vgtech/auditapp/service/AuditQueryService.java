package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.repository.AuditRepository;
import com.vgtech.auditapp.service.criteria.AuditCriteria;
import com.vgtech.auditapp.service.dto.Audit;
import com.vgtech.auditapp.service.mapper.AuditMapper;
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
 * Service for executing complex queries for {@link Audit} entities in the database.
 * The main input is a {@link AuditCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Audit} or a {@link Page} of {@link Audit} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AuditQueryService extends QueryService<Audit> {

  private final Logger log = LoggerFactory.getLogger(AuditQueryService.class);

  private final AuditRepository auditRepository;

  private final AuditMapper auditMapper;

  public AuditQueryService(
    AuditRepository auditRepository,
    AuditMapper auditMapper
  ) {
    this.auditRepository = auditRepository;
    this.auditMapper = auditMapper;
  }

  /**
   * Return a {@link List} of {@link Audit} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<Audit> findByCriteria(AuditCriteria criteria) {
    log.debug("find by criteria : {}", criteria);
    final Specification<Audit> specification = createSpecification(criteria);
    return auditMapper.toDto(auditRepository.findAll(specification));
  }

  /**
   * Return a {@link Page} of {@link Audit} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<Audit> findByCriteria(AuditCriteria criteria, Pageable page) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<Audit> specification = createSpecification(criteria);
    return auditRepository.findAll(specification, page).map(auditMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AuditCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<Audit> specification = createSpecification(criteria);
    return auditRepository.count(specification);
  }

  /**
   * Function to convert {@link AuditCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<Audit> createSpecification(AuditCriteria criteria) {
    Specification<Audit> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), Audit_.id)
          );
      }
      if (criteria.getAuditDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getAuditDate(), Audit_.auditDate)
          );
      }
      if (criteria.getHospName() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getHospName(), Audit_.hospName)
          );
      }
      if (criteria.getIsAuditComplete() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getIsAuditComplete(),
              Audit_.isAuditComplete
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              Audit_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              Audit_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              Audit_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              Audit_.freeField4
            )
          );
      }
      if (criteria.getRemark() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getRemark(), Audit_.remark)
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getCreatedBy(), Audit_.createdBy)
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              Audit_.createdDate
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              Audit_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              Audit_.lastModifiedBy
            )
          );
      }
      if (criteria.getAuditPatientMonitoringFormId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAuditPatientMonitoringFormId(),
              root ->
                root
                  .join(Audit_.auditPatientMonitoringForm, JoinType.LEFT)
                  .get(AuditPatientMonitoringForm_.id)
            )
          );
      }
      if (criteria.getAuditFormSHospGenInfoId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAuditFormSHospGenInfoId(),
              root ->
                root
                  .join(Audit_.auditFormSHospGenInfos, JoinType.LEFT)
                  .get(AuditFormSHospGenInfo_.id)
            )
          );
      }
      if (criteria.getFireElectricalAuditId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getFireElectricalAuditId(),
              root ->
                root
                  .join(Audit_.fireElectricalAudits, JoinType.LEFT)
                  .get(FireElectricalAudit_.id)
            )
          );
      }
      if (criteria.getAnnexureAnswersId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAnnexureAnswersId(),
              root ->
                root
                  .join(Audit_.annexureAnswers, JoinType.LEFT)
                  .get(AnnexureAnswers_.id)
            )
          );
      }
      if (criteria.getInvetoryReportId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getInvetoryReportId(),
              root ->
                root
                  .join(Audit_.invetoryReports, JoinType.LEFT)
                  .get(InventoryReport_.id)
            )
          );
      }
      if (criteria.getInventorySupplierDetailsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getInventorySupplierDetailsId(),
              root ->
                root
                  .join(Audit_.inventorySupplierDetails, JoinType.LEFT)
                  .get(InventorySupplierDetails_.id)
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
                  .join(Audit_.oxygenConsumptionData, JoinType.LEFT)
                  .get(OxygenConsumptionData_.id)
            )
          );
      }
    }
    return specification;
  }
}
