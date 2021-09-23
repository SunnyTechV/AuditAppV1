package com.vgtech.auditapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnexureQuestionsTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(AnnexureQuestions.class);
    AnnexureQuestions annexureQuestions1 = new AnnexureQuestions();
    annexureQuestions1.setId(1L);
    AnnexureQuestions annexureQuestions2 = new AnnexureQuestions();
    annexureQuestions2.setId(annexureQuestions1.getId());
    assertThat(annexureQuestions1).isEqualTo(annexureQuestions2);
    annexureQuestions2.setId(2L);
    assertThat(annexureQuestions1).isNotEqualTo(annexureQuestions2);
    annexureQuestions1.setId(null);
    assertThat(annexureQuestions1).isNotEqualTo(annexureQuestions2);
  }
}
