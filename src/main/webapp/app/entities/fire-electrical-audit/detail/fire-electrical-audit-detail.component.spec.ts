import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { FireElectricalAuditDetailComponent } from "./fire-electrical-audit-detail.component";

describe("Component Tests", () => {
  describe("FireElectricalAudit Management Detail Component", () => {
    let comp: FireElectricalAuditDetailComponent;
    let fixture: ComponentFixture<FireElectricalAuditDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [FireElectricalAuditDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ fireElectricalAudit: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(FireElectricalAuditDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(FireElectricalAuditDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load fireElectricalAudit on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fireElectricalAudit).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
