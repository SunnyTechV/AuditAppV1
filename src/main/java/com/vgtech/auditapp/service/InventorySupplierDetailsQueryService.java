package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.InventorySupplierDetails;
import com.vgtech.auditapp.repository.InventorySupplierDetailsRepository;
import com.vgtech.auditapp.service.criteria.InventorySupplierDetailsCriteria;
import com.vgtech.auditapp.service.dto.InventorySupplierDetails;
import com.vgtech.auditapp.service.mapper.InventorySupplierDetailsMapper;
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
 * Service for executing complex queries for {@link InventorySupplierDetails} entities in the database.
 * The main input is a {@link InventorySupplierDetailsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InventorySupplierDetails} or a {@link Page} of {@link InventorySupplierDetails} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InventorySupplierDetailsQueryService
  extends QueryService<InventorySupplierDetails> {

  private final Logger log = LoggerFactory.getLogger(
    InventorySupplierDetailsQueryService.class
  );

  private final InventorySupplierDetailsRepository inventorySupplierDetailsRepository;

  private final InventorySupplierDetailsMapper inventorySupplierDetailsMapper;

  public InventorySupplierDetailsQueryService(
    InventorySupplierDetailsRepository inventorySupplierDetailsRepository,
    InventorySupplierDetailsMapper inventorySupplierDetailsMapper
  ) {
    this.inventorySupplierDetailsRepository =
      inventorySupplierDetailsRepository;
    this.inventorySupplierDetailsMapper = inventorySupplierDetailsMapper;
  }

  /**
   * Return a {@link List} of {@link InventorySupplierDetails} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<InventorySupplierDetails> findByCriteria(
    InventorySupplierDetailsCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<InventorySupplierDetails> specification = createSpecification(
      criteria
    );
    return inventorySupplierDetailsMapper.toDto(
      inventorySupplierDetailsRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link InventorySupplierDetails} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<InventorySupplierDetails> findByCriteria(
    InventorySupplierDetailsCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<InventorySupplierDetails> specification = createSpecification(
      criteria
    );
    return inventorySupplierDetailsRepository
      .findAll(specification, page)
      .map(inventorySupplierDetailsMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(InventorySupplierDetailsCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<InventorySupplierDetails> specification = createSpecification(
      criteria
    );
    return inventorySupplierDetailsRepository.count(specification);
  }

  /**
   * Function to convert {@link InventorySupplierDetailsCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<InventorySupplierDetails> createSpecification(
    InventorySupplierDetailsCriteria criteria
  ) {
    Specification<InventorySupplierDetails> specification = Specification.where(
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
              InventorySupplierDetails_.id
            )
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              InventorySupplierDetails_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getType(),
              InventorySupplierDetails_.type
            )
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              InventorySupplierDetails_.subType
            )
          );
      }
      if (criteria.getTableName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getTableName(),
              InventorySupplierDetails_.tableName
            )
          );
      }
      if (criteria.getSupplierName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSupplierName(),
              InventorySupplierDetails_.supplierName
            )
          );
      }
      if (criteria.getSupplierAddress() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSupplierAddress(),
              InventorySupplierDetails_.supplierAddress
            )
          );
      }
      if (criteria.getSupplierContactName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSupplierContactName(),
              InventorySupplierDetails_.supplierContactName
            )
          );
      }
      if (criteria.getSupplierContactNameNumber() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSupplierContactNameNumber(),
              InventorySupplierDetails_.supplierContactNameNumber
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              InventorySupplierDetails_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              InventorySupplierDetails_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              InventorySupplierDetails_.freeField3
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              InventorySupplierDetails_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              InventorySupplierDetails_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              InventorySupplierDetails_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              InventorySupplierDetails_.lastModifiedBy
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
                  .join(InventorySupplierDetails_.audit, JoinType.LEFT)
                  .get(Audit_.id)
            )
          );
      }
    }
    return specification;
  }
}
