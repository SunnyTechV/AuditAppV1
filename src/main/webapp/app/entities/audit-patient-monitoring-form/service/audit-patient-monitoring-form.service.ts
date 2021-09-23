import { Injectable } from "@angular/core";
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { map } from "rxjs/operators";
import * as dayjs from "dayjs";

import { isPresent } from "app/core/util/operators";
import { DATE_FORMAT } from "app/config/input.constants";
import { ApplicationConfigService } from "app/core/config/application-config.service";
import { createRequestOption } from "app/core/request/request-util";
import {
  IAuditPatientMonitoringForm,
  getAuditPatientMonitoringFormIdentifier,
} from "../audit-patient-monitoring-form.model";

export type EntityResponseType = HttpResponse<IAuditPatientMonitoringForm>;
export type EntityArrayResponseType = HttpResponse<
  IAuditPatientMonitoringForm[]
>;

@Injectable({ providedIn: "root" })
export class AuditPatientMonitoringFormService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor(
    "api/audit-patient-monitoring-forms"
  );

  constructor(
    protected http: HttpClient,
    protected applicationConfigService: ApplicationConfigService
  ) {}

  create(
    auditPatientMonitoringForm: IAuditPatientMonitoringForm
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditPatientMonitoringForm);
    return this.http
      .post<IAuditPatientMonitoringForm>(this.resourceUrl, copy, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(
    auditPatientMonitoringForm: IAuditPatientMonitoringForm
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditPatientMonitoringForm);
    return this.http
      .put<IAuditPatientMonitoringForm>(
        `${this.resourceUrl}/${
          getAuditPatientMonitoringFormIdentifier(
            auditPatientMonitoringForm
          ) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(
    auditPatientMonitoringForm: IAuditPatientMonitoringForm
  ): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(auditPatientMonitoringForm);
    return this.http
      .patch<IAuditPatientMonitoringForm>(
        `${this.resourceUrl}/${
          getAuditPatientMonitoringFormIdentifier(
            auditPatientMonitoringForm
          ) as number
        }`,
        copy,
        { observe: "response" }
      )
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAuditPatientMonitoringForm>(`${this.resourceUrl}/${id}`, {
        observe: "response",
      })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAuditPatientMonitoringForm[]>(this.resourceUrl, {
        params: options,
        observe: "response",
      })
      .pipe(
        map((res: EntityArrayResponseType) =>
          this.convertDateArrayFromServer(res)
        )
      );
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, {
      observe: "response",
    });
  }

  addAuditPatientMonitoringFormToCollectionIfMissing(
    auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[],
    ...auditPatientMonitoringFormsToCheck: (
      | IAuditPatientMonitoringForm
      | null
      | undefined
    )[]
  ): IAuditPatientMonitoringForm[] {
    const auditPatientMonitoringForms: IAuditPatientMonitoringForm[] =
      auditPatientMonitoringFormsToCheck.filter(isPresent);
    if (auditPatientMonitoringForms.length > 0) {
      const auditPatientMonitoringFormCollectionIdentifiers =
        auditPatientMonitoringFormCollection.map(
          (auditPatientMonitoringFormItem) =>
            getAuditPatientMonitoringFormIdentifier(
              auditPatientMonitoringFormItem
            )!
        );
      const auditPatientMonitoringFormsToAdd =
        auditPatientMonitoringForms.filter((auditPatientMonitoringFormItem) => {
          const auditPatientMonitoringFormIdentifier =
            getAuditPatientMonitoringFormIdentifier(
              auditPatientMonitoringFormItem
            );
          if (
            auditPatientMonitoringFormIdentifier == null ||
            auditPatientMonitoringFormCollectionIdentifiers.includes(
              auditPatientMonitoringFormIdentifier
            )
          ) {
            return false;
          }
          auditPatientMonitoringFormCollectionIdentifiers.push(
            auditPatientMonitoringFormIdentifier
          );
          return true;
        });
      return [
        ...auditPatientMonitoringFormsToAdd,
        ...auditPatientMonitoringFormCollection,
      ];
    }
    return auditPatientMonitoringFormCollection;
  }

  protected convertDateFromClient(
    auditPatientMonitoringForm: IAuditPatientMonitoringForm
  ): IAuditPatientMonitoringForm {
    return Object.assign({}, auditPatientMonitoringForm, {
      dateOfAdmission: auditPatientMonitoringForm.dateOfAdmission?.isValid()
        ? auditPatientMonitoringForm.dateOfAdmission.format(DATE_FORMAT)
        : undefined,
      createdDate: auditPatientMonitoringForm.createdDate?.isValid()
        ? auditPatientMonitoringForm.createdDate.format(DATE_FORMAT)
        : undefined,
      lastModified: auditPatientMonitoringForm.lastModified?.isValid()
        ? auditPatientMonitoringForm.lastModified.format(DATE_FORMAT)
        : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateOfAdmission = res.body.dateOfAdmission
        ? dayjs(res.body.dateOfAdmission)
        : undefined;
      res.body.createdDate = res.body.createdDate
        ? dayjs(res.body.createdDate)
        : undefined;
      res.body.lastModified = res.body.lastModified
        ? dayjs(res.body.lastModified)
        : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(
    res: EntityArrayResponseType
  ): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach(
        (auditPatientMonitoringForm: IAuditPatientMonitoringForm) => {
          auditPatientMonitoringForm.dateOfAdmission =
            auditPatientMonitoringForm.dateOfAdmission
              ? dayjs(auditPatientMonitoringForm.dateOfAdmission)
              : undefined;
          auditPatientMonitoringForm.createdDate =
            auditPatientMonitoringForm.createdDate
              ? dayjs(auditPatientMonitoringForm.createdDate)
              : undefined;
          auditPatientMonitoringForm.lastModified =
            auditPatientMonitoringForm.lastModified
              ? dayjs(auditPatientMonitoringForm.lastModified)
              : undefined;
        }
      );
    }
    return res;
  }
}
