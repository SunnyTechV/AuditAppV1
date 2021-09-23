package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.AnnexureAnswers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AnnexureAnswers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnexureAnswersRepository
  extends
    JpaRepository<AnnexureAnswers, Long>,
    JpaSpecificationExecutor<AnnexureAnswers> {}
