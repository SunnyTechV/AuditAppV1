package com.vgtech.auditapp.repository;

import com.vgtech.auditapp.domain.AnnexureQuestions;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the AnnexureQuestions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnexureQuestionsRepository
  extends
    JpaRepository<AnnexureQuestions, Long>,
    JpaSpecificationExecutor<AnnexureQuestions> {}
