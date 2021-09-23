jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import {
  IFireElectricalAudit,
  FireElectricalAudit,
} from "../fire-electrical-audit.model";
import { FireElectricalAuditService } from "../service/fire-electrical-audit.service";

import { FireElectricalAuditRoutingResolveService } from "./fire-electrical-audit-routing-resolve.service";

describe("Service Tests", () => {
  describe("FireElectricalAudit routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: FireElectricalAuditRoutingResolveService;
    let service: FireElectricalAuditService;
    let resultFireElectricalAudit: IFireElectricalAudit | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        FireElectricalAuditRoutingResolveService
      );
      service = TestBed.inject(FireElectricalAuditService);
      resultFireElectricalAudit = undefined;
    });

    describe("resolve", () => {
      it("should return IFireElectricalAudit returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultFireElectricalAudit = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultFireElectricalAudit).toEqual({ id: 123 });
      });

      it("should return new IFireElectricalAudit if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultFireElectricalAudit = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultFireElectricalAudit).toEqual(new FireElectricalAudit());
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(
              new HttpResponse({ body: null as unknown as FireElectricalAudit })
            )
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultFireElectricalAudit = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultFireElectricalAudit).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
