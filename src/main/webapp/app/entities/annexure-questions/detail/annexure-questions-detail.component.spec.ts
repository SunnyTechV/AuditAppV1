import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { AnnexureQuestionsDetailComponent } from "./annexure-questions-detail.component";

describe("Component Tests", () => {
  describe("AnnexureQuestions Management Detail Component", () => {
    let comp: AnnexureQuestionsDetailComponent;
    let fixture: ComponentFixture<AnnexureQuestionsDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [AnnexureQuestionsDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ annexureQuestions: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(AnnexureQuestionsDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(AnnexureQuestionsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load annexureQuestions on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.annexureQuestions).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
