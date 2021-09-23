package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.FireElectricalAudit;
import com.vgtech.auditapp.repository.FireElectricalAuditRepository;
import com.vgtech.auditapp.service.criteria.FireElectricalAuditCriteria;
import com.vgtech.auditapp.service.dto.FireElectricalAudit;
import com.vgtech.auditapp.service.mapper.FireElectricalAuditMapper;
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
 * Service for executing complex queries for {@link FireElectricalAudit} entities in the database.
 * The main input is a {@link FireElectricalAuditCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FireElectricalAudit} or a {@link Page} of {@link FireElectricalAudit} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FireElectricalAuditQueryService
  extends QueryService<FireElectricalAudit> {

  private final Logger log = LoggerFactory.getLogger(
    FireElectricalAuditQueryService.class
  );

  private final FireElectricalAuditRepository fireElectricalAuditRepository;

  private final FireElectricalAuditMapper fireElectricalAuditMapper;

  public FireElectricalAuditQueryService(
    FireElectricalAuditRepository fireElectricalAuditRepository,
    FireElectricalAuditMapper fireElectricalAuditMapper
  ) {
    this.fireElectricalAuditRepository = fireElectricalAuditRepository;
    this.fireElectricalAuditMapper = fireElectricalAuditMapper;
  }

  /**
   * Return a {@link List} of {@link FireElectricalAudit} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<FireElectricalAudit> findByCriteria(
    FireElectricalAuditCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<FireElectricalAudit> specification = createSpecification(
      criteria
    );
    return fireElectricalAuditMapper.toDto(
      fireElectricalAuditRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link FireElectricalAudit} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<FireElectricalAudit> findByCriteria(
    FireElectricalAuditCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<FireElectricalAudit> specification = createSpecification(
      criteria
    );
    return fireElectricalAuditRepository
      .findAll(specification, page)
      .map(fireElectricalAuditMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(FireElectricalAuditCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<FireElectricalAudit> specification = createSpecification(
      criteria
    );
    return fireElectricalAuditRepository.count(specification);
  }

  /**
   * Function to convert {@link FireElectricalAuditCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<FireElectricalAudit> createSpecification(
    FireElectricalAuditCriteria criteria
  ) {
    Specification<FireElectricalAudit> specification = Specification.where(
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
            buildRangeSpecification(criteria.getId(), FireElectricalAudit_.id)
          );
      }
      if (criteria.getFireAuditDoneOrnot() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getFireAuditDoneOrnot(),
              FireElectricalAudit_.fireAuditDoneOrnot
            )
          );
      }
      if (criteria.getFireAuditDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getFireAuditDate(),
              FireElectricalAudit_.fireAuditDate
            )
          );
      }
      if (criteria.getFireFaults() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFireFaults(),
              FireElectricalAudit_.fireFaults
            )
          );
      }
      if (criteria.getFireCorrectiveAction() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFireCorrectiveAction(),
              FireElectricalAudit_.fireCorrectiveAction
            )
          );
      }
      if (criteria.getFireAuditPlan() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFireAuditPlan(),
              FireElectricalAudit_.fireAuditPlan
            )
          );
      }
      if (criteria.getElectricalAuditDone() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getElectricalAuditDone(),
              FireElectricalAudit_.electricalAuditDone
            )
          );
      }
      if (criteria.getElectricalAuditDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getElectricalAuditDate(),
              FireElectricalAudit_.electricalAuditDate
            )
          );
      }
      if (criteria.getElectricalFaults() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getElectricalFaults(),
              FireElectricalAudit_.electricalFaults
            )
          );
      }
      if (criteria.getElectricalCorrectiveAction() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getElectricalCorrectiveAction(),
              FireElectricalAudit_.electricalCorrectiveAction
            )
          );
      }
      if (criteria.getElectricalAuditInspection() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getElectricalAuditInspection(),
              FireElectricalAudit_.electricalAuditInspection
            )
          );
      }
      if (criteria.getTechnicalPersonAppoint() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getTechnicalPersonAppoint(),
              FireElectricalAudit_.technicalPersonAppoint
            )
          );
      }
      if (criteria.getTechPersonname() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechPersonname(),
              FireElectricalAudit_.techPersonname
            )
          );
      }
      if (criteria.getTechPersonMobNo() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechPersonMobNo(),
              FireElectricalAudit_.techPersonMobNo
            )
          );
      }
      if (criteria.getTechnicalEngineerName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechnicalEngineerName(),
              FireElectricalAudit_.technicalEngineerName
            )
          );
      }
      if (criteria.getTechnicalEngineerAddress() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechnicalEngineerAddress(),
              FireElectricalAudit_.technicalEngineerAddress
            )
          );
      }
      if (criteria.getTechnicalEngineerMob() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechnicalEngineerMob(),
              FireElectricalAudit_.technicalEngineerMob
            )
          );
      }
      if (criteria.getTechnicalEngineerAlternateMob() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTechnicalEngineerAlternateMob(),
              FireElectricalAudit_.technicalEngineerAlternateMob
            )
          );
      }
      if (criteria.geto2HospRequirement() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.geto2HospRequirement(),
              FireElectricalAudit_.o2HospRequirement
            )
          );
      }
      if (criteria.geto2HospProjectedRequirement() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.geto2HospProjectedRequirement(),
              FireElectricalAudit_.o2HospProjectedRequirement
            )
          );
      }
      if (criteria.getSaveO2RequirementPossibleMT() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getSaveO2RequirementPossibleMT(),
              FireElectricalAudit_.saveO2RequirementPossibleMT
            )
          );
      }
      if (criteria.getMonitoringO2ValvesPort() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getMonitoringO2ValvesPort(),
              FireElectricalAudit_.monitoringO2ValvesPort
            )
          );
      }
      if (criteria.getPortValvesShutDown() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getPortValvesShutDown(),
              FireElectricalAudit_.portValvesShutDown
            )
          );
      }
      if (criteria.getIdPatientDrillDone() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getIdPatientDrillDone(),
              FireElectricalAudit_.idPatientDrillDone
            )
          );
      }
      if (criteria.getStaffCheckingLeakage() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getStaffCheckingLeakage(),
              FireElectricalAudit_.staffCheckingLeakage
            )
          );
      }
      if (criteria.getPatientO2ReqFinalized() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getPatientO2ReqFinalized(),
              FireElectricalAudit_.patientO2ReqFinalized
            )
          );
      }
      if (criteria.getTimeByDoctor() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTimeByDoctor(),
              FireElectricalAudit_.timeByDoctor
            )
          );
      }
      if (criteria.getIsLightingInstalled() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getIsLightingInstalled(),
              FireElectricalAudit_.isLightingInstalled
            )
          );
      }
      if (criteria.getLocLightningArrerstor() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLocLightningArrerstor(),
              FireElectricalAudit_.locLightningArrerstor
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              FireElectricalAudit_.createdBy
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              FireElectricalAudit_.createdDate
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              FireElectricalAudit_.lastModifiedBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              FireElectricalAudit_.lastModified
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              FireElectricalAudit_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              FireElectricalAudit_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              FireElectricalAudit_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              FireElectricalAudit_.freeField4
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
                  .join(FireElectricalAudit_.audit, JoinType.LEFT)
                  .get(Audit_.id)
            )
          );
      }
    }
    return specification;
  }
}
