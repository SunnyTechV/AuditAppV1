import { ComponentFixture, TestBed } from "@angular/core/testing";
import { ActivatedRoute } from "@angular/router";
import { of } from "rxjs";

import { TableDetailsDetailComponent } from "./table-details-detail.component";

describe("Component Tests", () => {
  describe("TableDetails Management Detail Component", () => {
    let comp: TableDetailsDetailComponent;
    let fixture: ComponentFixture<TableDetailsDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [TableDetailsDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ tableDetails: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(TableDetailsDetailComponent, "")
        .compileComponents();
      fixture = TestBed.createComponent(TableDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe("OnInit", () => {
      it("Should load tableDetails on init", () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tableDetails).toEqual(expect.objectContaining({ id: 123 }));
      });
    });
  });
});
