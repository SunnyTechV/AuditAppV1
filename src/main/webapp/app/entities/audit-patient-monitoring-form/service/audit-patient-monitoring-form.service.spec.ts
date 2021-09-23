import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IAuditPatientMonitoringForm,
  AuditPatientMonitoringForm,
} from "../audit-patient-monitoring-form.model";

import { AuditPatientMonitoringFormService } from "./audit-patient-monitoring-form.service";

describe("Service Tests", () => {
  describe("AuditPatientMonitoringForm Service", () => {
    let service: AuditPatientMonitoringFormService;
    let httpMock: HttpTestingController;
    let elemDefault: IAuditPatientMonitoringForm;
    let expectedResult:
      | IAuditPatientMonitoringForm
      | IAuditPatientMonitoringForm[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(AuditPatientMonitoringFormService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        wardNo: 0,
        patientName: "AAAAAAA",
        bedNo: 0,
        dateOfAdmission: currentDate,
        oxygenType: "AAAAAAA",
        sixToEightAM: 0,
        oxySixToEightAM: 0,
        eightToTenAM: 0,
        oxyEightToTenAM: 0,
        tenToTwelveAM: 0,
        oxyTenToTwelveAM: 0,
        twelveToTowPM: 0,
        oxyTwelveToTowPM: 0,
        twoToFourPM: 0,
        oxyTwoToFourPM: 0,
        fourToSixPM: 0,
        oxyFourToSixPM: 0,
        sixToEightPM: 0,
        oxySixToEightPM: 0,
        eightToTenPM: 0,
        oxyEightToTenPM: 0,
        tenToTwelvePM: 0,
        oxyTenToTwelvePM: 0,
        twelveToTwoAM: 0,
        oxyTwelveToTwoAM: 0,
        twoToFourAM: 0,
        oxyTwoToFourAM: 0,
        fourToSixAM: 0,
        oxyFourToSixAM: 0,
        drName: "AAAAAAA",
        nurseName: "AAAAAAA",
        createdBy: "AAAAAAA",
        createdDate: currentDate,
        lastModifiedBy: "AAAAAAA",
        lastModified: currentDate,
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
            dateOfAdmission: currentDate.format(DATE_FORMAT),
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

      it("should create a AuditPatientMonitoringForm", () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateOfAdmission: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfAdmission: currentDate,
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .create(new AuditPatientMonitoringForm())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a AuditPatientMonitoringForm", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            wardNo: 1,
            patientName: "BBBBBB",
            bedNo: 1,
            dateOfAdmission: currentDate.format(DATE_FORMAT),
            oxygenType: "BBBBBB",
            sixToEightAM: 1,
            oxySixToEightAM: 1,
            eightToTenAM: 1,
            oxyEightToTenAM: 1,
            tenToTwelveAM: 1,
            oxyTenToTwelveAM: 1,
            twelveToTowPM: 1,
            oxyTwelveToTowPM: 1,
            twoToFourPM: 1,
            oxyTwoToFourPM: 1,
            fourToSixPM: 1,
            oxyFourToSixPM: 1,
            sixToEightPM: 1,
            oxySixToEightPM: 1,
            eightToTenPM: 1,
            oxyEightToTenPM: 1,
            tenToTwelvePM: 1,
            oxyTenToTwelvePM: 1,
            twelveToTwoAM: 1,
            oxyTwelveToTwoAM: 1,
            twoToFourAM: 1,
            oxyTwoToFourAM: 1,
            fourToSixAM: 1,
            oxyFourToSixAM: 1,
            drName: "BBBBBB",
            nurseName: "BBBBBB",
            createdBy: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfAdmission: currentDate,
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

      it("should partial update a AuditPatientMonitoringForm", () => {
        const patchObject = Object.assign(
          {
            patientName: "BBBBBB",
            sixToEightAM: 1,
            oxyEightToTenAM: 1,
            oxyTwoToFourPM: 1,
            fourToSixPM: 1,
            sixToEightPM: 1,
            eightToTenPM: 1,
            oxyEightToTenPM: 1,
            tenToTwelvePM: 1,
            oxyTenToTwelvePM: 1,
            twelveToTwoAM: 1,
            nurseName: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            freeField2: "BBBBBB",
            freeField4: "BBBBBB",
          },
          new AuditPatientMonitoringForm()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            dateOfAdmission: currentDate,
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

      it("should return a list of AuditPatientMonitoringForm", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            wardNo: 1,
            patientName: "BBBBBB",
            bedNo: 1,
            dateOfAdmission: currentDate.format(DATE_FORMAT),
            oxygenType: "BBBBBB",
            sixToEightAM: 1,
            oxySixToEightAM: 1,
            eightToTenAM: 1,
            oxyEightToTenAM: 1,
            tenToTwelveAM: 1,
            oxyTenToTwelveAM: 1,
            twelveToTowPM: 1,
            oxyTwelveToTowPM: 1,
            twoToFourPM: 1,
            oxyTwoToFourPM: 1,
            fourToSixPM: 1,
            oxyFourToSixPM: 1,
            sixToEightPM: 1,
            oxySixToEightPM: 1,
            eightToTenPM: 1,
            oxyEightToTenPM: 1,
            tenToTwelvePM: 1,
            oxyTenToTwelvePM: 1,
            twelveToTwoAM: 1,
            oxyTwelveToTwoAM: 1,
            twoToFourAM: 1,
            oxyTwoToFourAM: 1,
            fourToSixAM: 1,
            oxyFourToSixAM: 1,
            drName: "BBBBBB",
            nurseName: "BBBBBB",
            createdBy: "BBBBBB",
            createdDate: currentDate.format(DATE_FORMAT),
            lastModifiedBy: "BBBBBB",
            lastModified: currentDate.format(DATE_FORMAT),
            freeField1: "BBBBBB",
            freeField2: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateOfAdmission: currentDate,
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

      it("should delete a AuditPatientMonitoringForm", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addAuditPatientMonitoringFormToCollectionIfMissing", () => {
        it("should add a AuditPatientMonitoringForm to an empty array", () => {
          const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
            id: 123,
          };
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              [],
              auditPatientMonitoringForm
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(auditPatientMonitoringForm);
        });

        it("should not add a AuditPatientMonitoringForm to an array that contains it", () => {
          const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
            id: 123,
          };
          const auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[] =
            [
              {
                ...auditPatientMonitoringForm,
              },
              { id: 456 },
            ];
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              auditPatientMonitoringFormCollection,
              auditPatientMonitoringForm
            );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a AuditPatientMonitoringForm to an array that doesn't contain it", () => {
          const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
            id: 123,
          };
          const auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[] =
            [{ id: 456 }];
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              auditPatientMonitoringFormCollection,
              auditPatientMonitoringForm
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(auditPatientMonitoringForm);
        });

        it("should add only unique AuditPatientMonitoringForm to an array", () => {
          const auditPatientMonitoringFormArray: IAuditPatientMonitoringForm[] =
            [{ id: 123 }, { id: 456 }, { id: 76932 }];
          const auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[] =
            [{ id: 123 }];
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              auditPatientMonitoringFormCollection,
              ...auditPatientMonitoringFormArray
            );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
            id: 123,
          };
          const auditPatientMonitoringForm2: IAuditPatientMonitoringForm = {
            id: 456,
          };
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              [],
              auditPatientMonitoringForm,
              auditPatientMonitoringForm2
            );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(auditPatientMonitoringForm);
          expect(expectedResult).toContain(auditPatientMonitoringForm2);
        });

        it("should accept null and undefined values", () => {
          const auditPatientMonitoringForm: IAuditPatientMonitoringForm = {
            id: 123,
          };
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              [],
              null,
              auditPatientMonitoringForm,
              undefined
            );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(auditPatientMonitoringForm);
        });

        it("should return initial array if no AuditPatientMonitoringForm is added", () => {
          const auditPatientMonitoringFormCollection: IAuditPatientMonitoringForm[] =
            [{ id: 123 }];
          expectedResult =
            service.addAuditPatientMonitoringFormToCollectionIfMissing(
              auditPatientMonitoringFormCollection,
              undefined,
              null
            );
          expect(expectedResult).toEqual(auditPatientMonitoringFormCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
