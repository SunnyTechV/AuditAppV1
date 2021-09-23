package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.AnnexureAnswers;
import com.vgtech.auditapp.repository.AnnexureAnswersRepository;
import com.vgtech.auditapp.service.criteria.AnnexureAnswersCriteria;
import com.vgtech.auditapp.service.dto.AnnexureAnswers;
import com.vgtech.auditapp.service.mapper.AnnexureAnswersMapper;
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
 * Service for executing complex queries for {@link AnnexureAnswers} entities in the database.
 * The main input is a {@link AnnexureAnswersCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnnexureAnswers} or a {@link Page} of {@link AnnexureAnswers} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnnexureAnswersQueryService extends QueryService<AnnexureAnswers> {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureAnswersQueryService.class
  );

  private final AnnexureAnswersRepository annexureAnswersRepository;

  private final AnnexureAnswersMapper annexureAnswersMapper;

  public AnnexureAnswersQueryService(
    AnnexureAnswersRepository annexureAnswersRepository,
    AnnexureAnswersMapper annexureAnswersMapper
  ) {
    this.annexureAnswersRepository = annexureAnswersRepository;
    this.annexureAnswersMapper = annexureAnswersMapper;
  }

  /**
   * Return a {@link List} of {@link AnnexureAnswers} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<AnnexureAnswers> findByCriteria(
    AnnexureAnswersCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<AnnexureAnswers> specification = createSpecification(
      criteria
    );
    return annexureAnswersMapper.toDto(
      annexureAnswersRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link AnnexureAnswers} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<AnnexureAnswers> findByCriteria(
    AnnexureAnswersCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<AnnexureAnswers> specification = createSpecification(
      criteria
    );
    return annexureAnswersRepository
      .findAll(specification, page)
      .map(annexureAnswersMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AnnexureAnswersCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<AnnexureAnswers> specification = createSpecification(
      criteria
    );
    return annexureAnswersRepository.count(specification);
  }

  /**
   * Function to convert {@link AnnexureAnswersCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<AnnexureAnswers> createSpecification(
    AnnexureAnswersCriteria criteria
  ) {
    Specification<AnnexureAnswers> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), AnnexureAnswers_.id)
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              AnnexureAnswers_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(criteria.getType(), AnnexureAnswers_.type)
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              AnnexureAnswers_.subType
            )
          );
      }
      if (criteria.getCompliance() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getCompliance(),
              AnnexureAnswers_.compliance
            )
          );
      }
      if (criteria.getComment() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getComment(),
              AnnexureAnswers_.comment
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              AnnexureAnswers_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              AnnexureAnswers_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              AnnexureAnswers_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              AnnexureAnswers_.freeField4
            )
          );
      }
      if (criteria.getRemark() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getRemark(),
              AnnexureAnswers_.remark
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              AnnexureAnswers_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              AnnexureAnswers_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              AnnexureAnswers_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              AnnexureAnswers_.lastModifiedBy
            )
          );
      }
      if (criteria.getAuditId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAuditId(),
              root ->
                root.join(AnnexureAnswers_.audit, JoinType.LEFT).get(Audit_.id)
            )
          );
      }
      if (criteria.getAnnexureQuestionsId() != null) {
        specification =
          specification.and(
            buildSpecification(
              criteria.getAnnexureQuestionsId(),
              root ->
                root
                  .join(AnnexureAnswers_.annexureQuestions, JoinType.LEFT)
                  .get(AnnexureQuestions_.id)
            )
          );
      }
    }
    return specification;
  }
}
