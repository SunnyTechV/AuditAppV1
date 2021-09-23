package com.vgtech.auditapp.service;

import com.vgtech.auditapp.domain.*; // for static metamodels
import com.vgtech.auditapp.domain.AnnexureQuestions;
import com.vgtech.auditapp.repository.AnnexureQuestionsRepository;
import com.vgtech.auditapp.service.criteria.AnnexureQuestionsCriteria;
import com.vgtech.auditapp.service.dto.AnnexureQuestions;
import com.vgtech.auditapp.service.mapper.AnnexureQuestionsMapper;
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
 * Service for executing complex queries for {@link AnnexureQuestions} entities in the database.
 * The main input is a {@link AnnexureQuestionsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnnexureQuestions} or a {@link Page} of {@link AnnexureQuestions} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnnexureQuestionsQueryService
  extends QueryService<AnnexureQuestions> {

  private final Logger log = LoggerFactory.getLogger(
    AnnexureQuestionsQueryService.class
  );

  private final AnnexureQuestionsRepository annexureQuestionsRepository;

  private final AnnexureQuestionsMapper annexureQuestionsMapper;

  public AnnexureQuestionsQueryService(
    AnnexureQuestionsRepository annexureQuestionsRepository,
    AnnexureQuestionsMapper annexureQuestionsMapper
  ) {
    this.annexureQuestionsRepository = annexureQuestionsRepository;
    this.annexureQuestionsMapper = annexureQuestionsMapper;
  }

  /**
   * Return a {@link List} of {@link AnnexureQuestions} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public List<AnnexureQuestions> findByCriteria(
    AnnexureQuestionsCriteria criteria
  ) {
    log.debug("find by criteria : {}", criteria);
    final Specification<AnnexureQuestions> specification = createSpecification(
      criteria
    );
    return annexureQuestionsMapper.toDto(
      annexureQuestionsRepository.findAll(specification)
    );
  }

  /**
   * Return a {@link Page} of {@link AnnexureQuestions} which matches the criteria from the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @param page The page, which should be returned.
   * @return the matching entities.
   */
  @Transactional(readOnly = true)
  public Page<AnnexureQuestions> findByCriteria(
    AnnexureQuestionsCriteria criteria,
    Pageable page
  ) {
    log.debug("find by criteria : {}, page: {}", criteria, page);
    final Specification<AnnexureQuestions> specification = createSpecification(
      criteria
    );
    return annexureQuestionsRepository
      .findAll(specification, page)
      .map(annexureQuestionsMapper::toDto);
  }

  /**
   * Return the number of matching entities in the database.
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the number of matching entities.
   */
  @Transactional(readOnly = true)
  public long countByCriteria(AnnexureQuestionsCriteria criteria) {
    log.debug("count by criteria : {}", criteria);
    final Specification<AnnexureQuestions> specification = createSpecification(
      criteria
    );
    return annexureQuestionsRepository.count(specification);
  }

  /**
   * Function to convert {@link AnnexureQuestionsCriteria} to a {@link Specification}
   * @param criteria The object which holds all the filters, which the entities should match.
   * @return the matching {@link Specification} of the entity.
   */
  protected Specification<AnnexureQuestions> createSpecification(
    AnnexureQuestionsCriteria criteria
  ) {
    Specification<AnnexureQuestions> specification = Specification.where(null);
    if (criteria != null) {
      // This has to be called first, because the distinct method returns null
      if (criteria.getDistinct() != null) {
        specification = specification.and(distinct(criteria.getDistinct()));
      }
      if (criteria.getId() != null) {
        specification =
          specification.and(
            buildRangeSpecification(criteria.getId(), AnnexureQuestions_.id)
          );
      }
      if (criteria.getFormName() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFormName(),
              AnnexureQuestions_.formName
            )
          );
      }
      if (criteria.getType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getType(),
              AnnexureQuestions_.type
            )
          );
      }
      if (criteria.getSubType() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getSubType(),
              AnnexureQuestions_.subType
            )
          );
      }
      if (criteria.getDescription() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getDescription(),
              AnnexureQuestions_.description
            )
          );
      }
      if (criteria.getFreeField1() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField1(),
              AnnexureQuestions_.freeField1
            )
          );
      }
      if (criteria.getFreeField2() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField2(),
              AnnexureQuestions_.freeField2
            )
          );
      }
      if (criteria.getFreeField3() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField3(),
              AnnexureQuestions_.freeField3
            )
          );
      }
      if (criteria.getFreeField4() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getFreeField4(),
              AnnexureQuestions_.freeField4
            )
          );
      }
      if (criteria.getCreatedDate() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getCreatedDate(),
              AnnexureQuestions_.createdDate
            )
          );
      }
      if (criteria.getCreatedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getCreatedBy(),
              AnnexureQuestions_.createdBy
            )
          );
      }
      if (criteria.getLastModified() != null) {
        specification =
          specification.and(
            buildRangeSpecification(
              criteria.getLastModified(),
              AnnexureQuestions_.lastModified
            )
          );
      }
      if (criteria.getLastModifiedBy() != null) {
        specification =
          specification.and(
            buildStringSpecification(
              criteria.getLastModifiedBy(),
              AnnexureQuestions_.lastModifiedBy
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
                  .join(AnnexureQuestions_.annexureAnswers, JoinType.LEFT)
                  .get(AnnexureAnswers_.id)
            )
          );
      }
    }
    return specification;
  }
}
