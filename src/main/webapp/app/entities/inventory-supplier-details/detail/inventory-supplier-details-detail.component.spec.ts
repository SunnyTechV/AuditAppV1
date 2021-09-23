import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { InventorySupplierDetailsDetailComponent } from "./inventory-supplier-details-detail.component";

describe("Component Tests", () => {
  describe("InventorySupplierDetails Management Detail Component", () => {
    let comp: InventorySupplierDetailsDetailComponent;
    let fixture: ComponentFixture<InventorySupplierDetailsDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [InventorySupplierDetailsDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ inventorySupplierDetails: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(InventorySupplierDetailsDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(
        InventorySupplierDetailsDetailComponent
      );
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load inventorySupplierDetails on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.inventorySupplierDetails).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
