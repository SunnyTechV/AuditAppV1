package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.AuditFormSHospGenInfo;
import com.vgtech.auditapp.repository.AuditFormSHospGenInfoRepository;
import com.vgtech.auditapp.service.criteria.AuditFormSHospGenInfoCriteria;
import com.vgtech.auditapp.service.dto.AuditFormSHospGenInfo;
import com.vgtech.auditapp.service.mapper.AuditFormSHospGenInfoMapper;
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
 * Integration tests for the {@link AuditFormSHospGenInfoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditFormSHospGenInfoResourceIT {

  private static final String DEFAULT_HOSP_NAME = "AAAAAAAAAA";
  private static final String UPDATED_HOSP_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_HOSP_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_HOSP_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_INCHARGE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_INCHARGE_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_HOSP_ADDRESS = "AAAAAAAAAA";
  private static final String UPDATED_HOSP_ADDRESS = "BBBBBBBBBB";

  private static final String DEFAULT_HOSP_PHONE_NO = "AAAAAAAAAA";
  private static final String UPDATED_HOSP_PHONE_NO = "BBBBBBBBBB";

  private static final Integer DEFAULT_NORMAL_BEDS = 1;
  private static final Integer UPDATED_NORMAL_BEDS = 2;
  private static final Integer SMALLER_NORMAL_BEDS = 1 - 1;

  private static final Integer DEFAULT_OXYGEN_BEDS = 1;
  private static final Integer UPDATED_OXYGEN_BEDS = 2;
  private static final Integer SMALLER_OXYGEN_BEDS = 1 - 1;

  private static final Integer DEFAULT_VENTILATOR_BEDS = 1;
  private static final Integer UPDATED_VENTILATOR_BEDS = 2;
  private static final Integer SMALLER_VENTILATOR_BEDS = 1 - 1;

  private static final Integer DEFAULT_ICU_BEDS = 1;
  private static final Integer UPDATED_ICU_BEDS = 2;
  private static final Integer SMALLER_ICU_BEDS = 1 - 1;

  private static final Integer DEFAULT_ON_CYLINDER_PATIENT = 1;
  private static final Integer UPDATED_ON_CYLINDER_PATIENT = 2;
  private static final Integer SMALLER_ON_CYLINDER_PATIENT = 1 - 1;

  private static final Integer DEFAULT_ON_PIPED_BEDS_PATIENT = 1;
  private static final Integer UPDATED_ON_PIPED_BEDS_PATIENT = 2;
  private static final Integer SMALLER_ON_PIPED_BEDS_PATIENT = 1 - 1;

  private static final Integer DEFAULT_ON_NIV = 1;
  private static final Integer UPDATED_ON_NIV = 2;
  private static final Integer SMALLER_ON_NIV = 1 - 1;

  private static final Integer DEFAULT_ON_INTUBATED = 1;
  private static final Integer UPDATED_ON_INTUBATED = 2;
  private static final Integer SMALLER_ON_INTUBATED = 1 - 1;

  private static final String DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE =
    "AAAAAAAAAA";
  private static final String UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE =
    "BBBBBBBBBB";

  private static final Integer DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7 = 1;
  private static final Integer UPDATED_AVAILABLE_CYLINDER_TYPE_D_7 = 2;
  private static final Integer SMALLER_AVAILABLE_CYLINDER_TYPE_D_7 = 1 - 1;

  private static final Integer DEFAULT_AVAILABLE_CYLINDER_TYPE_B = 1;
  private static final Integer UPDATED_AVAILABLE_CYLINDER_TYPE_B = 2;
  private static final Integer SMALLER_AVAILABLE_CYLINDER_TYPE_B = 1 - 1;

  private static final String DEFAULT_CYLINDER_AGENCY_NAME = "AAAAAAAAAA";
  private static final String UPDATED_CYLINDER_AGENCY_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_CYLINDER_AGENCY_ADDRESS = "AAAAAAAAAA";
  private static final String UPDATED_CYLINDER_AGENCY_ADDRESS = "BBBBBBBBBB";

  private static final Double DEFAULT_AVAILABLE_LMO_CAPACITY_KL = 1D;
  private static final Double UPDATED_AVAILABLE_LMO_CAPACITY_KL = 2D;
  private static final Double SMALLER_AVAILABLE_LMO_CAPACITY_KL = 1D - 1D;

  private static final String DEFAULT_LMO_SUPPLIER_NAME = "AAAAAAAAAA";
  private static final String UPDATED_LMO_SUPPLIER_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_LMO_SUPPLIER_FREQUENCY = 1;
  private static final Integer UPDATED_LMO_SUPPLIER_FREQUENCY = 2;
  private static final Integer SMALLER_LMO_SUPPLIER_FREQUENCY = 1 - 1;

  private static final Double DEFAULT_LAST_LMO_SUPPLIED_QUANTITY = 1D;
  private static final Double UPDATED_LAST_LMO_SUPPLIED_QUANTITY = 2D;
  private static final Double SMALLER_LAST_LMO_SUPPLIED_QUANTITY = 1D - 1D;

  private static final String DEFAULT_REMARK = "AAAAAAAAAA";
  private static final String UPDATED_REMARK = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_CREATED_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final LocalDate DEFAULT_LAST_MODIFIED = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_LAST_MODIFIED = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_LAST_MODIFIED = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_4 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_4 = "BBBBBBBBBB";

  private static final String ENTITY_API_URL =
    "/api/audit-form-s-hosp-gen-infos";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private AuditFormSHospGenInfoRepository auditFormSHospGenInfoRepository;

  @Autowired
  private AuditFormSHospGenInfoMapper auditFormSHospGenInfoMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restAuditFormSHospGenInfoMockMvc;

  private AuditFormSHospGenInfo auditFormSHospGenInfo;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AuditFormSHospGenInfo createEntity(EntityManager em) {
    AuditFormSHospGenInfo auditFormSHospGenInfo = new AuditFormSHospGenInfo()
      .hospName(DEFAULT_HOSP_NAME)
      .hospType(DEFAULT_HOSP_TYPE)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .formName(DEFAULT_FORM_NAME)
      .inchargeName(DEFAULT_INCHARGE_NAME)
      .hospAddress(DEFAULT_HOSP_ADDRESS)
      .hospPhoneNo(DEFAULT_HOSP_PHONE_NO)
      .normalBeds(DEFAULT_NORMAL_BEDS)
      .oxygenBeds(DEFAULT_OXYGEN_BEDS)
      .ventilatorBeds(DEFAULT_VENTILATOR_BEDS)
      .icuBeds(DEFAULT_ICU_BEDS)
      .onCylinderPatient(DEFAULT_ON_CYLINDER_PATIENT)
      .onPipedBedsPatient(DEFAULT_ON_PIPED_BEDS_PATIENT)
      .onNIV(DEFAULT_ON_NIV)
      .onIntubated(DEFAULT_ON_INTUBATED)
      .jumboSystemInstalledType(DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE)
      .availableCylinderTypeD7(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7)
      .availableCylinderTypeB(DEFAULT_AVAILABLE_CYLINDER_TYPE_B)
      .cylinderAgencyName(DEFAULT_CYLINDER_AGENCY_NAME)
      .cylinderAgencyAddress(DEFAULT_CYLINDER_AGENCY_ADDRESS)
      .availableLMOCapacityKL(DEFAULT_AVAILABLE_LMO_CAPACITY_KL)
      .lmoSupplierName(DEFAULT_LMO_SUPPLIER_NAME)
      .lmoSupplierFrequency(DEFAULT_LMO_SUPPLIER_FREQUENCY)
      .lastLmoSuppliedQuantity(DEFAULT_LAST_LMO_SUPPLIED_QUANTITY)
      .remark(DEFAULT_REMARK)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4);
    return auditFormSHospGenInfo;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static AuditFormSHospGenInfo createUpdatedEntity(EntityManager em) {
    AuditFormSHospGenInfo auditFormSHospGenInfo = new AuditFormSHospGenInfo()
      .hospName(UPDATED_HOSP_NAME)
      .hospType(UPDATED_HOSP_TYPE)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .formName(UPDATED_FORM_NAME)
      .inchargeName(UPDATED_INCHARGE_NAME)
      .hospAddress(UPDATED_HOSP_ADDRESS)
      .hospPhoneNo(UPDATED_HOSP_PHONE_NO)
      .normalBeds(UPDATED_NORMAL_BEDS)
      .oxygenBeds(UPDATED_OXYGEN_BEDS)
      .ventilatorBeds(UPDATED_VENTILATOR_BEDS)
      .icuBeds(UPDATED_ICU_BEDS)
      .onCylinderPatient(UPDATED_ON_CYLINDER_PATIENT)
      .onPipedBedsPatient(UPDATED_ON_PIPED_BEDS_PATIENT)
      .onNIV(UPDATED_ON_NIV)
      .onIntubated(UPDATED_ON_INTUBATED)
      .jumboSystemInstalledType(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE)
      .availableCylinderTypeD7(UPDATED_AVAILABLE_CYLINDER_TYPE_D_7)
      .availableCylinderTypeB(UPDATED_AVAILABLE_CYLINDER_TYPE_B)
      .cylinderAgencyName(UPDATED_CYLINDER_AGENCY_NAME)
      .cylinderAgencyAddress(UPDATED_CYLINDER_AGENCY_ADDRESS)
      .availableLMOCapacityKL(UPDATED_AVAILABLE_LMO_CAPACITY_KL)
      .lmoSupplierName(UPDATED_LMO_SUPPLIER_NAME)
      .lmoSupplierFrequency(UPDATED_LMO_SUPPLIER_FREQUENCY)
      .lastLmoSuppliedQuantity(UPDATED_LAST_LMO_SUPPLIED_QUANTITY)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    return auditFormSHospGenInfo;
  }

  @BeforeEach
  public void initTest() {
    auditFormSHospGenInfo = createEntity(em);
  }

  @Test
  @Transactional
  void createAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeCreate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );
    restAuditFormSHospGenInfoMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfo))
      )
      .andExpect(status().isCreated());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeCreate + 1);
    AuditFormSHospGenInfo testAuditFormSHospGenInfo = auditFormSHospGenInfoList.get(
      auditFormSHospGenInfoList.size() - 1
    );
    assertThat(testAuditFormSHospGenInfo.getHospName())
      .isEqualTo(DEFAULT_HOSP_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospType())
      .isEqualTo(DEFAULT_HOSP_TYPE);
    assertThat(testAuditFormSHospGenInfo.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAuditFormSHospGenInfo.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testAuditFormSHospGenInfo.getFormName())
      .isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testAuditFormSHospGenInfo.getInchargeName())
      .isEqualTo(DEFAULT_INCHARGE_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospAddress())
      .isEqualTo(DEFAULT_HOSP_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getHospPhoneNo())
      .isEqualTo(DEFAULT_HOSP_PHONE_NO);
    assertThat(testAuditFormSHospGenInfo.getNormalBeds())
      .isEqualTo(DEFAULT_NORMAL_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOxygenBeds())
      .isEqualTo(DEFAULT_OXYGEN_BEDS);
    assertThat(testAuditFormSHospGenInfo.getVentilatorBeds())
      .isEqualTo(DEFAULT_VENTILATOR_BEDS);
    assertThat(testAuditFormSHospGenInfo.getIcuBeds())
      .isEqualTo(DEFAULT_ICU_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOnCylinderPatient())
      .isEqualTo(DEFAULT_ON_CYLINDER_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnPipedBedsPatient())
      .isEqualTo(DEFAULT_ON_PIPED_BEDS_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnNIV()).isEqualTo(DEFAULT_ON_NIV);
    assertThat(testAuditFormSHospGenInfo.getOnIntubated())
      .isEqualTo(DEFAULT_ON_INTUBATED);
    assertThat(testAuditFormSHospGenInfo.getJumboSystemInstalledType())
      .isEqualTo(DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeD7())
      .isEqualTo(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeB())
      .isEqualTo(DEFAULT_AVAILABLE_CYLINDER_TYPE_B);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyName())
      .isEqualTo(DEFAULT_CYLINDER_AGENCY_NAME);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyAddress())
      .isEqualTo(DEFAULT_CYLINDER_AGENCY_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getAvailableLMOCapacityKL())
      .isEqualTo(DEFAULT_AVAILABLE_LMO_CAPACITY_KL);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierName())
      .isEqualTo(DEFAULT_LMO_SUPPLIER_NAME);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierFrequency())
      .isEqualTo(DEFAULT_LMO_SUPPLIER_FREQUENCY);
    assertThat(testAuditFormSHospGenInfo.getLastLmoSuppliedQuantity())
      .isEqualTo(DEFAULT_LAST_LMO_SUPPLIED_QUANTITY);
    assertThat(testAuditFormSHospGenInfo.getRemark()).isEqualTo(DEFAULT_REMARK);
    assertThat(testAuditFormSHospGenInfo.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testAuditFormSHospGenInfo.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testAuditFormSHospGenInfo.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testAuditFormSHospGenInfo.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    assertThat(testAuditFormSHospGenInfo.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAuditFormSHospGenInfo.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testAuditFormSHospGenInfo.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAuditFormSHospGenInfo.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void createAuditFormSHospGenInfoWithExistingId() throws Exception {
    // Create the AuditFormSHospGenInfo with an existing ID
    auditFormSHospGenInfo.setId(1L);
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    int databaseSizeBeforeCreate = auditFormSHospGenInfoRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restAuditFormSHospGenInfoMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfo))
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfos() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(auditFormSHospGenInfo.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].hospName").value(hasItem(DEFAULT_HOSP_NAME)))
      .andExpect(jsonPath("$.[*].hospType").value(hasItem(DEFAULT_HOSP_TYPE)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(
        jsonPath("$.[*].inchargeName").value(hasItem(DEFAULT_INCHARGE_NAME))
      )
      .andExpect(
        jsonPath("$.[*].hospAddress").value(hasItem(DEFAULT_HOSP_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].hospPhoneNo").value(hasItem(DEFAULT_HOSP_PHONE_NO))
      )
      .andExpect(
        jsonPath("$.[*].normalBeds").value(hasItem(DEFAULT_NORMAL_BEDS))
      )
      .andExpect(
        jsonPath("$.[*].oxygenBeds").value(hasItem(DEFAULT_OXYGEN_BEDS))
      )
      .andExpect(
        jsonPath("$.[*].ventilatorBeds").value(hasItem(DEFAULT_VENTILATOR_BEDS))
      )
      .andExpect(jsonPath("$.[*].icuBeds").value(hasItem(DEFAULT_ICU_BEDS)))
      .andExpect(
        jsonPath("$.[*].onCylinderPatient")
          .value(hasItem(DEFAULT_ON_CYLINDER_PATIENT))
      )
      .andExpect(
        jsonPath("$.[*].onPipedBedsPatient")
          .value(hasItem(DEFAULT_ON_PIPED_BEDS_PATIENT))
      )
      .andExpect(jsonPath("$.[*].onNIV").value(hasItem(DEFAULT_ON_NIV)))
      .andExpect(
        jsonPath("$.[*].onIntubated").value(hasItem(DEFAULT_ON_INTUBATED))
      )
      .andExpect(
        jsonPath("$.[*].jumboSystemInstalledType")
          .value(hasItem(DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].availableCylinderTypeD7")
          .value(hasItem(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7))
      )
      .andExpect(
        jsonPath("$.[*].availableCylinderTypeB")
          .value(hasItem(DEFAULT_AVAILABLE_CYLINDER_TYPE_B))
      )
      .andExpect(
        jsonPath("$.[*].cylinderAgencyName")
          .value(hasItem(DEFAULT_CYLINDER_AGENCY_NAME))
      )
      .andExpect(
        jsonPath("$.[*].cylinderAgencyAddress")
          .value(hasItem(DEFAULT_CYLINDER_AGENCY_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].availableLMOCapacityKL")
          .value(hasItem(DEFAULT_AVAILABLE_LMO_CAPACITY_KL.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].lmoSupplierName")
          .value(hasItem(DEFAULT_LMO_SUPPLIER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].lmoSupplierFrequency")
          .value(hasItem(DEFAULT_LMO_SUPPLIER_FREQUENCY))
      )
      .andExpect(
        jsonPath("$.[*].lastLmoSuppliedQuantity")
          .value(hasItem(DEFAULT_LAST_LMO_SUPPLIED_QUANTITY.doubleValue()))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
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
  void getAuditFormSHospGenInfo() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get the auditFormSHospGenInfo
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, auditFormSHospGenInfo.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.id").value(auditFormSHospGenInfo.getId().intValue())
      )
      .andExpect(jsonPath("$.hospName").value(DEFAULT_HOSP_NAME))
      .andExpect(jsonPath("$.hospType").value(DEFAULT_HOSP_TYPE))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.inchargeName").value(DEFAULT_INCHARGE_NAME))
      .andExpect(jsonPath("$.hospAddress").value(DEFAULT_HOSP_ADDRESS))
      .andExpect(jsonPath("$.hospPhoneNo").value(DEFAULT_HOSP_PHONE_NO))
      .andExpect(jsonPath("$.normalBeds").value(DEFAULT_NORMAL_BEDS))
      .andExpect(jsonPath("$.oxygenBeds").value(DEFAULT_OXYGEN_BEDS))
      .andExpect(jsonPath("$.ventilatorBeds").value(DEFAULT_VENTILATOR_BEDS))
      .andExpect(jsonPath("$.icuBeds").value(DEFAULT_ICU_BEDS))
      .andExpect(
        jsonPath("$.onCylinderPatient").value(DEFAULT_ON_CYLINDER_PATIENT)
      )
      .andExpect(
        jsonPath("$.onPipedBedsPatient").value(DEFAULT_ON_PIPED_BEDS_PATIENT)
      )
      .andExpect(jsonPath("$.onNIV").value(DEFAULT_ON_NIV))
      .andExpect(jsonPath("$.onIntubated").value(DEFAULT_ON_INTUBATED))
      .andExpect(
        jsonPath("$.jumboSystemInstalledType")
          .value(DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE)
      )
      .andExpect(
        jsonPath("$.availableCylinderTypeD7")
          .value(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7)
      )
      .andExpect(
        jsonPath("$.availableCylinderTypeB")
          .value(DEFAULT_AVAILABLE_CYLINDER_TYPE_B)
      )
      .andExpect(
        jsonPath("$.cylinderAgencyName").value(DEFAULT_CYLINDER_AGENCY_NAME)
      )
      .andExpect(
        jsonPath("$.cylinderAgencyAddress")
          .value(DEFAULT_CYLINDER_AGENCY_ADDRESS)
      )
      .andExpect(
        jsonPath("$.availableLMOCapacityKL")
          .value(DEFAULT_AVAILABLE_LMO_CAPACITY_KL.doubleValue())
      )
      .andExpect(jsonPath("$.lmoSupplierName").value(DEFAULT_LMO_SUPPLIER_NAME))
      .andExpect(
        jsonPath("$.lmoSupplierFrequency").value(DEFAULT_LMO_SUPPLIER_FREQUENCY)
      )
      .andExpect(
        jsonPath("$.lastLmoSuppliedQuantity")
          .value(DEFAULT_LAST_LMO_SUPPLIED_QUANTITY.doubleValue())
      )
      .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
      .andExpect(
        jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString())
      )
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(
        jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString())
      )
      .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(jsonPath("$.freeField4").value(DEFAULT_FREE_FIELD_4));
  }

  @Test
  @Transactional
  void getAuditFormSHospGenInfosByIdFiltering() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    Long id = auditFormSHospGenInfo.getId();

    defaultAuditFormSHospGenInfoShouldBeFound("id.equals=" + id);
    defaultAuditFormSHospGenInfoShouldNotBeFound("id.notEquals=" + id);

    defaultAuditFormSHospGenInfoShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultAuditFormSHospGenInfoShouldNotBeFound("id.greaterThan=" + id);

    defaultAuditFormSHospGenInfoShouldBeFound("id.lessThanOrEqual=" + id);
    defaultAuditFormSHospGenInfoShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName equals to DEFAULT_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospName.equals=" + DEFAULT_HOSP_NAME
    );

    // Get all the auditFormSHospGenInfoList where hospName equals to UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospName.equals=" + UPDATED_HOSP_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName not equals to DEFAULT_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospName.notEquals=" + DEFAULT_HOSP_NAME
    );

    // Get all the auditFormSHospGenInfoList where hospName not equals to UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospName.notEquals=" + UPDATED_HOSP_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName in DEFAULT_HOSP_NAME or UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospName.in=" + DEFAULT_HOSP_NAME + "," + UPDATED_HOSP_NAME
    );

    // Get all the auditFormSHospGenInfoList where hospName equals to UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospName.in=" + UPDATED_HOSP_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName is not null
    defaultAuditFormSHospGenInfoShouldBeFound("hospName.specified=true");

    // Get all the auditFormSHospGenInfoList where hospName is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("hospName.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName contains DEFAULT_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospName.contains=" + DEFAULT_HOSP_NAME
    );

    // Get all the auditFormSHospGenInfoList where hospName contains UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospName.contains=" + UPDATED_HOSP_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospName does not contain DEFAULT_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospName.doesNotContain=" + DEFAULT_HOSP_NAME
    );

    // Get all the auditFormSHospGenInfoList where hospName does not contain UPDATED_HOSP_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospName.doesNotContain=" + UPDATED_HOSP_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType equals to DEFAULT_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospType.equals=" + DEFAULT_HOSP_TYPE
    );

    // Get all the auditFormSHospGenInfoList where hospType equals to UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospType.equals=" + UPDATED_HOSP_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType not equals to DEFAULT_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospType.notEquals=" + DEFAULT_HOSP_TYPE
    );

    // Get all the auditFormSHospGenInfoList where hospType not equals to UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospType.notEquals=" + UPDATED_HOSP_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType in DEFAULT_HOSP_TYPE or UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospType.in=" + DEFAULT_HOSP_TYPE + "," + UPDATED_HOSP_TYPE
    );

    // Get all the auditFormSHospGenInfoList where hospType equals to UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospType.in=" + UPDATED_HOSP_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType is not null
    defaultAuditFormSHospGenInfoShouldBeFound("hospType.specified=true");

    // Get all the auditFormSHospGenInfoList where hospType is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("hospType.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType contains DEFAULT_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospType.contains=" + DEFAULT_HOSP_TYPE
    );

    // Get all the auditFormSHospGenInfoList where hospType contains UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospType.contains=" + UPDATED_HOSP_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospType does not contain DEFAULT_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospType.doesNotContain=" + DEFAULT_HOSP_TYPE
    );

    // Get all the auditFormSHospGenInfoList where hospType does not contain UPDATED_HOSP_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospType.doesNotContain=" + UPDATED_HOSP_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type equals to DEFAULT_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the auditFormSHospGenInfoList where type equals to UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type not equals to DEFAULT_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "type.notEquals=" + DEFAULT_TYPE
    );

    // Get all the auditFormSHospGenInfoList where type not equals to UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where type equals to UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type is not null
    defaultAuditFormSHospGenInfoShouldBeFound("type.specified=true");

    // Get all the auditFormSHospGenInfoList where type is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeContainsSomething() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type contains DEFAULT_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the auditFormSHospGenInfoList where type contains UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "type.contains=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where type does not contain DEFAULT_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the auditFormSHospGenInfoList where type does not contain UPDATED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "type.doesNotContain=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType equals to DEFAULT_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "subType.equals=" + DEFAULT_SUB_TYPE
    );

    // Get all the auditFormSHospGenInfoList where subType equals to UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType not equals to DEFAULT_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the auditFormSHospGenInfoList where subType not equals to UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the auditFormSHospGenInfoList where subType equals to UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "subType.in=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType is not null
    defaultAuditFormSHospGenInfoShouldBeFound("subType.specified=true");

    // Get all the auditFormSHospGenInfoList where subType is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType contains DEFAULT_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "subType.contains=" + DEFAULT_SUB_TYPE
    );

    // Get all the auditFormSHospGenInfoList where subType contains UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosBySubTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where subType does not contain DEFAULT_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the auditFormSHospGenInfoList where subType does not contain UPDATED_SUB_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName equals to DEFAULT_FORM_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "formName.equals=" + DEFAULT_FORM_NAME
    );

    // Get all the auditFormSHospGenInfoList where formName equals to UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName not equals to DEFAULT_FORM_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the auditFormSHospGenInfoList where formName not equals to UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the auditFormSHospGenInfoList where formName equals to UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "formName.in=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName is not null
    defaultAuditFormSHospGenInfoShouldBeFound("formName.specified=true");

    // Get all the auditFormSHospGenInfoList where formName is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName contains DEFAULT_FORM_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the auditFormSHospGenInfoList where formName contains UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFormNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where formName does not contain DEFAULT_FORM_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the auditFormSHospGenInfoList where formName does not contain UPDATED_FORM_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName equals to DEFAULT_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "inchargeName.equals=" + DEFAULT_INCHARGE_NAME
    );

    // Get all the auditFormSHospGenInfoList where inchargeName equals to UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.equals=" + UPDATED_INCHARGE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName not equals to DEFAULT_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.notEquals=" + DEFAULT_INCHARGE_NAME
    );

    // Get all the auditFormSHospGenInfoList where inchargeName not equals to UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "inchargeName.notEquals=" + UPDATED_INCHARGE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName in DEFAULT_INCHARGE_NAME or UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "inchargeName.in=" + DEFAULT_INCHARGE_NAME + "," + UPDATED_INCHARGE_NAME
    );

    // Get all the auditFormSHospGenInfoList where inchargeName equals to UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.in=" + UPDATED_INCHARGE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName is not null
    defaultAuditFormSHospGenInfoShouldBeFound("inchargeName.specified=true");

    // Get all the auditFormSHospGenInfoList where inchargeName is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName contains DEFAULT_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "inchargeName.contains=" + DEFAULT_INCHARGE_NAME
    );

    // Get all the auditFormSHospGenInfoList where inchargeName contains UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.contains=" + UPDATED_INCHARGE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByInchargeNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where inchargeName does not contain DEFAULT_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "inchargeName.doesNotContain=" + DEFAULT_INCHARGE_NAME
    );

    // Get all the auditFormSHospGenInfoList where inchargeName does not contain UPDATED_INCHARGE_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "inchargeName.doesNotContain=" + UPDATED_INCHARGE_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress equals to DEFAULT_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospAddress.equals=" + DEFAULT_HOSP_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where hospAddress equals to UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospAddress.equals=" + UPDATED_HOSP_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress not equals to DEFAULT_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospAddress.notEquals=" + DEFAULT_HOSP_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where hospAddress not equals to UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospAddress.notEquals=" + UPDATED_HOSP_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress in DEFAULT_HOSP_ADDRESS or UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospAddress.in=" + DEFAULT_HOSP_ADDRESS + "," + UPDATED_HOSP_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where hospAddress equals to UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospAddress.in=" + UPDATED_HOSP_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress is not null
    defaultAuditFormSHospGenInfoShouldBeFound("hospAddress.specified=true");

    // Get all the auditFormSHospGenInfoList where hospAddress is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("hospAddress.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress contains DEFAULT_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospAddress.contains=" + DEFAULT_HOSP_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where hospAddress contains UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospAddress.contains=" + UPDATED_HOSP_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospAddressNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospAddress does not contain DEFAULT_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospAddress.doesNotContain=" + DEFAULT_HOSP_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where hospAddress does not contain UPDATED_HOSP_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospAddress.doesNotContain=" + UPDATED_HOSP_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo equals to DEFAULT_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospPhoneNo.equals=" + DEFAULT_HOSP_PHONE_NO
    );

    // Get all the auditFormSHospGenInfoList where hospPhoneNo equals to UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospPhoneNo.equals=" + UPDATED_HOSP_PHONE_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo not equals to DEFAULT_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospPhoneNo.notEquals=" + DEFAULT_HOSP_PHONE_NO
    );

    // Get all the auditFormSHospGenInfoList where hospPhoneNo not equals to UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospPhoneNo.notEquals=" + UPDATED_HOSP_PHONE_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo in DEFAULT_HOSP_PHONE_NO or UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospPhoneNo.in=" + DEFAULT_HOSP_PHONE_NO + "," + UPDATED_HOSP_PHONE_NO
    );

    // Get all the auditFormSHospGenInfoList where hospPhoneNo equals to UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospPhoneNo.in=" + UPDATED_HOSP_PHONE_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo is not null
    defaultAuditFormSHospGenInfoShouldBeFound("hospPhoneNo.specified=true");

    // Get all the auditFormSHospGenInfoList where hospPhoneNo is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("hospPhoneNo.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo contains DEFAULT_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospPhoneNo.contains=" + DEFAULT_HOSP_PHONE_NO
    );

    // Get all the auditFormSHospGenInfoList where hospPhoneNo contains UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospPhoneNo.contains=" + UPDATED_HOSP_PHONE_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByHospPhoneNoNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where hospPhoneNo does not contain DEFAULT_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "hospPhoneNo.doesNotContain=" + DEFAULT_HOSP_PHONE_NO
    );

    // Get all the auditFormSHospGenInfoList where hospPhoneNo does not contain UPDATED_HOSP_PHONE_NO
    defaultAuditFormSHospGenInfoShouldBeFound(
      "hospPhoneNo.doesNotContain=" + UPDATED_HOSP_PHONE_NO
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds equals to DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.equals=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds equals to UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.equals=" + UPDATED_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds not equals to DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.notEquals=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds not equals to UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.notEquals=" + UPDATED_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds in DEFAULT_NORMAL_BEDS or UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.in=" + DEFAULT_NORMAL_BEDS + "," + UPDATED_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds equals to UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.in=" + UPDATED_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds is not null
    defaultAuditFormSHospGenInfoShouldBeFound("normalBeds.specified=true");

    // Get all the auditFormSHospGenInfoList where normalBeds is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("normalBeds.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds is greater than or equal to DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.greaterThanOrEqual=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds is greater than or equal to UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.greaterThanOrEqual=" + UPDATED_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds is less than or equal to DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.lessThanOrEqual=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds is less than or equal to SMALLER_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.lessThanOrEqual=" + SMALLER_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds is less than DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.lessThan=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds is less than UPDATED_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.lessThan=" + UPDATED_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByNormalBedsIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where normalBeds is greater than DEFAULT_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "normalBeds.greaterThan=" + DEFAULT_NORMAL_BEDS
    );

    // Get all the auditFormSHospGenInfoList where normalBeds is greater than SMALLER_NORMAL_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "normalBeds.greaterThan=" + SMALLER_NORMAL_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds equals to DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.equals=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds equals to UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.equals=" + UPDATED_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds not equals to DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.notEquals=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds not equals to UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.notEquals=" + UPDATED_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds in DEFAULT_OXYGEN_BEDS or UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.in=" + DEFAULT_OXYGEN_BEDS + "," + UPDATED_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds equals to UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.in=" + UPDATED_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds is not null
    defaultAuditFormSHospGenInfoShouldBeFound("oxygenBeds.specified=true");

    // Get all the auditFormSHospGenInfoList where oxygenBeds is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("oxygenBeds.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds is greater than or equal to DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.greaterThanOrEqual=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds is greater than or equal to UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.greaterThanOrEqual=" + UPDATED_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds is less than or equal to DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.lessThanOrEqual=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds is less than or equal to SMALLER_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.lessThanOrEqual=" + SMALLER_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds is less than DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.lessThan=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds is less than UPDATED_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.lessThan=" + UPDATED_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOxygenBedsIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where oxygenBeds is greater than DEFAULT_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "oxygenBeds.greaterThan=" + DEFAULT_OXYGEN_BEDS
    );

    // Get all the auditFormSHospGenInfoList where oxygenBeds is greater than SMALLER_OXYGEN_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "oxygenBeds.greaterThan=" + SMALLER_OXYGEN_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds equals to DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.equals=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds equals to UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.equals=" + UPDATED_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds not equals to DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.notEquals=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds not equals to UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.notEquals=" + UPDATED_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds in DEFAULT_VENTILATOR_BEDS or UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.in=" +
      DEFAULT_VENTILATOR_BEDS +
      "," +
      UPDATED_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds equals to UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.in=" + UPDATED_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is not null
    defaultAuditFormSHospGenInfoShouldBeFound("ventilatorBeds.specified=true");

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is greater than or equal to DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.greaterThanOrEqual=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is greater than or equal to UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.greaterThanOrEqual=" + UPDATED_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is less than or equal to DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.lessThanOrEqual=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is less than or equal to SMALLER_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.lessThanOrEqual=" + SMALLER_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is less than DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.lessThan=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is less than UPDATED_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.lessThan=" + UPDATED_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByVentilatorBedsIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is greater than DEFAULT_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "ventilatorBeds.greaterThan=" + DEFAULT_VENTILATOR_BEDS
    );

    // Get all the auditFormSHospGenInfoList where ventilatorBeds is greater than SMALLER_VENTILATOR_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "ventilatorBeds.greaterThan=" + SMALLER_VENTILATOR_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds equals to DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.equals=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds equals to UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.equals=" + UPDATED_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds not equals to DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.notEquals=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds not equals to UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.notEquals=" + UPDATED_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds in DEFAULT_ICU_BEDS or UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.in=" + DEFAULT_ICU_BEDS + "," + UPDATED_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds equals to UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.in=" + UPDATED_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds is not null
    defaultAuditFormSHospGenInfoShouldBeFound("icuBeds.specified=true");

    // Get all the auditFormSHospGenInfoList where icuBeds is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("icuBeds.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds is greater than or equal to DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.greaterThanOrEqual=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds is greater than or equal to UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.greaterThanOrEqual=" + UPDATED_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds is less than or equal to DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.lessThanOrEqual=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds is less than or equal to SMALLER_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.lessThanOrEqual=" + SMALLER_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds is less than DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.lessThan=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds is less than UPDATED_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.lessThan=" + UPDATED_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByIcuBedsIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where icuBeds is greater than DEFAULT_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "icuBeds.greaterThan=" + DEFAULT_ICU_BEDS
    );

    // Get all the auditFormSHospGenInfoList where icuBeds is greater than SMALLER_ICU_BEDS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "icuBeds.greaterThan=" + SMALLER_ICU_BEDS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient equals to DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.equals=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient equals to UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.equals=" + UPDATED_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient not equals to DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.notEquals=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient not equals to UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.notEquals=" + UPDATED_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient in DEFAULT_ON_CYLINDER_PATIENT or UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.in=" +
      DEFAULT_ON_CYLINDER_PATIENT +
      "," +
      UPDATED_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient equals to UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.in=" + UPDATED_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is greater than or equal to DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.greaterThanOrEqual=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is greater than or equal to UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.greaterThanOrEqual=" + UPDATED_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is less than or equal to DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.lessThanOrEqual=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is less than or equal to SMALLER_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.lessThanOrEqual=" + SMALLER_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is less than DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.lessThan=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is less than UPDATED_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.lessThan=" + UPDATED_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnCylinderPatientIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is greater than DEFAULT_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onCylinderPatient.greaterThan=" + DEFAULT_ON_CYLINDER_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onCylinderPatient is greater than SMALLER_ON_CYLINDER_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onCylinderPatient.greaterThan=" + SMALLER_ON_CYLINDER_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient equals to DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.equals=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient equals to UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.equals=" + UPDATED_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient not equals to DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.notEquals=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient not equals to UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.notEquals=" + UPDATED_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient in DEFAULT_ON_PIPED_BEDS_PATIENT or UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.in=" +
      DEFAULT_ON_PIPED_BEDS_PATIENT +
      "," +
      UPDATED_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient equals to UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.in=" + UPDATED_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is greater than or equal to DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.greaterThanOrEqual=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is greater than or equal to UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.greaterThanOrEqual=" + UPDATED_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is less than or equal to DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.lessThanOrEqual=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is less than or equal to SMALLER_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.lessThanOrEqual=" + SMALLER_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is less than DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.lessThan=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is less than UPDATED_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.lessThan=" + UPDATED_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnPipedBedsPatientIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is greater than DEFAULT_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onPipedBedsPatient.greaterThan=" + DEFAULT_ON_PIPED_BEDS_PATIENT
    );

    // Get all the auditFormSHospGenInfoList where onPipedBedsPatient is greater than SMALLER_ON_PIPED_BEDS_PATIENT
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onPipedBedsPatient.greaterThan=" + SMALLER_ON_PIPED_BEDS_PATIENT
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV equals to DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound("onNIV.equals=" + DEFAULT_ON_NIV);

    // Get all the auditFormSHospGenInfoList where onNIV equals to UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.equals=" + UPDATED_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV not equals to DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.notEquals=" + DEFAULT_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV not equals to UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.notEquals=" + UPDATED_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV in DEFAULT_ON_NIV or UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.in=" + DEFAULT_ON_NIV + "," + UPDATED_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV equals to UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound("onNIV.in=" + UPDATED_ON_NIV);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV is not null
    defaultAuditFormSHospGenInfoShouldBeFound("onNIV.specified=true");

    // Get all the auditFormSHospGenInfoList where onNIV is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("onNIV.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV is greater than or equal to DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.greaterThanOrEqual=" + DEFAULT_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV is greater than or equal to UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.greaterThanOrEqual=" + UPDATED_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV is less than or equal to DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.lessThanOrEqual=" + DEFAULT_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV is less than or equal to SMALLER_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.lessThanOrEqual=" + SMALLER_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV is less than DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.lessThan=" + DEFAULT_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV is less than UPDATED_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.lessThan=" + UPDATED_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnNIVIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onNIV is greater than DEFAULT_ON_NIV
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onNIV.greaterThan=" + DEFAULT_ON_NIV
    );

    // Get all the auditFormSHospGenInfoList where onNIV is greater than SMALLER_ON_NIV
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onNIV.greaterThan=" + SMALLER_ON_NIV
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated equals to DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.equals=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated equals to UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.equals=" + UPDATED_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated not equals to DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.notEquals=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated not equals to UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.notEquals=" + UPDATED_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated in DEFAULT_ON_INTUBATED or UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.in=" + DEFAULT_ON_INTUBATED + "," + UPDATED_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated equals to UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.in=" + UPDATED_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated is not null
    defaultAuditFormSHospGenInfoShouldBeFound("onIntubated.specified=true");

    // Get all the auditFormSHospGenInfoList where onIntubated is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("onIntubated.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated is greater than or equal to DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.greaterThanOrEqual=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated is greater than or equal to UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.greaterThanOrEqual=" + UPDATED_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated is less than or equal to DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.lessThanOrEqual=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated is less than or equal to SMALLER_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.lessThanOrEqual=" + SMALLER_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated is less than DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.lessThan=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated is less than UPDATED_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.lessThan=" + UPDATED_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByOnIntubatedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where onIntubated is greater than DEFAULT_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "onIntubated.greaterThan=" + DEFAULT_ON_INTUBATED
    );

    // Get all the auditFormSHospGenInfoList where onIntubated is greater than SMALLER_ON_INTUBATED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "onIntubated.greaterThan=" + SMALLER_ON_INTUBATED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType equals to DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.equals=" + DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType equals to UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.equals=" + UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType not equals to DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.notEquals=" +
      DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType not equals to UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.notEquals=" +
      UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType in DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE or UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.in=" +
      DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE +
      "," +
      UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType equals to UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.in=" + UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType contains DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.contains=" + DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType contains UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.contains=" + UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByJumboSystemInstalledTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType does not contain DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "jumboSystemInstalledType.doesNotContain=" +
      DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE
    );

    // Get all the auditFormSHospGenInfoList where jumboSystemInstalledType does not contain UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "jumboSystemInstalledType.doesNotContain=" +
      UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 equals to DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.equals=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 equals to UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.equals=" + UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 not equals to DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.notEquals=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 not equals to UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.notEquals=" + UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 in DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7 or UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.in=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7 +
      "," +
      UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 equals to UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.in=" + UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is greater than or equal to DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.greaterThanOrEqual=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is greater than or equal to UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.greaterThanOrEqual=" +
      UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is less than or equal to DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.lessThanOrEqual=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is less than or equal to SMALLER_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.lessThanOrEqual=" +
      SMALLER_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is less than DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.lessThan=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is less than UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.lessThan=" + UPDATED_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeD7IsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is greater than DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeD7.greaterThan=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeD7 is greater than SMALLER_AVAILABLE_CYLINDER_TYPE_D_7
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeD7.greaterThan=" +
      SMALLER_AVAILABLE_CYLINDER_TYPE_D_7
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB equals to DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.equals=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB equals to UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.equals=" + UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB not equals to DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.notEquals=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB not equals to UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.notEquals=" + UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB in DEFAULT_AVAILABLE_CYLINDER_TYPE_B or UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.in=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_B +
      "," +
      UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB equals to UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.in=" + UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is greater than or equal to DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.greaterThanOrEqual=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is greater than or equal to UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.greaterThanOrEqual=" +
      UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is less than or equal to DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.lessThanOrEqual=" +
      DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is less than or equal to SMALLER_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.lessThanOrEqual=" +
      SMALLER_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is less than DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.lessThan=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is less than UPDATED_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.lessThan=" + UPDATED_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableCylinderTypeBIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is greater than DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableCylinderTypeB.greaterThan=" + DEFAULT_AVAILABLE_CYLINDER_TYPE_B
    );

    // Get all the auditFormSHospGenInfoList where availableCylinderTypeB is greater than SMALLER_AVAILABLE_CYLINDER_TYPE_B
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableCylinderTypeB.greaterThan=" + SMALLER_AVAILABLE_CYLINDER_TYPE_B
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName equals to DEFAULT_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.equals=" + DEFAULT_CYLINDER_AGENCY_NAME
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName equals to UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.equals=" + UPDATED_CYLINDER_AGENCY_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName not equals to DEFAULT_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.notEquals=" + DEFAULT_CYLINDER_AGENCY_NAME
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName not equals to UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.notEquals=" + UPDATED_CYLINDER_AGENCY_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName in DEFAULT_CYLINDER_AGENCY_NAME or UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.in=" +
      DEFAULT_CYLINDER_AGENCY_NAME +
      "," +
      UPDATED_CYLINDER_AGENCY_NAME
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName equals to UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.in=" + UPDATED_CYLINDER_AGENCY_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName contains DEFAULT_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.contains=" + DEFAULT_CYLINDER_AGENCY_NAME
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName contains UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.contains=" + UPDATED_CYLINDER_AGENCY_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName does not contain DEFAULT_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyName.doesNotContain=" + DEFAULT_CYLINDER_AGENCY_NAME
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyName does not contain UPDATED_CYLINDER_AGENCY_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyName.doesNotContain=" + UPDATED_CYLINDER_AGENCY_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress equals to DEFAULT_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.equals=" + DEFAULT_CYLINDER_AGENCY_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress equals to UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.equals=" + UPDATED_CYLINDER_AGENCY_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress not equals to DEFAULT_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.notEquals=" + DEFAULT_CYLINDER_AGENCY_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress not equals to UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.notEquals=" + UPDATED_CYLINDER_AGENCY_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress in DEFAULT_CYLINDER_AGENCY_ADDRESS or UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.in=" +
      DEFAULT_CYLINDER_AGENCY_ADDRESS +
      "," +
      UPDATED_CYLINDER_AGENCY_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress equals to UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.in=" + UPDATED_CYLINDER_AGENCY_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress contains DEFAULT_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.contains=" + DEFAULT_CYLINDER_AGENCY_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress contains UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.contains=" + UPDATED_CYLINDER_AGENCY_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCylinderAgencyAddressNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress does not contain DEFAULT_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "cylinderAgencyAddress.doesNotContain=" + DEFAULT_CYLINDER_AGENCY_ADDRESS
    );

    // Get all the auditFormSHospGenInfoList where cylinderAgencyAddress does not contain UPDATED_CYLINDER_AGENCY_ADDRESS
    defaultAuditFormSHospGenInfoShouldBeFound(
      "cylinderAgencyAddress.doesNotContain=" + UPDATED_CYLINDER_AGENCY_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL equals to DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.equals=" + DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL equals to UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.equals=" + UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL not equals to DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.notEquals=" + DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL not equals to UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.notEquals=" + UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL in DEFAULT_AVAILABLE_LMO_CAPACITY_KL or UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.in=" +
      DEFAULT_AVAILABLE_LMO_CAPACITY_KL +
      "," +
      UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL equals to UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.in=" + UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is greater than or equal to DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.greaterThanOrEqual=" +
      DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is greater than or equal to UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.greaterThanOrEqual=" +
      UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is less than or equal to DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.lessThanOrEqual=" +
      DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is less than or equal to SMALLER_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.lessThanOrEqual=" +
      SMALLER_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is less than DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.lessThan=" + DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is less than UPDATED_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.lessThan=" + UPDATED_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAvailableLMOCapacityKLIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is greater than DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "availableLMOCapacityKL.greaterThan=" + DEFAULT_AVAILABLE_LMO_CAPACITY_KL
    );

    // Get all the auditFormSHospGenInfoList where availableLMOCapacityKL is greater than SMALLER_AVAILABLE_LMO_CAPACITY_KL
    defaultAuditFormSHospGenInfoShouldBeFound(
      "availableLMOCapacityKL.greaterThan=" + SMALLER_AVAILABLE_LMO_CAPACITY_KL
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName equals to DEFAULT_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierName.equals=" + DEFAULT_LMO_SUPPLIER_NAME
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierName equals to UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.equals=" + UPDATED_LMO_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName not equals to DEFAULT_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.notEquals=" + DEFAULT_LMO_SUPPLIER_NAME
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierName not equals to UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierName.notEquals=" + UPDATED_LMO_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName in DEFAULT_LMO_SUPPLIER_NAME or UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierName.in=" +
      DEFAULT_LMO_SUPPLIER_NAME +
      "," +
      UPDATED_LMO_SUPPLIER_NAME
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierName equals to UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.in=" + UPDATED_LMO_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName is not null
    defaultAuditFormSHospGenInfoShouldBeFound("lmoSupplierName.specified=true");

    // Get all the auditFormSHospGenInfoList where lmoSupplierName is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName contains DEFAULT_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierName.contains=" + DEFAULT_LMO_SUPPLIER_NAME
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierName contains UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.contains=" + UPDATED_LMO_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierName does not contain DEFAULT_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierName.doesNotContain=" + DEFAULT_LMO_SUPPLIER_NAME
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierName does not contain UPDATED_LMO_SUPPLIER_NAME
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierName.doesNotContain=" + UPDATED_LMO_SUPPLIER_NAME
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency equals to DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.equals=" + DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency equals to UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.equals=" + UPDATED_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency not equals to DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.notEquals=" + DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency not equals to UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.notEquals=" + UPDATED_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency in DEFAULT_LMO_SUPPLIER_FREQUENCY or UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.in=" +
      DEFAULT_LMO_SUPPLIER_FREQUENCY +
      "," +
      UPDATED_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency equals to UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.in=" + UPDATED_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is greater than or equal to DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.greaterThanOrEqual=" +
      DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is greater than or equal to UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.greaterThanOrEqual=" +
      UPDATED_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is less than or equal to DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.lessThanOrEqual=" + DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is less than or equal to SMALLER_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.lessThanOrEqual=" + SMALLER_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is less than DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.lessThan=" + DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is less than UPDATED_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.lessThan=" + UPDATED_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLmoSupplierFrequencyIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is greater than DEFAULT_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lmoSupplierFrequency.greaterThan=" + DEFAULT_LMO_SUPPLIER_FREQUENCY
    );

    // Get all the auditFormSHospGenInfoList where lmoSupplierFrequency is greater than SMALLER_LMO_SUPPLIER_FREQUENCY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lmoSupplierFrequency.greaterThan=" + SMALLER_LMO_SUPPLIER_FREQUENCY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity equals to DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.equals=" + DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity equals to UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.equals=" + UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity not equals to DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.notEquals=" + DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity not equals to UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.notEquals=" + UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity in DEFAULT_LAST_LMO_SUPPLIED_QUANTITY or UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.in=" +
      DEFAULT_LAST_LMO_SUPPLIED_QUANTITY +
      "," +
      UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity equals to UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.in=" + UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is not null
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.specified=true"
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is greater than or equal to DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.greaterThanOrEqual=" +
      DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is greater than or equal to UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.greaterThanOrEqual=" +
      UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is less than or equal to DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.lessThanOrEqual=" +
      DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is less than or equal to SMALLER_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.lessThanOrEqual=" +
      SMALLER_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is less than DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.lessThan=" + DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is less than UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.lessThan=" + UPDATED_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastLmoSuppliedQuantityIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is greater than DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastLmoSuppliedQuantity.greaterThan=" +
      DEFAULT_LAST_LMO_SUPPLIED_QUANTITY
    );

    // Get all the auditFormSHospGenInfoList where lastLmoSuppliedQuantity is greater than SMALLER_LAST_LMO_SUPPLIED_QUANTITY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastLmoSuppliedQuantity.greaterThan=" +
      SMALLER_LAST_LMO_SUPPLIED_QUANTITY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark equals to DEFAULT_REMARK
    defaultAuditFormSHospGenInfoShouldBeFound(
      "remark.equals=" + DEFAULT_REMARK
    );

    // Get all the auditFormSHospGenInfoList where remark equals to UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "remark.equals=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark not equals to DEFAULT_REMARK
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "remark.notEquals=" + DEFAULT_REMARK
    );

    // Get all the auditFormSHospGenInfoList where remark not equals to UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldBeFound(
      "remark.notEquals=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkIsInShouldWork() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark in DEFAULT_REMARK or UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldBeFound(
      "remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK
    );

    // Get all the auditFormSHospGenInfoList where remark equals to UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldNotBeFound("remark.in=" + UPDATED_REMARK);
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkIsNullOrNotNull() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark is not null
    defaultAuditFormSHospGenInfoShouldBeFound("remark.specified=true");

    // Get all the auditFormSHospGenInfoList where remark is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("remark.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark contains DEFAULT_REMARK
    defaultAuditFormSHospGenInfoShouldBeFound(
      "remark.contains=" + DEFAULT_REMARK
    );

    // Get all the auditFormSHospGenInfoList where remark contains UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "remark.contains=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByRemarkNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where remark does not contain DEFAULT_REMARK
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "remark.doesNotContain=" + DEFAULT_REMARK
    );

    // Get all the auditFormSHospGenInfoList where remark does not contain UPDATED_REMARK
    defaultAuditFormSHospGenInfoShouldBeFound(
      "remark.doesNotContain=" + UPDATED_REMARK
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate equals to DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate not equals to UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate equals to UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate is not null
    defaultAuditFormSHospGenInfoShouldBeFound("createdDate.specified=true");

    // Get all the auditFormSHospGenInfoList where createdDate is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate is less than DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate is less than UPDATED_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the auditFormSHospGenInfoList where createdDate is greater than SMALLER_CREATED_DATE
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy equals to DEFAULT_CREATED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the auditFormSHospGenInfoList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy not equals to DEFAULT_CREATED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the auditFormSHospGenInfoList where createdBy not equals to UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the auditFormSHospGenInfoList where createdBy equals to UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy is not null
    defaultAuditFormSHospGenInfoShouldBeFound("createdBy.specified=true");

    // Get all the auditFormSHospGenInfoList where createdBy is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy contains DEFAULT_CREATED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the auditFormSHospGenInfoList where createdBy contains UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where createdBy does not contain DEFAULT_CREATED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the auditFormSHospGenInfoList where createdBy does not contain UPDATED_CREATED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified is not null
    defaultAuditFormSHospGenInfoShouldBeFound("lastModified.specified=true");

    // Get all the auditFormSHospGenInfoList where lastModified is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the auditFormSHospGenInfoList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditFormSHospGenInfoList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditFormSHospGenInfoList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the auditFormSHospGenInfoList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy is not null
    defaultAuditFormSHospGenInfoShouldBeFound("lastModifiedBy.specified=true");

    // Get all the auditFormSHospGenInfoList where lastModifiedBy is null
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditFormSHospGenInfoList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the auditFormSHospGenInfoList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultAuditFormSHospGenInfoShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditFormSHospGenInfoList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditFormSHospGenInfoList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the auditFormSHospGenInfoList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 is not null
    defaultAuditFormSHospGenInfoShouldBeFound("freeField1.specified=true");

    // Get all the auditFormSHospGenInfoList where freeField1 is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1ContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditFormSHospGenInfoList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the auditFormSHospGenInfoList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditFormSHospGenInfoList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditFormSHospGenInfoList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the auditFormSHospGenInfoList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 is not null
    defaultAuditFormSHospGenInfoShouldBeFound("freeField2.specified=true");

    // Get all the auditFormSHospGenInfoList where freeField2 is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2ContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditFormSHospGenInfoList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the auditFormSHospGenInfoList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditFormSHospGenInfoList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditFormSHospGenInfoList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the auditFormSHospGenInfoList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 is not null
    defaultAuditFormSHospGenInfoShouldBeFound("freeField3.specified=true");

    // Get all the auditFormSHospGenInfoList where freeField3 is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3ContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditFormSHospGenInfoList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the auditFormSHospGenInfoList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4IsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField4.equals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditFormSHospGenInfoList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField4.equals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditFormSHospGenInfoList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField4.notEquals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4IsInShouldWork()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the auditFormSHospGenInfoList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField4.in=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 is not null
    defaultAuditFormSHospGenInfoShouldBeFound("freeField4.specified=true");

    // Get all the auditFormSHospGenInfoList where freeField4 is null
    defaultAuditFormSHospGenInfoShouldNotBeFound("freeField4.specified=false");
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4ContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField4.contains=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditFormSHospGenInfoList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField4.contains=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByFreeField4NotContainsSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    // Get all the auditFormSHospGenInfoList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the auditFormSHospGenInfoList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultAuditFormSHospGenInfoShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllAuditFormSHospGenInfosByAuditIsEqualToSomething()
    throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);
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
    auditFormSHospGenInfo.setAudit(audit);
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);
    Long auditId = audit.getId();

    // Get all the auditFormSHospGenInfoList where audit equals to auditId
    defaultAuditFormSHospGenInfoShouldBeFound("auditId.equals=" + auditId);

    // Get all the auditFormSHospGenInfoList where audit equals to (auditId + 1)
    defaultAuditFormSHospGenInfoShouldNotBeFound(
      "auditId.equals=" + (auditId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultAuditFormSHospGenInfoShouldBeFound(String filter)
    throws Exception {
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(auditFormSHospGenInfo.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].hospName").value(hasItem(DEFAULT_HOSP_NAME)))
      .andExpect(jsonPath("$.[*].hospType").value(hasItem(DEFAULT_HOSP_TYPE)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(
        jsonPath("$.[*].inchargeName").value(hasItem(DEFAULT_INCHARGE_NAME))
      )
      .andExpect(
        jsonPath("$.[*].hospAddress").value(hasItem(DEFAULT_HOSP_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].hospPhoneNo").value(hasItem(DEFAULT_HOSP_PHONE_NO))
      )
      .andExpect(
        jsonPath("$.[*].normalBeds").value(hasItem(DEFAULT_NORMAL_BEDS))
      )
      .andExpect(
        jsonPath("$.[*].oxygenBeds").value(hasItem(DEFAULT_OXYGEN_BEDS))
      )
      .andExpect(
        jsonPath("$.[*].ventilatorBeds").value(hasItem(DEFAULT_VENTILATOR_BEDS))
      )
      .andExpect(jsonPath("$.[*].icuBeds").value(hasItem(DEFAULT_ICU_BEDS)))
      .andExpect(
        jsonPath("$.[*].onCylinderPatient")
          .value(hasItem(DEFAULT_ON_CYLINDER_PATIENT))
      )
      .andExpect(
        jsonPath("$.[*].onPipedBedsPatient")
          .value(hasItem(DEFAULT_ON_PIPED_BEDS_PATIENT))
      )
      .andExpect(jsonPath("$.[*].onNIV").value(hasItem(DEFAULT_ON_NIV)))
      .andExpect(
        jsonPath("$.[*].onIntubated").value(hasItem(DEFAULT_ON_INTUBATED))
      )
      .andExpect(
        jsonPath("$.[*].jumboSystemInstalledType")
          .value(hasItem(DEFAULT_JUMBO_SYSTEM_INSTALLED_TYPE))
      )
      .andExpect(
        jsonPath("$.[*].availableCylinderTypeD7")
          .value(hasItem(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7))
      )
      .andExpect(
        jsonPath("$.[*].availableCylinderTypeB")
          .value(hasItem(DEFAULT_AVAILABLE_CYLINDER_TYPE_B))
      )
      .andExpect(
        jsonPath("$.[*].cylinderAgencyName")
          .value(hasItem(DEFAULT_CYLINDER_AGENCY_NAME))
      )
      .andExpect(
        jsonPath("$.[*].cylinderAgencyAddress")
          .value(hasItem(DEFAULT_CYLINDER_AGENCY_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].availableLMOCapacityKL")
          .value(hasItem(DEFAULT_AVAILABLE_LMO_CAPACITY_KL.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].lmoSupplierName")
          .value(hasItem(DEFAULT_LMO_SUPPLIER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].lmoSupplierFrequency")
          .value(hasItem(DEFAULT_LMO_SUPPLIER_FREQUENCY))
      )
      .andExpect(
        jsonPath("$.[*].lastLmoSuppliedQuantity")
          .value(hasItem(DEFAULT_LAST_LMO_SUPPLIED_QUANTITY.doubleValue()))
      )
      .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
      .andExpect(
        jsonPath("$.[*].createdDate")
          .value(hasItem(DEFAULT_CREATED_DATE.toString()))
      )
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(
        jsonPath("$.[*].lastModified")
          .value(hasItem(DEFAULT_LAST_MODIFIED.toString()))
      )
      .andExpect(
        jsonPath("$.[*].lastModifiedBy")
          .value(hasItem(DEFAULT_LAST_MODIFIED_BY))
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
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultAuditFormSHospGenInfoShouldNotBeFound(String filter)
    throws Exception {
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingAuditFormSHospGenInfo() throws Exception {
    // Get the auditFormSHospGenInfo
    restAuditFormSHospGenInfoMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewAuditFormSHospGenInfo() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();

    // Update the auditFormSHospGenInfo
    AuditFormSHospGenInfo updatedAuditFormSHospGenInfo = auditFormSHospGenInfoRepository
      .findById(auditFormSHospGenInfo.getId())
      .get();
    // Disconnect from session so that the updates on updatedAuditFormSHospGenInfo are not directly saved in db
    em.detach(updatedAuditFormSHospGenInfo);
    updatedAuditFormSHospGenInfo
      .hospName(UPDATED_HOSP_NAME)
      .hospType(UPDATED_HOSP_TYPE)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .formName(UPDATED_FORM_NAME)
      .inchargeName(UPDATED_INCHARGE_NAME)
      .hospAddress(UPDATED_HOSP_ADDRESS)
      .hospPhoneNo(UPDATED_HOSP_PHONE_NO)
      .normalBeds(UPDATED_NORMAL_BEDS)
      .oxygenBeds(UPDATED_OXYGEN_BEDS)
      .ventilatorBeds(UPDATED_VENTILATOR_BEDS)
      .icuBeds(UPDATED_ICU_BEDS)
      .onCylinderPatient(UPDATED_ON_CYLINDER_PATIENT)
      .onPipedBedsPatient(UPDATED_ON_PIPED_BEDS_PATIENT)
      .onNIV(UPDATED_ON_NIV)
      .onIntubated(UPDATED_ON_INTUBATED)
      .jumboSystemInstalledType(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE)
      .availableCylinderTypeD7(UPDATED_AVAILABLE_CYLINDER_TYPE_D_7)
      .availableCylinderTypeB(UPDATED_AVAILABLE_CYLINDER_TYPE_B)
      .cylinderAgencyName(UPDATED_CYLINDER_AGENCY_NAME)
      .cylinderAgencyAddress(UPDATED_CYLINDER_AGENCY_ADDRESS)
      .availableLMOCapacityKL(UPDATED_AVAILABLE_LMO_CAPACITY_KL)
      .lmoSupplierName(UPDATED_LMO_SUPPLIER_NAME)
      .lmoSupplierFrequency(UPDATED_LMO_SUPPLIER_FREQUENCY)
      .lastLmoSuppliedQuantity(UPDATED_LAST_LMO_SUPPLIED_QUANTITY)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      updatedAuditFormSHospGenInfo
    );

    restAuditFormSHospGenInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, auditFormSHospGenInfoDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfoDTO))
      )
      .andExpect(status().isOk());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
    AuditFormSHospGenInfo testAuditFormSHospGenInfo = auditFormSHospGenInfoList.get(
      auditFormSHospGenInfoList.size() - 1
    );
    assertThat(testAuditFormSHospGenInfo.getHospName())
      .isEqualTo(UPDATED_HOSP_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospType())
      .isEqualTo(UPDATED_HOSP_TYPE);
    assertThat(testAuditFormSHospGenInfo.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAuditFormSHospGenInfo.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAuditFormSHospGenInfo.getInchargeName())
      .isEqualTo(UPDATED_INCHARGE_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospAddress())
      .isEqualTo(UPDATED_HOSP_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getHospPhoneNo())
      .isEqualTo(UPDATED_HOSP_PHONE_NO);
    assertThat(testAuditFormSHospGenInfo.getNormalBeds())
      .isEqualTo(UPDATED_NORMAL_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOxygenBeds())
      .isEqualTo(UPDATED_OXYGEN_BEDS);
    assertThat(testAuditFormSHospGenInfo.getVentilatorBeds())
      .isEqualTo(UPDATED_VENTILATOR_BEDS);
    assertThat(testAuditFormSHospGenInfo.getIcuBeds())
      .isEqualTo(UPDATED_ICU_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOnCylinderPatient())
      .isEqualTo(UPDATED_ON_CYLINDER_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnPipedBedsPatient())
      .isEqualTo(UPDATED_ON_PIPED_BEDS_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnNIV()).isEqualTo(UPDATED_ON_NIV);
    assertThat(testAuditFormSHospGenInfo.getOnIntubated())
      .isEqualTo(UPDATED_ON_INTUBATED);
    assertThat(testAuditFormSHospGenInfo.getJumboSystemInstalledType())
      .isEqualTo(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeD7())
      .isEqualTo(UPDATED_AVAILABLE_CYLINDER_TYPE_D_7);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeB())
      .isEqualTo(UPDATED_AVAILABLE_CYLINDER_TYPE_B);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyName())
      .isEqualTo(UPDATED_CYLINDER_AGENCY_NAME);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyAddress())
      .isEqualTo(UPDATED_CYLINDER_AGENCY_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getAvailableLMOCapacityKL())
      .isEqualTo(UPDATED_AVAILABLE_LMO_CAPACITY_KL);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierName())
      .isEqualTo(UPDATED_LMO_SUPPLIER_NAME);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierFrequency())
      .isEqualTo(UPDATED_LMO_SUPPLIER_FREQUENCY);
    assertThat(testAuditFormSHospGenInfo.getLastLmoSuppliedQuantity())
      .isEqualTo(UPDATED_LAST_LMO_SUPPLIED_QUANTITY);
    assertThat(testAuditFormSHospGenInfo.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAuditFormSHospGenInfo.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditFormSHospGenInfo.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAuditFormSHospGenInfo.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAuditFormSHospGenInfo.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testAuditFormSHospGenInfo.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAuditFormSHospGenInfo.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAuditFormSHospGenInfo.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAuditFormSHospGenInfo.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void putNonExistingAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, auditFormSHospGenInfo.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfo))
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfo))
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfo auditFormSHospGenInfo = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfo))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateAuditFormSHospGenInfoWithPatch() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();

    // Update the auditFormSHospGenInfo using partial update
    AuditFormSHospGenInfo partialUpdatedAuditFormSHospGenInfo = new AuditFormSHospGenInfo();
    partialUpdatedAuditFormSHospGenInfo.setId(auditFormSHospGenInfo.getId());

    partialUpdatedAuditFormSHospGenInfo
      .formName(UPDATED_FORM_NAME)
      .inchargeName(UPDATED_INCHARGE_NAME)
      .hospAddress(UPDATED_HOSP_ADDRESS)
      .normalBeds(UPDATED_NORMAL_BEDS)
      .icuBeds(UPDATED_ICU_BEDS)
      .onCylinderPatient(UPDATED_ON_CYLINDER_PATIENT)
      .onIntubated(UPDATED_ON_INTUBATED)
      .jumboSystemInstalledType(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE)
      .availableCylinderTypeB(UPDATED_AVAILABLE_CYLINDER_TYPE_B)
      .availableLMOCapacityKL(UPDATED_AVAILABLE_LMO_CAPACITY_KL)
      .lastLmoSuppliedQuantity(UPDATED_LAST_LMO_SUPPLIED_QUANTITY)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField2(UPDATED_FREE_FIELD_2);

    restAuditFormSHospGenInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAuditFormSHospGenInfo.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedAuditFormSHospGenInfo
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
    AuditFormSHospGenInfo testAuditFormSHospGenInfo = auditFormSHospGenInfoList.get(
      auditFormSHospGenInfoList.size() - 1
    );
    assertThat(testAuditFormSHospGenInfo.getHospName())
      .isEqualTo(DEFAULT_HOSP_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospType())
      .isEqualTo(DEFAULT_HOSP_TYPE);
    assertThat(testAuditFormSHospGenInfo.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testAuditFormSHospGenInfo.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testAuditFormSHospGenInfo.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAuditFormSHospGenInfo.getInchargeName())
      .isEqualTo(UPDATED_INCHARGE_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospAddress())
      .isEqualTo(UPDATED_HOSP_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getHospPhoneNo())
      .isEqualTo(DEFAULT_HOSP_PHONE_NO);
    assertThat(testAuditFormSHospGenInfo.getNormalBeds())
      .isEqualTo(UPDATED_NORMAL_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOxygenBeds())
      .isEqualTo(DEFAULT_OXYGEN_BEDS);
    assertThat(testAuditFormSHospGenInfo.getVentilatorBeds())
      .isEqualTo(DEFAULT_VENTILATOR_BEDS);
    assertThat(testAuditFormSHospGenInfo.getIcuBeds())
      .isEqualTo(UPDATED_ICU_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOnCylinderPatient())
      .isEqualTo(UPDATED_ON_CYLINDER_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnPipedBedsPatient())
      .isEqualTo(DEFAULT_ON_PIPED_BEDS_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnNIV()).isEqualTo(DEFAULT_ON_NIV);
    assertThat(testAuditFormSHospGenInfo.getOnIntubated())
      .isEqualTo(UPDATED_ON_INTUBATED);
    assertThat(testAuditFormSHospGenInfo.getJumboSystemInstalledType())
      .isEqualTo(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeD7())
      .isEqualTo(DEFAULT_AVAILABLE_CYLINDER_TYPE_D_7);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeB())
      .isEqualTo(UPDATED_AVAILABLE_CYLINDER_TYPE_B);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyName())
      .isEqualTo(DEFAULT_CYLINDER_AGENCY_NAME);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyAddress())
      .isEqualTo(DEFAULT_CYLINDER_AGENCY_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getAvailableLMOCapacityKL())
      .isEqualTo(UPDATED_AVAILABLE_LMO_CAPACITY_KL);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierName())
      .isEqualTo(DEFAULT_LMO_SUPPLIER_NAME);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierFrequency())
      .isEqualTo(DEFAULT_LMO_SUPPLIER_FREQUENCY);
    assertThat(testAuditFormSHospGenInfo.getLastLmoSuppliedQuantity())
      .isEqualTo(UPDATED_LAST_LMO_SUPPLIED_QUANTITY);
    assertThat(testAuditFormSHospGenInfo.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAuditFormSHospGenInfo.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditFormSHospGenInfo.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAuditFormSHospGenInfo.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAuditFormSHospGenInfo.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    assertThat(testAuditFormSHospGenInfo.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testAuditFormSHospGenInfo.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAuditFormSHospGenInfo.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testAuditFormSHospGenInfo.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void fullUpdateAuditFormSHospGenInfoWithPatch() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();

    // Update the auditFormSHospGenInfo using partial update
    AuditFormSHospGenInfo partialUpdatedAuditFormSHospGenInfo = new AuditFormSHospGenInfo();
    partialUpdatedAuditFormSHospGenInfo.setId(auditFormSHospGenInfo.getId());

    partialUpdatedAuditFormSHospGenInfo
      .hospName(UPDATED_HOSP_NAME)
      .hospType(UPDATED_HOSP_TYPE)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .formName(UPDATED_FORM_NAME)
      .inchargeName(UPDATED_INCHARGE_NAME)
      .hospAddress(UPDATED_HOSP_ADDRESS)
      .hospPhoneNo(UPDATED_HOSP_PHONE_NO)
      .normalBeds(UPDATED_NORMAL_BEDS)
      .oxygenBeds(UPDATED_OXYGEN_BEDS)
      .ventilatorBeds(UPDATED_VENTILATOR_BEDS)
      .icuBeds(UPDATED_ICU_BEDS)
      .onCylinderPatient(UPDATED_ON_CYLINDER_PATIENT)
      .onPipedBedsPatient(UPDATED_ON_PIPED_BEDS_PATIENT)
      .onNIV(UPDATED_ON_NIV)
      .onIntubated(UPDATED_ON_INTUBATED)
      .jumboSystemInstalledType(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE)
      .availableCylinderTypeD7(UPDATED_AVAILABLE_CYLINDER_TYPE_D_7)
      .availableCylinderTypeB(UPDATED_AVAILABLE_CYLINDER_TYPE_B)
      .cylinderAgencyName(UPDATED_CYLINDER_AGENCY_NAME)
      .cylinderAgencyAddress(UPDATED_CYLINDER_AGENCY_ADDRESS)
      .availableLMOCapacityKL(UPDATED_AVAILABLE_LMO_CAPACITY_KL)
      .lmoSupplierName(UPDATED_LMO_SUPPLIER_NAME)
      .lmoSupplierFrequency(UPDATED_LMO_SUPPLIER_FREQUENCY)
      .lastLmoSuppliedQuantity(UPDATED_LAST_LMO_SUPPLIED_QUANTITY)
      .remark(UPDATED_REMARK)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);

    restAuditFormSHospGenInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedAuditFormSHospGenInfo.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedAuditFormSHospGenInfo
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
    AuditFormSHospGenInfo testAuditFormSHospGenInfo = auditFormSHospGenInfoList.get(
      auditFormSHospGenInfoList.size() - 1
    );
    assertThat(testAuditFormSHospGenInfo.getHospName())
      .isEqualTo(UPDATED_HOSP_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospType())
      .isEqualTo(UPDATED_HOSP_TYPE);
    assertThat(testAuditFormSHospGenInfo.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testAuditFormSHospGenInfo.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testAuditFormSHospGenInfo.getInchargeName())
      .isEqualTo(UPDATED_INCHARGE_NAME);
    assertThat(testAuditFormSHospGenInfo.getHospAddress())
      .isEqualTo(UPDATED_HOSP_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getHospPhoneNo())
      .isEqualTo(UPDATED_HOSP_PHONE_NO);
    assertThat(testAuditFormSHospGenInfo.getNormalBeds())
      .isEqualTo(UPDATED_NORMAL_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOxygenBeds())
      .isEqualTo(UPDATED_OXYGEN_BEDS);
    assertThat(testAuditFormSHospGenInfo.getVentilatorBeds())
      .isEqualTo(UPDATED_VENTILATOR_BEDS);
    assertThat(testAuditFormSHospGenInfo.getIcuBeds())
      .isEqualTo(UPDATED_ICU_BEDS);
    assertThat(testAuditFormSHospGenInfo.getOnCylinderPatient())
      .isEqualTo(UPDATED_ON_CYLINDER_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnPipedBedsPatient())
      .isEqualTo(UPDATED_ON_PIPED_BEDS_PATIENT);
    assertThat(testAuditFormSHospGenInfo.getOnNIV()).isEqualTo(UPDATED_ON_NIV);
    assertThat(testAuditFormSHospGenInfo.getOnIntubated())
      .isEqualTo(UPDATED_ON_INTUBATED);
    assertThat(testAuditFormSHospGenInfo.getJumboSystemInstalledType())
      .isEqualTo(UPDATED_JUMBO_SYSTEM_INSTALLED_TYPE);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeD7())
      .isEqualTo(UPDATED_AVAILABLE_CYLINDER_TYPE_D_7);
    assertThat(testAuditFormSHospGenInfo.getAvailableCylinderTypeB())
      .isEqualTo(UPDATED_AVAILABLE_CYLINDER_TYPE_B);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyName())
      .isEqualTo(UPDATED_CYLINDER_AGENCY_NAME);
    assertThat(testAuditFormSHospGenInfo.getCylinderAgencyAddress())
      .isEqualTo(UPDATED_CYLINDER_AGENCY_ADDRESS);
    assertThat(testAuditFormSHospGenInfo.getAvailableLMOCapacityKL())
      .isEqualTo(UPDATED_AVAILABLE_LMO_CAPACITY_KL);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierName())
      .isEqualTo(UPDATED_LMO_SUPPLIER_NAME);
    assertThat(testAuditFormSHospGenInfo.getLmoSupplierFrequency())
      .isEqualTo(UPDATED_LMO_SUPPLIER_FREQUENCY);
    assertThat(testAuditFormSHospGenInfo.getLastLmoSuppliedQuantity())
      .isEqualTo(UPDATED_LAST_LMO_SUPPLIED_QUANTITY);
    assertThat(testAuditFormSHospGenInfo.getRemark()).isEqualTo(UPDATED_REMARK);
    assertThat(testAuditFormSHospGenInfo.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testAuditFormSHospGenInfo.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testAuditFormSHospGenInfo.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testAuditFormSHospGenInfo.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testAuditFormSHospGenInfo.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testAuditFormSHospGenInfo.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testAuditFormSHospGenInfo.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testAuditFormSHospGenInfo.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void patchNonExistingAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfoDTO auditFormSHospGenInfoDTO = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, auditFormSHospGenInfoDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfoDTO auditFormSHospGenInfoDTO = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfoDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamAuditFormSHospGenInfo() throws Exception {
    int databaseSizeBeforeUpdate = auditFormSHospGenInfoRepository
      .findAll()
      .size();
    auditFormSHospGenInfo.setId(count.incrementAndGet());

    // Create the AuditFormSHospGenInfo
    AuditFormSHospGenInfoDTO auditFormSHospGenInfoDTO = auditFormSHospGenInfoMapper.toDto(
      auditFormSHospGenInfo
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restAuditFormSHospGenInfoMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(auditFormSHospGenInfoDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the AuditFormSHospGenInfo in the database
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteAuditFormSHospGenInfo() throws Exception {
    // Initialize the database
    auditFormSHospGenInfoRepository.saveAndFlush(auditFormSHospGenInfo);

    int databaseSizeBeforeDelete = auditFormSHospGenInfoRepository
      .findAll()
      .size();

    // Delete the auditFormSHospGenInfo
    restAuditFormSHospGenInfoMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, auditFormSHospGenInfo.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<AuditFormSHospGenInfo> auditFormSHospGenInfoList = auditFormSHospGenInfoRepository.findAll();
    assertThat(auditFormSHospGenInfoList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
