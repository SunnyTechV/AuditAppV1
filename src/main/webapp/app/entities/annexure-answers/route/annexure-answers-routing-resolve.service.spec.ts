jest.mock("@angular/router");

import { TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRouteSnapshot, Router } from "@angular/router";
import { of } from "rxjs";

import { IAnnexureAnswers, AnnexureAnswers } from "../annexure-answers.model";
import { AnnexureAnswersService } from "../service/annexure-answers.service";

import { AnnexureAnswersRoutingResolveService } from "./annexure-answers-routing-resolve.service";

describe("Service Tests", () => {
  describe("AnnexureAnswers routing resolve service", () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: AnnexureAnswersRoutingResolveService;
    let service: AnnexureAnswersService;
    let resultAnnexureAnswers: IAnnexureAnswers | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(
        AnnexureAnswersRoutingResolveService
      );
      service = TestBed.inject(AnnexureAnswersService);
      resultAnnexureAnswers = undefined;
    });

    describe("resolve", () => {
      it("should return IAnnexureAnswers returned by find", () => {
        // GIVEN
        service.find = jest.fn((id) => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAnnexureAnswers = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAnnexureAnswers).toEqual({ id: 123 });
      });

      it("should return new IAnnexureAnswers if id is not provided", () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAnnexureAnswers = result;
          });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultAnnexureAnswers).toEqual(new AnnexureAnswers());
      });

      it("should route to 404 page if data not found in server", () => {
        // GIVEN
        jest
          .spyOn(service, "find")
          .mockReturnValue(
            of(new HttpResponse({ body: null as unknown as AnnexureAnswers }))
          );
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService
          .resolve(mockActivatedRouteSnapshot)
          .subscribe((result) => {
            resultAnnexureAnswers = result;
          });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultAnnexureAnswers).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(["404"]);
      });
    });
  });
});
