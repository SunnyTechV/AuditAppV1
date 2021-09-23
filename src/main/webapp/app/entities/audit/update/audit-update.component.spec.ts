jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { AuditService } from "../service/audit.service";
import { IAudit, Audit } from "../audit.model";
import { IAuditPatientMonitoringForm } from "app/entities/audit-patient-monitoring-form/audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "app/entities/audit-patient-monitoring-form/service/audit-patient-monitoring-form.service";

import { AuditUpdateComponent } from "./audit-update.component";

describe("Component Tests", () => {
  describe("Audit Management Update Component", () => {
    let comp: AuditUpdateComponent;
    let fixture: ComponentFixture<AuditUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let auditService: AuditService;
    let auditPatientMonitoringFormService: AuditPatientMonitoringFormService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AuditUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AuditUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(AuditUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      auditService = TestBed.inject(AuditService);
      auditPatientMonitoringFormService = TestBed.inject(
        AuditPatientMonitoringFormService
      );

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call auditPatientMonitoringForm query and add missing value", () => {
        const audit: IAudit = { id: 456 };
        const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
          id: 77127,
        };
        audit.auditPatientMonitoringForm = auditPatientMonitoringForm;

        const auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[] =
          [{ id: 67379 }];
        jest
          .spyOn(auditPatientMonitoringFormService, "query")
          .mockReturnValue(
            of(new HttpResponse({ body: auditPatientMonitoringFormCollection }))
          );
        const expectedCollection: IAuditPatientMonitoringForm[] = [
          auditPatientMonitoringForm,
          ...auditPatientMonitoringFormCollection,
        ];
        jest
          .spyOn(
            auditPatientMonitoringFormService,
            "addAuditPatientMonitoringFormToCollectionIfMissing"
          )
          .mockReturnValue(expectedCollection);

        activatedRoute.data = of({ audit });
        comp.ngOnInit();

        expect(auditPatientMonitoringFormService.query).toHaveBeenCalled();
        expect(
          auditPatientMonitoringFormService.addAuditPatientMonitoringFormToCollectionIfMissing
        ).toHaveBeenCalledWith(
          auditPatientMonitoringFormCollection,
          auditPatientMonitoringForm
        );
        expect(comp.auditPatientMonitoringFormsCollection).toEqual(
          expectedCollection
        );
      });

      it("Should update editForm", () => {
        const audit: IAudit = { id: 456 };
        const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
          id: 29350,
        };
        audit.auditPatientMonitoringForm = auditPatientMonitoringForm;

        activatedRoute.data = of({ audit });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(audit));
        expect(comp.auditPatientMonitoringFormsCollection).toContain(
          auditPatientMonitoringForm
        );
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Audit>>();
        const audit = { id: 123 };
        jest.spyOn(auditService, "update").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ audit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: audit }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(auditService.update).toHaveBeenCalledWith(audit);
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Audit>>();
        const audit = new Audit();
        jest.spyOn(auditService, "create").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ audit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: audit }));
        saveSubject.complete();

        // THEN
        expect(auditService.create).toHaveBeenCalledWith(audit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<Audit>>();
        const audit = { id: 123 };
        jest.spyOn(auditService, "update").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ audit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(auditService.update).toHaveBeenCalledWith(audit);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe("Tracking relationships identifiers", () => {
      describe("trackAuditPatientMonitoringFormById", () => {
        it("Should return tracked AuditPatientMonitoringForm primary key", () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAuditPatientMonitoringFormById(
            0,
            entity
          );
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
