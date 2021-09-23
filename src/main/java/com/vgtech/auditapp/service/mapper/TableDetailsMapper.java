package com.vgtech.auditapp.service.mapper;

import com.vgtech.auditapp.domain.*;
import com.vgtech.auditapp.service.dto.TableDetails;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TableDetails} and its DTO {@link TableDetails}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TableDetailsMapper
  extends EntityMapper<TableDetails, TableDetails> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  TableDetails toDtoId(TableDetails tableDetails);
}
