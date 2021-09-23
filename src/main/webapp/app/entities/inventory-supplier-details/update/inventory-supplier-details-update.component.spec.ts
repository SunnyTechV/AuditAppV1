jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { InventorySupplierDetailsService } from "../service/inventory-supplier-details.service";
import {
  IInventorySupplierDetails,
  InventorySupplierDetails,
} from "../inventory-supplier-details.model";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";

import { InventorySupplierDetailsUpdateComponent } from "./inventory-supplier-details-update.component";

describe("Component Tests", () => {
  describe("InventorySupplierDetails Management Update Component", () => {
    let comp: InventorySupplierDetailsUpdateComponent;
    let fixture: ComponentFixture<InventorySupplierDetailsUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let inventorySupplierDetailsService: InventorySupplierDetailsService;
    let auditService: AuditService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [InventorySupplierDetailsUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(InventorySupplierDetailsUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(
        InventorySupplierDetailsUpdateComponent
      );
      activatedRoute = TestBed.inject(ActivatedRoute);
      inventorySupplierDetailsService = TestBed.inject(
        InventorySupplierDetailsService
      );
      auditService = TestBed.inject(AuditService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call Audit query and add missing value", () => {
        const inventorySupplierDetails: IInventorySupplierDetails = { id: 456 };
        const audit: IAudit = { id: 12466 };
        inventorySupplierDetails.audit = audit;

        const auditCollection: IAudit[] = [{ id: 81662 }];
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

        activatedRoute.data = of({ inventorySupplierDetails });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(
          auditCollection,
          ...additionalAudits
        );
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it("Should update editForm", () => {
        const inventorySupplierDetails: IInventorySupplierDetails = { id: 456 };
        const audit: IAudit = { id: 82639 };
        inventorySupplierDetails.audit = audit;

        activatedRoute.data = of({ inventorySupplierDetails });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(inventorySupplierDetails)
        );
        expect(comp.auditsSharedCollection).toContain(audit);
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<InventorySupplierDetails>
        >();
        const inventorySupplierDetails = { id: 123 };
        jest
          .spyOn(inventorySupplierDetailsService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventorySupplierDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: inventorySupplierDetails }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(inventorySupplierDetailsService.update).toHaveBeenCalledWith(
          inventorySupplierDetails
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<InventorySupplierDetails>
        >();
        const inventorySupplierDetails = new InventorySupplierDetails();
        jest
          .spyOn(inventorySupplierDetailsService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventorySupplierDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: inventorySupplierDetails }));
        saveSubject.complete();

        // THEN
        expect(inventorySupplierDetailsService.create).toHaveBeenCalledWith(
          inventorySupplierDetails
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<
          HttpResponse<InventorySupplierDetails>
        >();
        const inventorySupplierDetails = { id: 123 };
        jest
          .spyOn(inventorySupplierDetailsService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ inventorySupplierDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(inventorySupplierDetailsService.update).toHaveBeenCalledWith(
          inventorySupplierDetails
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
