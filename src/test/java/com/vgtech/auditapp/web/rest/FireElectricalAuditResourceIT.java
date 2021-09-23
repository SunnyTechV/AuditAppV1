package com.vgtech.auditapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.vgtech.auditapp.IntegrationTest;
import com.vgtech.auditapp.domain.Audit;
import com.vgtech.auditapp.domain.FireElectricalAudit;
import com.vgtech.auditapp.repository.FireElectricalAuditRepository;
import com.vgtech.auditapp.service.criteria.FireElectricalAuditCriteria;
import com.vgtech.auditapp.service.dto.FireElectricalAudit;
import com.vgtech.auditapp.service.mapper.FireElectricalAuditMapper;
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
 * Integration tests for the {@link FireElectricalAuditResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FireElectricalAuditResourceIT {

  private static final Boolean DEFAULT_FIRE_AUDIT_DONE_ORNOT = false;
  private static final Boolean UPDATED_FIRE_AUDIT_DONE_ORNOT = true;

  private static final LocalDate DEFAULT_FIRE_AUDIT_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_FIRE_AUDIT_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_FIRE_AUDIT_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_FIRE_FAULTS = "AAAAAAAAAA";
  private static final String UPDATED_FIRE_FAULTS = "BBBBBBBBBB";

  private static final String DEFAULT_FIRE_CORRECTIVE_ACTION = "AAAAAAAAAA";
  private static final String UPDATED_FIRE_CORRECTIVE_ACTION = "BBBBBBBBBB";

  private static final String DEFAULT_FIRE_AUDIT_PLAN = "AAAAAAAAAA";
  private static final String UPDATED_FIRE_AUDIT_PLAN = "BBBBBBBBBB";

  private static final Boolean DEFAULT_ELECTRICAL_AUDIT_DONE = false;
  private static final Boolean UPDATED_ELECTRICAL_AUDIT_DONE = true;

  private static final LocalDate DEFAULT_ELECTRICAL_AUDIT_DATE = LocalDate.ofEpochDay(
    0L
  );
  private static final LocalDate UPDATED_ELECTRICAL_AUDIT_DATE = LocalDate.now(
    ZoneId.systemDefault()
  );
  private static final LocalDate SMALLER_ELECTRICAL_AUDIT_DATE = LocalDate.ofEpochDay(
    -1L
  );

  private static final String DEFAULT_ELECTRICAL_FAULTS = "AAAAAAAAAA";
  private static final String UPDATED_ELECTRICAL_FAULTS = "BBBBBBBBBB";

  private static final String DEFAULT_ELECTRICAL_CORRECTIVE_ACTION =
    "AAAAAAAAAA";
  private static final String UPDATED_ELECTRICAL_CORRECTIVE_ACTION =
    "BBBBBBBBBB";

  private static final String DEFAULT_ELECTRICAL_AUDIT_INSPECTION =
    "AAAAAAAAAA";
  private static final String UPDATED_ELECTRICAL_AUDIT_INSPECTION =
    "BBBBBBBBBB";

  private static final Boolean DEFAULT_TECHNICAL_PERSON_APPOINT = false;
  private static final Boolean UPDATED_TECHNICAL_PERSON_APPOINT = true;

  private static final String DEFAULT_TECH_PERSONNAME = "AAAAAAAAAA";
  private static final String UPDATED_TECH_PERSONNAME = "BBBBBBBBBB";

  private static final String DEFAULT_TECH_PERSON_MOB_NO = "AAAAAAAAAA";
  private static final String UPDATED_TECH_PERSON_MOB_NO = "BBBBBBBBBB";

  private static final String DEFAULT_TECHNICAL_ENGINEER_NAME = "AAAAAAAAAA";
  private static final String UPDATED_TECHNICAL_ENGINEER_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TECHNICAL_ENGINEER_ADDRESS = "AAAAAAAAAA";
  private static final String UPDATED_TECHNICAL_ENGINEER_ADDRESS = "BBBBBBBBBB";

  private static final String DEFAULT_TECHNICAL_ENGINEER_MOB = "AAAAAAAAAA";
  private static final String UPDATED_TECHNICAL_ENGINEER_MOB = "BBBBBBBBBB";

  private static final String DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB =
    "AAAAAAAAAA";
  private static final String UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB =
    "BBBBBBBBBB";

  private static final Double DEFAULT_O_2_HOSP_REQUIREMENT = 1D;
  private static final Double UPDATED_O_2_HOSP_REQUIREMENT = 2D;
  private static final Double SMALLER_O_2_HOSP_REQUIREMENT = 1D - 1D;

  private static final Double DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT = 1D;
  private static final Double UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT = 2D;
  private static final Double SMALLER_O_2_HOSP_PROJECTED_REQUIREMENT = 1D - 1D;

  private static final Double DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT = 1D;
  private static final Double UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT = 2D;
  private static final Double SMALLER_SAVE_O_2_REQUIREMENT_POSSIBLE_MT =
    1D - 1D;

  private static final Boolean DEFAULT_MONITORING_O_2_VALVES_PORT = false;
  private static final Boolean UPDATED_MONITORING_O_2_VALVES_PORT = true;

  private static final Boolean DEFAULT_PORT_VALVES_SHUT_DOWN = false;
  private static final Boolean UPDATED_PORT_VALVES_SHUT_DOWN = true;

  private static final Boolean DEFAULT_ID_PATIENT_DRILL_DONE = false;
  private static final Boolean UPDATED_ID_PATIENT_DRILL_DONE = true;

  private static final Boolean DEFAULT_STAFF_CHECKING_LEAKAGE = false;
  private static final Boolean UPDATED_STAFF_CHECKING_LEAKAGE = true;

  private static final Boolean DEFAULT_PATIENT_O_2_REQ_FINALIZED = false;
  private static final Boolean UPDATED_PATIENT_O_2_REQ_FINALIZED = true;

  private static final String DEFAULT_TIME_BY_DOCTOR = "AAAAAAAAAA";
  private static final String UPDATED_TIME_BY_DOCTOR = "BBBBBBBBBB";

  private static final Boolean DEFAULT_IS_LIGHTING_INSTALLED = false;
  private static final Boolean UPDATED_IS_LIGHTING_INSTALLED = true;

  private static final String DEFAULT_LOC_LIGHTNING_ARRERSTOR = "AAAAAAAAAA";
  private static final String UPDATED_LOC_LIGHTNING_ARRERSTOR = "BBBBBBBBBB";

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

  private static final String ENTITY_API_URL = "/api/fire-electrical-audits";
  private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

  private static Random random = new Random();
  private static AtomicLong count = new AtomicLong(
    random.nextInt() + (2 * Integer.MAX_VALUE)
  );

  @Autowired
  private FireElectricalAuditRepository fireElectricalAuditRepository;

  @Autowired
  private FireElectricalAuditMapper fireElectricalAuditMapper;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restFireElectricalAuditMockMvc;

  private FireElectricalAudit fireElectricalAudit;

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FireElectricalAudit createEntity(EntityManager em) {
    FireElectricalAudit fireElectricalAudit = new FireElectricalAudit()
      .fireAuditDoneOrnot(DEFAULT_FIRE_AUDIT_DONE_ORNOT)
      .fireAuditDate(DEFAULT_FIRE_AUDIT_DATE)
      .fireFaults(DEFAULT_FIRE_FAULTS)
      .fireCorrectiveAction(DEFAULT_FIRE_CORRECTIVE_ACTION)
      .fireAuditPlan(DEFAULT_FIRE_AUDIT_PLAN)
      .electricalAuditDone(DEFAULT_ELECTRICAL_AUDIT_DONE)
      .electricalAuditDate(DEFAULT_ELECTRICAL_AUDIT_DATE)
      .electricalFaults(DEFAULT_ELECTRICAL_FAULTS)
      .electricalCorrectiveAction(DEFAULT_ELECTRICAL_CORRECTIVE_ACTION)
      .electricalAuditInspection(DEFAULT_ELECTRICAL_AUDIT_INSPECTION)
      .technicalPersonAppoint(DEFAULT_TECHNICAL_PERSON_APPOINT)
      .techPersonname(DEFAULT_TECH_PERSONNAME)
      .techPersonMobNo(DEFAULT_TECH_PERSON_MOB_NO)
      .technicalEngineerName(DEFAULT_TECHNICAL_ENGINEER_NAME)
      .technicalEngineerAddress(DEFAULT_TECHNICAL_ENGINEER_ADDRESS)
      .technicalEngineerMob(DEFAULT_TECHNICAL_ENGINEER_MOB)
      .technicalEngineerAlternateMob(DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      .o2HospRequirement(DEFAULT_O_2_HOSP_REQUIREMENT)
      .o2HospProjectedRequirement(DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT)
      .saveO2RequirementPossibleMT(DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT)
      .monitoringO2ValvesPort(DEFAULT_MONITORING_O_2_VALVES_PORT)
      .portValvesShutDown(DEFAULT_PORT_VALVES_SHUT_DOWN)
      .idPatientDrillDone(DEFAULT_ID_PATIENT_DRILL_DONE)
      .staffCheckingLeakage(DEFAULT_STAFF_CHECKING_LEAKAGE)
      .patientO2ReqFinalized(DEFAULT_PATIENT_O_2_REQ_FINALIZED)
      .timeByDoctor(DEFAULT_TIME_BY_DOCTOR)
      .isLightingInstalled(DEFAULT_IS_LIGHTING_INSTALLED)
      .locLightningArrerstor(DEFAULT_LOC_LIGHTNING_ARRERSTOR)
      .createdBy(DEFAULT_CREATED_BY)
      .createdDate(DEFAULT_CREATED_DATE)
      .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
      .lastModified(DEFAULT_LAST_MODIFIED)
      .freeField1(DEFAULT_FREE_FIELD_1)
      .freeField2(DEFAULT_FREE_FIELD_2)
      .freeField3(DEFAULT_FREE_FIELD_3)
      .freeField4(DEFAULT_FREE_FIELD_4);
    return fireElectricalAudit;
  }

  /**
   * Create an updated entity for this test.
   *
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static FireElectricalAudit createUpdatedEntity(EntityManager em) {
    FireElectricalAudit fireElectricalAudit = new FireElectricalAudit()
      .fireAuditDoneOrnot(UPDATED_FIRE_AUDIT_DONE_ORNOT)
      .fireAuditDate(UPDATED_FIRE_AUDIT_DATE)
      .fireFaults(UPDATED_FIRE_FAULTS)
      .fireCorrectiveAction(UPDATED_FIRE_CORRECTIVE_ACTION)
      .fireAuditPlan(UPDATED_FIRE_AUDIT_PLAN)
      .electricalAuditDone(UPDATED_ELECTRICAL_AUDIT_DONE)
      .electricalAuditDate(UPDATED_ELECTRICAL_AUDIT_DATE)
      .electricalFaults(UPDATED_ELECTRICAL_FAULTS)
      .electricalCorrectiveAction(UPDATED_ELECTRICAL_CORRECTIVE_ACTION)
      .electricalAuditInspection(UPDATED_ELECTRICAL_AUDIT_INSPECTION)
      .technicalPersonAppoint(UPDATED_TECHNICAL_PERSON_APPOINT)
      .techPersonname(UPDATED_TECH_PERSONNAME)
      .techPersonMobNo(UPDATED_TECH_PERSON_MOB_NO)
      .technicalEngineerName(UPDATED_TECHNICAL_ENGINEER_NAME)
      .technicalEngineerAddress(UPDATED_TECHNICAL_ENGINEER_ADDRESS)
      .technicalEngineerMob(UPDATED_TECHNICAL_ENGINEER_MOB)
      .technicalEngineerAlternateMob(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      .o2HospRequirement(UPDATED_O_2_HOSP_REQUIREMENT)
      .o2HospProjectedRequirement(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT)
      .saveO2RequirementPossibleMT(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT)
      .monitoringO2ValvesPort(UPDATED_MONITORING_O_2_VALVES_PORT)
      .portValvesShutDown(UPDATED_PORT_VALVES_SHUT_DOWN)
      .idPatientDrillDone(UPDATED_ID_PATIENT_DRILL_DONE)
      .staffCheckingLeakage(UPDATED_STAFF_CHECKING_LEAKAGE)
      .patientO2ReqFinalized(UPDATED_PATIENT_O_2_REQ_FINALIZED)
      .timeByDoctor(UPDATED_TIME_BY_DOCTOR)
      .isLightingInstalled(UPDATED_IS_LIGHTING_INSTALLED)
      .locLightningArrerstor(UPDATED_LOC_LIGHTNING_ARRERSTOR)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    return fireElectricalAudit;
  }

  @BeforeEach
  public void initTest() {
    fireElectricalAudit = createEntity(em);
  }

  @Test
  @Transactional
  void createFireElectricalAudit() throws Exception {
    int databaseSizeBeforeCreate = fireElectricalAuditRepository
      .findAll()
      .size();
    // Create the FireElectricalAudit
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );
    restFireElectricalAuditMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAudit))
      )
      .andExpect(status().isCreated());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeCreate + 1);
    FireElectricalAudit testFireElectricalAudit = fireElectricalAuditList.get(
      fireElectricalAuditList.size() - 1
    );
    assertThat(testFireElectricalAudit.getFireAuditDoneOrnot())
      .isEqualTo(DEFAULT_FIRE_AUDIT_DONE_ORNOT);
    assertThat(testFireElectricalAudit.getFireAuditDate())
      .isEqualTo(DEFAULT_FIRE_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getFireFaults())
      .isEqualTo(DEFAULT_FIRE_FAULTS);
    assertThat(testFireElectricalAudit.getFireCorrectiveAction())
      .isEqualTo(DEFAULT_FIRE_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getFireAuditPlan())
      .isEqualTo(DEFAULT_FIRE_AUDIT_PLAN);
    assertThat(testFireElectricalAudit.getElectricalAuditDone())
      .isEqualTo(DEFAULT_ELECTRICAL_AUDIT_DONE);
    assertThat(testFireElectricalAudit.getElectricalAuditDate())
      .isEqualTo(DEFAULT_ELECTRICAL_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getElectricalFaults())
      .isEqualTo(DEFAULT_ELECTRICAL_FAULTS);
    assertThat(testFireElectricalAudit.getElectricalCorrectiveAction())
      .isEqualTo(DEFAULT_ELECTRICAL_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getElectricalAuditInspection())
      .isEqualTo(DEFAULT_ELECTRICAL_AUDIT_INSPECTION);
    assertThat(testFireElectricalAudit.getTechnicalPersonAppoint())
      .isEqualTo(DEFAULT_TECHNICAL_PERSON_APPOINT);
    assertThat(testFireElectricalAudit.getTechPersonname())
      .isEqualTo(DEFAULT_TECH_PERSONNAME);
    assertThat(testFireElectricalAudit.getTechPersonMobNo())
      .isEqualTo(DEFAULT_TECH_PERSON_MOB_NO);
    assertThat(testFireElectricalAudit.getTechnicalEngineerName())
      .isEqualTo(DEFAULT_TECHNICAL_ENGINEER_NAME);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAddress())
      .isEqualTo(DEFAULT_TECHNICAL_ENGINEER_ADDRESS);
    assertThat(testFireElectricalAudit.getTechnicalEngineerMob())
      .isEqualTo(DEFAULT_TECHNICAL_ENGINEER_MOB);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAlternateMob())
      .isEqualTo(DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB);
    assertThat(testFireElectricalAudit.geto2HospRequirement())
      .isEqualTo(DEFAULT_O_2_HOSP_REQUIREMENT);
    assertThat(testFireElectricalAudit.geto2HospProjectedRequirement())
      .isEqualTo(DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT);
    assertThat(testFireElectricalAudit.getSaveO2RequirementPossibleMT())
      .isEqualTo(DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT);
    assertThat(testFireElectricalAudit.getMonitoringO2ValvesPort())
      .isEqualTo(DEFAULT_MONITORING_O_2_VALVES_PORT);
    assertThat(testFireElectricalAudit.getPortValvesShutDown())
      .isEqualTo(DEFAULT_PORT_VALVES_SHUT_DOWN);
    assertThat(testFireElectricalAudit.getIdPatientDrillDone())
      .isEqualTo(DEFAULT_ID_PATIENT_DRILL_DONE);
    assertThat(testFireElectricalAudit.getStaffCheckingLeakage())
      .isEqualTo(DEFAULT_STAFF_CHECKING_LEAKAGE);
    assertThat(testFireElectricalAudit.getPatientO2ReqFinalized())
      .isEqualTo(DEFAULT_PATIENT_O_2_REQ_FINALIZED);
    assertThat(testFireElectricalAudit.getTimeByDoctor())
      .isEqualTo(DEFAULT_TIME_BY_DOCTOR);
    assertThat(testFireElectricalAudit.getIsLightingInstalled())
      .isEqualTo(DEFAULT_IS_LIGHTING_INSTALLED);
    assertThat(testFireElectricalAudit.getLocLightningArrerstor())
      .isEqualTo(DEFAULT_LOC_LIGHTNING_ARRERSTOR);
    assertThat(testFireElectricalAudit.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testFireElectricalAudit.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testFireElectricalAudit.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    assertThat(testFireElectricalAudit.getLastModified())
      .isEqualTo(DEFAULT_LAST_MODIFIED);
    assertThat(testFireElectricalAudit.getFreeField1())
      .isEqualTo(DEFAULT_FREE_FIELD_1);
    assertThat(testFireElectricalAudit.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testFireElectricalAudit.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testFireElectricalAudit.getFreeField4())
      .isEqualTo(DEFAULT_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void createFireElectricalAuditWithExistingId() throws Exception {
    // Create the FireElectricalAudit with an existing ID
    fireElectricalAudit.setId(1L);
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    int databaseSizeBeforeCreate = fireElectricalAuditRepository
      .findAll()
      .size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restFireElectricalAuditMockMvc
      .perform(
        post(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAudit))
      )
      .andExpect(status().isBadRequest());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  void getAllFireElectricalAudits() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(fireElectricalAudit.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditDoneOrnot")
          .value(hasItem(DEFAULT_FIRE_AUDIT_DONE_ORNOT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditDate")
          .value(hasItem(DEFAULT_FIRE_AUDIT_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].fireFaults").value(hasItem(DEFAULT_FIRE_FAULTS))
      )
      .andExpect(
        jsonPath("$.[*].fireCorrectiveAction")
          .value(hasItem(DEFAULT_FIRE_CORRECTIVE_ACTION))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditPlan").value(hasItem(DEFAULT_FIRE_AUDIT_PLAN))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditDone")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_DONE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditDate")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].electricalFaults")
          .value(hasItem(DEFAULT_ELECTRICAL_FAULTS))
      )
      .andExpect(
        jsonPath("$.[*].electricalCorrectiveAction")
          .value(hasItem(DEFAULT_ELECTRICAL_CORRECTIVE_ACTION))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditInspection")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_INSPECTION))
      )
      .andExpect(
        jsonPath("$.[*].technicalPersonAppoint")
          .value(hasItem(DEFAULT_TECHNICAL_PERSON_APPOINT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].techPersonname").value(hasItem(DEFAULT_TECH_PERSONNAME))
      )
      .andExpect(
        jsonPath("$.[*].techPersonMobNo")
          .value(hasItem(DEFAULT_TECH_PERSON_MOB_NO))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerName")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerAddress")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerMob")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_MOB))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerAlternateMob")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB))
      )
      .andExpect(
        jsonPath("$.[*].o2HospRequirement")
          .value(hasItem(DEFAULT_O_2_HOSP_REQUIREMENT.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].o2HospProjectedRequirement")
          .value(hasItem(DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].saveO2RequirementPossibleMT")
          .value(
            hasItem(DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT.doubleValue())
          )
      )
      .andExpect(
        jsonPath("$.[*].monitoringO2ValvesPort")
          .value(hasItem(DEFAULT_MONITORING_O_2_VALVES_PORT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].portValvesShutDown")
          .value(hasItem(DEFAULT_PORT_VALVES_SHUT_DOWN.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].idPatientDrillDone")
          .value(hasItem(DEFAULT_ID_PATIENT_DRILL_DONE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].staffCheckingLeakage")
          .value(hasItem(DEFAULT_STAFF_CHECKING_LEAKAGE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].patientO2ReqFinalized")
          .value(hasItem(DEFAULT_PATIENT_O_2_REQ_FINALIZED.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].timeByDoctor").value(hasItem(DEFAULT_TIME_BY_DOCTOR))
      )
      .andExpect(
        jsonPath("$.[*].isLightingInstalled")
          .value(hasItem(DEFAULT_IS_LIGHTING_INSTALLED.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].locLightningArrerstor")
          .value(hasItem(DEFAULT_LOC_LIGHTNING_ARRERSTOR))
      )
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
  void getFireElectricalAudit() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get the fireElectricalAudit
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL_ID, fireElectricalAudit.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(fireElectricalAudit.getId().intValue()))
      .andExpect(
        jsonPath("$.fireAuditDoneOrnot")
          .value(DEFAULT_FIRE_AUDIT_DONE_ORNOT.booleanValue())
      )
      .andExpect(
        jsonPath("$.fireAuditDate").value(DEFAULT_FIRE_AUDIT_DATE.toString())
      )
      .andExpect(jsonPath("$.fireFaults").value(DEFAULT_FIRE_FAULTS))
      .andExpect(
        jsonPath("$.fireCorrectiveAction").value(DEFAULT_FIRE_CORRECTIVE_ACTION)
      )
      .andExpect(jsonPath("$.fireAuditPlan").value(DEFAULT_FIRE_AUDIT_PLAN))
      .andExpect(
        jsonPath("$.electricalAuditDone")
          .value(DEFAULT_ELECTRICAL_AUDIT_DONE.booleanValue())
      )
      .andExpect(
        jsonPath("$.electricalAuditDate")
          .value(DEFAULT_ELECTRICAL_AUDIT_DATE.toString())
      )
      .andExpect(
        jsonPath("$.electricalFaults").value(DEFAULT_ELECTRICAL_FAULTS)
      )
      .andExpect(
        jsonPath("$.electricalCorrectiveAction")
          .value(DEFAULT_ELECTRICAL_CORRECTIVE_ACTION)
      )
      .andExpect(
        jsonPath("$.electricalAuditInspection")
          .value(DEFAULT_ELECTRICAL_AUDIT_INSPECTION)
      )
      .andExpect(
        jsonPath("$.technicalPersonAppoint")
          .value(DEFAULT_TECHNICAL_PERSON_APPOINT.booleanValue())
      )
      .andExpect(jsonPath("$.techPersonname").value(DEFAULT_TECH_PERSONNAME))
      .andExpect(
        jsonPath("$.techPersonMobNo").value(DEFAULT_TECH_PERSON_MOB_NO)
      )
      .andExpect(
        jsonPath("$.technicalEngineerName")
          .value(DEFAULT_TECHNICAL_ENGINEER_NAME)
      )
      .andExpect(
        jsonPath("$.technicalEngineerAddress")
          .value(DEFAULT_TECHNICAL_ENGINEER_ADDRESS)
      )
      .andExpect(
        jsonPath("$.technicalEngineerMob").value(DEFAULT_TECHNICAL_ENGINEER_MOB)
      )
      .andExpect(
        jsonPath("$.technicalEngineerAlternateMob")
          .value(DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      )
      .andExpect(
        jsonPath("$.o2HospRequirement")
          .value(DEFAULT_O_2_HOSP_REQUIREMENT.doubleValue())
      )
      .andExpect(
        jsonPath("$.o2HospProjectedRequirement")
          .value(DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT.doubleValue())
      )
      .andExpect(
        jsonPath("$.saveO2RequirementPossibleMT")
          .value(DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT.doubleValue())
      )
      .andExpect(
        jsonPath("$.monitoringO2ValvesPort")
          .value(DEFAULT_MONITORING_O_2_VALVES_PORT.booleanValue())
      )
      .andExpect(
        jsonPath("$.portValvesShutDown")
          .value(DEFAULT_PORT_VALVES_SHUT_DOWN.booleanValue())
      )
      .andExpect(
        jsonPath("$.idPatientDrillDone")
          .value(DEFAULT_ID_PATIENT_DRILL_DONE.booleanValue())
      )
      .andExpect(
        jsonPath("$.staffCheckingLeakage")
          .value(DEFAULT_STAFF_CHECKING_LEAKAGE.booleanValue())
      )
      .andExpect(
        jsonPath("$.patientO2ReqFinalized")
          .value(DEFAULT_PATIENT_O_2_REQ_FINALIZED.booleanValue())
      )
      .andExpect(jsonPath("$.timeByDoctor").value(DEFAULT_TIME_BY_DOCTOR))
      .andExpect(
        jsonPath("$.isLightingInstalled")
          .value(DEFAULT_IS_LIGHTING_INSTALLED.booleanValue())
      )
      .andExpect(
        jsonPath("$.locLightningArrerstor")
          .value(DEFAULT_LOC_LIGHTNING_ARRERSTOR)
      )
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
  void getFireElectricalAuditsByIdFiltering() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    Long id = fireElectricalAudit.getId();

    defaultFireElectricalAuditShouldBeFound("id.equals=" + id);
    defaultFireElectricalAuditShouldNotBeFound("id.notEquals=" + id);

    defaultFireElectricalAuditShouldBeFound("id.greaterThanOrEqual=" + id);
    defaultFireElectricalAuditShouldNotBeFound("id.greaterThan=" + id);

    defaultFireElectricalAuditShouldBeFound("id.lessThanOrEqual=" + id);
    defaultFireElectricalAuditShouldNotBeFound("id.lessThan=" + id);
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDoneOrnotIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot equals to DEFAULT_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDoneOrnot.equals=" + DEFAULT_FIRE_AUDIT_DONE_ORNOT
    );

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot equals to UPDATED_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDoneOrnot.equals=" + UPDATED_FIRE_AUDIT_DONE_ORNOT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDoneOrnotIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot not equals to DEFAULT_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDoneOrnot.notEquals=" + DEFAULT_FIRE_AUDIT_DONE_ORNOT
    );

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot not equals to UPDATED_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDoneOrnot.notEquals=" + UPDATED_FIRE_AUDIT_DONE_ORNOT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDoneOrnotIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot in DEFAULT_FIRE_AUDIT_DONE_ORNOT or UPDATED_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDoneOrnot.in=" +
      DEFAULT_FIRE_AUDIT_DONE_ORNOT +
      "," +
      UPDATED_FIRE_AUDIT_DONE_ORNOT
    );

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot equals to UPDATED_FIRE_AUDIT_DONE_ORNOT
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDoneOrnot.in=" + UPDATED_FIRE_AUDIT_DONE_ORNOT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDoneOrnotIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot is not null
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDoneOrnot.specified=true"
    );

    // Get all the fireElectricalAuditList where fireAuditDoneOrnot is null
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDoneOrnot.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate equals to DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.equals=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate equals to UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.equals=" + UPDATED_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate not equals to DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.notEquals=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate not equals to UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.notEquals=" + UPDATED_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate in DEFAULT_FIRE_AUDIT_DATE or UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.in=" +
      DEFAULT_FIRE_AUDIT_DATE +
      "," +
      UPDATED_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate equals to UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.in=" + UPDATED_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate is not null
    defaultFireElectricalAuditShouldBeFound("fireAuditDate.specified=true");

    // Get all the fireElectricalAuditList where fireAuditDate is null
    defaultFireElectricalAuditShouldNotBeFound("fireAuditDate.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate is greater than or equal to DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.greaterThanOrEqual=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate is greater than or equal to UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.greaterThanOrEqual=" + UPDATED_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate is less than or equal to DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.lessThanOrEqual=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate is less than or equal to SMALLER_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.lessThanOrEqual=" + SMALLER_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate is less than DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.lessThan=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate is less than UPDATED_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.lessThan=" + UPDATED_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditDate is greater than DEFAULT_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditDate.greaterThan=" + DEFAULT_FIRE_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where fireAuditDate is greater than SMALLER_FIRE_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditDate.greaterThan=" + SMALLER_FIRE_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults equals to DEFAULT_FIRE_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "fireFaults.equals=" + DEFAULT_FIRE_FAULTS
    );

    // Get all the fireElectricalAuditList where fireFaults equals to UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "fireFaults.equals=" + UPDATED_FIRE_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults not equals to DEFAULT_FIRE_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "fireFaults.notEquals=" + DEFAULT_FIRE_FAULTS
    );

    // Get all the fireElectricalAuditList where fireFaults not equals to UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "fireFaults.notEquals=" + UPDATED_FIRE_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsIsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults in DEFAULT_FIRE_FAULTS or UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "fireFaults.in=" + DEFAULT_FIRE_FAULTS + "," + UPDATED_FIRE_FAULTS
    );

    // Get all the fireElectricalAuditList where fireFaults equals to UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "fireFaults.in=" + UPDATED_FIRE_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults is not null
    defaultFireElectricalAuditShouldBeFound("fireFaults.specified=true");

    // Get all the fireElectricalAuditList where fireFaults is null
    defaultFireElectricalAuditShouldNotBeFound("fireFaults.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults contains DEFAULT_FIRE_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "fireFaults.contains=" + DEFAULT_FIRE_FAULTS
    );

    // Get all the fireElectricalAuditList where fireFaults contains UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "fireFaults.contains=" + UPDATED_FIRE_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireFaultsNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireFaults does not contain DEFAULT_FIRE_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "fireFaults.doesNotContain=" + DEFAULT_FIRE_FAULTS
    );

    // Get all the fireElectricalAuditList where fireFaults does not contain UPDATED_FIRE_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "fireFaults.doesNotContain=" + UPDATED_FIRE_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction equals to DEFAULT_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.equals=" + DEFAULT_FIRE_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction equals to UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.equals=" + UPDATED_FIRE_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction not equals to DEFAULT_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.notEquals=" + DEFAULT_FIRE_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction not equals to UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.notEquals=" + UPDATED_FIRE_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction in DEFAULT_FIRE_CORRECTIVE_ACTION or UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.in=" +
      DEFAULT_FIRE_CORRECTIVE_ACTION +
      "," +
      UPDATED_FIRE_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction equals to UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.in=" + UPDATED_FIRE_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction is not null
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.specified=true"
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction is null
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction contains DEFAULT_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.contains=" + DEFAULT_FIRE_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction contains UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.contains=" + UPDATED_FIRE_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireCorrectiveActionNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireCorrectiveAction does not contain DEFAULT_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "fireCorrectiveAction.doesNotContain=" + DEFAULT_FIRE_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where fireCorrectiveAction does not contain UPDATED_FIRE_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "fireCorrectiveAction.doesNotContain=" + UPDATED_FIRE_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan equals to DEFAULT_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditPlan.equals=" + DEFAULT_FIRE_AUDIT_PLAN
    );

    // Get all the fireElectricalAuditList where fireAuditPlan equals to UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditPlan.equals=" + UPDATED_FIRE_AUDIT_PLAN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan not equals to DEFAULT_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditPlan.notEquals=" + DEFAULT_FIRE_AUDIT_PLAN
    );

    // Get all the fireElectricalAuditList where fireAuditPlan not equals to UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditPlan.notEquals=" + UPDATED_FIRE_AUDIT_PLAN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan in DEFAULT_FIRE_AUDIT_PLAN or UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditPlan.in=" +
      DEFAULT_FIRE_AUDIT_PLAN +
      "," +
      UPDATED_FIRE_AUDIT_PLAN
    );

    // Get all the fireElectricalAuditList where fireAuditPlan equals to UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditPlan.in=" + UPDATED_FIRE_AUDIT_PLAN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan is not null
    defaultFireElectricalAuditShouldBeFound("fireAuditPlan.specified=true");

    // Get all the fireElectricalAuditList where fireAuditPlan is null
    defaultFireElectricalAuditShouldNotBeFound("fireAuditPlan.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan contains DEFAULT_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditPlan.contains=" + DEFAULT_FIRE_AUDIT_PLAN
    );

    // Get all the fireElectricalAuditList where fireAuditPlan contains UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditPlan.contains=" + UPDATED_FIRE_AUDIT_PLAN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFireAuditPlanNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where fireAuditPlan does not contain DEFAULT_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldNotBeFound(
      "fireAuditPlan.doesNotContain=" + DEFAULT_FIRE_AUDIT_PLAN
    );

    // Get all the fireElectricalAuditList where fireAuditPlan does not contain UPDATED_FIRE_AUDIT_PLAN
    defaultFireElectricalAuditShouldBeFound(
      "fireAuditPlan.doesNotContain=" + UPDATED_FIRE_AUDIT_PLAN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDoneIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDone equals to DEFAULT_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDone.equals=" + DEFAULT_ELECTRICAL_AUDIT_DONE
    );

    // Get all the fireElectricalAuditList where electricalAuditDone equals to UPDATED_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDone.equals=" + UPDATED_ELECTRICAL_AUDIT_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDoneIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDone not equals to DEFAULT_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDone.notEquals=" + DEFAULT_ELECTRICAL_AUDIT_DONE
    );

    // Get all the fireElectricalAuditList where electricalAuditDone not equals to UPDATED_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDone.notEquals=" + UPDATED_ELECTRICAL_AUDIT_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDoneIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDone in DEFAULT_ELECTRICAL_AUDIT_DONE or UPDATED_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDone.in=" +
      DEFAULT_ELECTRICAL_AUDIT_DONE +
      "," +
      UPDATED_ELECTRICAL_AUDIT_DONE
    );

    // Get all the fireElectricalAuditList where electricalAuditDone equals to UPDATED_ELECTRICAL_AUDIT_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDone.in=" + UPDATED_ELECTRICAL_AUDIT_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDoneIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDone is not null
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDone.specified=true"
    );

    // Get all the fireElectricalAuditList where electricalAuditDone is null
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDone.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate equals to DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.equals=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate equals to UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.equals=" + UPDATED_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate not equals to DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.notEquals=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate not equals to UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.notEquals=" + UPDATED_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate in DEFAULT_ELECTRICAL_AUDIT_DATE or UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.in=" +
      DEFAULT_ELECTRICAL_AUDIT_DATE +
      "," +
      UPDATED_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate equals to UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.in=" + UPDATED_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate is not null
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.specified=true"
    );

    // Get all the fireElectricalAuditList where electricalAuditDate is null
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate is greater than or equal to DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.greaterThanOrEqual=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate is greater than or equal to UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.greaterThanOrEqual=" + UPDATED_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate is less than or equal to DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.lessThanOrEqual=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate is less than or equal to SMALLER_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.lessThanOrEqual=" + SMALLER_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate is less than DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.lessThan=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate is less than UPDATED_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.lessThan=" + UPDATED_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditDate is greater than DEFAULT_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditDate.greaterThan=" + DEFAULT_ELECTRICAL_AUDIT_DATE
    );

    // Get all the fireElectricalAuditList where electricalAuditDate is greater than SMALLER_ELECTRICAL_AUDIT_DATE
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditDate.greaterThan=" + SMALLER_ELECTRICAL_AUDIT_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults equals to DEFAULT_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "electricalFaults.equals=" + DEFAULT_ELECTRICAL_FAULTS
    );

    // Get all the fireElectricalAuditList where electricalFaults equals to UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.equals=" + UPDATED_ELECTRICAL_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults not equals to DEFAULT_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.notEquals=" + DEFAULT_ELECTRICAL_FAULTS
    );

    // Get all the fireElectricalAuditList where electricalFaults not equals to UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "electricalFaults.notEquals=" + UPDATED_ELECTRICAL_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults in DEFAULT_ELECTRICAL_FAULTS or UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "electricalFaults.in=" +
      DEFAULT_ELECTRICAL_FAULTS +
      "," +
      UPDATED_ELECTRICAL_FAULTS
    );

    // Get all the fireElectricalAuditList where electricalFaults equals to UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.in=" + UPDATED_ELECTRICAL_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults is not null
    defaultFireElectricalAuditShouldBeFound("electricalFaults.specified=true");

    // Get all the fireElectricalAuditList where electricalFaults is null
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults contains DEFAULT_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "electricalFaults.contains=" + DEFAULT_ELECTRICAL_FAULTS
    );

    // Get all the fireElectricalAuditList where electricalFaults contains UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.contains=" + UPDATED_ELECTRICAL_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalFaultsNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalFaults does not contain DEFAULT_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalFaults.doesNotContain=" + DEFAULT_ELECTRICAL_FAULTS
    );

    // Get all the fireElectricalAuditList where electricalFaults does not contain UPDATED_ELECTRICAL_FAULTS
    defaultFireElectricalAuditShouldBeFound(
      "electricalFaults.doesNotContain=" + UPDATED_ELECTRICAL_FAULTS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction equals to DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.equals=" +
      DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction equals to UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.equals=" +
      UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction not equals to DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.notEquals=" +
      DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction not equals to UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.notEquals=" +
      UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction in DEFAULT_ELECTRICAL_CORRECTIVE_ACTION or UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.in=" +
      DEFAULT_ELECTRICAL_CORRECTIVE_ACTION +
      "," +
      UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction equals to UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.in=" + UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction is not null
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.specified=true"
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction is null
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction contains DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.contains=" +
      DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction contains UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.contains=" +
      UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalCorrectiveActionNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalCorrectiveAction does not contain DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalCorrectiveAction.doesNotContain=" +
      DEFAULT_ELECTRICAL_CORRECTIVE_ACTION
    );

    // Get all the fireElectricalAuditList where electricalCorrectiveAction does not contain UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalCorrectiveAction.doesNotContain=" +
      UPDATED_ELECTRICAL_CORRECTIVE_ACTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection equals to DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.equals=" + DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection equals to UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.equals=" + UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection not equals to DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.notEquals=" +
      DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection not equals to UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.notEquals=" +
      UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection in DEFAULT_ELECTRICAL_AUDIT_INSPECTION or UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.in=" +
      DEFAULT_ELECTRICAL_AUDIT_INSPECTION +
      "," +
      UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection equals to UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.in=" + UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection is not null
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.specified=true"
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection is null
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection contains DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.contains=" +
      DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection contains UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.contains=" +
      UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByElectricalAuditInspectionNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where electricalAuditInspection does not contain DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldNotBeFound(
      "electricalAuditInspection.doesNotContain=" +
      DEFAULT_ELECTRICAL_AUDIT_INSPECTION
    );

    // Get all the fireElectricalAuditList where electricalAuditInspection does not contain UPDATED_ELECTRICAL_AUDIT_INSPECTION
    defaultFireElectricalAuditShouldBeFound(
      "electricalAuditInspection.doesNotContain=" +
      UPDATED_ELECTRICAL_AUDIT_INSPECTION
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalPersonAppointIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalPersonAppoint equals to DEFAULT_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldBeFound(
      "technicalPersonAppoint.equals=" + DEFAULT_TECHNICAL_PERSON_APPOINT
    );

    // Get all the fireElectricalAuditList where technicalPersonAppoint equals to UPDATED_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalPersonAppoint.equals=" + UPDATED_TECHNICAL_PERSON_APPOINT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalPersonAppointIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalPersonAppoint not equals to DEFAULT_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalPersonAppoint.notEquals=" + DEFAULT_TECHNICAL_PERSON_APPOINT
    );

    // Get all the fireElectricalAuditList where technicalPersonAppoint not equals to UPDATED_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldBeFound(
      "technicalPersonAppoint.notEquals=" + UPDATED_TECHNICAL_PERSON_APPOINT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalPersonAppointIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalPersonAppoint in DEFAULT_TECHNICAL_PERSON_APPOINT or UPDATED_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldBeFound(
      "technicalPersonAppoint.in=" +
      DEFAULT_TECHNICAL_PERSON_APPOINT +
      "," +
      UPDATED_TECHNICAL_PERSON_APPOINT
    );

    // Get all the fireElectricalAuditList where technicalPersonAppoint equals to UPDATED_TECHNICAL_PERSON_APPOINT
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalPersonAppoint.in=" + UPDATED_TECHNICAL_PERSON_APPOINT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalPersonAppointIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalPersonAppoint is not null
    defaultFireElectricalAuditShouldBeFound(
      "technicalPersonAppoint.specified=true"
    );

    // Get all the fireElectricalAuditList where technicalPersonAppoint is null
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalPersonAppoint.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname equals to DEFAULT_TECH_PERSONNAME
    defaultFireElectricalAuditShouldBeFound(
      "techPersonname.equals=" + DEFAULT_TECH_PERSONNAME
    );

    // Get all the fireElectricalAuditList where techPersonname equals to UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.equals=" + UPDATED_TECH_PERSONNAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname not equals to DEFAULT_TECH_PERSONNAME
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.notEquals=" + DEFAULT_TECH_PERSONNAME
    );

    // Get all the fireElectricalAuditList where techPersonname not equals to UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldBeFound(
      "techPersonname.notEquals=" + UPDATED_TECH_PERSONNAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname in DEFAULT_TECH_PERSONNAME or UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldBeFound(
      "techPersonname.in=" +
      DEFAULT_TECH_PERSONNAME +
      "," +
      UPDATED_TECH_PERSONNAME
    );

    // Get all the fireElectricalAuditList where techPersonname equals to UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.in=" + UPDATED_TECH_PERSONNAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname is not null
    defaultFireElectricalAuditShouldBeFound("techPersonname.specified=true");

    // Get all the fireElectricalAuditList where techPersonname is null
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname contains DEFAULT_TECH_PERSONNAME
    defaultFireElectricalAuditShouldBeFound(
      "techPersonname.contains=" + DEFAULT_TECH_PERSONNAME
    );

    // Get all the fireElectricalAuditList where techPersonname contains UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.contains=" + UPDATED_TECH_PERSONNAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonnameNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonname does not contain DEFAULT_TECH_PERSONNAME
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonname.doesNotContain=" + DEFAULT_TECH_PERSONNAME
    );

    // Get all the fireElectricalAuditList where techPersonname does not contain UPDATED_TECH_PERSONNAME
    defaultFireElectricalAuditShouldBeFound(
      "techPersonname.doesNotContain=" + UPDATED_TECH_PERSONNAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo equals to DEFAULT_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldBeFound(
      "techPersonMobNo.equals=" + DEFAULT_TECH_PERSON_MOB_NO
    );

    // Get all the fireElectricalAuditList where techPersonMobNo equals to UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.equals=" + UPDATED_TECH_PERSON_MOB_NO
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo not equals to DEFAULT_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.notEquals=" + DEFAULT_TECH_PERSON_MOB_NO
    );

    // Get all the fireElectricalAuditList where techPersonMobNo not equals to UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldBeFound(
      "techPersonMobNo.notEquals=" + UPDATED_TECH_PERSON_MOB_NO
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo in DEFAULT_TECH_PERSON_MOB_NO or UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldBeFound(
      "techPersonMobNo.in=" +
      DEFAULT_TECH_PERSON_MOB_NO +
      "," +
      UPDATED_TECH_PERSON_MOB_NO
    );

    // Get all the fireElectricalAuditList where techPersonMobNo equals to UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.in=" + UPDATED_TECH_PERSON_MOB_NO
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo is not null
    defaultFireElectricalAuditShouldBeFound("techPersonMobNo.specified=true");

    // Get all the fireElectricalAuditList where techPersonMobNo is null
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo contains DEFAULT_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldBeFound(
      "techPersonMobNo.contains=" + DEFAULT_TECH_PERSON_MOB_NO
    );

    // Get all the fireElectricalAuditList where techPersonMobNo contains UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.contains=" + UPDATED_TECH_PERSON_MOB_NO
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechPersonMobNoNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where techPersonMobNo does not contain DEFAULT_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldNotBeFound(
      "techPersonMobNo.doesNotContain=" + DEFAULT_TECH_PERSON_MOB_NO
    );

    // Get all the fireElectricalAuditList where techPersonMobNo does not contain UPDATED_TECH_PERSON_MOB_NO
    defaultFireElectricalAuditShouldBeFound(
      "techPersonMobNo.doesNotContain=" + UPDATED_TECH_PERSON_MOB_NO
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName equals to DEFAULT_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.equals=" + DEFAULT_TECHNICAL_ENGINEER_NAME
    );

    // Get all the fireElectricalAuditList where technicalEngineerName equals to UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.equals=" + UPDATED_TECHNICAL_ENGINEER_NAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName not equals to DEFAULT_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.notEquals=" + DEFAULT_TECHNICAL_ENGINEER_NAME
    );

    // Get all the fireElectricalAuditList where technicalEngineerName not equals to UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.notEquals=" + UPDATED_TECHNICAL_ENGINEER_NAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName in DEFAULT_TECHNICAL_ENGINEER_NAME or UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.in=" +
      DEFAULT_TECHNICAL_ENGINEER_NAME +
      "," +
      UPDATED_TECHNICAL_ENGINEER_NAME
    );

    // Get all the fireElectricalAuditList where technicalEngineerName equals to UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.in=" + UPDATED_TECHNICAL_ENGINEER_NAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName is not null
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.specified=true"
    );

    // Get all the fireElectricalAuditList where technicalEngineerName is null
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName contains DEFAULT_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.contains=" + DEFAULT_TECHNICAL_ENGINEER_NAME
    );

    // Get all the fireElectricalAuditList where technicalEngineerName contains UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.contains=" + UPDATED_TECHNICAL_ENGINEER_NAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerNameNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerName does not contain DEFAULT_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerName.doesNotContain=" + DEFAULT_TECHNICAL_ENGINEER_NAME
    );

    // Get all the fireElectricalAuditList where technicalEngineerName does not contain UPDATED_TECHNICAL_ENGINEER_NAME
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerName.doesNotContain=" + UPDATED_TECHNICAL_ENGINEER_NAME
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress equals to DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.equals=" + DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress equals to UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.equals=" + UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress not equals to DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.notEquals=" + DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress not equals to UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.notEquals=" + UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress in DEFAULT_TECHNICAL_ENGINEER_ADDRESS or UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.in=" +
      DEFAULT_TECHNICAL_ENGINEER_ADDRESS +
      "," +
      UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress equals to UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.in=" + UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress is not null
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.specified=true"
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress is null
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress contains DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.contains=" + DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress contains UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.contains=" + UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAddressNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAddress does not contain DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAddress.doesNotContain=" +
      DEFAULT_TECHNICAL_ENGINEER_ADDRESS
    );

    // Get all the fireElectricalAuditList where technicalEngineerAddress does not contain UPDATED_TECHNICAL_ENGINEER_ADDRESS
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAddress.doesNotContain=" +
      UPDATED_TECHNICAL_ENGINEER_ADDRESS
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob equals to DEFAULT_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.equals=" + DEFAULT_TECHNICAL_ENGINEER_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob equals to UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.equals=" + UPDATED_TECHNICAL_ENGINEER_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob not equals to DEFAULT_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.notEquals=" + DEFAULT_TECHNICAL_ENGINEER_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob not equals to UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.notEquals=" + UPDATED_TECHNICAL_ENGINEER_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob in DEFAULT_TECHNICAL_ENGINEER_MOB or UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.in=" +
      DEFAULT_TECHNICAL_ENGINEER_MOB +
      "," +
      UPDATED_TECHNICAL_ENGINEER_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob equals to UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.in=" + UPDATED_TECHNICAL_ENGINEER_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob is not null
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.specified=true"
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob is null
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob contains DEFAULT_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.contains=" + DEFAULT_TECHNICAL_ENGINEER_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob contains UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.contains=" + UPDATED_TECHNICAL_ENGINEER_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerMobNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerMob does not contain DEFAULT_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerMob.doesNotContain=" + DEFAULT_TECHNICAL_ENGINEER_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerMob does not contain UPDATED_TECHNICAL_ENGINEER_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerMob.doesNotContain=" + UPDATED_TECHNICAL_ENGINEER_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob equals to DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.equals=" +
      DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob equals to UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.equals=" +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob not equals to DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.notEquals=" +
      DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob not equals to UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.notEquals=" +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob in DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB or UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.in=" +
      DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB +
      "," +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob equals to UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.in=" +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob is not null
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.specified=true"
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob is null
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob contains DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.contains=" +
      DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob contains UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.contains=" +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTechnicalEngineerAlternateMobNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob does not contain DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldNotBeFound(
      "technicalEngineerAlternateMob.doesNotContain=" +
      DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );

    // Get all the fireElectricalAuditList where technicalEngineerAlternateMob does not contain UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    defaultFireElectricalAuditShouldBeFound(
      "technicalEngineerAlternateMob.doesNotContain=" +
      UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement equals to DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.equals=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement equals to UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.equals=" + UPDATED_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement not equals to DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.notEquals=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement not equals to UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.notEquals=" + UPDATED_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement in DEFAULT_O_2_HOSP_REQUIREMENT or UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.in=" +
      DEFAULT_O_2_HOSP_REQUIREMENT +
      "," +
      UPDATED_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement equals to UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.in=" + UPDATED_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement is not null
    defaultFireElectricalAuditShouldBeFound("o2HospRequirement.specified=true");

    // Get all the fireElectricalAuditList where o2HospRequirement is null
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement is greater than or equal to DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.greaterThanOrEqual=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement is greater than or equal to UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.greaterThanOrEqual=" + UPDATED_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement is less than or equal to DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.lessThanOrEqual=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement is less than or equal to SMALLER_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.lessThanOrEqual=" + SMALLER_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement is less than DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.lessThan=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement is less than UPDATED_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.lessThan=" + UPDATED_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospRequirementIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospRequirement is greater than DEFAULT_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospRequirement.greaterThan=" + DEFAULT_O_2_HOSP_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospRequirement is greater than SMALLER_O_2_HOSP_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospRequirement.greaterThan=" + SMALLER_O_2_HOSP_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement equals to DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.equals=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement equals to UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.equals=" +
      UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement not equals to DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.notEquals=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement not equals to UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.notEquals=" +
      UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement in DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT or UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.in=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT +
      "," +
      UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement equals to UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.in=" + UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is not null
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.specified=true"
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is null
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is greater than or equal to DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.greaterThanOrEqual=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is greater than or equal to UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.greaterThanOrEqual=" +
      UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is less than or equal to DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.lessThanOrEqual=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is less than or equal to SMALLER_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.lessThanOrEqual=" +
      SMALLER_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is less than DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.lessThan=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is less than UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.lessThan=" +
      UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByo2HospProjectedRequirementIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is greater than DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldNotBeFound(
      "o2HospProjectedRequirement.greaterThan=" +
      DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT
    );

    // Get all the fireElectricalAuditList where o2HospProjectedRequirement is greater than SMALLER_O_2_HOSP_PROJECTED_REQUIREMENT
    defaultFireElectricalAuditShouldBeFound(
      "o2HospProjectedRequirement.greaterThan=" +
      SMALLER_O_2_HOSP_PROJECTED_REQUIREMENT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT equals to DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.equals=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT equals to UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.equals=" +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT not equals to DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.notEquals=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT not equals to UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.notEquals=" +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT in DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT or UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.in=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT +
      "," +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT equals to UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.in=" +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is not null
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.specified=true"
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is null
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is greater than or equal to DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.greaterThanOrEqual=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is greater than or equal to UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.greaterThanOrEqual=" +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is less than or equal to DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.lessThanOrEqual=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is less than or equal to SMALLER_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.lessThanOrEqual=" +
      SMALLER_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is less than DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.lessThan=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is less than UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.lessThan=" +
      UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsBySaveO2RequirementPossibleMTIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is greater than DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldNotBeFound(
      "saveO2RequirementPossibleMT.greaterThan=" +
      DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );

    // Get all the fireElectricalAuditList where saveO2RequirementPossibleMT is greater than SMALLER_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    defaultFireElectricalAuditShouldBeFound(
      "saveO2RequirementPossibleMT.greaterThan=" +
      SMALLER_SAVE_O_2_REQUIREMENT_POSSIBLE_MT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByMonitoringO2ValvesPortIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort equals to DEFAULT_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldBeFound(
      "monitoringO2ValvesPort.equals=" + DEFAULT_MONITORING_O_2_VALVES_PORT
    );

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort equals to UPDATED_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldNotBeFound(
      "monitoringO2ValvesPort.equals=" + UPDATED_MONITORING_O_2_VALVES_PORT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByMonitoringO2ValvesPortIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort not equals to DEFAULT_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldNotBeFound(
      "monitoringO2ValvesPort.notEquals=" + DEFAULT_MONITORING_O_2_VALVES_PORT
    );

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort not equals to UPDATED_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldBeFound(
      "monitoringO2ValvesPort.notEquals=" + UPDATED_MONITORING_O_2_VALVES_PORT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByMonitoringO2ValvesPortIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort in DEFAULT_MONITORING_O_2_VALVES_PORT or UPDATED_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldBeFound(
      "monitoringO2ValvesPort.in=" +
      DEFAULT_MONITORING_O_2_VALVES_PORT +
      "," +
      UPDATED_MONITORING_O_2_VALVES_PORT
    );

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort equals to UPDATED_MONITORING_O_2_VALVES_PORT
    defaultFireElectricalAuditShouldNotBeFound(
      "monitoringO2ValvesPort.in=" + UPDATED_MONITORING_O_2_VALVES_PORT
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByMonitoringO2ValvesPortIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort is not null
    defaultFireElectricalAuditShouldBeFound(
      "monitoringO2ValvesPort.specified=true"
    );

    // Get all the fireElectricalAuditList where monitoringO2ValvesPort is null
    defaultFireElectricalAuditShouldNotBeFound(
      "monitoringO2ValvesPort.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPortValvesShutDownIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where portValvesShutDown equals to DEFAULT_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldBeFound(
      "portValvesShutDown.equals=" + DEFAULT_PORT_VALVES_SHUT_DOWN
    );

    // Get all the fireElectricalAuditList where portValvesShutDown equals to UPDATED_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldNotBeFound(
      "portValvesShutDown.equals=" + UPDATED_PORT_VALVES_SHUT_DOWN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPortValvesShutDownIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where portValvesShutDown not equals to DEFAULT_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldNotBeFound(
      "portValvesShutDown.notEquals=" + DEFAULT_PORT_VALVES_SHUT_DOWN
    );

    // Get all the fireElectricalAuditList where portValvesShutDown not equals to UPDATED_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldBeFound(
      "portValvesShutDown.notEquals=" + UPDATED_PORT_VALVES_SHUT_DOWN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPortValvesShutDownIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where portValvesShutDown in DEFAULT_PORT_VALVES_SHUT_DOWN or UPDATED_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldBeFound(
      "portValvesShutDown.in=" +
      DEFAULT_PORT_VALVES_SHUT_DOWN +
      "," +
      UPDATED_PORT_VALVES_SHUT_DOWN
    );

    // Get all the fireElectricalAuditList where portValvesShutDown equals to UPDATED_PORT_VALVES_SHUT_DOWN
    defaultFireElectricalAuditShouldNotBeFound(
      "portValvesShutDown.in=" + UPDATED_PORT_VALVES_SHUT_DOWN
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPortValvesShutDownIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where portValvesShutDown is not null
    defaultFireElectricalAuditShouldBeFound(
      "portValvesShutDown.specified=true"
    );

    // Get all the fireElectricalAuditList where portValvesShutDown is null
    defaultFireElectricalAuditShouldNotBeFound(
      "portValvesShutDown.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIdPatientDrillDoneIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where idPatientDrillDone equals to DEFAULT_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldBeFound(
      "idPatientDrillDone.equals=" + DEFAULT_ID_PATIENT_DRILL_DONE
    );

    // Get all the fireElectricalAuditList where idPatientDrillDone equals to UPDATED_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "idPatientDrillDone.equals=" + UPDATED_ID_PATIENT_DRILL_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIdPatientDrillDoneIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where idPatientDrillDone not equals to DEFAULT_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "idPatientDrillDone.notEquals=" + DEFAULT_ID_PATIENT_DRILL_DONE
    );

    // Get all the fireElectricalAuditList where idPatientDrillDone not equals to UPDATED_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldBeFound(
      "idPatientDrillDone.notEquals=" + UPDATED_ID_PATIENT_DRILL_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIdPatientDrillDoneIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where idPatientDrillDone in DEFAULT_ID_PATIENT_DRILL_DONE or UPDATED_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldBeFound(
      "idPatientDrillDone.in=" +
      DEFAULT_ID_PATIENT_DRILL_DONE +
      "," +
      UPDATED_ID_PATIENT_DRILL_DONE
    );

    // Get all the fireElectricalAuditList where idPatientDrillDone equals to UPDATED_ID_PATIENT_DRILL_DONE
    defaultFireElectricalAuditShouldNotBeFound(
      "idPatientDrillDone.in=" + UPDATED_ID_PATIENT_DRILL_DONE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIdPatientDrillDoneIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where idPatientDrillDone is not null
    defaultFireElectricalAuditShouldBeFound(
      "idPatientDrillDone.specified=true"
    );

    // Get all the fireElectricalAuditList where idPatientDrillDone is null
    defaultFireElectricalAuditShouldNotBeFound(
      "idPatientDrillDone.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByStaffCheckingLeakageIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where staffCheckingLeakage equals to DEFAULT_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldBeFound(
      "staffCheckingLeakage.equals=" + DEFAULT_STAFF_CHECKING_LEAKAGE
    );

    // Get all the fireElectricalAuditList where staffCheckingLeakage equals to UPDATED_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldNotBeFound(
      "staffCheckingLeakage.equals=" + UPDATED_STAFF_CHECKING_LEAKAGE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByStaffCheckingLeakageIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where staffCheckingLeakage not equals to DEFAULT_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldNotBeFound(
      "staffCheckingLeakage.notEquals=" + DEFAULT_STAFF_CHECKING_LEAKAGE
    );

    // Get all the fireElectricalAuditList where staffCheckingLeakage not equals to UPDATED_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldBeFound(
      "staffCheckingLeakage.notEquals=" + UPDATED_STAFF_CHECKING_LEAKAGE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByStaffCheckingLeakageIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where staffCheckingLeakage in DEFAULT_STAFF_CHECKING_LEAKAGE or UPDATED_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldBeFound(
      "staffCheckingLeakage.in=" +
      DEFAULT_STAFF_CHECKING_LEAKAGE +
      "," +
      UPDATED_STAFF_CHECKING_LEAKAGE
    );

    // Get all the fireElectricalAuditList where staffCheckingLeakage equals to UPDATED_STAFF_CHECKING_LEAKAGE
    defaultFireElectricalAuditShouldNotBeFound(
      "staffCheckingLeakage.in=" + UPDATED_STAFF_CHECKING_LEAKAGE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByStaffCheckingLeakageIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where staffCheckingLeakage is not null
    defaultFireElectricalAuditShouldBeFound(
      "staffCheckingLeakage.specified=true"
    );

    // Get all the fireElectricalAuditList where staffCheckingLeakage is null
    defaultFireElectricalAuditShouldNotBeFound(
      "staffCheckingLeakage.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPatientO2ReqFinalizedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where patientO2ReqFinalized equals to DEFAULT_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldBeFound(
      "patientO2ReqFinalized.equals=" + DEFAULT_PATIENT_O_2_REQ_FINALIZED
    );

    // Get all the fireElectricalAuditList where patientO2ReqFinalized equals to UPDATED_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldNotBeFound(
      "patientO2ReqFinalized.equals=" + UPDATED_PATIENT_O_2_REQ_FINALIZED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPatientO2ReqFinalizedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where patientO2ReqFinalized not equals to DEFAULT_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldNotBeFound(
      "patientO2ReqFinalized.notEquals=" + DEFAULT_PATIENT_O_2_REQ_FINALIZED
    );

    // Get all the fireElectricalAuditList where patientO2ReqFinalized not equals to UPDATED_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldBeFound(
      "patientO2ReqFinalized.notEquals=" + UPDATED_PATIENT_O_2_REQ_FINALIZED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPatientO2ReqFinalizedIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where patientO2ReqFinalized in DEFAULT_PATIENT_O_2_REQ_FINALIZED or UPDATED_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldBeFound(
      "patientO2ReqFinalized.in=" +
      DEFAULT_PATIENT_O_2_REQ_FINALIZED +
      "," +
      UPDATED_PATIENT_O_2_REQ_FINALIZED
    );

    // Get all the fireElectricalAuditList where patientO2ReqFinalized equals to UPDATED_PATIENT_O_2_REQ_FINALIZED
    defaultFireElectricalAuditShouldNotBeFound(
      "patientO2ReqFinalized.in=" + UPDATED_PATIENT_O_2_REQ_FINALIZED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByPatientO2ReqFinalizedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where patientO2ReqFinalized is not null
    defaultFireElectricalAuditShouldBeFound(
      "patientO2ReqFinalized.specified=true"
    );

    // Get all the fireElectricalAuditList where patientO2ReqFinalized is null
    defaultFireElectricalAuditShouldNotBeFound(
      "patientO2ReqFinalized.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor equals to DEFAULT_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldBeFound(
      "timeByDoctor.equals=" + DEFAULT_TIME_BY_DOCTOR
    );

    // Get all the fireElectricalAuditList where timeByDoctor equals to UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "timeByDoctor.equals=" + UPDATED_TIME_BY_DOCTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor not equals to DEFAULT_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "timeByDoctor.notEquals=" + DEFAULT_TIME_BY_DOCTOR
    );

    // Get all the fireElectricalAuditList where timeByDoctor not equals to UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldBeFound(
      "timeByDoctor.notEquals=" + UPDATED_TIME_BY_DOCTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor in DEFAULT_TIME_BY_DOCTOR or UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldBeFound(
      "timeByDoctor.in=" + DEFAULT_TIME_BY_DOCTOR + "," + UPDATED_TIME_BY_DOCTOR
    );

    // Get all the fireElectricalAuditList where timeByDoctor equals to UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "timeByDoctor.in=" + UPDATED_TIME_BY_DOCTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor is not null
    defaultFireElectricalAuditShouldBeFound("timeByDoctor.specified=true");

    // Get all the fireElectricalAuditList where timeByDoctor is null
    defaultFireElectricalAuditShouldNotBeFound("timeByDoctor.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor contains DEFAULT_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldBeFound(
      "timeByDoctor.contains=" + DEFAULT_TIME_BY_DOCTOR
    );

    // Get all the fireElectricalAuditList where timeByDoctor contains UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "timeByDoctor.contains=" + UPDATED_TIME_BY_DOCTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByTimeByDoctorNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where timeByDoctor does not contain DEFAULT_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "timeByDoctor.doesNotContain=" + DEFAULT_TIME_BY_DOCTOR
    );

    // Get all the fireElectricalAuditList where timeByDoctor does not contain UPDATED_TIME_BY_DOCTOR
    defaultFireElectricalAuditShouldBeFound(
      "timeByDoctor.doesNotContain=" + UPDATED_TIME_BY_DOCTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIsLightingInstalledIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where isLightingInstalled equals to DEFAULT_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldBeFound(
      "isLightingInstalled.equals=" + DEFAULT_IS_LIGHTING_INSTALLED
    );

    // Get all the fireElectricalAuditList where isLightingInstalled equals to UPDATED_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldNotBeFound(
      "isLightingInstalled.equals=" + UPDATED_IS_LIGHTING_INSTALLED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIsLightingInstalledIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where isLightingInstalled not equals to DEFAULT_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldNotBeFound(
      "isLightingInstalled.notEquals=" + DEFAULT_IS_LIGHTING_INSTALLED
    );

    // Get all the fireElectricalAuditList where isLightingInstalled not equals to UPDATED_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldBeFound(
      "isLightingInstalled.notEquals=" + UPDATED_IS_LIGHTING_INSTALLED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIsLightingInstalledIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where isLightingInstalled in DEFAULT_IS_LIGHTING_INSTALLED or UPDATED_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldBeFound(
      "isLightingInstalled.in=" +
      DEFAULT_IS_LIGHTING_INSTALLED +
      "," +
      UPDATED_IS_LIGHTING_INSTALLED
    );

    // Get all the fireElectricalAuditList where isLightingInstalled equals to UPDATED_IS_LIGHTING_INSTALLED
    defaultFireElectricalAuditShouldNotBeFound(
      "isLightingInstalled.in=" + UPDATED_IS_LIGHTING_INSTALLED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByIsLightingInstalledIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where isLightingInstalled is not null
    defaultFireElectricalAuditShouldBeFound(
      "isLightingInstalled.specified=true"
    );

    // Get all the fireElectricalAuditList where isLightingInstalled is null
    defaultFireElectricalAuditShouldNotBeFound(
      "isLightingInstalled.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor equals to DEFAULT_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.equals=" + DEFAULT_LOC_LIGHTNING_ARRERSTOR
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor equals to UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.equals=" + UPDATED_LOC_LIGHTNING_ARRERSTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor not equals to DEFAULT_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.notEquals=" + DEFAULT_LOC_LIGHTNING_ARRERSTOR
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor not equals to UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.notEquals=" + UPDATED_LOC_LIGHTNING_ARRERSTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor in DEFAULT_LOC_LIGHTNING_ARRERSTOR or UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.in=" +
      DEFAULT_LOC_LIGHTNING_ARRERSTOR +
      "," +
      UPDATED_LOC_LIGHTNING_ARRERSTOR
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor equals to UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.in=" + UPDATED_LOC_LIGHTNING_ARRERSTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor is not null
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.specified=true"
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor is null
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor contains DEFAULT_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.contains=" + DEFAULT_LOC_LIGHTNING_ARRERSTOR
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor contains UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.contains=" + UPDATED_LOC_LIGHTNING_ARRERSTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLocLightningArrerstorNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where locLightningArrerstor does not contain DEFAULT_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldNotBeFound(
      "locLightningArrerstor.doesNotContain=" + DEFAULT_LOC_LIGHTNING_ARRERSTOR
    );

    // Get all the fireElectricalAuditList where locLightningArrerstor does not contain UPDATED_LOC_LIGHTNING_ARRERSTOR
    defaultFireElectricalAuditShouldBeFound(
      "locLightningArrerstor.doesNotContain=" + UPDATED_LOC_LIGHTNING_ARRERSTOR
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy equals to DEFAULT_CREATED_BY
    defaultFireElectricalAuditShouldBeFound(
      "createdBy.equals=" + DEFAULT_CREATED_BY
    );

    // Get all the fireElectricalAuditList where createdBy equals to UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "createdBy.equals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy not equals to DEFAULT_CREATED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "createdBy.notEquals=" + DEFAULT_CREATED_BY
    );

    // Get all the fireElectricalAuditList where createdBy not equals to UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldBeFound(
      "createdBy.notEquals=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByIsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldBeFound(
      "createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY
    );

    // Get all the fireElectricalAuditList where createdBy equals to UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "createdBy.in=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByIsNullOrNotNull() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy is not null
    defaultFireElectricalAuditShouldBeFound("createdBy.specified=true");

    // Get all the fireElectricalAuditList where createdBy is null
    defaultFireElectricalAuditShouldNotBeFound("createdBy.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy contains DEFAULT_CREATED_BY
    defaultFireElectricalAuditShouldBeFound(
      "createdBy.contains=" + DEFAULT_CREATED_BY
    );

    // Get all the fireElectricalAuditList where createdBy contains UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "createdBy.contains=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdBy does not contain DEFAULT_CREATED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "createdBy.doesNotContain=" + DEFAULT_CREATED_BY
    );

    // Get all the fireElectricalAuditList where createdBy does not contain UPDATED_CREATED_BY
    defaultFireElectricalAuditShouldBeFound(
      "createdBy.doesNotContain=" + UPDATED_CREATED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate equals to DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.equals=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate equals to UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.equals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate not equals to DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.notEquals=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate not equals to UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.notEquals=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate in DEFAULT_CREATED_DATE or UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.in=" + DEFAULT_CREATED_DATE + "," + UPDATED_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate equals to UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.in=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate is not null
    defaultFireElectricalAuditShouldBeFound("createdDate.specified=true");

    // Get all the fireElectricalAuditList where createdDate is null
    defaultFireElectricalAuditShouldNotBeFound("createdDate.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate is greater than or equal to DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.greaterThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate is greater than or equal to UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.greaterThanOrEqual=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate is less than or equal to DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.lessThanOrEqual=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate is less than or equal to SMALLER_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.lessThanOrEqual=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate is less than DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.lessThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate is less than UPDATED_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.lessThan=" + UPDATED_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByCreatedDateIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where createdDate is greater than DEFAULT_CREATED_DATE
    defaultFireElectricalAuditShouldNotBeFound(
      "createdDate.greaterThan=" + DEFAULT_CREATED_DATE
    );

    // Get all the fireElectricalAuditList where createdDate is greater than SMALLER_CREATED_DATE
    defaultFireElectricalAuditShouldBeFound(
      "createdDate.greaterThan=" + SMALLER_CREATED_DATE
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy equals to DEFAULT_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldBeFound(
      "lastModifiedBy.equals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the fireElectricalAuditList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.equals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy not equals to DEFAULT_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.notEquals=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the fireElectricalAuditList where lastModifiedBy not equals to UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldBeFound(
      "lastModifiedBy.notEquals=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy in DEFAULT_LAST_MODIFIED_BY or UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldBeFound(
      "lastModifiedBy.in=" +
      DEFAULT_LAST_MODIFIED_BY +
      "," +
      UPDATED_LAST_MODIFIED_BY
    );

    // Get all the fireElectricalAuditList where lastModifiedBy equals to UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.in=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy is not null
    defaultFireElectricalAuditShouldBeFound("lastModifiedBy.specified=true");

    // Get all the fireElectricalAuditList where lastModifiedBy is null
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.specified=false"
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy contains DEFAULT_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldBeFound(
      "lastModifiedBy.contains=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the fireElectricalAuditList where lastModifiedBy contains UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.contains=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedByNotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModifiedBy does not contain DEFAULT_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModifiedBy.doesNotContain=" + DEFAULT_LAST_MODIFIED_BY
    );

    // Get all the fireElectricalAuditList where lastModifiedBy does not contain UPDATED_LAST_MODIFIED_BY
    defaultFireElectricalAuditShouldBeFound(
      "lastModifiedBy.doesNotContain=" + UPDATED_LAST_MODIFIED_BY
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified equals to DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.equals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.equals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified not equals to DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.notEquals=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified not equals to UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.notEquals=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsInShouldWork()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified in DEFAULT_LAST_MODIFIED or UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.in=" + DEFAULT_LAST_MODIFIED + "," + UPDATED_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified equals to UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.in=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified is not null
    defaultFireElectricalAuditShouldBeFound("lastModified.specified=true");

    // Get all the fireElectricalAuditList where lastModified is null
    defaultFireElectricalAuditShouldNotBeFound("lastModified.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsGreaterThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified is greater than or equal to DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.greaterThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified is greater than or equal to UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.greaterThanOrEqual=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsLessThanOrEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified is less than or equal to DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.lessThanOrEqual=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified is less than or equal to SMALLER_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.lessThanOrEqual=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsLessThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified is less than DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.lessThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified is less than UPDATED_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.lessThan=" + UPDATED_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByLastModifiedIsGreaterThanSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where lastModified is greater than DEFAULT_LAST_MODIFIED
    defaultFireElectricalAuditShouldNotBeFound(
      "lastModified.greaterThan=" + DEFAULT_LAST_MODIFIED
    );

    // Get all the fireElectricalAuditList where lastModified is greater than SMALLER_LAST_MODIFIED
    defaultFireElectricalAuditShouldBeFound(
      "lastModified.greaterThan=" + SMALLER_LAST_MODIFIED
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1IsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 equals to DEFAULT_FREE_FIELD_1
    defaultFireElectricalAuditShouldBeFound(
      "freeField1.equals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the fireElectricalAuditList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField1.equals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 not equals to DEFAULT_FREE_FIELD_1
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField1.notEquals=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the fireElectricalAuditList where freeField1 not equals to UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldBeFound(
      "freeField1.notEquals=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1IsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 in DEFAULT_FREE_FIELD_1 or UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldBeFound(
      "freeField1.in=" + DEFAULT_FREE_FIELD_1 + "," + UPDATED_FREE_FIELD_1
    );

    // Get all the fireElectricalAuditList where freeField1 equals to UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField1.in=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 is not null
    defaultFireElectricalAuditShouldBeFound("freeField1.specified=true");

    // Get all the fireElectricalAuditList where freeField1 is null
    defaultFireElectricalAuditShouldNotBeFound("freeField1.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1ContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 contains DEFAULT_FREE_FIELD_1
    defaultFireElectricalAuditShouldBeFound(
      "freeField1.contains=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the fireElectricalAuditList where freeField1 contains UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField1.contains=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField1NotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField1 does not contain DEFAULT_FREE_FIELD_1
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField1.doesNotContain=" + DEFAULT_FREE_FIELD_1
    );

    // Get all the fireElectricalAuditList where freeField1 does not contain UPDATED_FREE_FIELD_1
    defaultFireElectricalAuditShouldBeFound(
      "freeField1.doesNotContain=" + UPDATED_FREE_FIELD_1
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2IsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 equals to DEFAULT_FREE_FIELD_2
    defaultFireElectricalAuditShouldBeFound(
      "freeField2.equals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the fireElectricalAuditList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField2.equals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 not equals to DEFAULT_FREE_FIELD_2
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField2.notEquals=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the fireElectricalAuditList where freeField2 not equals to UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldBeFound(
      "freeField2.notEquals=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2IsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 in DEFAULT_FREE_FIELD_2 or UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldBeFound(
      "freeField2.in=" + DEFAULT_FREE_FIELD_2 + "," + UPDATED_FREE_FIELD_2
    );

    // Get all the fireElectricalAuditList where freeField2 equals to UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField2.in=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 is not null
    defaultFireElectricalAuditShouldBeFound("freeField2.specified=true");

    // Get all the fireElectricalAuditList where freeField2 is null
    defaultFireElectricalAuditShouldNotBeFound("freeField2.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2ContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 contains DEFAULT_FREE_FIELD_2
    defaultFireElectricalAuditShouldBeFound(
      "freeField2.contains=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the fireElectricalAuditList where freeField2 contains UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField2.contains=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField2NotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField2 does not contain DEFAULT_FREE_FIELD_2
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField2.doesNotContain=" + DEFAULT_FREE_FIELD_2
    );

    // Get all the fireElectricalAuditList where freeField2 does not contain UPDATED_FREE_FIELD_2
    defaultFireElectricalAuditShouldBeFound(
      "freeField2.doesNotContain=" + UPDATED_FREE_FIELD_2
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3IsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 equals to DEFAULT_FREE_FIELD_3
    defaultFireElectricalAuditShouldBeFound(
      "freeField3.equals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the fireElectricalAuditList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField3.equals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 not equals to DEFAULT_FREE_FIELD_3
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField3.notEquals=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the fireElectricalAuditList where freeField3 not equals to UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldBeFound(
      "freeField3.notEquals=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3IsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 in DEFAULT_FREE_FIELD_3 or UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldBeFound(
      "freeField3.in=" + DEFAULT_FREE_FIELD_3 + "," + UPDATED_FREE_FIELD_3
    );

    // Get all the fireElectricalAuditList where freeField3 equals to UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField3.in=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 is not null
    defaultFireElectricalAuditShouldBeFound("freeField3.specified=true");

    // Get all the fireElectricalAuditList where freeField3 is null
    defaultFireElectricalAuditShouldNotBeFound("freeField3.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3ContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 contains DEFAULT_FREE_FIELD_3
    defaultFireElectricalAuditShouldBeFound(
      "freeField3.contains=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the fireElectricalAuditList where freeField3 contains UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField3.contains=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField3NotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField3 does not contain DEFAULT_FREE_FIELD_3
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField3.doesNotContain=" + DEFAULT_FREE_FIELD_3
    );

    // Get all the fireElectricalAuditList where freeField3 does not contain UPDATED_FREE_FIELD_3
    defaultFireElectricalAuditShouldBeFound(
      "freeField3.doesNotContain=" + UPDATED_FREE_FIELD_3
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4IsEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 equals to DEFAULT_FREE_FIELD_4
    defaultFireElectricalAuditShouldBeFound(
      "freeField4.equals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the fireElectricalAuditList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField4.equals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4IsNotEqualToSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 not equals to DEFAULT_FREE_FIELD_4
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField4.notEquals=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the fireElectricalAuditList where freeField4 not equals to UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldBeFound(
      "freeField4.notEquals=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4IsInShouldWork() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 in DEFAULT_FREE_FIELD_4 or UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldBeFound(
      "freeField4.in=" + DEFAULT_FREE_FIELD_4 + "," + UPDATED_FREE_FIELD_4
    );

    // Get all the fireElectricalAuditList where freeField4 equals to UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField4.in=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4IsNullOrNotNull()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 is not null
    defaultFireElectricalAuditShouldBeFound("freeField4.specified=true");

    // Get all the fireElectricalAuditList where freeField4 is null
    defaultFireElectricalAuditShouldNotBeFound("freeField4.specified=false");
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4ContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 contains DEFAULT_FREE_FIELD_4
    defaultFireElectricalAuditShouldBeFound(
      "freeField4.contains=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the fireElectricalAuditList where freeField4 contains UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField4.contains=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByFreeField4NotContainsSomething()
    throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    // Get all the fireElectricalAuditList where freeField4 does not contain DEFAULT_FREE_FIELD_4
    defaultFireElectricalAuditShouldNotBeFound(
      "freeField4.doesNotContain=" + DEFAULT_FREE_FIELD_4
    );

    // Get all the fireElectricalAuditList where freeField4 does not contain UPDATED_FREE_FIELD_4
    defaultFireElectricalAuditShouldBeFound(
      "freeField4.doesNotContain=" + UPDATED_FREE_FIELD_4
    );
  }

  @Test
  @Transactional
  void getAllFireElectricalAuditsByAuditIsEqualToSomething() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);
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
    fireElectricalAudit.setAudit(audit);
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);
    Long auditId = audit.getId();

    // Get all the fireElectricalAuditList where audit equals to auditId
    defaultFireElectricalAuditShouldBeFound("auditId.equals=" + auditId);

    // Get all the fireElectricalAuditList where audit equals to (auditId + 1)
    defaultFireElectricalAuditShouldNotBeFound(
      "auditId.equals=" + (auditId + 1)
    );
  }

  /**
   * Executes the search, and checks that the default entity is returned.
   */
  private void defaultFireElectricalAuditShouldBeFound(String filter)
    throws Exception {
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(
        jsonPath("$.[*].id")
          .value(hasItem(fireElectricalAudit.getId().intValue()))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditDoneOrnot")
          .value(hasItem(DEFAULT_FIRE_AUDIT_DONE_ORNOT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditDate")
          .value(hasItem(DEFAULT_FIRE_AUDIT_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].fireFaults").value(hasItem(DEFAULT_FIRE_FAULTS))
      )
      .andExpect(
        jsonPath("$.[*].fireCorrectiveAction")
          .value(hasItem(DEFAULT_FIRE_CORRECTIVE_ACTION))
      )
      .andExpect(
        jsonPath("$.[*].fireAuditPlan").value(hasItem(DEFAULT_FIRE_AUDIT_PLAN))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditDone")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_DONE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditDate")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_DATE.toString()))
      )
      .andExpect(
        jsonPath("$.[*].electricalFaults")
          .value(hasItem(DEFAULT_ELECTRICAL_FAULTS))
      )
      .andExpect(
        jsonPath("$.[*].electricalCorrectiveAction")
          .value(hasItem(DEFAULT_ELECTRICAL_CORRECTIVE_ACTION))
      )
      .andExpect(
        jsonPath("$.[*].electricalAuditInspection")
          .value(hasItem(DEFAULT_ELECTRICAL_AUDIT_INSPECTION))
      )
      .andExpect(
        jsonPath("$.[*].technicalPersonAppoint")
          .value(hasItem(DEFAULT_TECHNICAL_PERSON_APPOINT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].techPersonname").value(hasItem(DEFAULT_TECH_PERSONNAME))
      )
      .andExpect(
        jsonPath("$.[*].techPersonMobNo")
          .value(hasItem(DEFAULT_TECH_PERSON_MOB_NO))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerName")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_NAME))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerAddress")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_ADDRESS))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerMob")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_MOB))
      )
      .andExpect(
        jsonPath("$.[*].technicalEngineerAlternateMob")
          .value(hasItem(DEFAULT_TECHNICAL_ENGINEER_ALTERNATE_MOB))
      )
      .andExpect(
        jsonPath("$.[*].o2HospRequirement")
          .value(hasItem(DEFAULT_O_2_HOSP_REQUIREMENT.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].o2HospProjectedRequirement")
          .value(hasItem(DEFAULT_O_2_HOSP_PROJECTED_REQUIREMENT.doubleValue()))
      )
      .andExpect(
        jsonPath("$.[*].saveO2RequirementPossibleMT")
          .value(
            hasItem(DEFAULT_SAVE_O_2_REQUIREMENT_POSSIBLE_MT.doubleValue())
          )
      )
      .andExpect(
        jsonPath("$.[*].monitoringO2ValvesPort")
          .value(hasItem(DEFAULT_MONITORING_O_2_VALVES_PORT.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].portValvesShutDown")
          .value(hasItem(DEFAULT_PORT_VALVES_SHUT_DOWN.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].idPatientDrillDone")
          .value(hasItem(DEFAULT_ID_PATIENT_DRILL_DONE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].staffCheckingLeakage")
          .value(hasItem(DEFAULT_STAFF_CHECKING_LEAKAGE.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].patientO2ReqFinalized")
          .value(hasItem(DEFAULT_PATIENT_O_2_REQ_FINALIZED.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].timeByDoctor").value(hasItem(DEFAULT_TIME_BY_DOCTOR))
      )
      .andExpect(
        jsonPath("$.[*].isLightingInstalled")
          .value(hasItem(DEFAULT_IS_LIGHTING_INSTALLED.booleanValue()))
      )
      .andExpect(
        jsonPath("$.[*].locLightningArrerstor")
          .value(hasItem(DEFAULT_LOC_LIGHTNING_ARRERSTOR))
      )
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
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("1"));
  }

  /**
   * Executes the search, and checks that the default entity is not returned.
   */
  private void defaultFireElectricalAuditShouldNotBeFound(String filter)
    throws Exception {
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$").isArray())
      .andExpect(jsonPath("$").isEmpty());

    // Check, that the count call also returns 0
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(content().string("0"));
  }

  @Test
  @Transactional
  void getNonExistingFireElectricalAudit() throws Exception {
    // Get the fireElectricalAudit
    restFireElectricalAuditMockMvc
      .perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE))
      .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void putNewFireElectricalAudit() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();

    // Update the fireElectricalAudit
    FireElectricalAudit updatedFireElectricalAudit = fireElectricalAuditRepository
      .findById(fireElectricalAudit.getId())
      .get();
    // Disconnect from session so that the updates on updatedFireElectricalAudit are not directly saved in db
    em.detach(updatedFireElectricalAudit);
    updatedFireElectricalAudit
      .fireAuditDoneOrnot(UPDATED_FIRE_AUDIT_DONE_ORNOT)
      .fireAuditDate(UPDATED_FIRE_AUDIT_DATE)
      .fireFaults(UPDATED_FIRE_FAULTS)
      .fireCorrectiveAction(UPDATED_FIRE_CORRECTIVE_ACTION)
      .fireAuditPlan(UPDATED_FIRE_AUDIT_PLAN)
      .electricalAuditDone(UPDATED_ELECTRICAL_AUDIT_DONE)
      .electricalAuditDate(UPDATED_ELECTRICAL_AUDIT_DATE)
      .electricalFaults(UPDATED_ELECTRICAL_FAULTS)
      .electricalCorrectiveAction(UPDATED_ELECTRICAL_CORRECTIVE_ACTION)
      .electricalAuditInspection(UPDATED_ELECTRICAL_AUDIT_INSPECTION)
      .technicalPersonAppoint(UPDATED_TECHNICAL_PERSON_APPOINT)
      .techPersonname(UPDATED_TECH_PERSONNAME)
      .techPersonMobNo(UPDATED_TECH_PERSON_MOB_NO)
      .technicalEngineerName(UPDATED_TECHNICAL_ENGINEER_NAME)
      .technicalEngineerAddress(UPDATED_TECHNICAL_ENGINEER_ADDRESS)
      .technicalEngineerMob(UPDATED_TECHNICAL_ENGINEER_MOB)
      .technicalEngineerAlternateMob(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      .o2HospRequirement(UPDATED_O_2_HOSP_REQUIREMENT)
      .o2HospProjectedRequirement(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT)
      .saveO2RequirementPossibleMT(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT)
      .monitoringO2ValvesPort(UPDATED_MONITORING_O_2_VALVES_PORT)
      .portValvesShutDown(UPDATED_PORT_VALVES_SHUT_DOWN)
      .idPatientDrillDone(UPDATED_ID_PATIENT_DRILL_DONE)
      .staffCheckingLeakage(UPDATED_STAFF_CHECKING_LEAKAGE)
      .patientO2ReqFinalized(UPDATED_PATIENT_O_2_REQ_FINALIZED)
      .timeByDoctor(UPDATED_TIME_BY_DOCTOR)
      .isLightingInstalled(UPDATED_IS_LIGHTING_INSTALLED)
      .locLightningArrerstor(UPDATED_LOC_LIGHTNING_ARRERSTOR)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      updatedFireElectricalAudit
    );

    restFireElectricalAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, fireElectricalAuditDTO.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAuditDTO))
      )
      .andExpect(status().isOk());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
    FireElectricalAudit testFireElectricalAudit = fireElectricalAuditList.get(
      fireElectricalAuditList.size() - 1
    );
    assertThat(testFireElectricalAudit.getFireAuditDoneOrnot())
      .isEqualTo(UPDATED_FIRE_AUDIT_DONE_ORNOT);
    assertThat(testFireElectricalAudit.getFireAuditDate())
      .isEqualTo(UPDATED_FIRE_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getFireFaults())
      .isEqualTo(UPDATED_FIRE_FAULTS);
    assertThat(testFireElectricalAudit.getFireCorrectiveAction())
      .isEqualTo(UPDATED_FIRE_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getFireAuditPlan())
      .isEqualTo(UPDATED_FIRE_AUDIT_PLAN);
    assertThat(testFireElectricalAudit.getElectricalAuditDone())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_DONE);
    assertThat(testFireElectricalAudit.getElectricalAuditDate())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getElectricalFaults())
      .isEqualTo(UPDATED_ELECTRICAL_FAULTS);
    assertThat(testFireElectricalAudit.getElectricalCorrectiveAction())
      .isEqualTo(UPDATED_ELECTRICAL_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getElectricalAuditInspection())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_INSPECTION);
    assertThat(testFireElectricalAudit.getTechnicalPersonAppoint())
      .isEqualTo(UPDATED_TECHNICAL_PERSON_APPOINT);
    assertThat(testFireElectricalAudit.getTechPersonname())
      .isEqualTo(UPDATED_TECH_PERSONNAME);
    assertThat(testFireElectricalAudit.getTechPersonMobNo())
      .isEqualTo(UPDATED_TECH_PERSON_MOB_NO);
    assertThat(testFireElectricalAudit.getTechnicalEngineerName())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_NAME);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAddress())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ADDRESS);
    assertThat(testFireElectricalAudit.getTechnicalEngineerMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_MOB);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAlternateMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB);
    assertThat(testFireElectricalAudit.geto2HospRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_REQUIREMENT);
    assertThat(testFireElectricalAudit.geto2HospProjectedRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT);
    assertThat(testFireElectricalAudit.getSaveO2RequirementPossibleMT())
      .isEqualTo(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT);
    assertThat(testFireElectricalAudit.getMonitoringO2ValvesPort())
      .isEqualTo(UPDATED_MONITORING_O_2_VALVES_PORT);
    assertThat(testFireElectricalAudit.getPortValvesShutDown())
      .isEqualTo(UPDATED_PORT_VALVES_SHUT_DOWN);
    assertThat(testFireElectricalAudit.getIdPatientDrillDone())
      .isEqualTo(UPDATED_ID_PATIENT_DRILL_DONE);
    assertThat(testFireElectricalAudit.getStaffCheckingLeakage())
      .isEqualTo(UPDATED_STAFF_CHECKING_LEAKAGE);
    assertThat(testFireElectricalAudit.getPatientO2ReqFinalized())
      .isEqualTo(UPDATED_PATIENT_O_2_REQ_FINALIZED);
    assertThat(testFireElectricalAudit.getTimeByDoctor())
      .isEqualTo(UPDATED_TIME_BY_DOCTOR);
    assertThat(testFireElectricalAudit.getIsLightingInstalled())
      .isEqualTo(UPDATED_IS_LIGHTING_INSTALLED);
    assertThat(testFireElectricalAudit.getLocLightningArrerstor())
      .isEqualTo(UPDATED_LOC_LIGHTNING_ARRERSTOR);
    assertThat(testFireElectricalAudit.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testFireElectricalAudit.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testFireElectricalAudit.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testFireElectricalAudit.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testFireElectricalAudit.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testFireElectricalAudit.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testFireElectricalAudit.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testFireElectricalAudit.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void putNonExistingFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, fireElectricalAudit.getId())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAudit))
      )
      .andExpect(status().isBadRequest());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithIdMismatchFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        put(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAudit))
      )
      .andExpect(status().isBadRequest());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void putWithMissingIdPathParamFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAudit fireElectricalAudit = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        put(ENTITY_API_URL)
          .contentType(MediaType.APPLICATION_JSON)
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAudit))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void partialUpdateFireElectricalAuditWithPatch() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();

    // Update the fireElectricalAudit using partial update
    FireElectricalAudit partialUpdatedFireElectricalAudit = new FireElectricalAudit();
    partialUpdatedFireElectricalAudit.setId(fireElectricalAudit.getId());

    partialUpdatedFireElectricalAudit
      .fireAuditDoneOrnot(UPDATED_FIRE_AUDIT_DONE_ORNOT)
      .fireAuditDate(UPDATED_FIRE_AUDIT_DATE)
      .fireFaults(UPDATED_FIRE_FAULTS)
      .fireCorrectiveAction(UPDATED_FIRE_CORRECTIVE_ACTION)
      .fireAuditPlan(UPDATED_FIRE_AUDIT_PLAN)
      .electricalCorrectiveAction(UPDATED_ELECTRICAL_CORRECTIVE_ACTION)
      .electricalAuditInspection(UPDATED_ELECTRICAL_AUDIT_INSPECTION)
      .techPersonname(UPDATED_TECH_PERSONNAME)
      .technicalEngineerName(UPDATED_TECHNICAL_ENGINEER_NAME)
      .technicalEngineerAddress(UPDATED_TECHNICAL_ENGINEER_ADDRESS)
      .technicalEngineerMob(UPDATED_TECHNICAL_ENGINEER_MOB)
      .technicalEngineerAlternateMob(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      .o2HospRequirement(UPDATED_O_2_HOSP_REQUIREMENT)
      .o2HospProjectedRequirement(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT)
      .saveO2RequirementPossibleMT(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT)
      .monitoringO2ValvesPort(UPDATED_MONITORING_O_2_VALVES_PORT)
      .idPatientDrillDone(UPDATED_ID_PATIENT_DRILL_DONE)
      .timeByDoctor(UPDATED_TIME_BY_DOCTOR)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField4(UPDATED_FREE_FIELD_4);

    restFireElectricalAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFireElectricalAudit.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedFireElectricalAudit)
          )
      )
      .andExpect(status().isOk());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
    FireElectricalAudit testFireElectricalAudit = fireElectricalAuditList.get(
      fireElectricalAuditList.size() - 1
    );
    assertThat(testFireElectricalAudit.getFireAuditDoneOrnot())
      .isEqualTo(UPDATED_FIRE_AUDIT_DONE_ORNOT);
    assertThat(testFireElectricalAudit.getFireAuditDate())
      .isEqualTo(UPDATED_FIRE_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getFireFaults())
      .isEqualTo(UPDATED_FIRE_FAULTS);
    assertThat(testFireElectricalAudit.getFireCorrectiveAction())
      .isEqualTo(UPDATED_FIRE_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getFireAuditPlan())
      .isEqualTo(UPDATED_FIRE_AUDIT_PLAN);
    assertThat(testFireElectricalAudit.getElectricalAuditDone())
      .isEqualTo(DEFAULT_ELECTRICAL_AUDIT_DONE);
    assertThat(testFireElectricalAudit.getElectricalAuditDate())
      .isEqualTo(DEFAULT_ELECTRICAL_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getElectricalFaults())
      .isEqualTo(DEFAULT_ELECTRICAL_FAULTS);
    assertThat(testFireElectricalAudit.getElectricalCorrectiveAction())
      .isEqualTo(UPDATED_ELECTRICAL_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getElectricalAuditInspection())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_INSPECTION);
    assertThat(testFireElectricalAudit.getTechnicalPersonAppoint())
      .isEqualTo(DEFAULT_TECHNICAL_PERSON_APPOINT);
    assertThat(testFireElectricalAudit.getTechPersonname())
      .isEqualTo(UPDATED_TECH_PERSONNAME);
    assertThat(testFireElectricalAudit.getTechPersonMobNo())
      .isEqualTo(DEFAULT_TECH_PERSON_MOB_NO);
    assertThat(testFireElectricalAudit.getTechnicalEngineerName())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_NAME);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAddress())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ADDRESS);
    assertThat(testFireElectricalAudit.getTechnicalEngineerMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_MOB);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAlternateMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB);
    assertThat(testFireElectricalAudit.geto2HospRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_REQUIREMENT);
    assertThat(testFireElectricalAudit.geto2HospProjectedRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT);
    assertThat(testFireElectricalAudit.getSaveO2RequirementPossibleMT())
      .isEqualTo(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT);
    assertThat(testFireElectricalAudit.getMonitoringO2ValvesPort())
      .isEqualTo(UPDATED_MONITORING_O_2_VALVES_PORT);
    assertThat(testFireElectricalAudit.getPortValvesShutDown())
      .isEqualTo(DEFAULT_PORT_VALVES_SHUT_DOWN);
    assertThat(testFireElectricalAudit.getIdPatientDrillDone())
      .isEqualTo(UPDATED_ID_PATIENT_DRILL_DONE);
    assertThat(testFireElectricalAudit.getStaffCheckingLeakage())
      .isEqualTo(DEFAULT_STAFF_CHECKING_LEAKAGE);
    assertThat(testFireElectricalAudit.getPatientO2ReqFinalized())
      .isEqualTo(DEFAULT_PATIENT_O_2_REQ_FINALIZED);
    assertThat(testFireElectricalAudit.getTimeByDoctor())
      .isEqualTo(UPDATED_TIME_BY_DOCTOR);
    assertThat(testFireElectricalAudit.getIsLightingInstalled())
      .isEqualTo(DEFAULT_IS_LIGHTING_INSTALLED);
    assertThat(testFireElectricalAudit.getLocLightningArrerstor())
      .isEqualTo(DEFAULT_LOC_LIGHTNING_ARRERSTOR);
    assertThat(testFireElectricalAudit.getCreatedBy())
      .isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testFireElectricalAudit.getCreatedDate())
      .isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testFireElectricalAudit.getLastModifiedBy())
      .isEqualTo(DEFAULT_LAST_MODIFIED_BY);
    assertThat(testFireElectricalAudit.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testFireElectricalAudit.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testFireElectricalAudit.getFreeField2())
      .isEqualTo(DEFAULT_FREE_FIELD_2);
    assertThat(testFireElectricalAudit.getFreeField3())
      .isEqualTo(DEFAULT_FREE_FIELD_3);
    assertThat(testFireElectricalAudit.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void fullUpdateFireElectricalAuditWithPatch() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();

    // Update the fireElectricalAudit using partial update
    FireElectricalAudit partialUpdatedFireElectricalAudit = new FireElectricalAudit();
    partialUpdatedFireElectricalAudit.setId(fireElectricalAudit.getId());

    partialUpdatedFireElectricalAudit
      .fireAuditDoneOrnot(UPDATED_FIRE_AUDIT_DONE_ORNOT)
      .fireAuditDate(UPDATED_FIRE_AUDIT_DATE)
      .fireFaults(UPDATED_FIRE_FAULTS)
      .fireCorrectiveAction(UPDATED_FIRE_CORRECTIVE_ACTION)
      .fireAuditPlan(UPDATED_FIRE_AUDIT_PLAN)
      .electricalAuditDone(UPDATED_ELECTRICAL_AUDIT_DONE)
      .electricalAuditDate(UPDATED_ELECTRICAL_AUDIT_DATE)
      .electricalFaults(UPDATED_ELECTRICAL_FAULTS)
      .electricalCorrectiveAction(UPDATED_ELECTRICAL_CORRECTIVE_ACTION)
      .electricalAuditInspection(UPDATED_ELECTRICAL_AUDIT_INSPECTION)
      .technicalPersonAppoint(UPDATED_TECHNICAL_PERSON_APPOINT)
      .techPersonname(UPDATED_TECH_PERSONNAME)
      .techPersonMobNo(UPDATED_TECH_PERSON_MOB_NO)
      .technicalEngineerName(UPDATED_TECHNICAL_ENGINEER_NAME)
      .technicalEngineerAddress(UPDATED_TECHNICAL_ENGINEER_ADDRESS)
      .technicalEngineerMob(UPDATED_TECHNICAL_ENGINEER_MOB)
      .technicalEngineerAlternateMob(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB)
      .o2HospRequirement(UPDATED_O_2_HOSP_REQUIREMENT)
      .o2HospProjectedRequirement(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT)
      .saveO2RequirementPossibleMT(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT)
      .monitoringO2ValvesPort(UPDATED_MONITORING_O_2_VALVES_PORT)
      .portValvesShutDown(UPDATED_PORT_VALVES_SHUT_DOWN)
      .idPatientDrillDone(UPDATED_ID_PATIENT_DRILL_DONE)
      .staffCheckingLeakage(UPDATED_STAFF_CHECKING_LEAKAGE)
      .patientO2ReqFinalized(UPDATED_PATIENT_O_2_REQ_FINALIZED)
      .timeByDoctor(UPDATED_TIME_BY_DOCTOR)
      .isLightingInstalled(UPDATED_IS_LIGHTING_INSTALLED)
      .locLightningArrerstor(UPDATED_LOC_LIGHTNING_ARRERSTOR)
      .createdBy(UPDATED_CREATED_BY)
      .createdDate(UPDATED_CREATED_DATE)
      .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
      .lastModified(UPDATED_LAST_MODIFIED)
      .freeField1(UPDATED_FREE_FIELD_1)
      .freeField2(UPDATED_FREE_FIELD_2)
      .freeField3(UPDATED_FREE_FIELD_3)
      .freeField4(UPDATED_FREE_FIELD_4);

    restFireElectricalAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, partialUpdatedFireElectricalAudit.getId())
          .contentType("application/merge-patch+json")
          .content(
            TestUtil.convertObjectToJsonBytes(partialUpdatedFireElectricalAudit)
          )
      )
      .andExpect(status().isOk());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
    FireElectricalAudit testFireElectricalAudit = fireElectricalAuditList.get(
      fireElectricalAuditList.size() - 1
    );
    assertThat(testFireElectricalAudit.getFireAuditDoneOrnot())
      .isEqualTo(UPDATED_FIRE_AUDIT_DONE_ORNOT);
    assertThat(testFireElectricalAudit.getFireAuditDate())
      .isEqualTo(UPDATED_FIRE_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getFireFaults())
      .isEqualTo(UPDATED_FIRE_FAULTS);
    assertThat(testFireElectricalAudit.getFireCorrectiveAction())
      .isEqualTo(UPDATED_FIRE_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getFireAuditPlan())
      .isEqualTo(UPDATED_FIRE_AUDIT_PLAN);
    assertThat(testFireElectricalAudit.getElectricalAuditDone())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_DONE);
    assertThat(testFireElectricalAudit.getElectricalAuditDate())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_DATE);
    assertThat(testFireElectricalAudit.getElectricalFaults())
      .isEqualTo(UPDATED_ELECTRICAL_FAULTS);
    assertThat(testFireElectricalAudit.getElectricalCorrectiveAction())
      .isEqualTo(UPDATED_ELECTRICAL_CORRECTIVE_ACTION);
    assertThat(testFireElectricalAudit.getElectricalAuditInspection())
      .isEqualTo(UPDATED_ELECTRICAL_AUDIT_INSPECTION);
    assertThat(testFireElectricalAudit.getTechnicalPersonAppoint())
      .isEqualTo(UPDATED_TECHNICAL_PERSON_APPOINT);
    assertThat(testFireElectricalAudit.getTechPersonname())
      .isEqualTo(UPDATED_TECH_PERSONNAME);
    assertThat(testFireElectricalAudit.getTechPersonMobNo())
      .isEqualTo(UPDATED_TECH_PERSON_MOB_NO);
    assertThat(testFireElectricalAudit.getTechnicalEngineerName())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_NAME);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAddress())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ADDRESS);
    assertThat(testFireElectricalAudit.getTechnicalEngineerMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_MOB);
    assertThat(testFireElectricalAudit.getTechnicalEngineerAlternateMob())
      .isEqualTo(UPDATED_TECHNICAL_ENGINEER_ALTERNATE_MOB);
    assertThat(testFireElectricalAudit.geto2HospRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_REQUIREMENT);
    assertThat(testFireElectricalAudit.geto2HospProjectedRequirement())
      .isEqualTo(UPDATED_O_2_HOSP_PROJECTED_REQUIREMENT);
    assertThat(testFireElectricalAudit.getSaveO2RequirementPossibleMT())
      .isEqualTo(UPDATED_SAVE_O_2_REQUIREMENT_POSSIBLE_MT);
    assertThat(testFireElectricalAudit.getMonitoringO2ValvesPort())
      .isEqualTo(UPDATED_MONITORING_O_2_VALVES_PORT);
    assertThat(testFireElectricalAudit.getPortValvesShutDown())
      .isEqualTo(UPDATED_PORT_VALVES_SHUT_DOWN);
    assertThat(testFireElectricalAudit.getIdPatientDrillDone())
      .isEqualTo(UPDATED_ID_PATIENT_DRILL_DONE);
    assertThat(testFireElectricalAudit.getStaffCheckingLeakage())
      .isEqualTo(UPDATED_STAFF_CHECKING_LEAKAGE);
    assertThat(testFireElectricalAudit.getPatientO2ReqFinalized())
      .isEqualTo(UPDATED_PATIENT_O_2_REQ_FINALIZED);
    assertThat(testFireElectricalAudit.getTimeByDoctor())
      .isEqualTo(UPDATED_TIME_BY_DOCTOR);
    assertThat(testFireElectricalAudit.getIsLightingInstalled())
      .isEqualTo(UPDATED_IS_LIGHTING_INSTALLED);
    assertThat(testFireElectricalAudit.getLocLightningArrerstor())
      .isEqualTo(UPDATED_LOC_LIGHTNING_ARRERSTOR);
    assertThat(testFireElectricalAudit.getCreatedBy())
      .isEqualTo(UPDATED_CREATED_BY);
    assertThat(testFireElectricalAudit.getCreatedDate())
      .isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testFireElectricalAudit.getLastModifiedBy())
      .isEqualTo(UPDATED_LAST_MODIFIED_BY);
    assertThat(testFireElectricalAudit.getLastModified())
      .isEqualTo(UPDATED_LAST_MODIFIED);
    assertThat(testFireElectricalAudit.getFreeField1())
      .isEqualTo(UPDATED_FREE_FIELD_1);
    assertThat(testFireElectricalAudit.getFreeField2())
      .isEqualTo(UPDATED_FREE_FIELD_2);
    assertThat(testFireElectricalAudit.getFreeField3())
      .isEqualTo(UPDATED_FREE_FIELD_3);
    assertThat(testFireElectricalAudit.getFreeField4())
      .isEqualTo(UPDATED_FREE_FIELD_4);
  }

  @Test
  @Transactional
  void patchNonExistingFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAuditDTO fireElectricalAuditDTO = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, fireElectricalAuditDTO.getId())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAuditDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithIdMismatchFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAuditDTO fireElectricalAuditDTO = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        patch(ENTITY_API_URL_ID, count.incrementAndGet())
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAuditDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void patchWithMissingIdPathParamFireElectricalAudit() throws Exception {
    int databaseSizeBeforeUpdate = fireElectricalAuditRepository
      .findAll()
      .size();
    fireElectricalAudit.setId(count.incrementAndGet());

    // Create the FireElectricalAudit
    FireElectricalAuditDTO fireElectricalAuditDTO = fireElectricalAuditMapper.toDto(
      fireElectricalAudit
    );

    // If url ID doesn't match entity ID, it will throw BadRequestAlertException
    restFireElectricalAuditMockMvc
      .perform(
        patch(ENTITY_API_URL)
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(fireElectricalAuditDTO))
      )
      .andExpect(status().isMethodNotAllowed());

    // Validate the FireElectricalAudit in the database
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  void deleteFireElectricalAudit() throws Exception {
    // Initialize the database
    fireElectricalAuditRepository.saveAndFlush(fireElectricalAudit);

    int databaseSizeBeforeDelete = fireElectricalAuditRepository
      .findAll()
      .size();

    // Delete the fireElectricalAudit
    restFireElectricalAuditMockMvc
      .perform(
        delete(ENTITY_API_URL_ID, fireElectricalAudit.getId())
          .accept(MediaType.APPLICATION_JSON)
      )
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<FireElectricalAudit> fireElectricalAuditList = fireElectricalAuditRepository.findAll();
    assertThat(fireElectricalAuditList).hasSize(databaseSizeBeforeDelete - 1);
  }
}
