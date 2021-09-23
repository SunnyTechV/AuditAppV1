package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.AuditFormSHospGenInfo;
import com.vgtech.auditapp.repository.AuditFormSHospGenInfoRepository;
import com.vgtech.auditapp.service.criteria.AuditFormSHospGenInfoCriteria;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfo;
import com.vgtech.auditapp.service.mapper.AuditFormSHospGenInfoMapper;
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
 * Service for executing complex queries for {@link AuditFormSHospGenInfo} entities in the database.
 * The main input is a {@link AuditFormSHospGenInfoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AuditFormSHospGenInfo} or a {@link Page} of {@link AuditFormSHospGenInfo} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AuditFormSHospGenInfoQueryService
  extends QueryService<AuditFormSHospGenInfo> {

  private final Logger log = LoggerFactory.getLogger(
    AuditFormSHospGenInfoQueryService.class
  );

  private final AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository;

  private final AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper;

  public AuditFormSHospGenInfoQueryService(
    AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository,
    AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper
  ) {
    this.auditFormSHospGenInfoRepository = auditFormSHospGenInfoRepository;
    this.auditFormSHospGenInfoMapper = auditFormSHospGenInfoMapper;
  }

  /**
   * Return a {@link List} of {@link AuditFormSHospGenInfo} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<AuditFormSHospGenInfo> findByCriteria(
    AuditFormSHospGenInfoCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<AuditFormSHospGenInfo> specification = createSpecification(
      criteria
    );
    return auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfoRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link AuditFormSHospGenInfo} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<AuditFormSHospGenInfo> findByCriteria(
    AuditFormSHospGenInfoCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<AuditFormSHospGenInfo> specification = createSpecification(
      criteria
    );
    return auditFormSHospGenInfoRepository
      .findAll(specification, page)
      .map(auditFormSHospGenInfoMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AuditFormSHospGenInfoCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<AuditFormSHospGenInfo> specification = createSpecification(
      criteria
    );
    return auditFormSHospGenInfoRepository.count(specification);
  }

  /**
   * Function to convert {@link AuditFormSHospGenInfoCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<AuditFormSHospGenInfo> createSpecification(
    AuditFormSHospGenInfoCriteria criteria
  ) {
    Specification<AuditFormSHospGenInfo> specification = Specification.where(
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
            buildRangeSpecification(criteria.getId(), AuditFormSHospGenInfo_.id)
          );
      }
      if (criteria.getHospName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getHospName(),
              AuditFormSHospGenInfo_.hospName
            )
          );
      }
      if (criteria.getHospType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getHospType(),
              AuditFormSHospGenInfo_.hospType
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getType(),
              AuditFormSHospGenInfo_.type
            )
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              AuditFormSHospGenInfo_.subType
            )
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              AuditFormSHospGenInfo_.formName
            )
          );
      }
      if (criteria.getInchargeName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getInchargeName(),
              AuditFormSHospGenInfo_.inchargeName
            )
          );
      }
      if (criteria.getHospAddress() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getHospAddress(),
              AuditFormSHospGenInfo_.hospAddress
            )
          );
      }
      if (criteria.getHospPhoneNo() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getHospPhoneNo(),
              AuditFormSHospGenInfo_.hospPhoneNo
            )
          );
      }
      if (criteria.getNormalBeds() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getNormalBeds(),
              AuditFormSHospGenInfo_.normalBeds
            )
          );
      }
      if (criteria.getOxygenBeds() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOxygenBeds(),
              AuditFormSHospGenInfo_.oxygenBeds
            )
          );
      }
      if (criteria.getVentilatorBeds() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getVentilatorBeds(),
              AuditFormSHospGenInfo_.ventilatorBeds
            )
          );
      }
      if (criteria.getIcuBeds() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getIcuBeds(),
              AuditFormSHospGenInfo_.icuBeds
            )
          );
      }
      if (criteria.getOnCylinderPatient() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOnCylinderPatient(),
              AuditFormSHospGenInfo_.onCylinderPatient
            )
          );
      }
      if (criteria.getOnPipedBedsPatient() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOnPipedBedsPatient(),
              AuditFormSHospGenInfo_.onPipedBedsPatient
            )
          );
      }
      if (criteria.getOnNIV() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOnNIV(),
              AuditFormSHospGenInfo_.onNIV
            )
          );
      }
      if (criteria.getOnIntubated() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getOnIntubated(),
              AuditFormSHospGenInfo_.onIntubated
            )
          );
      }
      if (criteria.getJumboSystemInstalledType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getJumboSystemInstalledType(),
              AuditFormSHospGenInfo_.jumboSystemInstalledType
            )
          );
      }
      if (criteria.getAvailableCylinderTypeD7() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getAvailableCylinderTypeD7(),
              AuditFormSHospGenInfo_.availableCylinderTypeD7
            )
          );
      }
      if (criteria.getAvailableCylinderTypeB() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getAvailableCylinderTypeB(),
              AuditFormSHospGenInfo_.availableCylinderTypeB
            )
          );
      }
      if (criteria.getCylinderAgencyName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCylinderAgencyName(),
              AuditFormSHospGenInfo_.cylinderAgencyName
            )
          );
      }
      if (criteria.getCylinderAgencyAddress() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCylinderAgencyAddress(),
              AuditFormSHospGenInfo_.cylinderAgencyAddress
            )
          );
      }
      if (criteria.getAvailableLMOCapacityKL() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getAvailableLMOCapacityKL(),
              AuditFormSHospGenInfo_.availableLMOCapacityKL
            )
          );
      }
      if (criteria.getLmoSupplierName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLmoSupplierName(),
              AuditFormSHospGenInfo_.lmoSupplierName
            )
          );
      }
      if (criteria.getLmoSupplierFrequency() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLmoSupplierFrequency(),
              AuditFormSHospGenInfo_.lmoSupplierFrequency
            )
          );
      }
      if (criteria.getLastLmoSuppliedQuantity() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastLmoSuppliedQuantity(),
              AuditFormSHospGenInfo_.lastLmoSuppliedQuantity
            )
          );
      }
      if (criteria.getRemark() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getRemark(),
              AuditFormSHospGenInfo_.remark
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              AuditFormSHospGenInfo_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              AuditFormSHospGenInfo_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              AuditFormSHospGenInfo_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              AuditFormSHospGenInfo_.lastModifiedBy
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              AuditFormSHospGenInfo_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              AuditFormSHospGenInfo_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              AuditFormSHospGenInfo_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              AuditFormSHospGenInfo_.freeField4
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
                  .join(AuditFormSHospGenInfo_.audit, JoinType.LEFT)
                  .get(Audit_.id)
            )
          );
      }
    }
    return specification;
  }
}
