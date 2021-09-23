import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";

export interface IInventorySupplierDetails {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  tableName?: string | null;
  supplierName?: string | null;
  supplierAddress?: string | null;
  supplierContactName?: string | null;
  supplierContactNameNumber?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  audit?: IAudit | null;
}

export class InventorySupplierDetails implements IInventorySupplierDetails {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public tableName?: string | null,
    public supplierName?: string | null,
    public supplierAddress?: string | null,
    public supplierContactName?: string | null,
    public supplierContactNameNumber?: string | null,
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

export function getInventorySupplierDetailsIdentifier(
  inventorySupplierDetails: IInventorySupplierDetails
): number | undefined {
  return inventorySupplierDetails.id;
}
