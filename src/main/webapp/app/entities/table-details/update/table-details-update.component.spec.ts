jest.mock("@angular/router");

import { ComponentFixture, TestBed } from "@angular/core/testing";
import { HttpResponse } from "@angular/common/http";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { of, Subject } from "rxjs";

import { TableDetailsService } from "../service/table-details.service";
import { ITableDetails, TableDetails } from "../table-details.model";

import { TableDetailsUpdateComponent } from "./table-details-update.component";

describe("Component Tests", () => {
  describe("TableDetails Management Update Component", () => {
    let comp: TableDetailsUpdateComponent;
    let fixture: ComponentFixture<TableDetailsUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let tableDetailsService: TableDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [TableDetailsUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(TableDetailsUpdateComponent, "")
        .compileComponents();

      fixture = TestBed.createComponent(TableDetailsUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      tableDetailsService = TestBed.inject(TableDetailsService);

      comp = fixture.componentInstance;
    });

    describe("ngOnInit", () => {
      it("Should update editForm", () => {
        const tableDetails: ITableDetails = { id: 456 };

        activatedRoute.data = of({ tableDetails });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(
          expect.objectContaining(tableDetails)
        );
      });
    });

    describe("save", () => {
      it("Should call update service on save for existing entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<TableDetails>>();
        const tableDetails = { id: 123 };
        jest.spyOn(tableDetailsService, "update").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ tableDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tableDetails }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(tableDetailsService.update).toHaveBeenCalledWith(tableDetails);
        expect(comp.isSaving).toEqual(false);
      });

      it("Should call create service on save for new entity", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<TableDetails>>();
        const tableDetails = new TableDetails();
        jest.spyOn(tableDetailsService, "create").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ tableDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: tableDetails }));
        saveSubject.complete();

        // THEN
        expect(tableDetailsService.create).toHaveBeenCalledWith(tableDetails);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it("Should set isSaving to false on error", () => {
        // GIVEN
        const saveSubject = new Subject<HttpResponse<TableDetails>>();
        const tableDetails = { id: 123 };
        jest.spyOn(tableDetailsService, "update").mockReturnValue(saveSubject);
        jest.spyOn(comp, "previousState");
        activatedRoute.data = of({ tableDetails });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error("This is an error!");

        // THEN
        expect(tableDetailsService.update).toHaveBeenCalledWith(tableDetails);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
