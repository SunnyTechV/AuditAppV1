jest.mock("@ng-bootstrap/ng-bootstrap");

import {
  ComponentFixture,
  TestBed,
  inject,
  fakeAsync,
  tick,
} from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { of } from "rxjs";
import { NgbActiveModal } from "@ng-bootstrap/ng-bootstrap";

import { AuditFormSHospGenInfoService } from "../service/audit-form-s-hosp-gen-info.service";

import { AuditFormSHospGenInfoDeleteDialogComponent } from "./audit-form-s-hosp-gen-info-delete-dialog.component";

describe("Component Tests", () => {
  describe("AuditFormSHospGenInfo Management Delete Component", () => {
    let comp: AuditFormSHospGenInfoDeleteDialogComponent;
    let fixture: ComponentFixture<AuditFormSHospGenInfoDeleteDialogComponent>;
    let service: AuditFormSHospGenInfoService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AuditFormSHospGenInfoDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(AuditFormSHospGenInfoDeleteDialogComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(
        AuditFormSHospGenInfoDeleteDialogComponent
      );
      comp = fixture.componentInstance;
      service = TestBed.inject(AuditFormSHospGenInfoService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
    });

    describe("confirmDelete", () => {
      it("Should call delete service on confirmDelete", inject(
        [],
        fakeAsync(() => {
          // GIVEN
          jest
            .spyOn(service, "delete")
            .mockReturnValue(of(new HttpResponse({})));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.close).toHaveBeenCalledWith("deleted");
        })
      ));

      it("Should not call delete service on clear", () => {
        // GIVEN
        jest.spyOn(service, "delete");

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.close).not.toHaveBeenCalled();
        expect(mockActiveModal.dismiss).toHaveBeenCalled();
      });
    });
  });
});
