import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";

export interface IInventoryReport {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  tableName?: string | null;
  description?: string | null;
  descriptionParameter?: string | null;
  actualAvailable?: string | null;
  remark?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  audit?: IAudit | null;
}

export class InventoryReport implements IInventoryReport {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public tableName?: string | null,
    public description?: string | null,
    public descriptionParameter?: string | null,
    public actualAvailable?: string | null,
    public remark?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public audit?: IAudit | null
  ) {}
}

export function getInventoryReportIdentifier(
  inventoryReport: IInventoryReport
): number | undefined {
  return inventoryReport.id;
}
