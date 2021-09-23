import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IAnnexureQuestions,
  AnnexureQuestions,
} from "../annexure-questions.model";

import { AnnexureQuestionsService } from "./annexure-questions.service";

describe("Service Tests", () => {
  describe("AnnexureQuestions Service", () => {
    let service: AnnexureQuestionsService;
    let httpMock: HttpTestingController;
    let elemDefault: IAnnexureQuestions;
    let expectedResult:
      | IAnnexureQuestions
      | IAnnexureQuestions[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AnnexureQuestionsService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        description: "AAAAAAA",
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
        freeField4: "AAAAAAA",
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

      it("should create a AnnexureQuestions", () => {
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
          .create(new AnnexureQuestions())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a AnnexureQuestions", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            description: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
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

      it("should partial update a AnnexureQuestions", () => {
        const patchObject = Object.assign(
          {
            subType: "BBBBBB",
            description: "BBBBBB",
            freeField1: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModifiedBy: "BBBBBB",
          },
          new AnnexureQuestions()
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

      it("should return a list of AnnexureQuestions", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            description: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
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

      it("should delete a AnnexureQuestions", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addAnnexureQuestionsToCollectionIfMissing", () => {
        it("should add a AnnexureQuestions to an empty array", () => {
          const annexureQuestions: IAnnexureQuestions = { id: 123 };
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            [],
            annexureQuestions
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(annexureQuestions);
        });

        it("should not add a AnnexureQuestions to an array that contains it", () => {
          const annexureQuestions: IAnnexureQuestions = { id: 123 };
          const annexureQuestionsCollection: IAnnexureQuestions[] = [
            {
              ...annexureQuestions,
            },
            { id: 456 },
          ];
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            annexureQuestionsCollection,
            annexureQuestions
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a AnnexureQuestions to an array that doesn't contain it", () => {
          const annexureQuestions: IAnnexureQuestions = { id: 123 };
          const annexureQuestionsCollection: IAnnexureQuestions[] = [
            { id: 456 },
          ];
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            annexureQuestionsCollection,
            annexureQuestions
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(annexureQuestions);
        });

        it("should add only unique AnnexureQuestions to an array", () => {
          const annexureQuestionsArray: IAnnexureQuestions[] = [
            { id: 123 },
            { id: 456 },
            { id: 92570 },
          ];
          const annexureQuestionsCollection: IAnnexureQuestions[] = [
            { id: 123 },
          ];
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            annexureQuestionsCollection,
            ...annexureQuestionsArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const annexureQuestions: IAnnexureQuestions = { id: 123 };
          const annexureQuestions2: IAnnexureQuestions = { id: 456 };
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            [],
            annexureQuestions,
            annexureQuestions2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(annexureQuestions);
          expect(expectedResult).toContain(annexureQuestions2);
        });

        it("should accept null and undefined values", () => {
          const annexureQuestions: IAnnexureQuestions = { id: 123 };
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            [],
            null,
            annexureQuestions,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(annexureQuestions);
        });

        it("should return initial array if no AnnexureQuestions is added", () => {
          const annexureQuestionsCollection: IAnnexureQuestions[] = [
            { id: 123 },
          ];
          expectedResult = service.addAnnexureQuestionsToCollectionIfMissing(
            annexureQuestionsCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(annexureQuestionsCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
