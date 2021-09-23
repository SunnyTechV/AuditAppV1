import * as dayjs from "dayjs";
import { IOxygenConsumptionData } from "app/entities/oxygen-consumption-data/oxygen-consumption-data.model";

export interface ITableDetails {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  tableName?: string | null;
  description?: string | null;
  descriptionParameter?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  oxygenConsumptionData?: IOxygenConsumptionData[] | null;
}

export class TableDetails implements ITableDetails {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public tableName?: string | null,
    public description?: string | null,
    public descriptionParameter?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public oxygenConsumptionData?: IOxygenConsumptionData[] | null
  ) {}
}

export function getTableDetailsIdentifier(
  tableDetails: ITableDetails
): number | undefined {
  return tableDetails.id;
}
