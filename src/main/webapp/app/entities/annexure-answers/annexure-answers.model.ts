import * as dayjs from "dayjs";
import { IAudit } from "app/entities/audit/audit.model";
import { IAnnexureQuestions } from "app/entities/annexure-questions/annexure-questions.model";

export interface IAnnexureAnswers {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  compliance?: boolean | null;
  comment?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  remark?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  audit?: IAudit | null;
  annexureQuestions?: IAnnexureQuestions | null;
}

export class AnnexureAnswers implements IAnnexureAnswers {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public compliance?: boolean | null,
    public comment?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public remark?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public audit?: IAudit | null,
    public annexureQuestions?: IAnnexureQuestions | null
  ) {
    this.compliance = this.compliance ?? false;
  }
}

export function getAnnexureAnswersIdentifier(
  annexureAnswers: IAnnexureAnswers
): number | undefined {
  return annexureAnswers.id;
}
