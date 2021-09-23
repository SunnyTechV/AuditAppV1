jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { AnnexureAnswersService } from "../service/annexure-answers.service";
import { IAnnexureAnswers, AnnexureAnswers } from "../annexure-answers.model";
import { IAudit } from "app/entities/audit/audit.model";
import { AuditService } from "app/entities/audit/service/audit.service";
import { IAnnexureQuestions } from "app/entities/annexure-questions/annexure-questions.model";
import { AnnexureQuestionsService } from "app/entities/annexure-questions/service/annexure-questions.service";

import { AnnexureAnswersUpdateComponent } from "./annexure-answers-update.component";

describe("Component Tests", () => {
  describe("AnnexureAnswers Management Update Component", () => {
    let comp: AnnexureAnswersUpdateComponent;
    let fixture: ComponentFixture<AnnexureAnswersUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let annexureAnswersService: AnnexureAnswersService;
    let auditService: AuditService;
    let annexureQuestionsService: AnnexureQuestionsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [AnnexureAnswersUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(AnnexureAnswersUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(AnnexureAnswersUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      annexureAnswersService = TestBed.inject(AnnexureAnswersService);
      auditService = TestBed.inject(AuditService);
      annexureQuestionsService = TestBed.inject(AnnexureQuestionsService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should call Audit query and add missing value", () => {
        const annexureAnswers: IAnnexureAnswers = { id: 456 };
        const audit: IAudit = { id: 61520 };
        annexureAnswers.audit = audit;

        const auditCollection: IAudit[] = [{ id: 56865 }];
        jest
          .spyOn(auditService, "query")
          .mockReturnValue(of(new HttpResponse({ body: auditCollection })));
        const additionalAudits = [audit];
        const expectedCollection: IAudit[] = [
          ...additionalAudits,
          ...auditCollection,
        ];
        jest
          .spyOn(auditService, "addAuditToCollectionIfMissing")
          .mockReturnValue(expectedCollection);

        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        expect(auditService.query).toHaveBeenCalled();
        expect(auditService.addAuditToCollectionIfMissing).toHaveBeenCalledWith(
          auditCollection,
          ...additionalAudits
        );
        expect(comp.auditsSharedCollection).toEqual(expectedCollection);
      });

      it("Should call AnnexureQuestions query and add missing value", () => {
        const annexureAnswers: IAnnexureAnswers = { id: 456 };
        const annexureQuestions: IAnnexureQuestions = { id: 40711 };
        annexureAnswers.annexureQuestions = annexureQuestions;

        const annexureQuestionsCollection: IAnnexureQuestions[] = [
          { id: 51516 },
        ];
        jest
          .spyOn(annexureQuestionsService, "query")
          .mockReturnValue(
            of(new HttpResponse({ body: annexureQuestionsCollection }))
          );
        const additionalAnnexureQuestions = [annexureQuestions];
        const expectedCollection: IAnnexureQuestions[] = [
          ...additionalAnnexureQuestions,
          ...annexureQuestionsCollection,
        ];
        jest
          .spyOn(
            annexureQuestionsService,
            "addAnnexureQuestionsToCollectionIfMissing"
          )
          .mockReturnValue(expectedCollection);

        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        expect(annexureQuestionsService.query).toHaveBeenCalled();
        expect(
          annexureQuestionsService.addAnnexureQuestionsToCollectionIfMissing
        ).toHaveBeenCalledWith(
          annexureQuestionsCollection,
          ...additionalAnnexureQuestions
        );
        expect(comp.annexureQuestionsSharedCollection).toEqual(
          expectedCollection
        );
      });

      it("Should update editForm", () => {
        const annexureAnswers: IAnnexureAnswers = { id: 456 };
        const audit: IAudit = { id: 99442 };
        annexureAnswers.audit = audit;
        const annexureQuestions: IAnnexureQuestions = { id: 18313 };
        annexureAnswers.annexureQuestions = annexureQuestions;

        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(annexureAnswers)
        );
        expect(comp.auditsSharedCollection).toContain(audit);
        expect(comp.annexureQuestionsSharedCollection).toContain(
          annexureQuestions
        );
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureAnswers>>();
        const annexureAnswers = { id: 123 };
        jest
          .spyOn(annexureAnswersService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: annexureAnswers }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(annexureAnswersService.update).toHaveBeenCalledWith(
          annexureAnswers
        );
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureAnswers>>();
        const annexureAnswers = new AnnexureAnswers();
        jest
          .spyOn(annexureAnswersService, "create")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: annexureAnswers }));
        saveSubject.complete();

        // THEN
        expect(annexureAnswersService.create).toHaveBeenCalledWith(
          annexureAnswers
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<AnnexureAnswers>>();
        const annexureAnswers = { id: 123 };
        jest
          .spyOn(annexureAnswersService, "update")
          .mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ annexureAnswers });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(annexureAnswersService.update).toHaveBeenCalledWith(
          annexureAnswers
        );
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe("Tracking relationships identifiers", () => {
      describe("trackAuditById", () => {
        it("Should return tracked Audit primary key", () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAuditById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe("trackAnnexureQuestionsById", () => {
        it("Should return tracked AnnexureQuestions primary key", () => {
          const entity = { id: 123 };
          const trackResult = comp.trackAnnexureQuestionsById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
