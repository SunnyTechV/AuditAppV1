jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import { ITableDetails, TableDetails } from "../table-details.model";
import { TableDetailsService } from "../service/table-details.service";

import { TableDetailsRoutingResolveService } from "./table-details-routing-resolve.service";

describe("Service Tests", () => {
  describe("TableDetails routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: TableDetailsRoutingResolveService;
    let service: TableDetailsService;
    let resultTableDetails: ITableDetails | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(TableDetailsRoutingResolveService);
      service = TestBed.inject(TableDetailsService);
      resultTableDetails = undefined;
    });

    describe("resolve", () => {
      it("should return ITableDetails returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultTableDetails = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTableDetails).toEqual({ id: 123 });
      });

      it("should return new ITableDetails if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultTableDetails = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultTableDetails).toEqual(new TableDetails());
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(new HttpResponse({ body: null as unknown as TableDetails }))
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultTableDetails = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultTableDetails).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
