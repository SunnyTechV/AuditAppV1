jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { AuditPatientMonitoringFormService } from "../service/audit-patient-monitoring-form.service";
import {
  IAuditPatientMonitoringForm,
  AuditPatientMonitoringForm,
} from "../audit-patient-monitoring-form.model";

import { AuditPatientMonitoringFormUpdateComponent } from "./audit-patient-monitoring-form-update.component";

describe("Component Tests", () => {
  describe("AuditPatientMonitoringForm Management Update Component", () => {
    let comp: AuditPatientMonitoringFormUpdateComponent;
    let fixture: ComponentFixture<AuditPatientMonitoringFormUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let auditPatientMonitoringFormService: AuditPatientMonitoringFormService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AuditPatientMonitoringFormUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AuditPatientMonitoringFormUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(
        AuditPatientMonitoringFormUpdateComponent
      );
      activatedRoute = TestBed.inject(ActivatedRoute);
      auditPatientMonitoringFormService = TestBed.inject(
        AuditPatientMonitoringFormService
      );

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should update editForm", () => {
        const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
          id: 456,
        };

        activatedRoute.data = of({ auditPatientMonitoringForm });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(auditPatientMonitoringForm)
        );
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<AuditPatientMonitoringForm>
        >();
        const auditPatientMonitoringForm = { id: 123 };
        jest
          .spyOn(auditPatientMonitoringFormService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ auditPatientMonitoringForm });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(
          new HttpResponse({ body: auditPatientMonitoringForm })
        );
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(auditPatientMonitoringFormService.update).toHaveBeenCalledWith(
          auditPatientMonitoringForm
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<AuditPatientMonitoringForm>
        >();
        const auditPatientMonitoringForm = new AuditPatientMonitoringForm();
        jest
          .spyOn(auditPatientMonitoringFormService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ auditPatientMonitoringForm });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(
          new HttpResponse({ body: auditPatientMonitoringForm })
        );
        saveSubject.complete();

        // THEN
        expect(auditPatientMonitoringFormService.create).toHaveBeenCalledWith(
          auditPatientMonitoringForm
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<AuditPatientMonitoringForm>
        >();
        const auditPatientMonitoringForm = { id: 123 };
        jest
          .spyOn(auditPatientMonitoringFormService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ auditPatientMonitoringForm });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(auditPatientMonitoringFormService.update).toHaveBeenCalledWith(
          auditPatientMonitoringForm
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
