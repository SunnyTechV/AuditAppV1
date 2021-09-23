package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.OxygenConsumptionData;
import com.vgtech.auditapp.domain.TableDetails;
import com.vgtech.auditapp.repository.OxygenConsumptionDataRepository;
import com.vgtech.auditapp.service.criteria.OxygenConsumptionDataCriteria;
import com.vgtech.auditapp.service.dto.OxygenConsumptionData;
import com.vgtech.auditapp.service.mapper.OxygenConsumptionDataMapper;
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
 * Integration tests for the {@link OxygenConsumptionDataResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OxygenConsumptionDataResourceIT {

  private static final String DEFAULT_FORM_NAME = "AAAAAAAAAA";
  private static final String UPDATED_FORM_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_SUB_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_SUB_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_TABLE_NAME = "AAAAAAAAAA";
  private static final String UPDATED_TABLE_NAME = "BBBBBBBBBB";

  private static final Integer DEFAULT_NOOF_PATIENTS = 1;
  private static final Integer UPDATED_NOOF_PATIENTS = 2;
  private static final Integer SMALLER_NOOF_PATIENTS = 1 - 1;

  private static final Double DEFAULT_CONSUMPTION_LITPER_MIN = 1D;
  private static final Double UPDATED_CONSUMPTION_LITPER_MIN = 2D;
  private static final Double SMALLER_CONSUMPTION_LITPER_MIN = 1D - 1D;

  private static final Double DEFAULT_CONSUMPTION_LITPER_DAY = 1D;
  private static final Double UPDATED_CONSUMPTION_LITPER_DAY = 2D;
  private static final Double SMALLER_CONSUMPTION_LITPER_DAY = 1D - 1D;

  private static final Double DEFAULT_CONSUMPTION_K_LITPER_DAY = 1D;
  private static final Double UPDATED_CONSUMPTION_K_LITPER_DAY = 2D;
  private static final Double SMALLER_CONSUMPTION_K_LITPER_DAY = 1D - 1D;

  private static final Double DEFAULT_CONSUMPTION_TOTAL = 1D;
  private static final Double UPDATED_CONSUMPTION_TOTAL = 2D;
  private static final Double SMALLER_CONSUMPTION_TOTAL = 1D - 1D;

  private static final String DEFAULT_FREE_FIELD_1 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_1 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_2 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_2 = "BBBBBBBBBB";

  private static final String DEFAULT_FREE_FIELD_3 = "AAAAAAAAAA";
  private static final String UPDATED_FREE_FIELD_3 = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/oxygen-consumption-data";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private OxygenConsumptionDataRepository oxygenConsumptionDataRepository;

  @Autowired
  private OxygenConsumptionDataMapper oxygenConsumptionDataMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restOxygenConsumptionDataMockMvc;

  private OxygenConsumptionData oxygenConsumptionData;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static OxygenConsumptionData createEntity(EntityManager em) {
    OxygenConsumptionData oxygenConsumptionData = new OxygenConsumptionData()
      .formName(DEFAULT_FORM_NAME)
      .type(DEFAULT_TYPE)
      .subType(DEFAULT_SUB_TYPE)
      .tableName(DEFAULT_TABLE_NAME)
      .noofPatients(DEFAULT_NOOF_PATIENTS)
      .consumptionLitperMin(DEFAULT_CONSUMPTION_LITPER_MIN)
      .consumptionLitperDay(DEFAULT_CONSUMPTION_LITPER_DAY)
      .consumptionKLitperDay(DEFAULT_CONSUMPTION_K_LITPER_DAY)
      .consumptionTotal(DEFAULT_CONSUMPTION_TOTAL)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .createdDate(DEFAULT_CREATED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY);
    return oxygenConsumptionData;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static OxygenConsumptionData createUpdatedEntity(EntityManager em) {
    OxygenConsumptionData oxygenConsumptionData = new OxygenConsumptionData()
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .noofPatients(UPDATED_NOOF_PATIENTS)
      .consumptionLitperMin(UPDATED_CONSUMPTION_LITPER_MIN)
      .consumptionLitperDay(UPDATED_CONSUMPTION_LITPER_DAY)
      .consumptionKLitperDay(UPDATED_CONSUMPTION_K_LITPER_DAY)
      .consumptionTotal(UPDATED_CONSUMPTION_TOTAL)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    return oxygenConsumptionData;
  }

  @BeforeEach
  public void initTest() {
    oxygenConsumptionData = createEntity(em);
  }

  @Test
  @Transactional
  void createOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeCreate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    // Create the OxygenConsumptionData
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );
    restOxygenConsumptionDataMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionData))
      )
      .andExpect(status().isCreated());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeCreate + 1);
    OxygenConsumptionData testOxygenConsumptionData = oxygenConsumptionDataList.get(
      oxygenConsumptionDataList.size() - 1
    );
    assertThat(testOxygenConsumptionData.getFormName())
      .isEqualTo(DEFAULT_FORM_NAME);
    assertThat(testOxygenConsumptionData.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testOxygenConsumptionData.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testOxygenConsumptionData.getTableName())
      .isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testOxygenConsumptionData.getNoofPatients())
      .isEqualTo(DEFAULT_NOOF_PATIENTS);
    assertThat(testOxygenConsumptionData.getConsumptionLitperMin())
      .isEqualTo(DEFAULT_CONSUMPTION_LITPER_MIN);
    assertThat(testOxygenConsumptionData.getConsumptionLitperDay())
      .isEqualTo(DEFAULT_CONSUMPTION_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionKLitperDay())
      .isEqualTo(DEFAULT_CONSUMPTION_K_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionTotal())
      .isEqualTo(DEFAULT_CONSUMPTION_TOTAL);
    assertThat(testOxygenConsumptionData.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testOxygenConsumptionData.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testOxygenConsumptionData.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testOxygenConsumptionData.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testOxygenConsumptionData.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testOxygenConsumptionData.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testOxygenConsumptionData.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void createOxygenConsumptionDataWithExistingId() throws Exception {
    // Create the OxygenConsumptionData with an existing ID
    oxygenConsumptionData.setId(1L);
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    int databaseSizeBeforeCreate = oxygenConsumptionDataRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restOxygenConsumptionDataMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionData))
      )
      .andExpect(status().isBadRequest());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionData() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(oxygenConsumptionData.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].noofPatients").value(hasItem(DEFAULT_NOOF_PATIENTS))
      )
      .andExpect(
        jsonPath("$.[*].consumptionLitperMin")
          .value(hasItem(DEFAULT_CONSUMPTION_LITPER_MIN.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionLitperDay")
          .value(hasItem(DEFAULT_CONSUMPTION_LITPER_DAY.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionKLitperDay")
          .value(hasItem(DEFAULT_CONSUMPTION_K_LITPER_DAY.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionTotal")
          .value(hasItem(DEFAULT_CONSUMPTION_TOTAL.doubleValue()))
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
      );
  }

  @Test
  @Transactional
  void getOxygenConsumptionData() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get the oxygenConsumptionData
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL_ID, oxygenConsumptionData.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.id").value(oxygenConsumptionData.getId().intValue())
      )
      .andExpect(jsonPath("$.formName").value(DEFAULT_FORM_NAME))
      .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
      .andExpect(jsonPath("$.subType").value(DEFAULT_SUB_TYPE))
      .andExpect(jsonPath("$.tableName").value(DEFAULT_TABLE_NAME))
      .andExpect(jsonPath("$.noofPatients").value(DEFAULT_NOOF_PATIENTS))
      .andExpect(
        jsonPath("$.consumptionLitperMin")
          .value(DEFAULT_CONSUMPTION_LITPER_MIN.doubleValue())
      )
      .andExpect(
        jsonPath("$.consumptionLitperDay")
          .value(DEFAULT_CONSUMPTION_LITPER_DAY.doubleValue())
      )
      .andExpect(
        jsonPath("$.consumptionKLitperDay")
          .value(DEFAULT_CONSUMPTION_K_LITPER_DAY.doubleValue())
      )
      .andExpect(
        jsonPath("$.consumptionTotal")
          .value(DEFAULT_CONSUMPTION_TOTAL.doubleValue())
      )
      .andExpect(jsonPath("$.freeField1").value(DEFAULT_FREE_FIELD_1))
      .andExpect(jsonPath("$.freeField2").value(DEFAULT_FREE_FIELD_2))
      .andExpect(jsonPath("$.freeField3").value(DEFAULT_FREE_FIELD_3))
      .andExpect(
        jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString())
      )
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(
        jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED.toString())
      )
      .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY));
  }

  @Test
  @Transactional
  void getOxygenConsumptionDataByIdFiltering() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    Long id = oxygenConsumptionData.getId();

    defaultOxygenConsumptionDataShouldBeFound("id.equals=" + id);
    defaultOxygenConsumptionDataShouldNotBeFound("id.notEquals=" + id);

    defaultOxygenConsumptionDataShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultOxygenConsumptionDataShouldNotBeFound("id.greaterThan=" + id);

    defaultOxygenConsumptionDataShouldBeFound("id.lessThanOrEqual=" + id);
    defaultOxygenConsumptionDataShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName equals to DEFAULT_FORM_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "formName.equals=" + DEFAULT_FORM_NAME
    );

    // Get all the oxygenConsumptionDataList where formName equals to UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "formName.equals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName not equals to DEFAULT_FORM_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "formName.notEquals=" + DEFAULT_FORM_NAME
    );

    // Get all the oxygenConsumptionDataList where formName not equals to UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "formName.notEquals=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameIsInShouldWork() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName in DEFAULT_FORM_NAME or UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "formName.in=" + DEFAULT_FORM_NAME + "," + UPDATED_FORM_NAME
    );

    // Get all the oxygenConsumptionDataList where formName equals to UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "formName.in=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameIsNullOrNotNull() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName is not null
    defaultOxygenConsumptionDataShouldBeFound("formName.specified=true");

    // Get all the oxygenConsumptionDataList where formName is null
    defaultOxygenConsumptionDataShouldNotBeFound("formName.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName contains DEFAULT_FORM_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "formName.contains=" + DEFAULT_FORM_NAME
    );

    // Get all the oxygenConsumptionDataList where formName contains UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "formName.contains=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFormNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where formName does not contain DEFAULT_FORM_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "formName.doesNotContain=" + DEFAULT_FORM_NAME
    );

    // Get all the oxygenConsumptionDataList where formName does not contain UPDATED_FORM_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "formName.doesNotContain=" + UPDATED_FORM_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeIsEqualToSomething() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type equals to DEFAULT_TYPE
    defaultOxygenConsumptionDataShouldBeFound("type.equals=" + DEFAULT_TYPE);

    // Get all the oxygenConsumptionDataList where type equals to UPDATED_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound("type.equals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type not equals to DEFAULT_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "type.notEquals=" + DEFAULT_TYPE
    );

    // Get all the oxygenConsumptionDataList where type not equals to UPDATED_TYPE
    defaultOxygenConsumptionDataShouldBeFound("type.notEquals=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeIsInShouldWork() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type in DEFAULT_TYPE or UPDATED_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE
    );

    // Get all the oxygenConsumptionDataList where type equals to UPDATED_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound("type.in=" + UPDATED_TYPE);
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type is not null
    defaultOxygenConsumptionDataShouldBeFound("type.specified=true");

    // Get all the oxygenConsumptionDataList where type is null
    defaultOxygenConsumptionDataShouldNotBeFound("type.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeContainsSomething() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type contains DEFAULT_TYPE
    defaultOxygenConsumptionDataShouldBeFound("type.contains=" + DEFAULT_TYPE);

    // Get all the oxygenConsumptionDataList where type contains UPDATED_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "type.contains=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where type does not contain DEFAULT_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "type.doesNotContain=" + DEFAULT_TYPE
    );

    // Get all the oxygenConsumptionDataList where type does not contain UPDATED_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "type.doesNotContain=" + UPDATED_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType equals to DEFAULT_SUB_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "subType.equals=" + DEFAULT_SUB_TYPE
    );

    // Get all the oxygenConsumptionDataList where subType equals to UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "subType.equals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType not equals to DEFAULT_SUB_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "subType.notEquals=" + DEFAULT_SUB_TYPE
    );

    // Get all the oxygenConsumptionDataList where subType not equals to UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "subType.notEquals=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeIsInShouldWork() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType in DEFAULT_SUB_TYPE or UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "subType.in=" + DEFAULT_SUB_TYPE + "," + UPDATED_SUB_TYPE
    );

    // Get all the oxygenConsumptionDataList where subType equals to UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "subType.in=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeIsNullOrNotNull() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType is not null
    defaultOxygenConsumptionDataShouldBeFound("subType.specified=true");

    // Get all the oxygenConsumptionDataList where subType is null
    defaultOxygenConsumptionDataShouldNotBeFound("subType.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType contains DEFAULT_SUB_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "subType.contains=" + DEFAULT_SUB_TYPE
    );

    // Get all the oxygenConsumptionDataList where subType contains UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "subType.contains=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataBySubTypeNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where subType does not contain DEFAULT_SUB_TYPE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "subType.doesNotContain=" + DEFAULT_SUB_TYPE
    );

    // Get all the oxygenConsumptionDataList where subType does not contain UPDATED_SUB_TYPE
    defaultOxygenConsumptionDataShouldBeFound(
      "subType.doesNotContain=" + UPDATED_SUB_TYPE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName equals to DEFAULT_TABLE_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "tableName.equals=" + DEFAULT_TABLE_NAME
    );

    // Get all the oxygenConsumptionDataList where tableName equals to UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableName.equals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName not equals to DEFAULT_TABLE_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableName.notEquals=" + DEFAULT_TABLE_NAME
    );

    // Get all the oxygenConsumptionDataList where tableName not equals to UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "tableName.notEquals=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameIsInShouldWork() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName in DEFAULT_TABLE_NAME or UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "tableName.in=" + DEFAULT_TABLE_NAME + "," + UPDATED_TABLE_NAME
    );

    // Get all the oxygenConsumptionDataList where tableName equals to UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableName.in=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName is not null
    defaultOxygenConsumptionDataShouldBeFound("tableName.specified=true");

    // Get all the oxygenConsumptionDataList where tableName is null
    defaultOxygenConsumptionDataShouldNotBeFound("tableName.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName contains DEFAULT_TABLE_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "tableName.contains=" + DEFAULT_TABLE_NAME
    );

    // Get all the oxygenConsumptionDataList where tableName contains UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableName.contains=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where tableName does not contain DEFAULT_TABLE_NAME
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableName.doesNotContain=" + DEFAULT_TABLE_NAME
    );

    // Get all the oxygenConsumptionDataList where tableName does not contain UPDATED_TABLE_NAME
    defaultOxygenConsumptionDataShouldBeFound(
      "tableName.doesNotContain=" + UPDATED_TABLE_NAME
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients equals to DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.equals=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients equals to UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.equals=" + UPDATED_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients not equals to DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.notEquals=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients not equals to UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.notEquals=" + UPDATED_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients in DEFAULT_NOOF_PATIENTS or UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.in=" + DEFAULT_NOOF_PATIENTS + "," + UPDATED_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients equals to UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.in=" + UPDATED_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients is not null
    defaultOxygenConsumptionDataShouldBeFound("noofPatients.specified=true");

    // Get all the oxygenConsumptionDataList where noofPatients is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients is greater than or equal to DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.greaterThanOrEqual=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients is greater than or equal to UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.greaterThanOrEqual=" + UPDATED_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients is less than or equal to DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.lessThanOrEqual=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients is less than or equal to SMALLER_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.lessThanOrEqual=" + SMALLER_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients is less than DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.lessThan=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients is less than UPDATED_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.lessThan=" + UPDATED_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByNoofPatientsIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where noofPatients is greater than DEFAULT_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldNotBeFound(
      "noofPatients.greaterThan=" + DEFAULT_NOOF_PATIENTS
    );

    // Get all the oxygenConsumptionDataList where noofPatients is greater than SMALLER_NOOF_PATIENTS
    defaultOxygenConsumptionDataShouldBeFound(
      "noofPatients.greaterThan=" + SMALLER_NOOF_PATIENTS
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin equals to DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.equals=" + DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin equals to UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.equals=" + UPDATED_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin not equals to DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.notEquals=" + DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin not equals to UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.notEquals=" + UPDATED_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin in DEFAULT_CONSUMPTION_LITPER_MIN or UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.in=" +
      DEFAULT_CONSUMPTION_LITPER_MIN +
      "," +
      UPDATED_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin equals to UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.in=" + UPDATED_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is not null
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.specified=true"
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is greater than or equal to DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.greaterThanOrEqual=" +
      DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is greater than or equal to UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.greaterThanOrEqual=" +
      UPDATED_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is less than or equal to DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.lessThanOrEqual=" + DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is less than or equal to SMALLER_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.lessThanOrEqual=" + SMALLER_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is less than DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.lessThan=" + DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is less than UPDATED_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.lessThan=" + UPDATED_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperMinIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is greater than DEFAULT_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperMin.greaterThan=" + DEFAULT_CONSUMPTION_LITPER_MIN
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperMin is greater than SMALLER_CONSUMPTION_LITPER_MIN
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperMin.greaterThan=" + SMALLER_CONSUMPTION_LITPER_MIN
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay equals to DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.equals=" + DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay equals to UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.equals=" + UPDATED_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay not equals to DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.notEquals=" + DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay not equals to UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.notEquals=" + UPDATED_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay in DEFAULT_CONSUMPTION_LITPER_DAY or UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.in=" +
      DEFAULT_CONSUMPTION_LITPER_DAY +
      "," +
      UPDATED_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay equals to UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.in=" + UPDATED_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is not null
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.specified=true"
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is greater than or equal to DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.greaterThanOrEqual=" +
      DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is greater than or equal to UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.greaterThanOrEqual=" +
      UPDATED_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is less than or equal to DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.lessThanOrEqual=" + DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is less than or equal to SMALLER_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.lessThanOrEqual=" + SMALLER_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is less than DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.lessThan=" + DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is less than UPDATED_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.lessThan=" + UPDATED_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionLitperDayIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is greater than DEFAULT_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionLitperDay.greaterThan=" + DEFAULT_CONSUMPTION_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionLitperDay is greater than SMALLER_CONSUMPTION_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionLitperDay.greaterThan=" + SMALLER_CONSUMPTION_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay equals to DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.equals=" + DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay equals to UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.equals=" + UPDATED_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay not equals to DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.notEquals=" + DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay not equals to UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.notEquals=" + UPDATED_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay in DEFAULT_CONSUMPTION_K_LITPER_DAY or UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.in=" +
      DEFAULT_CONSUMPTION_K_LITPER_DAY +
      "," +
      UPDATED_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay equals to UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.in=" + UPDATED_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is not null
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.specified=true"
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is greater than or equal to DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.greaterThanOrEqual=" +
      DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is greater than or equal to UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.greaterThanOrEqual=" +
      UPDATED_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is less than or equal to DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.lessThanOrEqual=" +
      DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is less than or equal to SMALLER_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.lessThanOrEqual=" +
      SMALLER_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is less than DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.lessThan=" + DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is less than UPDATED_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.lessThan=" + UPDATED_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionKLitperDayIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is greater than DEFAULT_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionKLitperDay.greaterThan=" + DEFAULT_CONSUMPTION_K_LITPER_DAY
    );

    // Get all the oxygenConsumptionDataList where consumptionKLitperDay is greater than SMALLER_CONSUMPTION_K_LITPER_DAY
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionKLitperDay.greaterThan=" + SMALLER_CONSUMPTION_K_LITPER_DAY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal equals to DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.equals=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal equals to UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.equals=" + UPDATED_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal not equals to DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.notEquals=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal not equals to UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.notEquals=" + UPDATED_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal in DEFAULT_CONSUMPTION_TOTAL or UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.in=" +
      DEFAULT_CONSUMPTION_TOTAL +
      "," +
      UPDATED_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal equals to UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.in=" + UPDATED_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal is not null
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.specified=true"
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal is greater than or equal to DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.greaterThanOrEqual=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal is greater than or equal to UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.greaterThanOrEqual=" + UPDATED_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal is less than or equal to DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.lessThanOrEqual=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal is less than or equal to SMALLER_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.lessThanOrEqual=" + SMALLER_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal is less than DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.lessThan=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal is less than UPDATED_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.lessThan=" + UPDATED_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByConsumptionTotalIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where consumptionTotal is greater than DEFAULT_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldNotBeFound(
      "consumptionTotal.greaterThan=" + DEFAULT_CONSUMPTION_TOTAL
    );

    // Get all the oxygenConsumptionDataList where consumptionTotal is greater than SMALLER_CONSUMPTION_TOTAL
    defaultOxygenConsumptionDataShouldBeFound(
      "consumptionTotal.greaterThan=" + SMALLER_CONSUMPTION_TOTAL
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the oxygenConsumptionDataList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the oxygenConsumptionDataList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1IsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the oxygenConsumptionDataList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 is not null
    defaultOxygenConsumptionDataShouldBeFound("freeField1.specified=true");

    // Get all the oxygenConsumptionDataList where freeField1 is null
    defaultOxygenConsumptionDataShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1ContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the oxygenConsumptionDataList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the oxygenConsumptionDataList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the oxygenConsumptionDataList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the oxygenConsumptionDataList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2IsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the oxygenConsumptionDataList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 is not null
    defaultOxygenConsumptionDataShouldBeFound("freeField2.specified=true");

    // Get all the oxygenConsumptionDataList where freeField2 is null
    defaultOxygenConsumptionDataShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2ContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the oxygenConsumptionDataList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the oxygenConsumptionDataList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the oxygenConsumptionDataList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the oxygenConsumptionDataList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3IsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the oxygenConsumptionDataList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 is not null
    defaultOxygenConsumptionDataShouldBeFound("freeField3.specified=true");

    // Get all the oxygenConsumptionDataList where freeField3 is null
    defaultOxygenConsumptionDataShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3ContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the oxygenConsumptionDataList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the oxygenConsumptionDataList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultOxygenConsumptionDataShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate equals to DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate equals to UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate not equals to UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate equals to UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate is not null
    defaultOxygenConsumptionDataShouldBeFound("createdDate.specified=true");

    // Get all the oxygenConsumptionDataList where createdDate is null
    defaultOxygenConsumptionDataShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate is less than DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate is less than UPDATED_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the oxygenConsumptionDataList where createdDate is greater than SMALLER_CREATED_DATE
    defaultOxygenConsumptionDataShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy equals to DEFAULT_CREATED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the oxygenConsumptionDataList where createdBy equals to UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy not equals to DEFAULT_CREATED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the oxygenConsumptionDataList where createdBy not equals to UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the oxygenConsumptionDataList where createdBy equals to UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy is not null
    defaultOxygenConsumptionDataShouldBeFound("createdBy.specified=true");

    // Get all the oxygenConsumptionDataList where createdBy is null
    defaultOxygenConsumptionDataShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy contains DEFAULT_CREATED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the oxygenConsumptionDataList where createdBy contains UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where createdBy does not contain DEFAULT_CREATED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the oxygenConsumptionDataList where createdBy does not contain UPDATED_CREATED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified is not null
    defaultOxygenConsumptionDataShouldBeFound("lastModified.specified=true");

    // Get all the oxygenConsumptionDataList where lastModified is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the oxygenConsumptionDataList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the oxygenConsumptionDataList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the oxygenConsumptionDataList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the oxygenConsumptionDataList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy is not null
    defaultOxygenConsumptionDataShouldBeFound("lastModifiedBy.specified=true");

    // Get all the oxygenConsumptionDataList where lastModifiedBy is null
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the oxygenConsumptionDataList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    // Get all the oxygenConsumptionDataList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the oxygenConsumptionDataList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultOxygenConsumptionDataShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByAuditIsEqualToSomething() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);
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
    oxygenConsumptionData.setAudit(audit);
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);
    Long auditId = audit.getId();

    // Get all the oxygenConsumptionDataList where audit equals to auditId
    defaultOxygenConsumptionDataShouldBeFound("auditId.equals=" + auditId);

    // Get all the oxygenConsumptionDataList where audit equals to (auditId + 1)
    defaultOxygenConsumptionDataShouldNotBeFound(
      "auditId.equals=" + (auditId + 1)
    );
  }

  @Test
  @Transactional
  void getAllOxygenConsumptionDataByTableDetailsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);
    TableDetails tableDetails;
    if (TestUtil.findAll(em, TableDetails.class).isEmpty()) {
      tableDetails = TableDetailsResourceIT.createEntity(em);
      em.persist(tableDetails);
      em.flush();
    } else {
      tableDetails = TestUtil.findAll(em, TableDetails.class).get(0);
    }
    em.persist(tableDetails);
    em.flush();
    oxygenConsumptionData.setTableDetails(tableDetails);
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);
    Long tableDetailsId = tableDetails.getId();

    // Get all the oxygenConsumptionDataList where tableDetails equals to tableDetailsId
    defaultOxygenConsumptionDataShouldBeFound(
      "tableDetailsId.equals=" + tableDetailsId
    );

    // Get all the oxygenConsumptionDataList where tableDetails equals to (tableDetailsId + 1)
    defaultOxygenConsumptionDataShouldNotBeFound(
      "tableDetailsId.equals=" + (tableDetailsId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultOxygenConsumptionDataShouldBeFound(String filter)
    throws Exception {
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(oxygenConsumptionData.getId().intValue()))
      )
      .andExpect(jsonPath("$.[*].formName").value(hasItem(DEFAULT_FORM_NAME)))
      .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
      .andExpect(jsonPath("$.[*].subType").value(hasItem(DEFAULT_SUB_TYPE)))
      .andExpect(jsonPath("$.[*].tableName").value(hasItem(DEFAULT_TABLE_NAME)))
      .andExpect(
        jsonPath("$.[*].noofPatients").value(hasItem(DEFAULT_NOOF_PATIENTS))
      )
      .andExpect(
        jsonPath("$.[*].consumptionLitperMin")
          .value(hasItem(DEFAULT_CONSUMPTION_LITPER_MIN.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionLitperDay")
          .value(hasItem(DEFAULT_CONSUMPTION_LITPER_DAY.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionKLitperDay")
          .value(hasItem(DEFAULT_CONSUMPTION_K_LITPER_DAY.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].consumptionTotal")
          .value(hasItem(DEFAULT_CONSUMPTION_TOTAL.doubleValue()))
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
      );

    // Check, that the count call also returns 1
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultOxygenConsumptionDataShouldNotBeFound(String filter)
    throws Exception {
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingOxygenConsumptionData() throws Exception {
    // Get the oxygenConsumptionData
    restOxygenConsumptionDataMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewOxygenConsumptionData() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();

    // Update the oxygenConsumptionData
    OxygenConsumptionData updatedOxygenConsumptionData = oxygenConsumptionDataRepository
      .findById(oxygenConsumptionData.getId())
      .get();
    // Disconnect from session so that the updates on updatedOxygenConsumptionData are not directly saved in db
    em.detach(updatedOxygenConsumptionData);
    updatedOxygenConsumptionData
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .noofPatients(UPDATED_NOOF_PATIENTS)
      .consumptionLitperMin(UPDATED_CONSUMPTION_LITPER_MIN)
      .consumptionLitperDay(UPDATED_CONSUMPTION_LITPER_DAY)
      .consumptionKLitperDay(UPDATED_CONSUMPTION_K_LITPER_DAY)
      .consumptionTotal(UPDATED_CONSUMPTION_TOTAL)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      updatedOxygenConsumptionData
    );

    restOxygenConsumptionDataMockMvc
      .perform(
        put(ENTITY_API_URL_ID, oxygenConsumptionDataDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionDataDTO))
      )
      .andExpect(status().isOk());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
    OxygenConsumptionData testOxygenConsumptionData = oxygenConsumptionDataList.get(
      oxygenConsumptionDataList.size() - 1
    );
    assertThat(testOxygenConsumptionData.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testOxygenConsumptionData.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testOxygenConsumptionData.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testOxygenConsumptionData.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testOxygenConsumptionData.getNoofPatients())
      .isEqualTo(UPDATED_NOOF_PATIENTS);
    assertThat(testOxygenConsumptionData.getConsumptionLitperMin())
      .isEqualTo(UPDATED_CONSUMPTION_LITPER_MIN);
    assertThat(testOxygenConsumptionData.getConsumptionLitperDay())
      .isEqualTo(UPDATED_CONSUMPTION_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionKLitperDay())
      .isEqualTo(UPDATED_CONSUMPTION_K_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionTotal())
      .isEqualTo(UPDATED_CONSUMPTION_TOTAL);
    assertThat(testOxygenConsumptionData.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testOxygenConsumptionData.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testOxygenConsumptionData.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testOxygenConsumptionData.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testOxygenConsumptionData.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testOxygenConsumptionData.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testOxygenConsumptionData.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void putNonExistingOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        put(ENTITY_API_URL_ID, oxygenConsumptionData.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionData))
      )
      .andExpect(status().isBadRequest());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionData))
      )
      .andExpect(status().isBadRequest());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionData oxygenConsumptionData = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionData))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateOxygenConsumptionDataWithPatch() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();

    // Update the oxygenConsumptionData using partial update
    OxygenConsumptionData partialUpdatedOxygenConsumptionData = new OxygenConsumptionData();
    partialUpdatedOxygenConsumptionData.setId(oxygenConsumptionData.getId());

    partialUpdatedOxygenConsumptionData
      .formName(UPDATED_FORM_NAME)
      .noofPatients(UPDATED_NOOF_PATIENTS)
      .consumptionLitperDay(UPDATED_CONSUMPTION_LITPER_DAY)
      .freeField1(UPDATED_FREE_FIELD_1);

    restOxygenConsumptionDataMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedOxygenConsumptionData.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedOxygenConsumptionData
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
    OxygenConsumptionData testOxygenConsumptionData = oxygenConsumptionDataList.get(
      oxygenConsumptionDataList.size() - 1
    );
    assertThat(testOxygenConsumptionData.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testOxygenConsumptionData.getType()).isEqualTo(DEFAULT_TYPE);
    assertThat(testOxygenConsumptionData.getSubType())
      .isEqualTo(DEFAULT_SUB_TYPE);
    assertThat(testOxygenConsumptionData.getTableName())
      .isEqualTo(DEFAULT_TABLE_NAME);
    assertThat(testOxygenConsumptionData.getNoofPatients())
      .isEqualTo(UPDATED_NOOF_PATIENTS);
    assertThat(testOxygenConsumptionData.getConsumptionLitperMin())
      .isEqualTo(DEFAULT_CONSUMPTION_LITPER_MIN);
    assertThat(testOxygenConsumptionData.getConsumptionLitperDay())
      .isEqualTo(UPDATED_CONSUMPTION_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionKLitperDay())
      .isEqualTo(DEFAULT_CONSUMPTION_K_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionTotal())
      .isEqualTo(DEFAULT_CONSUMPTION_TOTAL);
    assertThat(testOxygenConsumptionData.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testOxygenConsumptionData.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testOxygenConsumptionData.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testOxygenConsumptionData.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testOxygenConsumptionData.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testOxygenConsumptionData.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testOxygenConsumptionData.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void fullUpdateOxygenConsumptionDataWithPatch() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();

    // Update the oxygenConsumptionData using partial update
    OxygenConsumptionData partialUpdatedOxygenConsumptionData = new OxygenConsumptionData();
    partialUpdatedOxygenConsumptionData.setId(oxygenConsumptionData.getId());

    partialUpdatedOxygenConsumptionData
      .formName(UPDATED_FORM_NAME)
      .type(UPDATED_TYPE)
      .subType(UPDATED_SUB_TYPE)
      .tableName(UPDATED_TABLE_NAME)
      .noofPatients(UPDATED_NOOF_PATIENTS)
      .consumptionLitperMin(UPDATED_CONSUMPTION_LITPER_MIN)
      .consumptionLitperDay(UPDATED_CONSUMPTION_LITPER_DAY)
      .consumptionKLitperDay(UPDATED_CONSUMPTION_K_LITPER_DAY)
      .consumptionTotal(UPDATED_CONSUMPTION_TOTAL)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .createdDate(UPDATED_CREATED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY);

    restOxygenConsumptionDataMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedOxygenConsumptionData.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(
              partialUpdatedOxygenConsumptionData
            )
          )
      )
      .andExpect(status().isOk());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
    OxygenConsumptionData testOxygenConsumptionData = oxygenConsumptionDataList.get(
      oxygenConsumptionDataList.size() - 1
    );
    assertThat(testOxygenConsumptionData.getFormName())
      .isEqualTo(UPDATED_FORM_NAME);
    assertThat(testOxygenConsumptionData.getType()).isEqualTo(UPDATED_TYPE);
    assertThat(testOxygenConsumptionData.getSubType())
      .isEqualTo(UPDATED_SUB_TYPE);
    assertThat(testOxygenConsumptionData.getTableName())
      .isEqualTo(UPDATED_TABLE_NAME);
    assertThat(testOxygenConsumptionData.getNoofPatients())
      .isEqualTo(UPDATED_NOOF_PATIENTS);
    assertThat(testOxygenConsumptionData.getConsumptionLitperMin())
      .isEqualTo(UPDATED_CONSUMPTION_LITPER_MIN);
    assertThat(testOxygenConsumptionData.getConsumptionLitperDay())
      .isEqualTo(UPDATED_CONSUMPTION_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionKLitperDay())
      .isEqualTo(UPDATED_CONSUMPTION_K_LITPER_DAY);
    assertThat(testOxygenConsumptionData.getConsumptionTotal())
      .isEqualTo(UPDATED_CONSUMPTION_TOTAL);
    assertThat(testOxygenConsumptionData.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testOxygenConsumptionData.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testOxygenConsumptionData.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testOxygenConsumptionData.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testOxygenConsumptionData.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testOxygenConsumptionData.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testOxygenConsumptionData.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
  }

  @Test
  @Transactional
  void patchNonExistingOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionDataDTO oxygenConsumptionDataDTO = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, oxygenConsumptionDataDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionDataDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionDataDTO oxygenConsumptionDataDTO = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionDataDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamOxygenConsumptionData() throws Exception {
    int databaseSizeBeforeUpdate = oxygenConsumptionDataRepository
      .findAll()
      .size();
    oxygenConsumptionData.setId(count.incrementAndGet());

    // Create the OxygenConsumptionData
    OxygenConsumptionDataDTO oxygenConsumptionDataDTO = oxygenConsumptionDataMapper.toDto(
      oxygenConsumptionData
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restOxygenConsumptionDataMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(oxygenConsumptionDataDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the OxygenConsumptionData in the database
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteOxygenConsumptionData() throws Exception {
    // Initialize the database
    oxygenConsumptionDataRepository.saveAndFlush(oxygenConsumptionData);

    int databaseSizeBeforeDelete = oxygenConsumptionDataRepository
      .findAll()
      .size();

    // Delete the oxygenConsumptionData
    restOxygenConsumptionDataMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, oxygenConsumptionData.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<OxygenConsumptionData> oxygenConsumptionDataList = oxygenConsumptionDataRepository.findAll();
    assertThat(oxygenConsumptionDataList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
