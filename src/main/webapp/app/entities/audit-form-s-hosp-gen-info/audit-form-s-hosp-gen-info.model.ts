import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";

export interface IAuditFormSHospGenInfo {
  id?: number;
  hospName?: string | null;
  hospType?: string | null;
  type?: string | null;
  subType?: string | null;
  formName?: string | null;
  inchargeName?: string | null;
  hospAddress?: string | null;
  hospPhoneNo?: string | null;
  normalBeds?: number | null;
  oxygenBeds?: number | null;
  ventilatorBeds?: number | null;
  icuBeds?: number | null;
  onCylinderPatient?: number | null;
  onPipedBedsPatient?: number | null;
  onNIV?: number | null;
  onIntubated?: number | null;
  jumboSystemInstalledType?: string | null;
  availableCylinderTypeD7?: number | null;
  availableCylinderTypeB?: number | null;
  cylinderAgencyName?: string | null;
  cylinderAgencyAddress?: string | null;
  availableLMOCapacityKL?: number | null;
  lmoSupplierName?: string | null;
  lmoSupplierFrequency?: number | null;
  lastLmoSuppliedQuantity?: number | null;
  remark?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  audit?: IAudit | null;
}

export class AuditFormSHospGenInfo implements IAuditFormSHospGenInfo {
  constructor(
    public id?: number,
    public hospName?: string | null,
    public hospType?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public formName?: string | null,
    public inchargeName?: string | null,
    public hospAddress?: string | null,
    public hospPhoneNo?: string | null,
    public normalBeds?: number | null,
    public oxygenBeds?: number | null,
    public ventilatorBeds?: number | null,
    public icuBeds?: number | null,
    public onCylinderPatient?: number | null,
    public onPipedBedsPatient?: number | null,
    public onNIV?: number | null,
    public onIntubated?: number | null,
    public jumboSystemInstalledType?: string | null,
    public availableCylinderTypeD7?: number | null,
    public availableCylinderTypeB?: number | null,
    public cylinderAgencyName?: string | null,
    public cylinderAgencyAddress?: string | null,
    public availableLMOCapacityKL?: number | null,
    public lmoSupplierName?: string | null,
    public lmoSupplierFrequency?: number | null,
    public lastLmoSuppliedQuantity?: number | null,
    public remark?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public audit?: IAudit | null
  ) {}
}

export function getAuditFormSHospGenInfoIdentifier(
  auditFormSHospGenInfo: IAuditFormSHospGenInfo
): number | undefined {
  return auditFormSHospGenInfo.id;
}
