import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { AnnexureAnswersDetailComponent } from "./annexure-answers-detail.component";

describe("Component Tests", () => {
  describe("AnnexureAnswers Management Detail Component", () => {
    let comp: AnnexureAnswersDetailComponent;
    let fixture: ComponentFixture<AnnexureAnswersDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [AnnexureAnswersDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ annexureAnswers: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(AnnexureAnswersDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(AnnexureAnswersDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load annexureAnswers on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.annexureAnswers).toEqual(
          expect.objectContaining({ id: 123 })
        );
      });
    });
  });
});
