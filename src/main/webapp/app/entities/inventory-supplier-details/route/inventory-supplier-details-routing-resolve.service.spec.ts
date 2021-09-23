jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import {
  IInventorySupplierDetails,
  InventorySupplierDetails,
} from "../inventory-supplier-details.model";
import { InventorySupplierDetailsService } from "../service/inventory-supplier-details.service";

import { InventorySupplierDetailsRoutingResolveService } from "./inventory-supplier-details-routing-resolve.service";

describe("Service Tests", () => {
  describe("InventorySupplierDetails routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: InventorySupplierDetailsRoutingResolveService;
    let service: InventorySupplierDetailsService;
    let resultInventorySupplierDetails: IInventorySupplierDetails | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        InventorySupplierDetailsRoutingResolveService
      );
      service = TestBed.inject(InventorySupplierDetailsService);
      resultInventorySupplierDetails = undefined;
    });

    describe("resolve", () => {
      it("should return IInventorySupplierDetails returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventorySupplierDetails = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultInventorySupplierDetails).toEqual({ id: 123 });
      });

      it("should return new IInventorySupplierDetails if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventorySupplierDetails = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultInventorySupplierDetails).toEqual(
          new InventorySupplierDetails()
        );
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(
              new HttpResponse({
                body: null as unknown as InventorySupplierDetails,
              })
            )
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultInventorySupplierDetails = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultInventorySupplierDetails).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
