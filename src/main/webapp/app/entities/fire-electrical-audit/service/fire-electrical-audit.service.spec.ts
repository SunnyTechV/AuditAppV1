import { TestBed } from "@angular/core/testing";
import {
  HttpClientTestingModule,
  HttpTestingController,
} from "@angular/common/http/testing";
import * as dayjs from "dayjs";

import { DATE_FORMAT } from "app/config/input.constants";
import {
  IFireElectricalAudit,
  FireElectricalAudit,
} from "../fire-electrical-audit.model";

import { FireElectricalAuditService } from "./fire-electrical-audit.service";

describe("Service Tests", () => {
  describe("FireElectricalAudit Service", () => {
    let service: FireElectricalAuditService;
    let httpMock: HttpTestingController;
    let elemDefault: IFireElectricalAudit;
    let expectedResult:
      | IFireElectricalAudit
      | IFireElectricalAudit[]
      | boolean
      | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(FireElectricalAuditService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        fireAuditDoneOrnot: false,
        fireAuditDate: currentDate,
        fireFaults: "AAAAAAA",
        fireCorrectiveAction: "AAAAAAA",
        fireAuditPlan: "AAAAAAA",
        electricalAuditDone: false,
        electricalAuditDate: currentDate,
        electricalFaults: "AAAAAAA",
        electricalCorrectiveAction: "AAAAAAA",
        electricalAuditInspection: "AAAAAAA",
        technicalPersonAppoint: false,
        techPersonname: "AAAAAAA",
        techPersonMobNo: "AAAAAAA",
        technicalEngineerName: "AAAAAAA",
        technicalEngineerAddress: "AAAAAAA",
        technicalEngineerMob: "AAAAAAA",
        technicalEngineerAlternateMob: "AAAAAAA",
        o2HospRequirement: 0,
        o2HospProjectedRequirement: 0,
        saveO2RequirementPossibleMT: 0,
        monitoringO2ValvesPort: false,
        portValvesShutDown: false,
        idPatientDrillDone: false,
        staffCheckingLeakage: false,
        patientO2ReqFinalized: false,
        timeByDoctor: "AAAAAAA",
        isLightingInstalled: false,
        locLightningArrerstor: "AAAAAAA",
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
            fireAuditDate: currentDate.format(DATE_FORMAT),
            electricalAuditDate: currentDate.format(DATE_FORMAT),
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

      it("should create a FireElectricalAudit", () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            fireAuditDate: currentDate.format(DATE_FORMAT),
            electricalAuditDate: currentDate.format(DATE_FORMAT),
            createdDate: currentDate.format(DATE_FORMAT),
            lastModified: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            fireAuditDate: currentDate,
            electricalAuditDate: currentDate,
            createdDate: currentDate,
            lastModified: currentDate,
          },
          returnedFromService
        );

        service
          .create(new FireElectricalAudit())
          .subscribe((resp) => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: "POST" });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it("should update a FireElectricalAudit", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            fireAuditDoneOrnot: true,
            fireAuditDate: currentDate.format(DATE_FORMAT),
            fireFaults: "BBBBBB",
            fireCorrectiveAction: "BBBBBB",
            fireAuditPlan: "BBBBBB",
            electricalAuditDone: true,
            electricalAuditDate: currentDate.format(DATE_FORMAT),
            electricalFaults: "BBBBBB",
            electricalCorrectiveAction: "BBBBBB",
            electricalAuditInspection: "BBBBBB",
            technicalPersonAppoint: true,
            techPersonname: "BBBBBB",
            techPersonMobNo: "BBBBBB",
            technicalEngineerName: "BBBBBB",
            technicalEngineerAddress: "BBBBBB",
            technicalEngineerMob: "BBBBBB",
            technicalEngineerAlternateMob: "BBBBBB",
            o2HospRequirement: 1,
            o2HospProjectedRequirement: 1,
            saveO2RequirementPossibleMT: 1,
            monitoringO2ValvesPort: true,
            portValvesShutDown: true,
            idPatientDrillDone: true,
            staffCheckingLeakage: true,
            patientO2ReqFinalized: true,
            timeByDoctor: "BBBBBB",
            isLightingInstalled: true,
            locLightningArrerstor: "BBBBBB",
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
            fireAuditDate: currentDate,
            electricalAuditDate: currentDate,
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

      it("should partial update a FireElectricalAudit", () => {
        const patchObject = Object.assign(
          {
            fireAuditDoneOrnot: true,
            fireAuditDate: currentDate.format(DATE_FORMAT),
            fireAuditPlan: "BBBBBB",
            electricalAuditDone: true,
            electricalAuditInspection: "BBBBBB",
            technicalPersonAppoint: true,
            techPersonname: "BBBBBB",
            technicalEngineerAddress: "BBBBBB",
            technicalEngineerMob: "BBBBBB",
            technicalEngineerAlternateMob: "BBBBBB",
            o2HospRequirement: 1,
            saveO2RequirementPossibleMT: 1,
            portValvesShutDown: true,
            staffCheckingLeakage: true,
            isLightingInstalled: true,
            locLightningArrerstor: "BBBBBB",
            createdBy: "BBBBBB",
            lastModifiedBy: "BBBBBB",
            freeField1: "BBBBBB",
            freeField3: "BBBBBB",
            freeField4: "BBBBBB",
          },
          new FireElectricalAudit()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            fireAuditDate: currentDate,
            electricalAuditDate: currentDate,
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

      it("should return a list of FireElectricalAudit", () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            fireAuditDoneOrnot: true,
            fireAuditDate: currentDate.format(DATE_FORMAT),
            fireFaults: "BBBBBB",
            fireCorrectiveAction: "BBBBBB",
            fireAuditPlan: "BBBBBB",
            electricalAuditDone: true,
            electricalAuditDate: currentDate.format(DATE_FORMAT),
            electricalFaults: "BBBBBB",
            electricalCorrectiveAction: "BBBBBB",
            electricalAuditInspection: "BBBBBB",
            technicalPersonAppoint: true,
            techPersonname: "BBBBBB",
            techPersonMobNo: "BBBBBB",
            technicalEngineerName: "BBBBBB",
            technicalEngineerAddress: "BBBBBB",
            technicalEngineerMob: "BBBBBB",
            technicalEngineerAlternateMob: "BBBBBB",
            o2HospRequirement: 1,
            o2HospProjectedRequirement: 1,
            saveO2RequirementPossibleMT: 1,
            monitoringO2ValvesPort: true,
            portValvesShutDown: true,
            idPatientDrillDone: true,
            staffCheckingLeakage: true,
            patientO2ReqFinalized: true,
            timeByDoctor: "BBBBBB",
            isLightingInstalled: true,
            locLightningArrerstor: "BBBBBB",
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
            fireAuditDate: currentDate,
            electricalAuditDate: currentDate,
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

      it("should delete a FireElectricalAudit", () => {
        service.delete(123).subscribe((resp) => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: "DELETE" });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe("addFireElectricalAuditToCollectionIfMissing", () => {
        it("should add a FireElectricalAudit to an empty array", () => {
          const fireElectricalAudit: IFireElectricalAudit = { id: 123 };
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            [],
            fireElectricalAudit
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(fireElectricalAudit);
        });

        it("should not add a FireElectricalAudit to an array that contains it", () => {
          const fireElectricalAudit: IFireElectricalAudit = { id: 123 };
          const fireElectricalAuditCollection: IFireElectricalAudit[] = [
            {
              ...fireElectricalAudit,
            },
            { id: 456 },
          ];
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            fireElectricalAuditCollection,
            fireElectricalAudit
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a FireElectricalAudit to an array that doesn't contain it", () => {
          const fireElectricalAudit: IFireElectricalAudit = { id: 123 };
          const fireElectricalAuditCollection: IFireElectricalAudit[] = [
            { id: 456 },
          ];
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            fireElectricalAuditCollection,
            fireElectricalAudit
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(fireElectricalAudit);
        });

        it("should add only unique FireElectricalAudit to an array", () => {
          const fireElectricalAuditArray: IFireElectricalAudit[] = [
            { id: 123 },
            { id: 456 },
            { id: 2726 },
          ];
          const fireElectricalAuditCollection: IFireElectricalAudit[] = [
            { id: 123 },
          ];
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            fireElectricalAuditCollection,
            ...fireElectricalAuditArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it("should accept varargs", () => {
          const fireElectricalAudit: IFireElectricalAudit = { id: 123 };
          const fireElectricalAudit2: IFireElectricalAudit = { id: 456 };
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            [],
            fireElectricalAudit,
            fireElectricalAudit2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(fireElectricalAudit);
          expect(expectedResult).toContain(fireElectricalAudit2);
        });

        it("should accept null and undefined values", () => {
          const fireElectricalAudit: IFireElectricalAudit = { id: 123 };
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            [],
            null,
            fireElectricalAudit,
            undefined
          );
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(fireElectricalAudit);
        });

        it("should return initial array if no FireElectricalAudit is added", () => {
          const fireElectricalAuditCollection: IFireElectricalAudit[] = [
            { id: 123 },
          ];
          expectedResult = service.addFireElectricalAuditToCollectionIfMissing(
            fireElectricalAuditCollection,
            undefined,
            null
          );
          expect(expectedResult).toEqual(fireElectricalAuditCollection);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
