jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { OxygenConsumptionDataService } from "../service/oxygen-consumption-data.service";
import {
  IOxygenConsumptionData,
  OxygenConsumptionData,
} from "../oxygen-consumption-data.model";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";
import { ITableDetails } from "app/entities/table-details/table-details.model";
import { TableDetailsService } from "app/entities/table-details/service/table-details.service";

import { OxygenConsumptionDataUpdateComponent } from "./oxygen-consumption-data-update.component";

describe("Component Tests", () => {
  describe("OxygenConsumptionData Management Update Component", () => {
    let comp: OxygenConsumptionDataUpdateComponent;
    let fixture: ComponentFixture<OxygenConsumptionDataUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let oxygenConsumptionDataService: OxygenConsumptionDataService;
    let auditService: AuditService;
    let tableDetailsService: TableDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [OxygenConsumptionDataUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(OxygenConsumptionDataUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(OxygenConsumptionDataUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      oxygenConsumptionDataService = TestBed.inject(
        OxygenConsumptionDataService
      );
      auditService = TestBed.inject(AuditService);
      tableDetailsService = TestBed.inject(TableDetailsService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call Audit query and add missing value", () => {
        const oxygenConsumptionData: IOxygenConsumptionData = { id: 456 };
        const audit: IAudit = { id: 78971 };
        oxygenConsumptionData.audit = audit;

        const auditCollection: IAudit[] = [{ id: 24132 }];
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

        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(
          auditCollection,
          ...additionalAudits
        );
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it("Should call TableDetails query and add missing value", () => {
        const oxygenConsumptionData: IOxygenConsumptionData = { id: 456 };
        const tableDetails: ITableDetails = { id: 19667 };
        oxygenConsumptionData.tableDetails = tableDetails;

        const tableDetailsCollection: ITableDetails[] = [{ id: 24678 }];
        jest
          .spyOn(tableDetailsService, "query")
          .mockReturnValue(
            of(new HttpResponse({ body: tableDetailsCollection }))
          );
        const additionalTableDetails = [tableDetails];
        const expectedCollection: ITableDetails[] = [
          ...additionalTableDetails,
          ...tableDetailsCollection,
        ];
        jest
          .spyOn(tableDetailsService, "addTableDetailsToCollectionIfMissing")
          .mockReturnValue(expectedCollection);

        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        expect(tableDetailsService.query).toHaveBeenCalled();
        expect(
          tableDetailsService.addTableDetailsToCollectionIfMissing
        ).toHaveBeenCalledWith(
          tableDetailsCollection,
          ...additionalTableDetails
        );
        expect(comp.tableDetailsSharedCollection).toEqual(expectedCollection);
      });

      it("Should update editForm", () => {
        const oxygenConsumptionData: IOxygenConsumptionData = { id: 456 };
        const audit: IAudit = { id: 74108 };
        oxygenConsumptionData.audit = audit;
        const tableDetails: ITableDetails = { id: 76793 };
        oxygenConsumptionData.tableDetails = tableDetails;

        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(oxygenConsumptionData)
        );
        expect(comp.auditsSharedCollection).toContain(audit);
        expect(comp.tableDetailsSharedCollection).toContain(tableDetails);
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<OxygenConsumptionData>>();
        const oxygenConsumptionData = { id: 123 };
        jest
          .spyOn(oxygenConsumptionDataService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: oxygenConsumptionData }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(oxygenConsumptionDataService.update).toHaveBeenCalledWith(
          oxygenConsumptionData
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<OxygenConsumptionData>>();
        const oxygenConsumptionData = new OxygenConsumptionData();
        jest
          .spyOn(oxygenConsumptionDataService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: oxygenConsumptionData }));
        saveSubject.complete();

        // THEN
        expect(oxygenConsumptionDataService.create).toHaveBeenCalledWith(
          oxygenConsumptionData
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<OxygenConsumptionData>>();
        const oxygenConsumptionData = { id: 123 };
        jest
          .spyOn(oxygenConsumptionDataService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ oxygenConsumptionData });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(oxygenConsumptionDataService.update).toHaveBeenCalledWith(
          oxygenConsumptionData
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

      describe("trackTableDetailsById", () => {
        it("Should return tracked TableDetails primary key", () => {
          const entity = { id: 123 };
          const trackResult = comp.trackTableDetailsById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
