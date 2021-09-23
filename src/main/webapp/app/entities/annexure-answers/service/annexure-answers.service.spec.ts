import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import { IAnnexureAnswers, AnnexureAnswers } from "../annexure-answers.model";

import { AnnexureAnswersService } from "./annexure-answers.service";

describe("Service Tests", () => {
  describe("AnnexureAnswers Service", () => {
    let service: AnnexureAnswersService;
    let httpMock: HttpTestingController;
    let elemDefault: IAnnexureAnswers;
    let expectedResult: IAnnexureAnswers | IAnnexureAnswers[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AnnexureAnswersService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        compliance: false,
        comment: "AAAAAAA",
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
        freeField4: "AAAAAAA",
        remark: "AAAAAAA",
        createdDate: currentDate,
        createdBy: "AAAAAAA",
        lastModified: currentDate,
        lastModifiedBy: "AAAAAAA",
      };
    });

    describe("Service methods", () => {
      it("should find an element", () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "GET" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it("should create a AnnexureAnswers", () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .create(new AnnexureAnswers())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a AnnexureAnswers", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            compliance: true,
            comment: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
            remark: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .update(expected)
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "PUT" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should partial update a AnnexureAnswers", () => {
        const patchObject = Object.assign(
          {
            formName: "BBBBBB",
            subType: "BBBBBB",
            comment: "BBBBBB",
            freeField2: "BBBBBB",
            freeField4: "BBBBBB",
            lastModifiedBy: "BBBBBB",
          },
          new AnnexureAnswers()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .partialUpdate(patchObject)
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "PATCH" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should return a list of AnnexureAnswers", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            compliance: true,
            comment: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
            remark: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "GET" });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it("should delete a AnnexureAnswers", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addAnnexureAnswersToCollectionIfMissing", () => {
        it("should add a AnnexureAnswers to an empty array", () => {
          const annexureAnswers: IAnnexureAnswers = { id: 123 };
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            [],
            annexureAnswers
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(annexureAnswers);
        });

        it("should not add a AnnexureAnswers to an array that contains it", () => {
          const annexureAnswers: IAnnexureAnswers = { id: 123 };
          const annexureAnswersCollection: IAnnexureAnswers[] = [
            {
              ...annexureAnswers,
            },
            { id: 456 },
          ];
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            annexureAnswersCollection,
            annexureAnswers
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a AnnexureAnswers to an array that doesn't contain it", () => {
          const annexureAnswers: IAnnexureAnswers = { id: 123 };
          const annexureAnswersCollection: IAnnexureAnswers[] = [{ id: 456 }];
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            annexureAnswersCollection,
            annexureAnswers
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(annexureAnswers);
        });

        it("should add only unique AnnexureAnswers to an array", () => {
          const annexureAnswersArray: IAnnexureAnswers[] = [
            { id: 123 },
            { id: 456 },
            { id: 94210 },
          ];
          const annexureAnswersCollection: IAnnexureAnswers[] = [{ id: 123 }];
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            annexureAnswersCollection,
            ...annexureAnswersArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const annexureAnswers: IAnnexureAnswers = { id: 123 };
          const annexureAnswers2: IAnnexureAnswers = { id: 456 };
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            [],
            annexureAnswers,
            annexureAnswers2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(annexureAnswers);
          expect(expectedResult).toContain(annexureAnswers2);
        });

        it("should accept null and undefined values", () => {
          const annexureAnswers: IAnnexureAnswers = { id: 123 };
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            [],
            null,
            annexureAnswers,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(annexureAnswers);
        });

        it("should return initial array if no AnnexureAnswers is added", () => {
          const annexureAnswersCollection: IAnnexureAnswers[] = [{ id: 123 }];
          expectedResult = service.addAnnexureAnswersToCollectionIfMissing(
            annexureAnswersCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(annexureAnswersCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
