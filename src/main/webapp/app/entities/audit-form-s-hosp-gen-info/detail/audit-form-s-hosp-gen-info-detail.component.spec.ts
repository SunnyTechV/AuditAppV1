import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { AuditFormSHospGenInfoDetailComponent } from "./audit-form-s-hosp-gen-info-detail.component";

describe("Component Tests", () => {
  describe("AuditFormSHospGenInfo Management Detail Component", () => {
    let comp: AuditFormSHospGenInfoDetailComponent;
    let fixture: ComponentFixture<AuditFormSHospGenInfoDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [AuditFormSHospGenInfoDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ auditFormSHospGenInfo: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(AuditFormSHospGenInfoDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(AuditFormSHospGenInfoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load auditFormSHospGenInfo on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.auditFormSHospGenInfo).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
