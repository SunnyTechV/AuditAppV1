import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import { ITableDetails, TableDetails } from "../table-details.model";

import { TableDetailsService } from "./table-details.service";

describe("Service Tests", () => {
  describe("TableDetails Service", () => {
    let service: TableDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: ITableDetails;
    let expectedResult: ITableDetails | ITableDetails[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(TableDetailsService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        formName: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        tableName: "AAAAAAA",
        description: "AAAAAAA",
        descriptionParameter: "AAAAAAA",
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

      it("should create a TableDetails", () => {
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
          .create(new TableDetails())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a TableDetails", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            description: "BBBBBB",
            descriptionParameter: "BBBBBB",
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

      it("should partial update a TableDetails", () => {
        const patchObject = Object.assign(
          {
            type: "BBBBBB",
            tableName: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          new TableDetails()
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

      it("should return a list of TableDetails", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            formName: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            tableName: "BBBBBB",
            description: "BBBBBB",
            descriptionParameter: "BBBBBB",
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

      it("should delete a TableDetails", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addTableDetailsToCollectionIfMissing", () => {
        it("should add a TableDetails to an empty array", () => {
          const tableDetails: ITableDetails = { id: 123 };
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            [],
            tableDetails
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tableDetails);
        });

        it("should not add a TableDetails to an array that contains it", () => {
          const tableDetails: ITableDetails = { id: 123 };
          const tableDetailsCollection: ITableDetails[] = [
            {
              ...tableDetails,
            },
            { id: 456 },
          ];
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            tableDetailsCollection,
            tableDetails
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a TableDetails to an array that doesn't contain it", () => {
          const tableDetails: ITableDetails = { id: 123 };
          const tableDetailsCollection: ITableDetails[] = [{ id: 456 }];
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            tableDetailsCollection,
            tableDetails
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tableDetails);
        });

        it("should add only unique TableDetails to an array", () => {
          const tableDetailsArray: ITableDetails[] = [
            { id: 123 },
            { id: 456 },
            { id: 99209 },
          ];
          const tableDetailsCollection: ITableDetails[] = [{ id: 123 }];
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            tableDetailsCollection,
            ...tableDetailsArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const tableDetails: ITableDetails = { id: 123 };
          const tableDetails2: ITableDetails = { id: 456 };
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            [],
            tableDetails,
            tableDetails2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(tableDetails);
          expect(expectedResult).toContain(tableDetails2);
        });

        it("should accept null and undefined values", () => {
          const tableDetails: ITableDetails = { id: 123 };
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            [],
            null,
            tableDetails,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(tableDetails);
        });

        it("should return initial array if no TableDetails is added", () => {
          const tableDetailsCollection: ITableDetails[] = [{ id: 123 }];
          expectedResult = service.addTableDetailsToCollectionIfMissing(
            tableDetailsCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(tableDetailsCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
