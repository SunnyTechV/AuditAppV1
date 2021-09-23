jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import { IInventoryReport, InventoryReport } from "../inventory-report.model";
import { InventoryReportService } from "../service/inventory-report.service";

import { InventoryReportRoutingResolveService } from "./inventory-report-routing-resolve.service";

describe("Service Tests", () => {
  describe("InventoryReport routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: InventoryReportRoutingResolveService;
    let service: InventoryReportService;
    let resultInventoryReport: IInventoryReport | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        InventoryReportRoutingResolveService
      );
      service = TestBed.inject(InventoryReportService);
      resultInventoryReport = undefined;
    });

    describe("resolve", () => {
      it("should return IInventoryReport returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventoryReport = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultInventoryReport).toEqual({ id: 123 });
      });

      it("should return new IInventoryReport if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventoryReport = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultInventoryReport).toEqual(new InventoryReport());
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(new HttpResponse({ body: null as unknown as InventoryReport }))
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventoryReport = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultInventoryReport).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
