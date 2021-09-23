import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { OxygenConsumptionDataDetailComponent } from "./oxygen-consumption-data-detail.component";

describe("Component Tests", () => {
  describe("OxygenConsumptionData Management Detail Component", () => {
    let comp: OxygenConsumptionDataDetailComponent;
    let fixture: ComponentFixture<OxygenConsumptionDataDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [OxygenConsumptionDataDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ oxygenConsumptionData: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(OxygenConsumptionDataDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(OxygenConsumptionDataDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load oxygenConsumptionData on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.oxygenConsumptionData).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
