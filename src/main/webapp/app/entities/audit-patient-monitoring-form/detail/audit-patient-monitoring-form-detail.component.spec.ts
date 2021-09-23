import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { AuditPatientMonitoringFormDetailComponent } from "./audit-patient-monitoring-form-detail.component";

describe("Component Tests", () => {
  describe("AuditPatientMonitoringForm Management Detail Component", () => {
    let comp: AuditPatientMonitoringFormDetailComponent;
    let fixture: ComponentFixture<AuditPatientMonitoringFormDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [AuditPatientMonitoringFormDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ auditPatientMonitoringForm: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(AuditPatientMonitoringFormDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(
        AuditPatientMonitoringFormDetailComponent
      );
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load auditPatientMonitoringForm on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.auditPatientMonitoringForm).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
