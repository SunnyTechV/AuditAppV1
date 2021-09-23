import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";
import { ITableDetails } from "app/entities/table-details/table-details.model";

export interface IOxygenConsumptionData {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  tableName?: string | null;
  noofPatients?: number | null;
  consumptionLitperMin?: number | null;
  consumptionLitperDay?: number | null;
  consumptionKLitperDay?: number | null;
  consumptionTotal?: number | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  audit?: IAudit | null;
  tableDetails?: ITableDetails | null;
}

export class OxygenConsumptionData implements IOxygenConsumptionData {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public tableName?: string | null,
    public noofPatients?: number | null,
    public consumptionLitperMin?: number | null,
    public consumptionLitperDay?: number | null,
    public consumptionKLitperDay?: number | null,
    public consumptionTotal?: number | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public audit?: IAudit | null,
    public tableDetails?: ITableDetails | null
  ) {}
}

export function getOxygenConsumptionDataIdentifier(
  oxygenConsumptionData: IOxygenConsumptionData
): number | undefined {
  return oxygenConsumptionData.id;
}
