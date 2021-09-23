jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { InventoryReportService } from "../service/inventory-report.service";
import { IInventoryReport, InventoryReport } from "../inventory-report.model";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

import { InventoryReportUpdateComponent } from "./inventory-report-update.component";

describe("Component Tests", () => {
  describe("InventoryReport Management Update Component", () => {
    let comp: InventoryReportUpdateComponent;
    let fixture: ComponentFixture<InventoryReportUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let inventoryReportService: InventoryReportService;
    let auditService: AuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [InventoryReportUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(InventoryReportUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(InventoryReportUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      inventoryReportService = TestBed.inject(InventoryReportService);
      auditService = TestBed.inject(AuditService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call Audit query and add missing value", () => {
        const inventoryReport: IInventoryReport = { id: 456 };
        const audit: IAudit = { id: 20088 };
        inventoryReport.audit = audit;

        const auditCollection: IAudit[] = [{ id: 40706 }];
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

        activatedRoute.data = of({ inventoryReport });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(
          auditCollection,
          ...additionalAudits
        );
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it("Should update editForm", () => {
        const inventoryReport: IInventoryReport = { id: 456 };
        const audit: IAudit = { id: 44106 };
        inventoryReport.audit = audit;

        activatedRoute.data = of({ inventoryReport });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(inventoryReport)
        );
        expect(comp.auditsSharedCollection).toContain(audit);
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<InventoryReport>>();
        const inventoryReport = { id: 123 };
        jest
          .spyOn(inventoryReportService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventoryReport });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: inventoryReport }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(inventoryReportService.update).toHaveBeenCalledWith(
          inventoryReport
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<InventoryReport>>();
        const inventoryReport = new InventoryReport();
        jest
          .spyOn(inventoryReportService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventoryReport });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: inventoryReport }));
        saveSubject.complete();

        // THEN
        expect(inventoryReportService.create).toHaveBeenCalledWith(
          inventoryReport
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<InventoryReport>>();
        const inventoryReport = { id: 123 };
        jest
          .spyOn(inventoryReportService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventoryReport });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(inventoryReportService.update).toHaveBeenCalledWith(
          inventoryReport
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
