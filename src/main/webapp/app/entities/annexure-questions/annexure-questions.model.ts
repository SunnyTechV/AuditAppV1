import * as dayjs from "dayjs";
import { IAnnexureAnswers } from "app/entities/annexure-answers/annexure-answers.model";

export interface IAnnexureQuestions {
  id?: number;
  formName?: string | null;
  type?: string | null;
  subType?: string | null;
  description?: string | null;
  freeField1?: string | null;
  freeField2?: string | null;
  freeField3?: string | null;
  freeField4?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModified?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
  annexureAnswers?: IAnnexureAnswers[] | null;
}

export class AnnexureQuestions implements IAnnexureQuestions {
  constructor(
    public id?: number,
    public formName?: string | null,
    public type?: string | null,
    public subType?: string | null,
    public description?: string | null,
    public freeField1?: string | null,
    public freeField2?: string | null,
    public freeField3?: string | null,
    public freeField4?: string | null,
    public createdDate?: dayjs.Dayjs | null,
    public createdBy?: string | null,
    public lastModified?: dayjs.Dayjs | null,
    public lastModifiedBy?: string | null,
    public annexureAnswers?: IAnnexureAnswers[] | null
  ) {}
}

export function getAnnexureQuestionsIdentifier(
  annexureQuestions: IAnnexureQuestions
): number | undefined {
  return annexureQuestions.id;
}
