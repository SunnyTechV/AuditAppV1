import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";

export interface IFireElectricalAudit {
  id?: number;
  fireAuditDoneOrnot?: boolean | null;
  fireAuditDate?: dayjs.Dayjs | null;
  fireFaults?: string | null;
  fireCorrectiveAction?: string | null;
  fireAuditPlan?: string | null;
  electricalAuditDone?: boolean | null;
  electricalAuditDate?: dayjs.Dayjs | null;
  electricalFaults?: string | null;
  electricalCorrectiveAction?: string | null;
  electricalAuditInspection?: string | null;
  technicalPersonAppoint?: boolean | null;
  techPersonname?: string | null;
  techPersonMobNo?: string | null;
  technicalEngineerName?: string | null;
  technicalEngineerAddress?: string | null;
  technicalEngineerMob?: string | null;
  technicalEngineerAlternateMob?: string | null;
  o2HospRequirement?: number | null;
  o2HospProjectedRequirement?: number | null;
  saveO2RequirementPossibleMT?: number | null;
  monitoringO2ValvesPort?: boolean | null;
  portValvesShutDown?: boolean | null;
  idPatientDrillDone?: boolean | null;
  staffCheckingLeakage?: boolean | null;
  patientO2ReqFinalized?: boolean | null;
  timeByDoctor?: string | null;
  isLightingInstalled?: boolean | null;
  locLightningArrerstor?: string | null;
  createdBy?: string | null;
  createdDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  audit?: IAudit | null;
}

export class FireElectricalAudit implements IFireElectricalAudit {
  constructor(
    public id?: number,
    public fireAuditDoneOrnot?: boolean | null,
    public fireAuditDate?: dayjs.Dayjs | null,
    public fireFaults?: string | null,
    public fireCorrectiveAction?: string | null,
    public fireAuditPlan?: string | null,
    public electricalAuditDone?: boolean | null,
    public electricalAuditDate?: dayjs.Dayjs | null,
    public electricalFaults?: string | null,
    public electricalCorrectiveAction?: string | null,
    public electricalAuditInspection?: string | null,
    public technicalPersonAppoint?: boolean | null,
    public techPersonname?: string | null,
    public techPersonMobNo?: string | null,
    public technicalEngineerName?: string | null,
    public technicalEngineerAddress?: string | null,
    public technicalEngineerMob?: string | null,
    public technicalEngineerAlternateMob?: string | null,
    public o2HospRequirement?: number | null,
    public o2HospProjectedRequirement?: number | null,
    public saveO2RequirementPossibleMT?: number | null,
    public monitoringO2ValvesPort?: boolean | null,
    public portValvesShutDown?: boolean | null,
    public idPatientDrillDone?: boolean | null,
    public staffCheckingLeakage?: boolean | null,
    public patientO2ReqFinalized?: boolean | null,
    public timeByDoctor?: string | null,
    public isLightingInstalled?: boolean | null,
    public locLightningArrerstor?: string | null,
    public createdBy?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public audit?: IAudit | null
  ) {
    this.fireAuditDoneOrnot = this.fireAuditDoneOrnot ?? false;
    this.electricalAuditDone = this.electricalAuditDone ?? false;
    this.technicalPersonAppoint = this.technicalPersonAppoint ?? false;
    this.monitoringO2ValvesPort = this.monitoringO2ValvesPort ?? false;
    this.portValvesShutDown = this.portValvesShutDown ?? false;
    this.idPatientDrillDone = this.idPatientDrillDone ?? false;
    this.staffCheckingLeakage = this.staffCheckingLeakage ?? false;
    this.patientO2ReqFinalized = this.patientO2ReqFinalized ?? false;
    this.isLightingInstalled = this.isLightingInstalled ?? false;
  }
}

export function getFireElectricalAuditIdentifier(
  fireElectricalAudit: IFireElectricalAudit
): number | undefined {
  return fireElectricalAudit.id;
}
