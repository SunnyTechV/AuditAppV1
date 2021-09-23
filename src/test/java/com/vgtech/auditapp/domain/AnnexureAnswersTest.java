package com.vgtech.auditapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.vgtech.auditapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnnexureAnswersTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(AnnexureAnswers.class);
    AnnexureAnswers annexureAnswers1 = new AnnexureAnswers();
    annexureAnswers1.setId(1L);
    AnnexureAnswers annexureAnswers2 = new AnnexureAnswers();
    annexureAnswers2.setId(annexureAnswers1.getId());
    assertThat(annexureAnswers1).isEqualTo(annexureAnswers2);
    annexureAnswers2.setId(2L);
    assertThat(annexureAnswers1).isNotEqualTo(annexureAnswers2);
    annexureAnswers1.setId(null);
    assertThat(annexureAnswers1).isNotEqualTo(annexureAnswers2);
  }
}
