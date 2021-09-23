import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { InventoryReportDetailComponent } from "./inventory-report-detail.component";

describe("Component Tests", () => {
  describe("InventoryReport Management Detail Component", () => {
    let comp: InventoryReportDetailComponent;
    let fixture: ComponentFixture<InventoryReportDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [InventoryReportDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ inventoryReport: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(InventoryReportDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(InventoryReportDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load inventoryReport on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.inventoryReport).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
