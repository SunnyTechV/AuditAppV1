jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import {
  IAuditPatientMonitoringForm,
  AuditPatientMonitoringForm,
} from "../audit-patient-monitoring-form.model";
import { AuditPatientMonitoringFormService } from "../service/audit-patient-monitoring-form.service";

import { AuditPatientMonitoringFormRoutingResolveService } from "./audit-patient-monitoring-form-routing-resolve.service";

describe("Service Tests", () => {
  describe("AuditPatientMonitoringForm routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: AuditPatientMonitoringFormRoutingResolveService;
    let service: AuditPatientMonitoringFormService;
    let resultAuditPatientMonitoringForm:
      | IAuditPatientMonitoringForm
      | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        AuditPatientMonitoringFormRoutingResolveService
      );
      service = TestBed.inject(AuditPatientMonitoringFormService);
      resultAuditPatientMonitoringForm = undefined;
    });

    describe("resolve", () => {
      it("should return IAuditPatientMonitoringForm returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditPatientMonitoringForm = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAuditPatientMonitoringForm).toEqual({ id: 123 });
      });

      it("should return new IAuditPatientMonitoringForm if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditPatientMonitoringForm = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultAuditPatientMonitoringForm).toEqual(
          new AuditPatientMonitoringForm()
        );
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(
              new HttpResponse({
                body: null as unknown as AuditPatientMonitoringForm,
              })
            )
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditPatientMonitoringForm = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAuditPatientMonitoringForm).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
