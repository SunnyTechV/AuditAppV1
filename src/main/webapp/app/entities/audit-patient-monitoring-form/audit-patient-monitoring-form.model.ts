import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";

export interface IAuditPatientMonitoringForm {
  id?: number;
  wardNo?: number | null;
  patientName?: string | null;
  bedNo?: number | null;
  dateOfAdmission?: dayjs.Dayjs | null;
  oxygenType?: string | null;
  sixToEightAM?: number | null;
  oxySixToEightAM?: number | null;
  eightToTenAM?: number | null;
  oxyEightToTenAM?: number | null;
  tenToTwelveAM?: number | null;
  oxyTenToTwelveAM?: number | null;
  twelveToTowPM?: number | null;
  oxyTwelveToTowPM?: number | null;
  twoToFourPM?: number | null;
  oxyTwoToFourPM?: number | null;
  fourToSixPM?: number | null;
  oxyFourToSixPM?: number | null;
  sixToEightPM?: number | null;
  oxySixToEightPM?: number | null;
  eightToTenPM?: number | null;
  oxyEightToTenPM?: number | null;
  tenToTwelvePM?: number | null;
  oxyTenToTwelvePM?: number | null;
  twelveToTwoAM?: number | null;
  oxyTwelveToTwoAM?: number | null;
  twoToFourAM?: number | null;
  oxyTwoToFourAM?: number | null;
  fourToSixAM?: number | null;
  oxyFourToSixAM?: number | null;
  drName?: string | null;
  nurseName?: string | null;
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

export class AuditPatientMonitoringForm implements IAuditPatientMonitoringForm {
  constructor(
    public id?: number,
    public wardNo?: number | null,
    public patientName?: string | null,
    public bedNo?: number | null,
    public dateOfAdmission?: dayjs.Dayjs | null,
    public oxygenType?: string | null,
    public sixToEightAM?: number | null,
    public oxySixToEightAM?: number | null,
    public eightToTenAM?: number | null,
    public oxyEightToTenAM?: number | null,
    public tenToTwelveAM?: number | null,
    public oxyTenToTwelveAM?: number | null,
    public twelveToTowPM?: number | null,
    public oxyTwelveToTowPM?: number | null,
    public twoToFourPM?: number | null,
    public oxyTwoToFourPM?: number | null,
    public fourToSixPM?: number | null,
    public oxyFourToSixPM?: number | null,
    public sixToEightPM?: number | null,
    public oxySixToEightPM?: number | null,
    public eightToTenPM?: number | null,
    public oxyEightToTenPM?: number | null,
    public tenToTwelvePM?: number | null,
    public oxyTenToTwelvePM?: number | null,
    public twelveToTwoAM?: number | null,
    public oxyTwelveToTwoAM?: number | null,
    public twoToFourAM?: number | null,
    public oxyTwoToFourAM?: number | null,
    public fourToSixAM?: number | null,
    public oxyFourToSixAM?: number | null,
    public drName?: string | null,
    public nurseName?: string | null,
    public createdBy?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public audit?: IAudit | null
  ) {}
}

export function getAuditPatientMonitoringFormIdentifier(
  auditPatientMonitoringForm: IAuditPatientMonitoringForm
): number | undefined {
  return auditPatientMonitoringForm.id;
}
