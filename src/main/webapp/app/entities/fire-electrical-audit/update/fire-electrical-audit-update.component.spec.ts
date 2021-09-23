jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { FireElectricalAuditService } from "../service/fire-electrical-audit.service";
import {
  IFireElectricalAudit,
  FireElectricalAudit,
} from "../fire-electrical-audit.model";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

import { FireElectricalAuditUpdateComponent } from "./fire-electrical-audit-update.component";

describe("Component Tests", () => {
  describe("FireElectricalAudit Management Update Component", () => {
    let comp: FireElectricalAuditUpdateComponent;
    let fixture: ComponentFixture<FireElectricalAuditUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let fireElectricalAuditService: FireElectricalAuditService;
    let auditService: AuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [FireElectricalAuditUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(FireElectricalAuditUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(FireElectricalAuditUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      fireElectricalAuditService = TestBed.inject(FireElectricalAuditService);
      auditService = TestBed.inject(AuditService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call Audit query and add missing value", () => {
        const fireElectricalAudit: IFireElectricalAudit = { id: 456 };
        const audit: IAudit = { id: 17320 };
        fireElectricalAudit.audit = audit;

        const auditCollection: IAudit[] = [{ id: 83841 }];
        jest
          .spyOn(auditService, "query")
          .mockReturnValue(of(new HttpResponse({ body: auditCollection })));
        const additionalAudits = [audit];
        const expectedCollection: IAudit[] = [
          ...additionalAudits,
          ...auditCollection,
        ];
        jest
          .spyOn(auditService, "addAuditToCollectionIfMissing")
          .mockReturnValue(expectedCollection);

        activatedRoute.data = of({ fireElectricalAudit });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(
          auditCollection,
          ...additionalAudits
        );
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it("Should update editForm", () => {
        const fireElectricalAudit: IFireElectricalAudit = { id: 456 };
        const audit: IAudit = { id: 24555 };
        fireElectricalAudit.audit = audit;

        activatedRoute.data = of({ fireElectricalAudit });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(fireElectricalAudit)
        );
        expect(comp.auditsSharedCollection).toContain(audit);
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<FireElectricalAudit>>();
        const fireElectricalAudit = { id: 123 };
        jest
          .spyOn(fireElectricalAuditService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ fireElectricalAudit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: fireElectricalAudit }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(fireElectricalAuditService.update).toHaveBeenCalledWith(
          fireElectricalAudit
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<FireElectricalAudit>>();
        const fireElectricalAudit = new FireElectricalAudit();
        jest
          .spyOn(fireElectricalAuditService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ fireElectricalAudit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: fireElectricalAudit }));
        saveSubject.complete();

        // THEN
        expect(fireElectricalAuditService.create).toHaveBeenCalledWith(
          fireElectricalAudit
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<FireElectricalAudit>>();
        const fireElectricalAudit = { id: 123 };
        jest
          .spyOn(fireElectricalAuditService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ fireElectricalAudit });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(fireElectricalAuditService.update).toHaveBeenCalledWith(
          fireElectricalAudit
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe("Tracking relationships identifiers", () => {
      describe("trackAuditById", () => {
        it("Should return tracked Audit primary key", () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAuditById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
