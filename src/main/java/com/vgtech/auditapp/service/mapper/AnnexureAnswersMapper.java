package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AnnexureAnswers;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnnexureAnswers} and its DTO {@link AnnexureAnswers}.
 */
@Mapper(
  componentModel = "spring",
  uses = { AuditMapper.class, AnnexureQuestionsMapper.class }
)
public interface AnnexureAnswersMapper
  extends EntityMapper<AnnexureAnswers, AnnexureAnswers> {
  @Mapping(target = "audit", source = "audit", qualifiedByName = "id")
  @Mapping(
    target = "annexureQuestions",
    source = "annexureQuestions",
    qualifiedByName = "id"
  )
  AnnexureAnswers toDto(AnnexureAnswers s);
}
