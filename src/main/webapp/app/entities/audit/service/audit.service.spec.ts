import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import { IAudit, Audit } from "../audit.model";

import { AuditService } from "./audit.service";

describe("Service Tests", () => {
  describe("Audit Service", () => {
    let service: AuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IAudit;
    let expectedResult: IAudit | IAudit[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AuditService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        auditDate: currentDate,
        hospName: "AAAAAAA",
        isAuditComplete: false,
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
        freeField4: "AAAAAAA",
        remark: "AAAAAAA",
        createdBy: "AAAAAAA",
        createdDate: currentDate,
        lastModified: currentDate,
        lastModifiedBy: "AAAAAAA",
      };
    });

    describe("Service methods", () => {
      it("should find an element", () => {
        const returnedFromService = Object.assign(
          {
            auditDate: currentDate.format(DATE_FORMAT),
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

      it("should create a Audit", () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            auditDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            auditDate: currentDate,
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .create(new Audit())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a Audit", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            auditDate: currentDate.format(DATE_FORMAT),
            hospName: "BBBBBB",
            isAuditComplete: true,
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
            remark: "BBBBBB",
            createdBy: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            auditDate: currentDate,
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

      it("should partial update a Audit", () => {
        const patchObject = Object.assign(
          {
            hospName: "BBBBBB",
            isAuditComplete: true,
            freeField2: "BBBBBB",
            remark: "BBBBBB",
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          new Audit()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            auditDate: currentDate,
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

      it("should return a list of Audit", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            auditDate: currentDate.format(DATE_FORMAT),
            hospName: "BBBBBB",
            isAuditComplete: true,
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
            remark: "BBBBBB",
            createdBy: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            auditDate: currentDate,
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

      it("should delete a Audit", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addAuditToCollectionIfMissing", () => {
        it("should add a Audit to an empty array", () => {
          const audit: IAudit = { id: 123 };
          expectedResult = service.addAuditToCollectionIfMissing([], audit);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(audit);
        });

        it("should not add a Audit to an array that contains it", () => {
          const audit: IAudit = { id: 123 };
          const auditCollection: IAudit[] = [
            {
              ...audit,
            },
            { id: 456 },
          ];
          expectedResult = service.addAuditToCollectionIfMissing(
            auditCollection,
            audit
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a Audit to an array that doesn't contain it", () => {
          const audit: IAudit = { id: 123 };
          const auditCollection: IAudit[] = [{ id: 456 }];
          expectedResult = service.addAuditToCollectionIfMissing(
            auditCollection,
            audit
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(audit);
        });

        it("should add only unique Audit to an array", () => {
          const auditArray: IAudit[] = [
            { id: 123 },
            { id: 456 },
            { id: 31370 },
          ];
          const auditCollection: IAudit[] = [{ id: 123 }];
          expectedResult = service.addAuditToCollectionIfMissing(
            auditCollection,
            ...auditArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const audit: IAudit = { id: 123 };
          const audit2: IAudit = { id: 456 };
          expectedResult = service.addAuditToCollectionIfMissing(
            [],
            audit,
            audit2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(audit);
          expect(expectedResult).toContain(audit2);
        });

        it("should accept null and undefined values", () => {
          const audit: IAudit = { id: 123 };
          expectedResult = service.addAuditToCollectionIfMissing(
            [],
            null,
            audit,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(audit);
        });

        it("should return initial array if no Audit is added", () => {
          const auditCollection: IAudit[] = [{ id: 123 }];
          expectedResult = service.addAuditToCollectionIfMissing(
            auditCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(auditCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
