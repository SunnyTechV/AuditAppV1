package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.AuditPatientMonitoringForm;
import com.vgtech.auditapp.repository.AuditPatientMonitoringFormRepository;
import com.vgtech.auditapp.service.criteria.AuditPatientMonitoringFormCriteria;
import com.vgtech.auditapp.service.dto.AuditPatientMonitoringForm;
import com.vgtech.auditapp.service.mapper.AuditPatientMonitoringFormMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AuditPatientMonitoringFormResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditPatientMonitoringFormResourceIT {

  private static final Integer DEFAULT_WARD_NO = 1;
  private static final Integer UPDATED_WARD_NO = 2;
  private static final Integer SMALLER_WARD_NO = 1 - 1;

  private static final String DEFAULT_PATIENT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_PATIENT_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_BED_NO = 1;
  private static final Integer UPDATED_BED_NO = 2;
  private static final Integer SMALLER_BED_NO = 1 - 1;

  private static final LocalDate DEFAULT_DATE_OF_ADMISSION = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_DATE_OF_ADMISSION = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_DATE_OF_ADMISSION = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_OXYGEN_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_OXYGEN_TYPE = "BBBBBBBBBB";

  private static final Double DEFAULT_SIX_TO_EIGHT_AM = 1D;
  private static final Double UPDATED_SIX_TO_EIGHT_AM = 2D;
  private static final Double SMALLER_SIX_TO_EIGHT_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_SIX_TO_EIGHT_AM = 1D;
  private static final Double UPDATED_OXY_SIX_TO_EIGHT_AM = 2D;
  private static final Double SMALLER_OXY_SIX_TO_EIGHT_AM = 1D - 1D;

  private static final Double DEFAULT_EIGHT_TO_TEN_AM = 1D;
  private static final Double UPDATED_EIGHT_TO_TEN_AM = 2D;
  private static final Double SMALLER_EIGHT_TO_TEN_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_EIGHT_TO_TEN_AM = 1D;
  private static final Double UPDATED_OXY_EIGHT_TO_TEN_AM = 2D;
  private static final Double SMALLER_OXY_EIGHT_TO_TEN_AM = 1D - 1D;

  private static final Double DEFAULT_TEN_TO_TWELVE_AM = 1D;
  private static final Double UPDATED_TEN_TO_TWELVE_AM = 2D;
  private static final Double SMALLER_TEN_TO_TWELVE_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_TEN_TO_TWELVE_AM = 1D;
  private static final Double UPDATED_OXY_TEN_TO_TWELVE_AM = 2D;
  private static final Double SMALLER_OXY_TEN_TO_TWELVE_AM = 1D - 1D;

  private static final Double DEFAULT_TWELVE_TO_TOW_PM = 1D;
  private static final Double UPDATED_TWELVE_TO_TOW_PM = 2D;
  private static final Double SMALLER_TWELVE_TO_TOW_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_TWELVE_TO_TOW_PM = 1D;
  private static final Double UPDATED_OXY_TWELVE_TO_TOW_PM = 2D;
  private static final Double SMALLER_OXY_TWELVE_TO_TOW_PM = 1D - 1D;

  private static final Double DEFAULT_TWO_TO_FOUR_PM = 1D;
  private static final Double UPDATED_TWO_TO_FOUR_PM = 2D;
  private static final Double SMALLER_TWO_TO_FOUR_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_TWO_TO_FOUR_PM = 1D;
  private static final Double UPDATED_OXY_TWO_TO_FOUR_PM = 2D;
  private static final Double SMALLER_OXY_TWO_TO_FOUR_PM = 1D - 1D;

  private static final Double DEFAULT_FOUR_TO_SIX_PM = 1D;
  private static final Double UPDATED_FOUR_TO_SIX_PM = 2D;
  private static final Double SMALLER_FOUR_TO_SIX_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_FOUR_TO_SIX_PM = 1D;
  private static final Double UPDATED_OXY_FOUR_TO_SIX_PM = 2D;
  private static final Double SMALLER_OXY_FOUR_TO_SIX_PM = 1D - 1D;

  private static final Double DEFAULT_SIX_TO_EIGHT_PM = 1D;
  private static final Double UPDATED_SIX_TO_EIGHT_PM = 2D;
  private static final Double SMALLER_SIX_TO_EIGHT_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_SIX_TO_EIGHT_PM = 1D;
  private static final Double UPDATED_OXY_SIX_TO_EIGHT_PM = 2D;
  private static final Double SMALLER_OXY_SIX_TO_EIGHT_PM = 1D - 1D;

  private static final Double DEFAULT_EIGHT_TO_TEN_PM = 1D;
  private static final Double UPDATED_EIGHT_TO_TEN_PM = 2D;
  private static final Double SMALLER_EIGHT_TO_TEN_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_EIGHT_TO_TEN_PM = 1D;
  private static final Double UPDATED_OXY_EIGHT_TO_TEN_PM = 2D;
  private static final Double SMALLER_OXY_EIGHT_TO_TEN_PM = 1D - 1D;

  private static final Double DEFAULT_TEN_TO_TWELVE_PM = 1D;
  private static final Double UPDATED_TEN_TO_TWELVE_PM = 2D;
  private static final Double SMALLER_TEN_TO_TWELVE_PM = 1D - 1D;

  private static final Double DEFAULT_OXY_TEN_TO_TWELVE_PM = 1D;
  private static final Double UPDATED_OXY_TEN_TO_TWELVE_PM = 2D;
  private static final Double SMALLER_OXY_TEN_TO_TWELVE_PM = 1D - 1D;

  private static final Double DEFAULT_TWELVE_TO_TWO_AM = 1D;
  private static final Double UPDATED_TWELVE_TO_TWO_AM = 2D;
  private static final Double SMALLER_TWELVE_TO_TWO_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_TWELVE_TO_TWO_AM = 1D;
  private static final Double UPDATED_OXY_TWELVE_TO_TWO_AM = 2D;
  private static final Double SMALLER_OXY_TWELVE_TO_TWO_AM = 1D - 1D;

  private static final Double DEFAULT_TWO_TO_FOUR_AM = 1D;
  private static final Double UPDATED_TWO_TO_FOUR_AM = 2D;
  private static final Double SMALLER_TWO_TO_FOUR_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_TWO_TO_FOUR_AM = 1D;
  private static final Double UPDATED_OXY_TWO_TO_FOUR_AM = 2D;
  private static final Double SMALLER_OXY_TWO_TO_FOUR_AM = 1D - 1D;

  private static final Double DEFAULT_FOUR_TO_SIX_AM = 1D;
  private static final Double UPDATED_FOUR_TO_SIX_AM = 2D;
  private static final Double SMALLER_FOUR_TO_SIX_AM = 1D - 1D;

  private static final Double DEFAULT_OXY_FOUR_TO_SIX_AM = 1D;
  private static final Double UPDATED_OXY_FOUR_TO_SIX_AM = 2D;
  private static final Double SMALLER_OXY_FOUR_TO_SIX_AM = 1D - 1D;

  private static final String DEFAULT_DR_NAME = "AAAAAAAAAA";
  private static final String UPDATED_DR_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_NURSE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NURSE_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_CREATED_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_LAST_MODIFIED = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_LAST_MODIFIED = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_LAST_MODIFIED = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

  private static final String ENTITY_API_URL =
    "/api/audit-patient-monitoring-forms";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private AuditPatientMonitoringFormRepository auditPatientMonitoringFormRepository;

  @Autowired
  private AuditPatientMonitoringFormMapper auditPatientMonitoringFormMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAuditPatientMonitoringFormMockMvc;

  private AuditPatientMonitoringForm auditPatientMonitoringForm;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AuditPatientMonitoringForm createEntity(EntityManager em) {
    AuditPatientMonitoringForm auditPatientMonitoringForm = new AuditPatientMonitoringForm()
      .wardNo(DEFAULT_WARD_NO)
      .patientName(DEFAULT_PATIENT_NAME)
      .bedNo(DEFAULT_BED_NO)
      .dateOfAdmission(DEFAULT_DATE_OF_ADMISSION)
      .oxygenType(DEFAULT_OXYGEN_TYPE)
      .sixToEightAM(DEFAULT_SIX_TO_EIGHT_AM)
      .oxySixToEightAM(DEFAULT_OXY_SIX_TO_EIGHT_AM)
      .eightToTenAM(DEFAULT_EIGHT_TO_TEN_AM)
      .oxyEightToTenAM(DEFAULT_OXY_EIGHT_TO_TEN_AM)
      .tenToTwelveAM(DEFAULT_TEN_TO_TWELVE_AM)
      .oxyTenToTwelveAM(DEFAULT_OXY_TEN_TO_TWELVE_AM)
      .twelveToTowPM(DEFAULT_TWELVE_TO_TOW_PM)
      .oxyTwelveToTowPM(DEFAULT_OXY_TWELVE_TO_TOW_PM)
      .twoToFourPM(DEFAULT_TWO_TO_FOUR_PM)
      .oxyTwoToFourPM(DEFAULT_OXY_TWO_TO_FOUR_PM)
      .fourToSixPM(DEFAULT_FOUR_TO_SIX_PM)
      .oxyFourToSixPM(DEFAULT_OXY_FOUR_TO_SIX_PM)
      .sixToEightPM(DEFAULT_SIX_TO_EIGHT_PM)
      .oxySixToEightPM(DEFAULT_OXY_SIX_TO_EIGHT_PM)
      .eightToTenPM(DEFAULT_EIGHT_TO_TEN_PM)
      .oxyEightToTenPM(DEFAULT_OXY_EIGHT_TO_TEN_PM)
      .tenToTwelvePM(DEFAULT_TEN_TO_TWELVE_PM)
      .oxyTenToTwelvePM(DEFAULT_OXY_TEN_TO_TWELVE_PM)
      .twelveToTwoAM(DEFAULT_TWELVE_TO_TWO_AM)
      .oxyTwelveToTwoAM(DEFAULT_OXY_TWELVE_TO_TWO_AM)
      .twoToFourAM(DEFAULT_TWO_TO_FOUR_AM)
      .oxyTwoToFourAM(DEFAULT_OXY_TWO_TO_FOUR_AM)
      .fourToSixAM(DEFAULT_FOUR_TO_SIX_AM)
      .oxyFourToSixAM(DEFAULT_OXY_FOUR_TO_SIX_AM)
      .drName(DEFAULT_DR_NAME)
      .nurseName(DEFAULT_NURSE_NAME)
      .createdBy(DEFAULT_CREATED_BY)
      .createdDate(DEFAULT_CREATED_DATE)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4);
    return auditPatientMonitoringForm;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AuditPatientMonitoringForm createUpdatedEntity(
    EntityManager em
  ) {
    AuditPatientMonitoringForm auditPatientMonitoringForm = new AuditPatientMonitoringForm()
      .wardNo(UPDATED_WARD_NO)
      .patientName(UPDATED_PATIENT_NAME)
      .bedNo(UPDATED_BED_NO)
      .dateOfAdmission(UPDATED_DATE_OF_ADMISSION)
      .oxygenType(UPDATED_OXYGEN_TYPE)
      .sixToEightAM(UPDATED_SIX_TO_EIGHT_AM)
      .oxySixToEightAM(UPDATED_OXY_SIX_TO_EIGHT_AM)
      .eightToTenAM(UPDATED_EIGHT_TO_TEN_AM)
      .oxyEightToTenAM(UPDATED_OXY_EIGHT_TO_TEN_AM)
      .tenToTwelveAM(UPDATED_TEN_TO_TWELVE_AM)
      .oxyTenToTwelveAM(UPDATED_OXY_TEN_TO_TWELVE_AM)
      .twelveToTowPM(UPDATED_TWELVE_TO_TOW_PM)
      .oxyTwelveToTowPM(UPDATED_OXY_TWELVE_TO_TOW_PM)
      .twoToFourPM(UPDATED_TWO_TO_FOUR_PM)
      .oxyTwoToFourPM(UPDATED_OXY_TWO_TO_FOUR_PM)
      .fourToSixPM(UPDATED_FOUR_TO_SIX_PM)
      .oxyFourToSixPM(UPDATED_OXY_FOUR_TO_SIX_PM)
      .sixToEightPM(UPDATED_SIX_TO_EIGHT_PM)
      .oxySixToEightPM(UPDATED_OXY_SIX_TO_EIGHT_PM)
      .eightToTenPM(UPDATED_EIGHT_TO_TEN_PM)
      .oxyEightToTenPM(UPDATED_OXY_EIGHT_TO_TEN_PM)
      .tenToTwelvePM(UPDATED_TEN_TO_TWELVE_PM)
      .oxyTenToTwelvePM(UPDATED_OXY_TEN_TO_TWELVE_PM)
      .twelveToTwoAM(UPDATED_TWELVE_TO_TWO_AM)
      .oxyTwelveToTwoAM(UPDATED_OXY_TWELVE_TO_TWO_AM)
      .twoToFourAM(UPDATED_TWO_TO_FOUR_AM)
      .oxyTwoToFourAM(UPDATED_OXY_TWO_TO_FOUR_AM)
      .fourToSixAM(UPDATED_FOUR_TO_SIX_AM)
      .oxyFourToSixAM(UPDATED_OXY_FOUR_TO_SIX_AM)
      .drName(UPDATED_DR_NAME)
      .nurseName(UPDATED_NURSE_NAME)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    return auditPatientMonitoringForm;
  }

  @BeforeEach
  public void initTest() {
    auditPatientMonitoringForm = createEntity(em);
  }

  @Test
  @Transactional
  void createAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeCreate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );
    restAuditPatientMonitoringFormMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringForm)
          )
      )
      .andExpect(status().isCreated());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeCreate + 1);
    AuditPatientMonitoringForm testAuditPatientMonitoringForm = auditPatientMonitoringFormList.get(
      auditPatientMonitoringFormList.size() - 1
    );
    assertThat(testAuditPatientMonitoringForm.getWardNo())
      .isEqualTo(DEFAULT_WARD_NO);
    assertThat(testAuditPatientMonitoringForm.getPatientName())
      .isEqualTo(DEFAULT_PATIENT_NAME);
    assertThat(testAuditPatientMonitoringForm.getBedNo())
      .isEqualTo(DEFAULT_BED_NO);
    assertThat(testAuditPatientMonitoringForm.getDateOfAdmission())
      .isEqualTo(DEFAULT_DATE_OF_ADMISSION);
    assertThat(testAuditPatientMonitoringForm.getOxygenType())
      .isEqualTo(DEFAULT_OXYGEN_TYPE);
    assertThat(testAuditPatientMonitoringForm.getSixToEightAM())
      .isEqualTo(DEFAULT_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightAM())
      .isEqualTo(DEFAULT_OXY_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenAM())
      .isEqualTo(DEFAULT_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenAM())
      .isEqualTo(DEFAULT_OXY_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelveAM())
      .isEqualTo(DEFAULT_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelveAM())
      .isEqualTo(DEFAULT_OXY_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTowPM())
      .isEqualTo(DEFAULT_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTowPM())
      .isEqualTo(DEFAULT_OXY_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourPM())
      .isEqualTo(DEFAULT_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourPM())
      .isEqualTo(DEFAULT_OXY_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixPM())
      .isEqualTo(DEFAULT_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixPM())
      .isEqualTo(DEFAULT_OXY_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getSixToEightPM())
      .isEqualTo(DEFAULT_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightPM())
      .isEqualTo(DEFAULT_OXY_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenPM())
      .isEqualTo(DEFAULT_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenPM())
      .isEqualTo(DEFAULT_OXY_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelvePM())
      .isEqualTo(DEFAULT_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelvePM())
      .isEqualTo(DEFAULT_OXY_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTwoAM())
      .isEqualTo(DEFAULT_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTwoAM())
      .isEqualTo(DEFAULT_OXY_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourAM())
      .isEqualTo(DEFAULT_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourAM())
      .isEqualTo(DEFAULT_OXY_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixAM())
      .isEqualTo(DEFAULT_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixAM())
      .isEqualTo(DEFAULT_OXY_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getDrName())
      .isEqualTo(DEFAULT_DR_NAME);
    assertThat(testAuditPatientMonitoringForm.getNurseName())
      .isEqualTo(DEFAULT_NURSE_NAME);
    assertThat(testAuditPatientMonitoringForm.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAuditPatientMonitoringForm.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testAuditPatientMonitoringForm.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    assertThat(testAuditPatientMonitoringForm.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAuditPatientMonitoringForm.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAuditPatientMonitoringForm.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAuditPatientMonitoringForm.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAuditPatientMonitoringForm.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void createAuditPatientMonitoringFormWithExistingId() throws Exception {
    // Create the AuditPatientMonitoringForm with an existing ID
    auditPatientMonitoringForm.setId(1L);
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    int databaseSizeBeforeCreate = auditPatientMonitoringFormRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAuditPatientMonitoringFormMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringForm)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringForms() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(auditPatientMonitoringForm.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].wardNo").value(hasItem(DEFAULT_WARD_NO)))
      .andExpect(
        jsonPath("$.[*].patientName").value(hasItem(DEFAULT_PATIENT_NAME))
      )
      .andExpect(jsonPath("$.[*].bedNo").value(hasItem(DEFAULT_BED_NO)))
      .andExpect(
        jsonPath("$.[*].dateOfAdmission")
          .value(hasItem(DEFAULT_DATE_OF_ADMISSION.toString()))
      )
      .andExpect(
        jsonPath("$.[*].oxygenType").value(hasItem(DEFAULT_OXYGEN_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].sixToEightAM")
          .value(hasItem(DEFAULT_SIX_TO_EIGHT_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxySixToEightAM")
          .value(hasItem(DEFAULT_OXY_SIX_TO_EIGHT_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].eightToTenAM")
          .value(hasItem(DEFAULT_EIGHT_TO_TEN_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyEightToTenAM")
          .value(hasItem(DEFAULT_OXY_EIGHT_TO_TEN_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].tenToTwelveAM")
          .value(hasItem(DEFAULT_TEN_TO_TWELVE_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTenToTwelveAM")
          .value(hasItem(DEFAULT_OXY_TEN_TO_TWELVE_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twelveToTowPM")
          .value(hasItem(DEFAULT_TWELVE_TO_TOW_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwelveToTowPM")
          .value(hasItem(DEFAULT_OXY_TWELVE_TO_TOW_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twoToFourPM")
          .value(hasItem(DEFAULT_TWO_TO_FOUR_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwoToFourPM")
          .value(hasItem(DEFAULT_OXY_TWO_TO_FOUR_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].fourToSixPM")
          .value(hasItem(DEFAULT_FOUR_TO_SIX_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyFourToSixPM")
          .value(hasItem(DEFAULT_OXY_FOUR_TO_SIX_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].sixToEightPM")
          .value(hasItem(DEFAULT_SIX_TO_EIGHT_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxySixToEightPM")
          .value(hasItem(DEFAULT_OXY_SIX_TO_EIGHT_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].eightToTenPM")
          .value(hasItem(DEFAULT_EIGHT_TO_TEN_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyEightToTenPM")
          .value(hasItem(DEFAULT_OXY_EIGHT_TO_TEN_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].tenToTwelvePM")
          .value(hasItem(DEFAULT_TEN_TO_TWELVE_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTenToTwelvePM")
          .value(hasItem(DEFAULT_OXY_TEN_TO_TWELVE_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twelveToTwoAM")
          .value(hasItem(DEFAULT_TWELVE_TO_TWO_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwelveToTwoAM")
          .value(hasItem(DEFAULT_OXY_TWELVE_TO_TWO_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twoToFourAM")
          .value(hasItem(DEFAULT_TWO_TO_FOUR_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwoToFourAM")
          .value(hasItem(DEFAULT_OXY_TWO_TO_FOUR_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].fourToSixAM")
          .value(hasItem(DEFAULT_FOUR_TO_SIX_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyFourToSixAM")
          .value(hasItem(DEFAULT_OXY_FOUR_TO_SIX_AM.doubleValue()))
      )
      .andExpect(jsonPath("$.[*].drName").value(hasItem(DEFAULT_DR_NAME)))
      .andExpect(jsonPath("$.[*].nurseName").value(hasItem(DEFAULT_NURSE_NAME)))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
      )
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1))
      )
      .andExpect(
        jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2))
      )
      .andExpect(
        jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3))
      )
      .andExpect(
        jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4))
      );
  }

  @Test
  @Transactional
  void getAuditPatientMonitoringForm() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get the auditPatientMonitoringForm
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL_ID, auditPatientMonitoringForm.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.id").value(auditPatientMonitoringForm.getId().intValue())
      )
      .andExpect(jsonPath("$.wardNo").value(DEFAULT_WARD_NO))
      .andExpect(jsonPath("$.patientName").value(DEFAULT_PATIENT_NAME))
      .andExpect(jsonPath("$.bedNo").value(DEFAULT_BED_NO))
      .andExpect(
        jsonPath("$.dateOfAdmission")
          .value(DEFAULT_DATE_OF_ADMISSION.toString())
      )
      .andExpect(jsonPath("$.oxygenType").value(DEFAULT_OXYGEN_TYPE))
      .andExpect(
        jsonPath("$.sixToEightAM").value(DEFAULT_SIX_TO_EIGHT_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxySixToEightAM")
          .value(DEFAULT_OXY_SIX_TO_EIGHT_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.eightToTenAM").value(DEFAULT_EIGHT_TO_TEN_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyEightToTenAM")
          .value(DEFAULT_OXY_EIGHT_TO_TEN_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.tenToTwelveAM")
          .value(DEFAULT_TEN_TO_TWELVE_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTenToTwelveAM")
          .value(DEFAULT_OXY_TEN_TO_TWELVE_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.twelveToTowPM")
          .value(DEFAULT_TWELVE_TO_TOW_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTwelveToTowPM")
          .value(DEFAULT_OXY_TWELVE_TO_TOW_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.twoToFourPM").value(DEFAULT_TWO_TO_FOUR_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTwoToFourPM")
          .value(DEFAULT_OXY_TWO_TO_FOUR_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.fourToSixPM").value(DEFAULT_FOUR_TO_SIX_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyFourToSixPM")
          .value(DEFAULT_OXY_FOUR_TO_SIX_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.sixToEightPM").value(DEFAULT_SIX_TO_EIGHT_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxySixToEightPM")
          .value(DEFAULT_OXY_SIX_TO_EIGHT_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.eightToTenPM").value(DEFAULT_EIGHT_TO_TEN_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyEightToTenPM")
          .value(DEFAULT_OXY_EIGHT_TO_TEN_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.tenToTwelvePM")
          .value(DEFAULT_TEN_TO_TWELVE_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTenToTwelvePM")
          .value(DEFAULT_OXY_TEN_TO_TWELVE_PM.doubleValue())
      )
      .andExpect(
        jsonPath("$.twelveToTwoAM")
          .value(DEFAULT_TWELVE_TO_TWO_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTwelveToTwoAM")
          .value(DEFAULT_OXY_TWELVE_TO_TWO_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.twoToFourAM").value(DEFAULT_TWO_TO_FOUR_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyTwoToFourAM")
          .value(DEFAULT_OXY_TWO_TO_FOUR_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.fourToSixAM").value(DEFAULT_FOUR_TO_SIX_AM.doubleValue())
      )
      .andExpect(
        jsonPath("$.oxyFourToSixAM")
          .value(DEFAULT_OXY_FOUR_TO_SIX_AM.doubleValue())
      )
      .andExpect(jsonPath("$.drName").value(DEFAULT_DR_NAME))
      .andExpect(jsonPath("$.nurseName").value(DEFAULT_NURSE_NAME))
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(
        jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString())
      )
      .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
      .andExpect(
        jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString())
      )
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4));
  }

  @Test
  @Transactional
  void getAuditPatientMonitoringFormsByIdFiltering() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    Long id = auditPatientMonitoringForm.getId();

    defaultAuditPatientMonitoringFormShouldBeFound("id.equals=" + id);
    defaultAuditPatientMonitoringFormShouldNotBeFound("id.notEquals=" + id);

    defaultAuditPatientMonitoringFormShouldBeFound(
      "id.greaterThanOrEqual=" + id
    );
    defaultAuditPatientMonitoringFormShouldNotBeFound("id.greaterThan=" + id);

    defaultAuditPatientMonitoringFormShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAuditPatientMonitoringFormShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo equals to DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.equals=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo equals to UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.equals=" + UPDATED_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo not equals to DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.notEquals=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo not equals to UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.notEquals=" + UPDATED_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo in DEFAULT_WARD_NO or UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.in=" + DEFAULT_WARD_NO + "," + UPDATED_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo equals to UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.in=" + UPDATED_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo is not null
    defaultAuditPatientMonitoringFormShouldBeFound("wardNo.specified=true");

    // Get all the auditPatientMonitoringFormList where wardNo is null
    defaultAuditPatientMonitoringFormShouldNotBeFound("wardNo.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo is greater than or equal to DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.greaterThanOrEqual=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo is greater than or equal to UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.greaterThanOrEqual=" + UPDATED_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo is less than or equal to DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.lessThanOrEqual=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo is less than or equal to SMALLER_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.lessThanOrEqual=" + SMALLER_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo is less than DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.lessThan=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo is less than UPDATED_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.lessThan=" + UPDATED_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByWardNoIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where wardNo is greater than DEFAULT_WARD_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "wardNo.greaterThan=" + DEFAULT_WARD_NO
    );

    // Get all the auditPatientMonitoringFormList where wardNo is greater than SMALLER_WARD_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "wardNo.greaterThan=" + SMALLER_WARD_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName equals to DEFAULT_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.equals=" + DEFAULT_PATIENT_NAME
    );

    // Get all the auditPatientMonitoringFormList where patientName equals to UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.equals=" + UPDATED_PATIENT_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName not equals to DEFAULT_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.notEquals=" + DEFAULT_PATIENT_NAME
    );

    // Get all the auditPatientMonitoringFormList where patientName not equals to UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.notEquals=" + UPDATED_PATIENT_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName in DEFAULT_PATIENT_NAME or UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.in=" + DEFAULT_PATIENT_NAME + "," + UPDATED_PATIENT_NAME
    );

    // Get all the auditPatientMonitoringFormList where patientName equals to UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.in=" + UPDATED_PATIENT_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where patientName is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName contains DEFAULT_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.contains=" + DEFAULT_PATIENT_NAME
    );

    // Get all the auditPatientMonitoringFormList where patientName contains UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.contains=" + UPDATED_PATIENT_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByPatientNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where patientName does not contain DEFAULT_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "patientName.doesNotContain=" + DEFAULT_PATIENT_NAME
    );

    // Get all the auditPatientMonitoringFormList where patientName does not contain UPDATED_PATIENT_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "patientName.doesNotContain=" + UPDATED_PATIENT_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo equals to DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.equals=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo equals to UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.equals=" + UPDATED_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo not equals to DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.notEquals=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo not equals to UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.notEquals=" + UPDATED_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo in DEFAULT_BED_NO or UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.in=" + DEFAULT_BED_NO + "," + UPDATED_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo equals to UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.in=" + UPDATED_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo is not null
    defaultAuditPatientMonitoringFormShouldBeFound("bedNo.specified=true");

    // Get all the auditPatientMonitoringFormList where bedNo is null
    defaultAuditPatientMonitoringFormShouldNotBeFound("bedNo.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo is greater than or equal to DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.greaterThanOrEqual=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo is greater than or equal to UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.greaterThanOrEqual=" + UPDATED_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo is less than or equal to DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.lessThanOrEqual=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo is less than or equal to SMALLER_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.lessThanOrEqual=" + SMALLER_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo is less than DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.lessThan=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo is less than UPDATED_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.lessThan=" + UPDATED_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByBedNoIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where bedNo is greater than DEFAULT_BED_NO
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "bedNo.greaterThan=" + DEFAULT_BED_NO
    );

    // Get all the auditPatientMonitoringFormList where bedNo is greater than SMALLER_BED_NO
    defaultAuditPatientMonitoringFormShouldBeFound(
      "bedNo.greaterThan=" + SMALLER_BED_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission equals to DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.equals=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission equals to UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.equals=" + UPDATED_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission not equals to DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.notEquals=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission not equals to UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.notEquals=" + UPDATED_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission in DEFAULT_DATE_OF_ADMISSION or UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.in=" +
      DEFAULT_DATE_OF_ADMISSION +
      "," +
      UPDATED_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission equals to UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.in=" + UPDATED_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is greater than or equal to DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.greaterThanOrEqual=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is greater than or equal to UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.greaterThanOrEqual=" + UPDATED_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is less than or equal to DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.lessThanOrEqual=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is less than or equal to SMALLER_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.lessThanOrEqual=" + SMALLER_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is less than DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.lessThan=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is less than UPDATED_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.lessThan=" + UPDATED_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDateOfAdmissionIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is greater than DEFAULT_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "dateOfAdmission.greaterThan=" + DEFAULT_DATE_OF_ADMISSION
    );

    // Get all the auditPatientMonitoringFormList where dateOfAdmission is greater than SMALLER_DATE_OF_ADMISSION
    defaultAuditPatientMonitoringFormShouldBeFound(
      "dateOfAdmission.greaterThan=" + SMALLER_DATE_OF_ADMISSION
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType equals to DEFAULT_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxygenType.equals=" + DEFAULT_OXYGEN_TYPE
    );

    // Get all the auditPatientMonitoringFormList where oxygenType equals to UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.equals=" + UPDATED_OXYGEN_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType not equals to DEFAULT_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.notEquals=" + DEFAULT_OXYGEN_TYPE
    );

    // Get all the auditPatientMonitoringFormList where oxygenType not equals to UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxygenType.notEquals=" + UPDATED_OXYGEN_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType in DEFAULT_OXYGEN_TYPE or UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxygenType.in=" + DEFAULT_OXYGEN_TYPE + "," + UPDATED_OXYGEN_TYPE
    );

    // Get all the auditPatientMonitoringFormList where oxygenType equals to UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.in=" + UPDATED_OXYGEN_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType is not null
    defaultAuditPatientMonitoringFormShouldBeFound("oxygenType.specified=true");

    // Get all the auditPatientMonitoringFormList where oxygenType is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType contains DEFAULT_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxygenType.contains=" + DEFAULT_OXYGEN_TYPE
    );

    // Get all the auditPatientMonitoringFormList where oxygenType contains UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.contains=" + UPDATED_OXYGEN_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxygenTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxygenType does not contain DEFAULT_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxygenType.doesNotContain=" + DEFAULT_OXYGEN_TYPE
    );

    // Get all the auditPatientMonitoringFormList where oxygenType does not contain UPDATED_OXYGEN_TYPE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxygenType.doesNotContain=" + UPDATED_OXYGEN_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM equals to DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.equals=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM equals to UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.equals=" + UPDATED_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM not equals to DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.notEquals=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM not equals to UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.notEquals=" + UPDATED_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM in DEFAULT_SIX_TO_EIGHT_AM or UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.in=" +
      DEFAULT_SIX_TO_EIGHT_AM +
      "," +
      UPDATED_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM equals to UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.in=" + UPDATED_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is greater than or equal to DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.greaterThanOrEqual=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is greater than or equal to UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.greaterThanOrEqual=" + UPDATED_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is less than or equal to DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.lessThanOrEqual=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is less than or equal to SMALLER_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.lessThanOrEqual=" + SMALLER_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is less than DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.lessThan=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is less than UPDATED_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.lessThan=" + UPDATED_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is greater than DEFAULT_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightAM.greaterThan=" + DEFAULT_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightAM is greater than SMALLER_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightAM.greaterThan=" + SMALLER_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM equals to DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.equals=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM equals to UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.equals=" + UPDATED_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM not equals to DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.notEquals=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM not equals to UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.notEquals=" + UPDATED_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM in DEFAULT_OXY_SIX_TO_EIGHT_AM or UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.in=" +
      DEFAULT_OXY_SIX_TO_EIGHT_AM +
      "," +
      UPDATED_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM equals to UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.in=" + UPDATED_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is greater than or equal to DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.greaterThanOrEqual=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is greater than or equal to UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.greaterThanOrEqual=" + UPDATED_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is less than or equal to DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.lessThanOrEqual=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is less than or equal to SMALLER_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.lessThanOrEqual=" + SMALLER_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is less than DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.lessThan=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is less than UPDATED_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.lessThan=" + UPDATED_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is greater than DEFAULT_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightAM.greaterThan=" + DEFAULT_OXY_SIX_TO_EIGHT_AM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightAM is greater than SMALLER_OXY_SIX_TO_EIGHT_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightAM.greaterThan=" + SMALLER_OXY_SIX_TO_EIGHT_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM equals to DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.equals=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM equals to UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.equals=" + UPDATED_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM not equals to DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.notEquals=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM not equals to UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.notEquals=" + UPDATED_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM in DEFAULT_EIGHT_TO_TEN_AM or UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.in=" +
      DEFAULT_EIGHT_TO_TEN_AM +
      "," +
      UPDATED_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM equals to UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.in=" + UPDATED_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is greater than or equal to DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.greaterThanOrEqual=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is greater than or equal to UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.greaterThanOrEqual=" + UPDATED_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is less than or equal to DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.lessThanOrEqual=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is less than or equal to SMALLER_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.lessThanOrEqual=" + SMALLER_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is less than DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.lessThan=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is less than UPDATED_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.lessThan=" + UPDATED_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is greater than DEFAULT_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenAM.greaterThan=" + DEFAULT_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenAM is greater than SMALLER_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenAM.greaterThan=" + SMALLER_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM equals to DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.equals=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM equals to UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.equals=" + UPDATED_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM not equals to DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.notEquals=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM not equals to UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.notEquals=" + UPDATED_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM in DEFAULT_OXY_EIGHT_TO_TEN_AM or UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.in=" +
      DEFAULT_OXY_EIGHT_TO_TEN_AM +
      "," +
      UPDATED_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM equals to UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.in=" + UPDATED_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is greater than or equal to DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.greaterThanOrEqual=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is greater than or equal to UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.greaterThanOrEqual=" + UPDATED_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is less than or equal to DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.lessThanOrEqual=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is less than or equal to SMALLER_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.lessThanOrEqual=" + SMALLER_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is less than DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.lessThan=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is less than UPDATED_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.lessThan=" + UPDATED_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is greater than DEFAULT_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenAM.greaterThan=" + DEFAULT_OXY_EIGHT_TO_TEN_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenAM is greater than SMALLER_OXY_EIGHT_TO_TEN_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenAM.greaterThan=" + SMALLER_OXY_EIGHT_TO_TEN_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM equals to DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.equals=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM equals to UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.equals=" + UPDATED_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM not equals to DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.notEquals=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM not equals to UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.notEquals=" + UPDATED_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM in DEFAULT_TEN_TO_TWELVE_AM or UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.in=" +
      DEFAULT_TEN_TO_TWELVE_AM +
      "," +
      UPDATED_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM equals to UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.in=" + UPDATED_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is greater than or equal to DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.greaterThanOrEqual=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is greater than or equal to UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.greaterThanOrEqual=" + UPDATED_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is less than or equal to DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.lessThanOrEqual=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is less than or equal to SMALLER_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.lessThanOrEqual=" + SMALLER_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is less than DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.lessThan=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is less than UPDATED_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.lessThan=" + UPDATED_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelveAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is greater than DEFAULT_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelveAM.greaterThan=" + DEFAULT_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelveAM is greater than SMALLER_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelveAM.greaterThan=" + SMALLER_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM equals to DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.equals=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM equals to UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.equals=" + UPDATED_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM not equals to DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.notEquals=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM not equals to UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.notEquals=" + UPDATED_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM in DEFAULT_OXY_TEN_TO_TWELVE_AM or UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.in=" +
      DEFAULT_OXY_TEN_TO_TWELVE_AM +
      "," +
      UPDATED_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM equals to UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.in=" + UPDATED_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is greater than or equal to DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.greaterThanOrEqual=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is greater than or equal to UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.greaterThanOrEqual=" + UPDATED_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is less than or equal to DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.lessThanOrEqual=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is less than or equal to SMALLER_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.lessThanOrEqual=" + SMALLER_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is less than DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.lessThan=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is less than UPDATED_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.lessThan=" + UPDATED_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelveAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is greater than DEFAULT_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelveAM.greaterThan=" + DEFAULT_OXY_TEN_TO_TWELVE_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelveAM is greater than SMALLER_OXY_TEN_TO_TWELVE_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelveAM.greaterThan=" + SMALLER_OXY_TEN_TO_TWELVE_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM equals to DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.equals=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM equals to UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.equals=" + UPDATED_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM not equals to DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.notEquals=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM not equals to UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.notEquals=" + UPDATED_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM in DEFAULT_TWELVE_TO_TOW_PM or UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.in=" +
      DEFAULT_TWELVE_TO_TOW_PM +
      "," +
      UPDATED_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM equals to UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.in=" + UPDATED_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is greater than or equal to DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.greaterThanOrEqual=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is greater than or equal to UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.greaterThanOrEqual=" + UPDATED_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is less than or equal to DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.lessThanOrEqual=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is less than or equal to SMALLER_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.lessThanOrEqual=" + SMALLER_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is less than DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.lessThan=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is less than UPDATED_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.lessThan=" + UPDATED_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTowPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is greater than DEFAULT_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTowPM.greaterThan=" + DEFAULT_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTowPM is greater than SMALLER_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTowPM.greaterThan=" + SMALLER_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM equals to DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.equals=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM equals to UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.equals=" + UPDATED_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM not equals to DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.notEquals=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM not equals to UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.notEquals=" + UPDATED_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM in DEFAULT_OXY_TWELVE_TO_TOW_PM or UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.in=" +
      DEFAULT_OXY_TWELVE_TO_TOW_PM +
      "," +
      UPDATED_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM equals to UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.in=" + UPDATED_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is greater than or equal to DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.greaterThanOrEqual=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is greater than or equal to UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.greaterThanOrEqual=" + UPDATED_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is less than or equal to DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.lessThanOrEqual=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is less than or equal to SMALLER_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.lessThanOrEqual=" + SMALLER_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is less than DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.lessThan=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is less than UPDATED_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.lessThan=" + UPDATED_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTowPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is greater than DEFAULT_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTowPM.greaterThan=" + DEFAULT_OXY_TWELVE_TO_TOW_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTowPM is greater than SMALLER_OXY_TWELVE_TO_TOW_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTowPM.greaterThan=" + SMALLER_OXY_TWELVE_TO_TOW_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM equals to DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.equals=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM equals to UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.equals=" + UPDATED_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM not equals to DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.notEquals=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM not equals to UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.notEquals=" + UPDATED_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM in DEFAULT_TWO_TO_FOUR_PM or UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.in=" + DEFAULT_TWO_TO_FOUR_PM + "," + UPDATED_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM equals to UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.in=" + UPDATED_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is greater than or equal to DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.greaterThanOrEqual=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is greater than or equal to UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.greaterThanOrEqual=" + UPDATED_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is less than or equal to DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.lessThanOrEqual=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is less than or equal to SMALLER_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.lessThanOrEqual=" + SMALLER_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is less than DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.lessThan=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is less than UPDATED_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.lessThan=" + UPDATED_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is greater than DEFAULT_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourPM.greaterThan=" + DEFAULT_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourPM is greater than SMALLER_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourPM.greaterThan=" + SMALLER_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM equals to DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.equals=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM equals to UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.equals=" + UPDATED_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM not equals to DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.notEquals=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM not equals to UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.notEquals=" + UPDATED_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM in DEFAULT_OXY_TWO_TO_FOUR_PM or UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.in=" +
      DEFAULT_OXY_TWO_TO_FOUR_PM +
      "," +
      UPDATED_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM equals to UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.in=" + UPDATED_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is greater than or equal to DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.greaterThanOrEqual=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is greater than or equal to UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.greaterThanOrEqual=" + UPDATED_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is less than or equal to DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.lessThanOrEqual=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is less than or equal to SMALLER_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.lessThanOrEqual=" + SMALLER_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is less than DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.lessThan=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is less than UPDATED_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.lessThan=" + UPDATED_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is greater than DEFAULT_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourPM.greaterThan=" + DEFAULT_OXY_TWO_TO_FOUR_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourPM is greater than SMALLER_OXY_TWO_TO_FOUR_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourPM.greaterThan=" + SMALLER_OXY_TWO_TO_FOUR_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM equals to DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.equals=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM equals to UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.equals=" + UPDATED_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM not equals to DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.notEquals=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM not equals to UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.notEquals=" + UPDATED_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM in DEFAULT_FOUR_TO_SIX_PM or UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.in=" + DEFAULT_FOUR_TO_SIX_PM + "," + UPDATED_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM equals to UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.in=" + UPDATED_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is greater than or equal to DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.greaterThanOrEqual=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is greater than or equal to UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.greaterThanOrEqual=" + UPDATED_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is less than or equal to DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.lessThanOrEqual=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is less than or equal to SMALLER_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.lessThanOrEqual=" + SMALLER_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is less than DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.lessThan=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is less than UPDATED_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.lessThan=" + UPDATED_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is greater than DEFAULT_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixPM.greaterThan=" + DEFAULT_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixPM is greater than SMALLER_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixPM.greaterThan=" + SMALLER_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM equals to DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.equals=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM equals to UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.equals=" + UPDATED_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM not equals to DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.notEquals=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM not equals to UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.notEquals=" + UPDATED_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM in DEFAULT_OXY_FOUR_TO_SIX_PM or UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.in=" +
      DEFAULT_OXY_FOUR_TO_SIX_PM +
      "," +
      UPDATED_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM equals to UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.in=" + UPDATED_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is greater than or equal to DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.greaterThanOrEqual=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is greater than or equal to UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.greaterThanOrEqual=" + UPDATED_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is less than or equal to DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.lessThanOrEqual=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is less than or equal to SMALLER_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.lessThanOrEqual=" + SMALLER_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is less than DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.lessThan=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is less than UPDATED_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.lessThan=" + UPDATED_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is greater than DEFAULT_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixPM.greaterThan=" + DEFAULT_OXY_FOUR_TO_SIX_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixPM is greater than SMALLER_OXY_FOUR_TO_SIX_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixPM.greaterThan=" + SMALLER_OXY_FOUR_TO_SIX_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM equals to DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.equals=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM equals to UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.equals=" + UPDATED_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM not equals to DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.notEquals=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM not equals to UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.notEquals=" + UPDATED_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM in DEFAULT_SIX_TO_EIGHT_PM or UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.in=" +
      DEFAULT_SIX_TO_EIGHT_PM +
      "," +
      UPDATED_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM equals to UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.in=" + UPDATED_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is greater than or equal to DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.greaterThanOrEqual=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is greater than or equal to UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.greaterThanOrEqual=" + UPDATED_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is less than or equal to DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.lessThanOrEqual=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is less than or equal to SMALLER_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.lessThanOrEqual=" + SMALLER_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is less than DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.lessThan=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is less than UPDATED_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.lessThan=" + UPDATED_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsBySixToEightPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is greater than DEFAULT_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "sixToEightPM.greaterThan=" + DEFAULT_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where sixToEightPM is greater than SMALLER_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "sixToEightPM.greaterThan=" + SMALLER_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM equals to DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.equals=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM equals to UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.equals=" + UPDATED_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM not equals to DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.notEquals=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM not equals to UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.notEquals=" + UPDATED_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM in DEFAULT_OXY_SIX_TO_EIGHT_PM or UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.in=" +
      DEFAULT_OXY_SIX_TO_EIGHT_PM +
      "," +
      UPDATED_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM equals to UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.in=" + UPDATED_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is greater than or equal to DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.greaterThanOrEqual=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is greater than or equal to UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.greaterThanOrEqual=" + UPDATED_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is less than or equal to DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.lessThanOrEqual=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is less than or equal to SMALLER_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.lessThanOrEqual=" + SMALLER_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is less than DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.lessThan=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is less than UPDATED_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.lessThan=" + UPDATED_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxySixToEightPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is greater than DEFAULT_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxySixToEightPM.greaterThan=" + DEFAULT_OXY_SIX_TO_EIGHT_PM
    );

    // Get all the auditPatientMonitoringFormList where oxySixToEightPM is greater than SMALLER_OXY_SIX_TO_EIGHT_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxySixToEightPM.greaterThan=" + SMALLER_OXY_SIX_TO_EIGHT_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM equals to DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.equals=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM equals to UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.equals=" + UPDATED_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM not equals to DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.notEquals=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM not equals to UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.notEquals=" + UPDATED_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM in DEFAULT_EIGHT_TO_TEN_PM or UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.in=" +
      DEFAULT_EIGHT_TO_TEN_PM +
      "," +
      UPDATED_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM equals to UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.in=" + UPDATED_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is greater than or equal to DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.greaterThanOrEqual=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is greater than or equal to UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.greaterThanOrEqual=" + UPDATED_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is less than or equal to DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.lessThanOrEqual=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is less than or equal to SMALLER_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.lessThanOrEqual=" + SMALLER_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is less than DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.lessThan=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is less than UPDATED_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.lessThan=" + UPDATED_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByEightToTenPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is greater than DEFAULT_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "eightToTenPM.greaterThan=" + DEFAULT_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where eightToTenPM is greater than SMALLER_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "eightToTenPM.greaterThan=" + SMALLER_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM equals to DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.equals=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM equals to UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.equals=" + UPDATED_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM not equals to DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.notEquals=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM not equals to UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.notEquals=" + UPDATED_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM in DEFAULT_OXY_EIGHT_TO_TEN_PM or UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.in=" +
      DEFAULT_OXY_EIGHT_TO_TEN_PM +
      "," +
      UPDATED_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM equals to UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.in=" + UPDATED_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is greater than or equal to DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.greaterThanOrEqual=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is greater than or equal to UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.greaterThanOrEqual=" + UPDATED_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is less than or equal to DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.lessThanOrEqual=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is less than or equal to SMALLER_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.lessThanOrEqual=" + SMALLER_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is less than DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.lessThan=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is less than UPDATED_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.lessThan=" + UPDATED_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyEightToTenPMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is greater than DEFAULT_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyEightToTenPM.greaterThan=" + DEFAULT_OXY_EIGHT_TO_TEN_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyEightToTenPM is greater than SMALLER_OXY_EIGHT_TO_TEN_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyEightToTenPM.greaterThan=" + SMALLER_OXY_EIGHT_TO_TEN_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM equals to DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.equals=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM equals to UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.equals=" + UPDATED_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM not equals to DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.notEquals=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM not equals to UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.notEquals=" + UPDATED_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM in DEFAULT_TEN_TO_TWELVE_PM or UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.in=" +
      DEFAULT_TEN_TO_TWELVE_PM +
      "," +
      UPDATED_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM equals to UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.in=" + UPDATED_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is greater than or equal to DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.greaterThanOrEqual=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is greater than or equal to UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.greaterThanOrEqual=" + UPDATED_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is less than or equal to DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.lessThanOrEqual=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is less than or equal to SMALLER_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.lessThanOrEqual=" + SMALLER_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is less than DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.lessThan=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is less than UPDATED_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.lessThan=" + UPDATED_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTenToTwelvePMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is greater than DEFAULT_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "tenToTwelvePM.greaterThan=" + DEFAULT_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where tenToTwelvePM is greater than SMALLER_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "tenToTwelvePM.greaterThan=" + SMALLER_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM equals to DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.equals=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM equals to UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.equals=" + UPDATED_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM not equals to DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.notEquals=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM not equals to UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.notEquals=" + UPDATED_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM in DEFAULT_OXY_TEN_TO_TWELVE_PM or UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.in=" +
      DEFAULT_OXY_TEN_TO_TWELVE_PM +
      "," +
      UPDATED_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM equals to UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.in=" + UPDATED_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is greater than or equal to DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.greaterThanOrEqual=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is greater than or equal to UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.greaterThanOrEqual=" + UPDATED_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is less than or equal to DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.lessThanOrEqual=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is less than or equal to SMALLER_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.lessThanOrEqual=" + SMALLER_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is less than DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.lessThan=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is less than UPDATED_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.lessThan=" + UPDATED_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTenToTwelvePMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is greater than DEFAULT_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTenToTwelvePM.greaterThan=" + DEFAULT_OXY_TEN_TO_TWELVE_PM
    );

    // Get all the auditPatientMonitoringFormList where oxyTenToTwelvePM is greater than SMALLER_OXY_TEN_TO_TWELVE_PM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTenToTwelvePM.greaterThan=" + SMALLER_OXY_TEN_TO_TWELVE_PM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM equals to DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.equals=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM equals to UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.equals=" + UPDATED_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM not equals to DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.notEquals=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM not equals to UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.notEquals=" + UPDATED_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM in DEFAULT_TWELVE_TO_TWO_AM or UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.in=" +
      DEFAULT_TWELVE_TO_TWO_AM +
      "," +
      UPDATED_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM equals to UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.in=" + UPDATED_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is greater than or equal to DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.greaterThanOrEqual=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is greater than or equal to UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.greaterThanOrEqual=" + UPDATED_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is less than or equal to DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.lessThanOrEqual=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is less than or equal to SMALLER_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.lessThanOrEqual=" + SMALLER_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is less than DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.lessThan=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is less than UPDATED_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.lessThan=" + UPDATED_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwelveToTwoAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is greater than DEFAULT_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twelveToTwoAM.greaterThan=" + DEFAULT_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where twelveToTwoAM is greater than SMALLER_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twelveToTwoAM.greaterThan=" + SMALLER_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM equals to DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.equals=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM equals to UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.equals=" + UPDATED_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM not equals to DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.notEquals=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM not equals to UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.notEquals=" + UPDATED_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM in DEFAULT_OXY_TWELVE_TO_TWO_AM or UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.in=" +
      DEFAULT_OXY_TWELVE_TO_TWO_AM +
      "," +
      UPDATED_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM equals to UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.in=" + UPDATED_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is greater than or equal to DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.greaterThanOrEqual=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is greater than or equal to UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.greaterThanOrEqual=" + UPDATED_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is less than or equal to DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.lessThanOrEqual=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is less than or equal to SMALLER_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.lessThanOrEqual=" + SMALLER_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is less than DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.lessThan=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is less than UPDATED_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.lessThan=" + UPDATED_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwelveToTwoAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is greater than DEFAULT_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwelveToTwoAM.greaterThan=" + DEFAULT_OXY_TWELVE_TO_TWO_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwelveToTwoAM is greater than SMALLER_OXY_TWELVE_TO_TWO_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwelveToTwoAM.greaterThan=" + SMALLER_OXY_TWELVE_TO_TWO_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM equals to DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.equals=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM equals to UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.equals=" + UPDATED_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM not equals to DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.notEquals=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM not equals to UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.notEquals=" + UPDATED_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM in DEFAULT_TWO_TO_FOUR_AM or UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.in=" + DEFAULT_TWO_TO_FOUR_AM + "," + UPDATED_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM equals to UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.in=" + UPDATED_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is greater than or equal to DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.greaterThanOrEqual=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is greater than or equal to UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.greaterThanOrEqual=" + UPDATED_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is less than or equal to DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.lessThanOrEqual=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is less than or equal to SMALLER_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.lessThanOrEqual=" + SMALLER_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is less than DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.lessThan=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is less than UPDATED_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.lessThan=" + UPDATED_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByTwoToFourAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is greater than DEFAULT_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "twoToFourAM.greaterThan=" + DEFAULT_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where twoToFourAM is greater than SMALLER_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "twoToFourAM.greaterThan=" + SMALLER_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM equals to DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.equals=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM equals to UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.equals=" + UPDATED_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM not equals to DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.notEquals=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM not equals to UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.notEquals=" + UPDATED_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM in DEFAULT_OXY_TWO_TO_FOUR_AM or UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.in=" +
      DEFAULT_OXY_TWO_TO_FOUR_AM +
      "," +
      UPDATED_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM equals to UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.in=" + UPDATED_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is greater than or equal to DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.greaterThanOrEqual=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is greater than or equal to UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.greaterThanOrEqual=" + UPDATED_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is less than or equal to DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.lessThanOrEqual=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is less than or equal to SMALLER_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.lessThanOrEqual=" + SMALLER_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is less than DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.lessThan=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is less than UPDATED_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.lessThan=" + UPDATED_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyTwoToFourAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is greater than DEFAULT_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyTwoToFourAM.greaterThan=" + DEFAULT_OXY_TWO_TO_FOUR_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyTwoToFourAM is greater than SMALLER_OXY_TWO_TO_FOUR_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyTwoToFourAM.greaterThan=" + SMALLER_OXY_TWO_TO_FOUR_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM equals to DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.equals=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM equals to UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.equals=" + UPDATED_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM not equals to DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.notEquals=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM not equals to UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.notEquals=" + UPDATED_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM in DEFAULT_FOUR_TO_SIX_AM or UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.in=" + DEFAULT_FOUR_TO_SIX_AM + "," + UPDATED_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM equals to UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.in=" + UPDATED_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is greater than or equal to DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.greaterThanOrEqual=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is greater than or equal to UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.greaterThanOrEqual=" + UPDATED_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is less than or equal to DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.lessThanOrEqual=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is less than or equal to SMALLER_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.lessThanOrEqual=" + SMALLER_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is less than DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.lessThan=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is less than UPDATED_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.lessThan=" + UPDATED_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFourToSixAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is greater than DEFAULT_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "fourToSixAM.greaterThan=" + DEFAULT_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where fourToSixAM is greater than SMALLER_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "fourToSixAM.greaterThan=" + SMALLER_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM equals to DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.equals=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM equals to UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.equals=" + UPDATED_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM not equals to DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.notEquals=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM not equals to UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.notEquals=" + UPDATED_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM in DEFAULT_OXY_FOUR_TO_SIX_AM or UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.in=" +
      DEFAULT_OXY_FOUR_TO_SIX_AM +
      "," +
      UPDATED_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM equals to UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.in=" + UPDATED_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is greater than or equal to DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.greaterThanOrEqual=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is greater than or equal to UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.greaterThanOrEqual=" + UPDATED_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is less than or equal to DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.lessThanOrEqual=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is less than or equal to SMALLER_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.lessThanOrEqual=" + SMALLER_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is less than DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.lessThan=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is less than UPDATED_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.lessThan=" + UPDATED_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByOxyFourToSixAMIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is greater than DEFAULT_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "oxyFourToSixAM.greaterThan=" + DEFAULT_OXY_FOUR_TO_SIX_AM
    );

    // Get all the auditPatientMonitoringFormList where oxyFourToSixAM is greater than SMALLER_OXY_FOUR_TO_SIX_AM
    defaultAuditPatientMonitoringFormShouldBeFound(
      "oxyFourToSixAM.greaterThan=" + SMALLER_OXY_FOUR_TO_SIX_AM
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName equals to DEFAULT_DR_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "drName.equals=" + DEFAULT_DR_NAME
    );

    // Get all the auditPatientMonitoringFormList where drName equals to UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "drName.equals=" + UPDATED_DR_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName not equals to DEFAULT_DR_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "drName.notEquals=" + DEFAULT_DR_NAME
    );

    // Get all the auditPatientMonitoringFormList where drName not equals to UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "drName.notEquals=" + UPDATED_DR_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName in DEFAULT_DR_NAME or UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "drName.in=" + DEFAULT_DR_NAME + "," + UPDATED_DR_NAME
    );

    // Get all the auditPatientMonitoringFormList where drName equals to UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "drName.in=" + UPDATED_DR_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName is not null
    defaultAuditPatientMonitoringFormShouldBeFound("drName.specified=true");

    // Get all the auditPatientMonitoringFormList where drName is null
    defaultAuditPatientMonitoringFormShouldNotBeFound("drName.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName contains DEFAULT_DR_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "drName.contains=" + DEFAULT_DR_NAME
    );

    // Get all the auditPatientMonitoringFormList where drName contains UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "drName.contains=" + UPDATED_DR_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByDrNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where drName does not contain DEFAULT_DR_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "drName.doesNotContain=" + DEFAULT_DR_NAME
    );

    // Get all the auditPatientMonitoringFormList where drName does not contain UPDATED_DR_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "drName.doesNotContain=" + UPDATED_DR_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName equals to DEFAULT_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "nurseName.equals=" + DEFAULT_NURSE_NAME
    );

    // Get all the auditPatientMonitoringFormList where nurseName equals to UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.equals=" + UPDATED_NURSE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName not equals to DEFAULT_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.notEquals=" + DEFAULT_NURSE_NAME
    );

    // Get all the auditPatientMonitoringFormList where nurseName not equals to UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "nurseName.notEquals=" + UPDATED_NURSE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName in DEFAULT_NURSE_NAME or UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "nurseName.in=" + DEFAULT_NURSE_NAME + "," + UPDATED_NURSE_NAME
    );

    // Get all the auditPatientMonitoringFormList where nurseName equals to UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.in=" + UPDATED_NURSE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName is not null
    defaultAuditPatientMonitoringFormShouldBeFound("nurseName.specified=true");

    // Get all the auditPatientMonitoringFormList where nurseName is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName contains DEFAULT_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "nurseName.contains=" + DEFAULT_NURSE_NAME
    );

    // Get all the auditPatientMonitoringFormList where nurseName contains UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.contains=" + UPDATED_NURSE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByNurseNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where nurseName does not contain DEFAULT_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "nurseName.doesNotContain=" + DEFAULT_NURSE_NAME
    );

    // Get all the auditPatientMonitoringFormList where nurseName does not contain UPDATED_NURSE_NAME
    defaultAuditPatientMonitoringFormShouldBeFound(
      "nurseName.doesNotContain=" + UPDATED_NURSE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy equals to DEFAULT_CREATED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the auditPatientMonitoringFormList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy not equals to DEFAULT_CREATED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the auditPatientMonitoringFormList where createdBy not equals to UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the auditPatientMonitoringFormList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy is not null
    defaultAuditPatientMonitoringFormShouldBeFound("createdBy.specified=true");

    // Get all the auditPatientMonitoringFormList where createdBy is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy contains DEFAULT_CREATED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the auditPatientMonitoringFormList where createdBy contains UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdBy does not contain DEFAULT_CREATED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the auditPatientMonitoringFormList where createdBy does not contain UPDATED_CREATED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate equals to DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate not equals to UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where createdDate is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate is less than DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate is less than UPDATED_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditPatientMonitoringFormList where createdDate is greater than SMALLER_CREATED_DATE
    defaultAuditPatientMonitoringFormShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditPatientMonitoringFormList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified is not null
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.specified=true"
    );

    // Get all the auditPatientMonitoringFormList where lastModified is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditPatientMonitoringFormList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultAuditPatientMonitoringFormShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditPatientMonitoringFormList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditPatientMonitoringFormList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the auditPatientMonitoringFormList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 is not null
    defaultAuditPatientMonitoringFormShouldBeFound("freeField1.specified=true");

    // Get all the auditPatientMonitoringFormList where freeField1 is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1ContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditPatientMonitoringFormList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditPatientMonitoringFormList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditPatientMonitoringFormList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditPatientMonitoringFormList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the auditPatientMonitoringFormList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 is not null
    defaultAuditPatientMonitoringFormShouldBeFound("freeField2.specified=true");

    // Get all the auditPatientMonitoringFormList where freeField2 is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2ContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditPatientMonitoringFormList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditPatientMonitoringFormList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditPatientMonitoringFormList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditPatientMonitoringFormList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the auditPatientMonitoringFormList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 is not null
    defaultAuditPatientMonitoringFormShouldBeFound("freeField3.specified=true");

    // Get all the auditPatientMonitoringFormList where freeField3 is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3ContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditPatientMonitoringFormList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditPatientMonitoringFormList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField4.equals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditPatientMonitoringFormList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.equals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditPatientMonitoringFormList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField4.notEquals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the auditPatientMonitoringFormList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.in=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 is not null
    defaultAuditPatientMonitoringFormShouldBeFound("freeField4.specified=true");

    // Get all the auditPatientMonitoringFormList where freeField4 is null
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4ContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField4.contains=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditPatientMonitoringFormList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.contains=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByFreeField4NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    // Get all the auditPatientMonitoringFormList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditPatientMonitoringFormList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultAuditPatientMonitoringFormShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditPatientMonitoringFormsByAuditIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );
    Audit audit;
    if (TestUtil.findAll(em, Audit.class).isEmpty()) {
      audit = AuditResourceIT.createEntity(em);
      em.persist(audit);
      em.flush();
    } else {
      audit = TestUtil.findAll(em, Audit.class).get(0);
    }
    em.persist(audit);
    em.flush();
    auditPatientMonitoringForm.setAudit(audit);
    audit.setAuditPatientMonitoringForm(auditPatientMonitoringForm);
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );
    Long auditId = audit.getId();

    // Get all the auditPatientMonitoringFormList where audit equals to auditId
    defaultAuditPatientMonitoringFormShouldBeFound("auditId.equals=" + auditId);

    // Get all the auditPatientMonitoringFormList where audit equals to (auditId + 1)
    defaultAuditPatientMonitoringFormShouldNotBeFound(
      "auditId.equals=" + (auditId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAuditPatientMonitoringFormShouldBeFound(String filter)
    throws Exception {
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(auditPatientMonitoringForm.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].wardNo").value(hasItem(DEFAULT_WARD_NO)))
      .andExpect(
        jsonPath("$.[*].patientName").value(hasItem(DEFAULT_PATIENT_NAME))
      )
      .andExpect(jsonPath("$.[*].bedNo").value(hasItem(DEFAULT_BED_NO)))
      .andExpect(
        jsonPath("$.[*].dateOfAdmission")
          .value(hasItem(DEFAULT_DATE_OF_ADMISSION.toString()))
      )
      .andExpect(
        jsonPath("$.[*].oxygenType").value(hasItem(DEFAULT_OXYGEN_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].sixToEightAM")
          .value(hasItem(DEFAULT_SIX_TO_EIGHT_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxySixToEightAM")
          .value(hasItem(DEFAULT_OXY_SIX_TO_EIGHT_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].eightToTenAM")
          .value(hasItem(DEFAULT_EIGHT_TO_TEN_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyEightToTenAM")
          .value(hasItem(DEFAULT_OXY_EIGHT_TO_TEN_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].tenToTwelveAM")
          .value(hasItem(DEFAULT_TEN_TO_TWELVE_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTenToTwelveAM")
          .value(hasItem(DEFAULT_OXY_TEN_TO_TWELVE_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twelveToTowPM")
          .value(hasItem(DEFAULT_TWELVE_TO_TOW_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwelveToTowPM")
          .value(hasItem(DEFAULT_OXY_TWELVE_TO_TOW_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twoToFourPM")
          .value(hasItem(DEFAULT_TWO_TO_FOUR_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwoToFourPM")
          .value(hasItem(DEFAULT_OXY_TWO_TO_FOUR_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].fourToSixPM")
          .value(hasItem(DEFAULT_FOUR_TO_SIX_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyFourToSixPM")
          .value(hasItem(DEFAULT_OXY_FOUR_TO_SIX_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].sixToEightPM")
          .value(hasItem(DEFAULT_SIX_TO_EIGHT_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxySixToEightPM")
          .value(hasItem(DEFAULT_OXY_SIX_TO_EIGHT_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].eightToTenPM")
          .value(hasItem(DEFAULT_EIGHT_TO_TEN_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyEightToTenPM")
          .value(hasItem(DEFAULT_OXY_EIGHT_TO_TEN_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].tenToTwelvePM")
          .value(hasItem(DEFAULT_TEN_TO_TWELVE_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTenToTwelvePM")
          .value(hasItem(DEFAULT_OXY_TEN_TO_TWELVE_PM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twelveToTwoAM")
          .value(hasItem(DEFAULT_TWELVE_TO_TWO_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwelveToTwoAM")
          .value(hasItem(DEFAULT_OXY_TWELVE_TO_TWO_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].twoToFourAM")
          .value(hasItem(DEFAULT_TWO_TO_FOUR_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyTwoToFourAM")
          .value(hasItem(DEFAULT_OXY_TWO_TO_FOUR_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].fourToSixAM")
          .value(hasItem(DEFAULT_FOUR_TO_SIX_AM.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].oxyFourToSixAM")
          .value(hasItem(DEFAULT_OXY_FOUR_TO_SIX_AM.doubleValue()))
      )
      .andExpect(jsonPath("$.[*].drName").value(hasItem(DEFAULT_DR_NAME)))
      .andExpect(jsonPath("$.[*].nurseName").value(hasItem(DEFAULT_NURSE_NAME)))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
      )
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].freeField1").value(hasItem(DEFAULT_FREE_FIELD_1))
      )
      .andExpect(
        jsonPath("$.[*].freeField2").value(hasItem(DEFAULT_FREE_FIELD_2))
      )
      .andExpect(
        jsonPath("$.[*].freeField3").value(hasItem(DEFAULT_FREE_FIELD_3))
      )
      .andExpect(
        jsonPath("$.[*].freeField4").value(hasItem(DEFAULT_FREE_FIELD_4))
      );

    // Check, that the count call also returns 1
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAuditPatientMonitoringFormShouldNotBeFound(String filter)
    throws Exception {
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAuditPatientMonitoringForm() throws Exception {
    // Get the auditPatientMonitoringForm
    restAuditPatientMonitoringFormMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAuditPatientMonitoringForm() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();

    // Update the auditPatientMonitoringForm
    AuditPatientMonitoringForm updatedAuditPatientMonitoringForm = auditPatientMonitoringFormRepository
      .findById(auditPatientMonitoringForm.getId())
      .get();
    // Disconnect from session so that the updates on updatedAuditPatientMonitoringForm are not directly saved in db
    em.detach(updatedAuditPatientMonitoringForm);
    updatedAuditPatientMonitoringForm
      .wardNo(UPDATED_WARD_NO)
      .patientName(UPDATED_PATIENT_NAME)
      .bedNo(UPDATED_BED_NO)
      .dateOfAdmission(UPDATED_DATE_OF_ADMISSION)
      .oxygenType(UPDATED_OXYGEN_TYPE)
      .sixToEightAM(UPDATED_SIX_TO_EIGHT_AM)
      .oxySixToEightAM(UPDATED_OXY_SIX_TO_EIGHT_AM)
      .eightToTenAM(UPDATED_EIGHT_TO_TEN_AM)
      .oxyEightToTenAM(UPDATED_OXY_EIGHT_TO_TEN_AM)
      .tenToTwelveAM(UPDATED_TEN_TO_TWELVE_AM)
      .oxyTenToTwelveAM(UPDATED_OXY_TEN_TO_TWELVE_AM)
      .twelveToTowPM(UPDATED_TWELVE_TO_TOW_PM)
      .oxyTwelveToTowPM(UPDATED_OXY_TWELVE_TO_TOW_PM)
      .twoToFourPM(UPDATED_TWO_TO_FOUR_PM)
      .oxyTwoToFourPM(UPDATED_OXY_TWO_TO_FOUR_PM)
      .fourToSixPM(UPDATED_FOUR_TO_SIX_PM)
      .oxyFourToSixPM(UPDATED_OXY_FOUR_TO_SIX_PM)
      .sixToEightPM(UPDATED_SIX_TO_EIGHT_PM)
      .oxySixToEightPM(UPDATED_OXY_SIX_TO_EIGHT_PM)
      .eightToTenPM(UPDATED_EIGHT_TO_TEN_PM)
      .oxyEightToTenPM(UPDATED_OXY_EIGHT_TO_TEN_PM)
      .tenToTwelvePM(UPDATED_TEN_TO_TWELVE_PM)
      .oxyTenToTwelvePM(UPDATED_OXY_TEN_TO_TWELVE_PM)
      .twelveToTwoAM(UPDATED_TWELVE_TO_TWO_AM)
      .oxyTwelveToTwoAM(UPDATED_OXY_TWELVE_TO_TWO_AM)
      .twoToFourAM(UPDATED_TWO_TO_FOUR_AM)
      .oxyTwoToFourAM(UPDATED_OXY_TWO_TO_FOUR_AM)
      .fourToSixAM(UPDATED_FOUR_TO_SIX_AM)
      .oxyFourToSixAM(UPDATED_OXY_FOUR_TO_SIX_AM)
      .drName(UPDATED_DR_NAME)
      .nurseName(UPDATED_NURSE_NAME)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      updatedAuditPatientMonitoringForm
    );

    restAuditPatientMonitoringFormMockMvc
      .perform(
        put(ENTITY_API_URL_ID, auditPatientMonitoringFormDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringFormDTO)
          )
      )
      .andExpect(status().isOk());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
    AuditPatientMonitoringForm testAuditPatientMonitoringForm = auditPatientMonitoringFormList.get(
      auditPatientMonitoringFormList.size() - 1
    );
    assertThat(testAuditPatientMonitoringForm.getWardNo())
      .isEqualTo(UPDATED_WARD_NO);
    assertThat(testAuditPatientMonitoringForm.getPatientName())
      .isEqualTo(UPDATED_PATIENT_NAME);
    assertThat(testAuditPatientMonitoringForm.getBedNo())
      .isEqualTo(UPDATED_BED_NO);
    assertThat(testAuditPatientMonitoringForm.getDateOfAdmission())
      .isEqualTo(UPDATED_DATE_OF_ADMISSION);
    assertThat(testAuditPatientMonitoringForm.getOxygenType())
      .isEqualTo(UPDATED_OXYGEN_TYPE);
    assertThat(testAuditPatientMonitoringForm.getSixToEightAM())
      .isEqualTo(UPDATED_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightAM())
      .isEqualTo(UPDATED_OXY_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenAM())
      .isEqualTo(UPDATED_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenAM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelveAM())
      .isEqualTo(UPDATED_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelveAM())
      .isEqualTo(UPDATED_OXY_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTowPM())
      .isEqualTo(UPDATED_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTowPM())
      .isEqualTo(UPDATED_OXY_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourPM())
      .isEqualTo(UPDATED_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourPM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixPM())
      .isEqualTo(UPDATED_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixPM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getSixToEightPM())
      .isEqualTo(UPDATED_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightPM())
      .isEqualTo(UPDATED_OXY_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenPM())
      .isEqualTo(UPDATED_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenPM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelvePM())
      .isEqualTo(UPDATED_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelvePM())
      .isEqualTo(UPDATED_OXY_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTwoAM())
      .isEqualTo(UPDATED_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTwoAM())
      .isEqualTo(UPDATED_OXY_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourAM())
      .isEqualTo(UPDATED_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourAM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixAM())
      .isEqualTo(UPDATED_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixAM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getDrName())
      .isEqualTo(UPDATED_DR_NAME);
    assertThat(testAuditPatientMonitoringForm.getNurseName())
      .isEqualTo(UPDATED_NURSE_NAME);
    assertThat(testAuditPatientMonitoringForm.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAuditPatientMonitoringForm.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditPatientMonitoringForm.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testAuditPatientMonitoringForm.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAuditPatientMonitoringForm.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAuditPatientMonitoringForm.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAuditPatientMonitoringForm.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAuditPatientMonitoringForm.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void putNonExistingAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        put(ENTITY_API_URL_ID, auditPatientMonitoringForm.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringForm)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringForm)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringForm auditPatientMonitoringForm = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringForm)
          )
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateAuditPatientMonitoringFormWithPatch() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();

    // Update the auditPatientMonitoringForm using partial update
    AuditPatientMonitoringForm partialUpdatedAuditPatientMonitoringForm = new AuditPatientMonitoringForm();
    partialUpdatedAuditPatientMonitoringForm.setId(
      auditPatientMonitoringForm.getId()
    );

    partialUpdatedAuditPatientMonitoringForm
      .wardNo(UPDATED_WARD_NO)
      .patientName(UPDATED_PATIENT_NAME)
      .oxygenType(UPDATED_OXYGEN_TYPE)
      .oxySixToEightAM(UPDATED_OXY_SIX_TO_EIGHT_AM)
      .eightToTenAM(UPDATED_EIGHT_TO_TEN_AM)
      .oxyEightToTenAM(UPDATED_OXY_EIGHT_TO_TEN_AM)
      .tenToTwelveAM(UPDATED_TEN_TO_TWELVE_AM)
      .oxyTwelveToTowPM(UPDATED_OXY_TWELVE_TO_TOW_PM)
      .twoToFourPM(UPDATED_TWO_TO_FOUR_PM)
      .oxyTwoToFourPM(UPDATED_OXY_TWO_TO_FOUR_PM)
      .oxyFourToSixPM(UPDATED_OXY_FOUR_TO_SIX_PM)
      .oxyEightToTenPM(UPDATED_OXY_EIGHT_TO_TEN_PM)
      .twelveToTwoAM(UPDATED_TWELVE_TO_TWO_AM)
      .oxyTwoToFourAM(UPDATED_OXY_TWO_TO_FOUR_AM)
      .fourToSixAM(UPDATED_FOUR_TO_SIX_AM)
      .oxyFourToSixAM(UPDATED_OXY_FOUR_TO_SIX_AM)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .freeField1(UPDATED_FREE_FIELD_1);

    restAuditPatientMonitoringFormMockMvc
      .perform(
        patch(
          ENTITY_API_URL_ID,
          partialUpdatedAuditPatientMonitoringForm.getId()
        )
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedAuditPatientMonitoringForm
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
    AuditPatientMonitoringForm testAuditPatientMonitoringForm = auditPatientMonitoringFormList.get(
      auditPatientMonitoringFormList.size() - 1
    );
    assertThat(testAuditPatientMonitoringForm.getWardNo())
      .isEqualTo(UPDATED_WARD_NO);
    assertThat(testAuditPatientMonitoringForm.getPatientName())
      .isEqualTo(UPDATED_PATIENT_NAME);
    assertThat(testAuditPatientMonitoringForm.getBedNo())
      .isEqualTo(DEFAULT_BED_NO);
    assertThat(testAuditPatientMonitoringForm.getDateOfAdmission())
      .isEqualTo(DEFAULT_DATE_OF_ADMISSION);
    assertThat(testAuditPatientMonitoringForm.getOxygenType())
      .isEqualTo(UPDATED_OXYGEN_TYPE);
    assertThat(testAuditPatientMonitoringForm.getSixToEightAM())
      .isEqualTo(DEFAULT_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightAM())
      .isEqualTo(UPDATED_OXY_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenAM())
      .isEqualTo(UPDATED_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenAM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelveAM())
      .isEqualTo(UPDATED_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelveAM())
      .isEqualTo(DEFAULT_OXY_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTowPM())
      .isEqualTo(DEFAULT_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTowPM())
      .isEqualTo(UPDATED_OXY_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourPM())
      .isEqualTo(UPDATED_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourPM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixPM())
      .isEqualTo(DEFAULT_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixPM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getSixToEightPM())
      .isEqualTo(DEFAULT_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightPM())
      .isEqualTo(DEFAULT_OXY_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenPM())
      .isEqualTo(DEFAULT_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenPM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelvePM())
      .isEqualTo(DEFAULT_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelvePM())
      .isEqualTo(DEFAULT_OXY_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTwoAM())
      .isEqualTo(UPDATED_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTwoAM())
      .isEqualTo(DEFAULT_OXY_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourAM())
      .isEqualTo(DEFAULT_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourAM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixAM())
      .isEqualTo(UPDATED_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixAM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getDrName())
      .isEqualTo(DEFAULT_DR_NAME);
    assertThat(testAuditPatientMonitoringForm.getNurseName())
      .isEqualTo(DEFAULT_NURSE_NAME);
    assertThat(testAuditPatientMonitoringForm.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAuditPatientMonitoringForm.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditPatientMonitoringForm.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testAuditPatientMonitoringForm.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAuditPatientMonitoringForm.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAuditPatientMonitoringForm.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAuditPatientMonitoringForm.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAuditPatientMonitoringForm.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void fullUpdateAuditPatientMonitoringFormWithPatch() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();

    // Update the auditPatientMonitoringForm using partial update
    AuditPatientMonitoringForm partialUpdatedAuditPatientMonitoringForm = new AuditPatientMonitoringForm();
    partialUpdatedAuditPatientMonitoringForm.setId(
      auditPatientMonitoringForm.getId()
    );

    partialUpdatedAuditPatientMonitoringForm
      .wardNo(UPDATED_WARD_NO)
      .patientName(UPDATED_PATIENT_NAME)
      .bedNo(UPDATED_BED_NO)
      .dateOfAdmission(UPDATED_DATE_OF_ADMISSION)
      .oxygenType(UPDATED_OXYGEN_TYPE)
      .sixToEightAM(UPDATED_SIX_TO_EIGHT_AM)
      .oxySixToEightAM(UPDATED_OXY_SIX_TO_EIGHT_AM)
      .eightToTenAM(UPDATED_EIGHT_TO_TEN_AM)
      .oxyEightToTenAM(UPDATED_OXY_EIGHT_TO_TEN_AM)
      .tenToTwelveAM(UPDATED_TEN_TO_TWELVE_AM)
      .oxyTenToTwelveAM(UPDATED_OXY_TEN_TO_TWELVE_AM)
      .twelveToTowPM(UPDATED_TWELVE_TO_TOW_PM)
      .oxyTwelveToTowPM(UPDATED_OXY_TWELVE_TO_TOW_PM)
      .twoToFourPM(UPDATED_TWO_TO_FOUR_PM)
      .oxyTwoToFourPM(UPDATED_OXY_TWO_TO_FOUR_PM)
      .fourToSixPM(UPDATED_FOUR_TO_SIX_PM)
      .oxyFourToSixPM(UPDATED_OXY_FOUR_TO_SIX_PM)
      .sixToEightPM(UPDATED_SIX_TO_EIGHT_PM)
      .oxySixToEightPM(UPDATED_OXY_SIX_TO_EIGHT_PM)
      .eightToTenPM(UPDATED_EIGHT_TO_TEN_PM)
      .oxyEightToTenPM(UPDATED_OXY_EIGHT_TO_TEN_PM)
      .tenToTwelvePM(UPDATED_TEN_TO_TWELVE_PM)
      .oxyTenToTwelvePM(UPDATED_OXY_TEN_TO_TWELVE_PM)
      .twelveToTwoAM(UPDATED_TWELVE_TO_TWO_AM)
      .oxyTwelveToTwoAM(UPDATED_OXY_TWELVE_TO_TWO_AM)
      .twoToFourAM(UPDATED_TWO_TO_FOUR_AM)
      .oxyTwoToFourAM(UPDATED_OXY_TWO_TO_FOUR_AM)
      .fourToSixAM(UPDATED_FOUR_TO_SIX_AM)
      .oxyFourToSixAM(UPDATED_OXY_FOUR_TO_SIX_AM)
      .drName(UPDATED_DR_NAME)
      .nurseName(UPDATED_NURSE_NAME)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);

    restAuditPatientMonitoringFormMockMvc
      .perform(
        patch(
          ENTITY_API_URL_ID,
          partialUpdatedAuditPatientMonitoringForm.getId()
        )
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedAuditPatientMonitoringForm
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
    AuditPatientMonitoringForm testAuditPatientMonitoringForm = auditPatientMonitoringFormList.get(
      auditPatientMonitoringFormList.size() - 1
    );
    assertThat(testAuditPatientMonitoringForm.getWardNo())
      .isEqualTo(UPDATED_WARD_NO);
    assertThat(testAuditPatientMonitoringForm.getPatientName())
      .isEqualTo(UPDATED_PATIENT_NAME);
    assertThat(testAuditPatientMonitoringForm.getBedNo())
      .isEqualTo(UPDATED_BED_NO);
    assertThat(testAuditPatientMonitoringForm.getDateOfAdmission())
      .isEqualTo(UPDATED_DATE_OF_ADMISSION);
    assertThat(testAuditPatientMonitoringForm.getOxygenType())
      .isEqualTo(UPDATED_OXYGEN_TYPE);
    assertThat(testAuditPatientMonitoringForm.getSixToEightAM())
      .isEqualTo(UPDATED_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightAM())
      .isEqualTo(UPDATED_OXY_SIX_TO_EIGHT_AM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenAM())
      .isEqualTo(UPDATED_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenAM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_AM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelveAM())
      .isEqualTo(UPDATED_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelveAM())
      .isEqualTo(UPDATED_OXY_TEN_TO_TWELVE_AM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTowPM())
      .isEqualTo(UPDATED_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTowPM())
      .isEqualTo(UPDATED_OXY_TWELVE_TO_TOW_PM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourPM())
      .isEqualTo(UPDATED_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourPM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_PM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixPM())
      .isEqualTo(UPDATED_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixPM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_PM);
    assertThat(testAuditPatientMonitoringForm.getSixToEightPM())
      .isEqualTo(UPDATED_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getOxySixToEightPM())
      .isEqualTo(UPDATED_OXY_SIX_TO_EIGHT_PM);
    assertThat(testAuditPatientMonitoringForm.getEightToTenPM())
      .isEqualTo(UPDATED_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyEightToTenPM())
      .isEqualTo(UPDATED_OXY_EIGHT_TO_TEN_PM);
    assertThat(testAuditPatientMonitoringForm.getTenToTwelvePM())
      .isEqualTo(UPDATED_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getOxyTenToTwelvePM())
      .isEqualTo(UPDATED_OXY_TEN_TO_TWELVE_PM);
    assertThat(testAuditPatientMonitoringForm.getTwelveToTwoAM())
      .isEqualTo(UPDATED_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwelveToTwoAM())
      .isEqualTo(UPDATED_OXY_TWELVE_TO_TWO_AM);
    assertThat(testAuditPatientMonitoringForm.getTwoToFourAM())
      .isEqualTo(UPDATED_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyTwoToFourAM())
      .isEqualTo(UPDATED_OXY_TWO_TO_FOUR_AM);
    assertThat(testAuditPatientMonitoringForm.getFourToSixAM())
      .isEqualTo(UPDATED_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getOxyFourToSixAM())
      .isEqualTo(UPDATED_OXY_FOUR_TO_SIX_AM);
    assertThat(testAuditPatientMonitoringForm.getDrName())
      .isEqualTo(UPDATED_DR_NAME);
    assertThat(testAuditPatientMonitoringForm.getNurseName())
      .isEqualTo(UPDATED_NURSE_NAME);
    assertThat(testAuditPatientMonitoringForm.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAuditPatientMonitoringForm.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditPatientMonitoringForm.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testAuditPatientMonitoringForm.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAuditPatientMonitoringForm.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAuditPatientMonitoringForm.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAuditPatientMonitoringForm.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAuditPatientMonitoringForm.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void patchNonExistingAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, auditPatientMonitoringFormDTO.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringFormDTO)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAuditPatientMonitoringForm() throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringFormDTO)
          )
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAuditPatientMonitoringForm()
    throws Exception {
    int databaseSizeBeforeUpdate = auditPatientMonitoringFormRepository
      .findAll()
      .size();
    auditPatientMonitoringForm.setId(count.incrementAndGet());

    // Create the AuditPatientMonitoringForm
    AuditPatientMonitoringFormDTO auditPatientMonitoringFormDTO = auditPatientMonitoringFormMapper.toDto(
      auditPatientMonitoringForm
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditPatientMonitoringFormMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(auditPatientMonitoringFormDTO)
          )
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AuditPatientMonitoringForm in the database
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteAuditPatientMonitoringForm() throws Exception {
    // Initialize the database
    auditPatientMonitoringFormRepository.saveAndFlush(
      auditPatientMonitoringForm
    );

    int databaseSizeBeforeDelete = auditPatientMonitoringFormRepository
      .findAll()
      .size();

    // Delete the auditPatientMonitoringForm
    restAuditPatientMonitoringFormMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, auditPatientMonitoringForm.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<AuditPatientMonitoringForm> auditPatientMonitoringFormList = auditPatientMonitoringFormRepository.findAll();
    assertThat(auditPatientMonitoringFormList)
      .hasSize(databaseSizeBeforeDelete - 1);
  }
}
