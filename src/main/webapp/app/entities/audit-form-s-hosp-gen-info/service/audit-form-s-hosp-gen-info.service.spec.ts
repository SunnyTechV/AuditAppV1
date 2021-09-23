import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IAuditFormSHospGenInfo,
  AuditFormSHospGenInfo,
} from "../audit-form-s-hosp-gen-info.model";

import { AuditFormSHospGenInfoService } from "./audit-form-s-hosp-gen-info.service";

describe("Service Tests", () => {
  describe("AuditFormSHospGenInfo Service", () => {
    let service: AuditFormSHospGenInfoService;
    let httpMock: HttpTestingController;
    let elemDefault: IAuditFormSHospGenInfo;
    let expectedResult:
      | IAuditFormSHospGenInfo
      | IAuditFormSHospGenInfo[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AuditFormSHospGenInfoService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        hospName: "AAAAAAA",
        hospType: "AAAAAAA",
        type: "AAAAAAA",
        subType: "AAAAAAA",
        formName: "AAAAAAA",
        inchargeName: "AAAAAAA",
        hospAddress: "AAAAAAA",
        hospPhoneNo: "AAAAAAA",
        normalBeds: 0,
        oxygenBeds: 0,
        ventilatorBeds: 0,
        icuBeds: 0,
        onCylinderPatient: 0,
        onPipedBedsPatient: 0,
        onNIV: 0,
        onIntubated: 0,
        jumboSystemInstalledType: "AAAAAAA",
        availableCylinderTypeD7: 0,
        availableCylinderTypeB: 0,
        cylinderAgencyName: "AAAAAAA",
        cylinderAgencyAddress: "AAAAAAA",
        availableLMOCapacityKL: 0,
        lmoSupplierName: "AAAAAAA",
        lmoSupplierFrequency: 0,
        lastLmoSuppliedQuantity: 0,
        remark: "AAAAAAA",
        createdDate: currentDate,
        createdBy: "AAAAAAA",
        lastModified: currentDate,
        lastModifiedBy: "AAAAAAA",
        freeField1: "AAAAAAA",
        freeField2: "AAAAAAA",
        freeField3: "AAAAAAA",
        freeField4: "AAAAAAA",
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

      it("should create a AuditFormSHospGenInfo", () => {
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
          .create(new AuditFormSHospGenInfo())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a AuditFormSHospGenInfo", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            hospName: "BBBBBB",
            hospType: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            formName: "BBBBBB",
            inchargeName: "BBBBBB",
            hospAddress: "BBBBBB",
            hospPhoneNo: "BBBBBB",
            normalBeds: 1,
            oxygenBeds: 1,
            ventilatorBeds: 1,
            icuBeds: 1,
            onCylinderPatient: 1,
            onPipedBedsPatient: 1,
            onNIV: 1,
            onIntubated: 1,
            jumboSystemInstalledType: "BBBBBB",
            availableCylinderTypeD7: 1,
            availableCylinderTypeB: 1,
            cylinderAgencyName: "BBBBBB",
            cylinderAgencyAddress: "BBBBBB",
            availableLMOCapacityKL: 1,
            lmoSupplierName: "BBBBBB",
            lmoSupplierFrequency: 1,
            lastLmoSuppliedQuantity: 1,
            remark: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
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

      it("should partial update a AuditFormSHospGenInfo", () => {
        const patchObject = Object.assign(
          {
            hospName: "BBBBBB",
            hospType: "BBBBBB",
            type: "BBBBBB",
            hospAddress: "BBBBBB",
            hospPhoneNo: "BBBBBB",
            normalBeds: 1,
            ventilatorBeds: 1,
            icuBeds: 1,
            onIntubated: 1,
            jumboSystemInstalledType: "BBBBBB",
            availableCylinderTypeB: 1,
            cylinderAgencyAddress: "BBBBBB",
            availableLMOCapacityKL: 1,
            lmoSupplierName: "BBBBBB",
            lmoSupplierFrequency: 1,
            lastLmoSuppliedQuantity: 1,
            remark: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField4: "BBBBBB",
          },
          new AuditFormSHospGenInfo()
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

      it("should return a list of AuditFormSHospGenInfo", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            hospName: "BBBBBB",
            hospType: "BBBBBB",
            type: "BBBBBB",
            subType: "BBBBBB",
            formName: "BBBBBB",
            inchargeName: "BBBBBB",
            hospAddress: "BBBBBB",
            hospPhoneNo: "BBBBBB",
            normalBeds: 1,
            oxygenBeds: 1,
            ventilatorBeds: 1,
            icuBeds: 1,
            onCylinderPatient: 1,
            onPipedBedsPatient: 1,
            onNIV: 1,
            onIntubated: 1,
            jumboSystemInstalledType: "BBBBBB",
            availableCylinderTypeD7: 1,
            availableCylinderTypeB: 1,
            cylinderAgencyName: "BBBBBB",
            cylinderAgencyAddress: "BBBBBB",
            availableLMOCapacityKL: 1,
            lmoSupplierName: "BBBBBB",
            lmoSupplierFrequency: 1,
            lastLmoSuppliedQuantity: 1,
            remark: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
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

      it("should delete a AuditFormSHospGenInfo", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addAuditFormSHospGenInfoToCollectionIfMissing", () => {
        it("should add a AuditFormSHospGenInfo to an empty array", () => {
          const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 123 };
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              [],
              auditFormSHospGenInfo
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(auditFormSHospGenInfo);
        });

        it("should not add a AuditFormSHospGenInfo to an array that contains it", () => {
          const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 123 };
          const auditFormSHospGenInfoCollection: IAuditFormSHospGenInfo[] = [
            {
              ...auditFormSHospGenInfo,
            },
            { id: 456 },
          ];
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              auditFormSHospGenInfoCollection,
              auditFormSHospGenInfo
            );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a AuditFormSHospGenInfo to an array that doesn't contain it", () => {
          const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 123 };
          const auditFormSHospGenInfoCollection: IAuditFormSHospGenInfo[] = [
            { id: 456 },
          ];
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              auditFormSHospGenInfoCollection,
              auditFormSHospGenInfo
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(auditFormSHospGenInfo);
        });

        it("should add only unique AuditFormSHospGenInfo to an array", () => {
          const auditFormSHospGenInfoArray: IAuditFormSHospGenInfo[] = [
            { id: 123 },
            { id: 456 },
            { id: 9910 },
          ];
          const auditFormSHospGenInfoCollection: IAuditFormSHospGenInfo[] = [
            { id: 123 },
          ];
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              auditFormSHospGenInfoCollection,
              ...auditFormSHospGenInfoArray
            );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 123 };
          const auditFormSHospGenInfo2: IAuditFormSHospGenInfo = { id: 456 };
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              [],
              auditFormSHospGenInfo,
              auditFormSHospGenInfo2
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(auditFormSHospGenInfo);
          expect(expectedResult).toContain(auditFormSHospGenInfo2);
        });

        it("should accept null and undefined values", () => {
          const auditFormSHospGenInfo: IAuditFormSHospGenInfo = { id: 123 };
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              [],
              null,
              auditFormSHospGenInfo,
              undefined
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(auditFormSHospGenInfo);
        });

        it("should return initial array if no AuditFormSHospGenInfo is added", () => {
          const auditFormSHospGenInfoCollection: IAuditFormSHospGenInfo[] = [
            { id: 123 },
          ];
          expectedResult =
            service.addAuditFormSHospGenInfoToCollectionIfMissing(
              auditFormSHospGenInfoCollection,
              undefined,
              null
            );
          expect(expectedResult).toEqual(auditFormSHospGenInfoCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
