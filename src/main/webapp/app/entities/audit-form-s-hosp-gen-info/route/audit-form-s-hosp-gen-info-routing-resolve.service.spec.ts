jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import {
  IAuditFormSHospGenInfo,
  AuditFormSHospGenInfo,
} from "../audit-form-s-hosp-gen-info.model";
import { AuditFormSHospGenInfoService } from "../service/audit-form-s-hosp-gen-info.service";

import { AuditFormSHospGenInfoRoutingResolveService } from "./audit-form-s-hosp-gen-info-routing-resolve.service";

describe("Service Tests", () => {
  describe("AuditFormSHospGenInfo routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: AuditFormSHospGenInfoRoutingResolveService;
    let service: AuditFormSHospGenInfoService;
    let resultAuditFormSHospGenInfo: IAuditFormSHospGenInfo | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        AuditFormSHospGenInfoRoutingResolveService
      );
      service = TestBed.inject(AuditFormSHospGenInfoService);
      resultAuditFormSHospGenInfo = undefined;
    });

    describe("resolve", () => {
      it("should return IAuditFormSHospGenInfo returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditFormSHospGenInfo = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAuditFormSHospGenInfo).toEqual({ id: 123 });
      });

      it("should return new IAuditFormSHospGenInfo if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditFormSHospGenInfo = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultAuditFormSHospGenInfo).toEqual(
          new AuditFormSHospGenInfo()
        );
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(
              new HttpResponse({
                body: null as unknown as AuditFormSHospGenInfo,
              })
            )
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAuditFormSHospGenInfo = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAuditFormSHospGenInfo).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
