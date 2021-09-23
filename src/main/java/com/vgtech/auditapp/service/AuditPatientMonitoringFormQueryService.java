package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.AuditPatientMonitoringForm;
import com.vgtech.auditapp.repository.AuditPatientMonitoringFormRepository;
import com.vgtech.auditapp.service.criteria.AuditPatientMonitoringFormCriteria;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringForm;
import com.vgtech.auditapp.service.mapper.AuditPatientMonitoringFormMapper;
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
 * Service for executing complex queries for {@link AuditPatientMonitoringForm} entities in the database.
 * The main input is a {@link AuditPatientMonitoringFormCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AuditPatientMonitoringForm} or a {@link Page} of {@link AuditPatientMonitoringForm} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AuditPatientMonitoringFormQueryService
  extends QueryService<AuditPatientMonitoringForm> {

  private final Logger log = LoggerFactory.getLogger(
    AuditPatientMonitoringFormQueryService.class
  );

  private final AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository;

  private final AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper;

  public AuditPatientMonitoringFormQueryService(
    AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository,
    AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper
  ) {
    this.auditPatientMonitoringFormRepository =
      auditPatientMonitoringFormRepository;
    this.auditPatientMonitoringFormMapper = auditPatientMonitoringFormMapper;
  }

  /**
   * Return a {@link List} of {@link AuditPatientMonitoringForm} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<AuditPatientMonitoringForm> findByCriteria(
    AuditPatientMonitoringFormCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<AuditPatientMonitoringForm> specification = createSpecification(
      criteria
    );
    return auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringFormRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link AuditPatientMonitoringForm} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<AuditPatientMonitoringForm> findByCriteria(
    AuditPatientMonitoringFormCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<AuditPatientMonitoringForm> specification = createSpecification(
      criteria
    );
    return auditPatientMonitoringFormRepository
      .findAll(specification, page)
      .map(auditPatientMonitoringFormMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AuditPatientMonitoringFormCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<AuditPatientMonitoringForm> specification = createSpecification(
      criteria
    );
    return auditPatientMonitoringFormRepository.count(specification);
  }

  /**
   * Function to convert {@link AuditPatientMonitoringFormCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<AuditPatientMonitoringForm> createSpecification(
    AuditPatientMonitoringFormCriteria criteria
  ) {
    Specification<AuditPatientMonitoringForm> specification = Specification.where(
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
            buildRangeSpecification(
              criteria.getId(),
              AuditPatientMonitoringForm_.id
            )
          );
      }
      if (criteria.getWardNo() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getWardNo(),
              AuditPatientMonitoringForm_.wardNo
            )
          );
      }
      if (criteria.getPatientName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getPatientName(),
              AuditPatientMonitoringForm_.patientName
            )
          );
      }
      if (criteria.getBedNo() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getBedNo(),
              AuditPatientMonitoringForm_.bedNo
            )
          );
      }
      if (criteria.getDateOfAdmission() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getDateOfAdmission(),
              AuditPatientMonitoringForm_.dateOfAdmission
            )
          );
      }
      if (criteria.getOxygenType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getOxygenType(),
              AuditPatientMonitoringForm_.oxygenType
            )
          );
      }
      if (criteria.getSixToEightAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getSixToEightAM(),
              AuditPatientMonitoringForm_.sixToEightAM
            )
          );
      }
      if (criteria.getOxySixToEightAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxySixToEightAM(),
              AuditPatientMonitoringForm_.oxySixToEightAM
            )
          );
      }
      if (criteria.getEightToTenAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getEightToTenAM(),
              AuditPatientMonitoringForm_.eightToTenAM
            )
          );
      }
      if (criteria.getOxyEightToTenAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyEightToTenAM(),
              AuditPatientMonitoringForm_.oxyEightToTenAM
            )
          );
      }
      if (criteria.getTenToTwelveAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTenToTwelveAM(),
              AuditPatientMonitoringForm_.tenToTwelveAM
            )
          );
      }
      if (criteria.getOxyTenToTwelveAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTenToTwelveAM(),
              AuditPatientMonitoringForm_.oxyTenToTwelveAM
            )
          );
      }
      if (criteria.getTwelveToTowPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTwelveToTowPM(),
              AuditPatientMonitoringForm_.twelveToTowPM
            )
          );
      }
      if (criteria.getOxyTwelveToTowPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTwelveToTowPM(),
              AuditPatientMonitoringForm_.oxyTwelveToTowPM
            )
          );
      }
      if (criteria.getTwoToFourPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTwoToFourPM(),
              AuditPatientMonitoringForm_.twoToFourPM
            )
          );
      }
      if (criteria.getOxyTwoToFourPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTwoToFourPM(),
              AuditPatientMonitoringForm_.oxyTwoToFourPM
            )
          );
      }
      if (criteria.getFourToSixPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getFourToSixPM(),
              AuditPatientMonitoringForm_.fourToSixPM
            )
          );
      }
      if (criteria.getOxyFourToSixPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyFourToSixPM(),
              AuditPatientMonitoringForm_.oxyFourToSixPM
            )
          );
      }
      if (criteria.getSixToEightPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getSixToEightPM(),
              AuditPatientMonitoringForm_.sixToEightPM
            )
          );
      }
      if (criteria.getOxySixToEightPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxySixToEightPM(),
              AuditPatientMonitoringForm_.oxySixToEightPM
            )
          );
      }
      if (criteria.getEightToTenPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getEightToTenPM(),
              AuditPatientMonitoringForm_.eightToTenPM
            )
          );
      }
      if (criteria.getOxyEightToTenPM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyEightToTenPM(),
              AuditPatientMonitoringForm_.oxyEightToTenPM
            )
          );
      }
      if (criteria.getTenToTwelvePM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTenToTwelvePM(),
              AuditPatientMonitoringForm_.tenToTwelvePM
            )
          );
      }
      if (criteria.getOxyTenToTwelvePM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTenToTwelvePM(),
              AuditPatientMonitoringForm_.oxyTenToTwelvePM
            )
          );
      }
      if (criteria.getTwelveToTwoAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTwelveToTwoAM(),
              AuditPatientMonitoringForm_.twelveToTwoAM
            )
          );
      }
      if (criteria.getOxyTwelveToTwoAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTwelveToTwoAM(),
              AuditPatientMonitoringForm_.oxyTwelveToTwoAM
            )
          );
      }
      if (criteria.getTwoToFourAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getTwoToFourAM(),
              AuditPatientMonitoringForm_.twoToFourAM
            )
          );
      }
      if (criteria.getOxyTwoToFourAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyTwoToFourAM(),
              AuditPatientMonitoringForm_.oxyTwoToFourAM
            )
          );
      }
      if (criteria.getFourToSixAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getFourToSixAM(),
              AuditPatientMonitoringForm_.fourToSixAM
            )
          );
      }
      if (criteria.getOxyFourToSixAM() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxyFourToSixAM(),
              AuditPatientMonitoringForm_.oxyFourToSixAM
            )
          );
      }
      if (criteria.getDrName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDrName(),
              AuditPatientMonitoringForm_.drName
            )
          );
      }
      if (criteria.getNurseName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getNurseName(),
              AuditPatientMonitoringForm_.nurseName
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              AuditPatientMonitoringForm_.createdBy
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              AuditPatientMonitoringForm_.createdDate
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              AuditPatientMonitoringForm_.lastModifiedBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              AuditPatientMonitoringForm_.lastModified
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              AuditPatientMonitoringForm_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              AuditPatientMonitoringForm_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              AuditPatientMonitoringForm_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              AuditPatientMonitoringForm_.freeField4
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
                  .join(AuditPatientMonitoringForm_.audit, JoinType.LEFT)
                  .get(Audit_.id)
            )
          );
      }
    }
    return specification;
  }
}
