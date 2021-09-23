import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IOxygenConsumptionData,
  OxygenConsumptionData,
} from "../oxygen-consumption-data.model";

import { OxygenConsumptionDataService } from "./oxygen-consumption-data.service";

describe("Service Tests", () => {
  describe("OxygenConsumptionData Service", () => {
    let service: OxygenConsumptionDataService;
    let httpMock: HttpTestingController;
    let elemDefault: IOxygenConsumptionData;
    let expectedResult:
      | IOxygenConsumptionData
      | IOxygenConsumptionData[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(OxygenConsumptionDataService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        tableName: "AAAAAAA",
        noofPatients: 0,
        consumptionLitperMin: 0,
        consumptionLitperDay: 0,
        consumptionKLitperDay: 0,
        consumptionTotal: 0,
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
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

      it("should create a OxygenConsumptionData", () => {
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
          .create(new OxygenConsumptionData())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a OxygenConsumptionData", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            noofPatients: 1,
            consumptionLitperMin: 1,
            consumptionLitperDay: 1,
            consumptionKLitperDay: 1,
            consumptionTotal: 1,
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
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

      it("should partial update a OxygenConsumptionData", () => {
        const patchObject = Object.assign(
          {
            tableName: "BBBBBB",
            consumptionKLitperDay: 1,
            freeField1: "BBBBBB",
            freeField3: "BBBBBB",
            createdBy: "BBBBBB",
          },
          new OxygenConsumptionData()
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

      it("should return a list of OxygenConsumptionData", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            noofPatients: 1,
            consumptionLitperMin: 1,
            consumptionLitperDay: 1,
            consumptionKLitperDay: 1,
            consumptionTotal: 1,
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
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

      it("should delete a OxygenConsumptionData", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addOxygenConsumptionDataToCollectionIfMissing", () => {
        it("should add a OxygenConsumptionData to an empty array", () => {
          const oxygenConsumptionData: IOxygenConsumptionData = { id: 123 };
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              [],
              oxygenConsumptionData
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(oxygenConsumptionData);
        });

        it("should not add a OxygenConsumptionData to an array that contains it", () => {
          const oxygenConsumptionData: IOxygenConsumptionData = { id: 123 };
          const oxygenConsumptionDataCollection: IOxygenConsumptionData[] = [
            {
              ...oxygenConsumptionData,
            },
            { id: 456 },
          ];
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              oxygenConsumptionDataCollection,
              oxygenConsumptionData
            );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a OxygenConsumptionData to an array that doesn't contain it", () => {
          const oxygenConsumptionData: IOxygenConsumptionData = { id: 123 };
          const oxygenConsumptionDataCollection: IOxygenConsumptionData[] = [
            { id: 456 },
          ];
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              oxygenConsumptionDataCollection,
              oxygenConsumptionData
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(oxygenConsumptionData);
        });

        it("should add only unique OxygenConsumptionData to an array", () => {
          const oxygenConsumptionDataArray: IOxygenConsumptionData[] = [
            { id: 123 },
            { id: 456 },
            { id: 39943 },
          ];
          const oxygenConsumptionDataCollection: IOxygenConsumptionData[] = [
            { id: 123 },
          ];
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              oxygenConsumptionDataCollection,
              ...oxygenConsumptionDataArray
            );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const oxygenConsumptionData: IOxygenConsumptionData = { id: 123 };
          const oxygenConsumptionData2: IOxygenConsumptionData = { id: 456 };
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              [],
              oxygenConsumptionData,
              oxygenConsumptionData2
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(oxygenConsumptionData);
          expect(expectedResult).toContain(oxygenConsumptionData2);
        });

        it("should accept null and undefined values", () => {
          const oxygenConsumptionData: IOxygenConsumptionData = { id: 123 };
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              [],
              null,
              oxygenConsumptionData,
              undefined
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(oxygenConsumptionData);
        });

        it("should return initial array if no OxygenConsumptionData is added", () => {
          const oxygenConsumptionDataCollection: IOxygenConsumptionData[] = [
            { id: 123 },
          ];
          expectedResult =
            service.addOxygenConsumptionDataToCollectionIfMissing(
              oxygenConsumptionDataCollection,
              undefined,
              null
            );
          expect(expectedResult).toEqual(oxygenConsumptionDataCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
