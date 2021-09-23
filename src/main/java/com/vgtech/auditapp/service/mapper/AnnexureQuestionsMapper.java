package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.AnnexureQuestions;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnnexureQuestions} and its DTO {@link AnnexureQuestions}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnnexureQuestionsMapper
  extends EntityMapper<AnnexureQuestions, AnnexureQuestions> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  AnnexureQuestions toDtoId(AnnexureQuestions annexureQuestions);
}
