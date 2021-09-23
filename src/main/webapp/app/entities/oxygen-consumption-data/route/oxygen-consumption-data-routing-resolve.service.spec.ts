jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import {
  IOxygenConsumptionData,
  OxygenConsumptionData,
} from "../oxygen-consumption-data.model";
import { OxygenConsumptionDataService } from "../service/oxygen-consumption-data.service";

import { OxygenConsumptionDataRoutingResolveService } from "./oxygen-consumption-data-routing-resolve.service";

describe("Service Tests", () => {
  describe("OxygenConsumptionData routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: OxygenConsumptionDataRoutingResolveService;
    let service: OxygenConsumptionDataService;
    let resultOxygenConsumptionData: IOxygenConsumptionData | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        OxygenConsumptionDataRoutingResolveService
      );
      service = TestBed.inject(OxygenConsumptionDataService);
      resultOxygenConsumptionData = undefined;
    });

    describe("resolve", () => {
      it("should return IOxygenConsumptionData returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultOxygenConsumptionData = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultOxygenConsumptionData).toEqual({ id: 123 });
      });

      it("should return new IOxygenConsumptionData if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultOxygenConsumptionData = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultOxygenConsumptionData).toEqual(
          new OxygenConsumptionData()
        );
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(
              new HttpResponse({
                body: null as unknown as OxygenConsumptionData,
              })
            )
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultOxygenConsumptionData = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultOxygenConsumptionData).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
