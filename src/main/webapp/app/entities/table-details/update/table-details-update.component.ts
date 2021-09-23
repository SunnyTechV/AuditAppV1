import { Component, OnInit } from "@angular/core";
import { HttpResponse } from "@angular/common/http";
import { FormBuilder } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { Observable } from "rxjs";
import { finalize } from "rxjs/operators";

import { ITableDetails, TableDetails } from "../table-details.model";
import { TableDetailsService } from "../service/table-details.service";

@Component({
  selector: "jhi-table-details-update",
  templateUrl: "./table-details-update.component.html",
})
export class TableDetailsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    formName: [],
    type: [],
    subType: [],
    tableName: [],
    description: [],
    descriptionParameter: [],
    freeField1: [],
    freeField2: [],
    freeField3: [],
    createdDate: [],
    createdBy: [],
    lastModified: [],
    lastModifiedBy: [],
  });

  constructor(
    protected tableDetailsService: TableDetailsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tableDetails }) => {
      this.updateForm(tableDetails);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tableDetails = this.createFromForm();
    if (tableDetails.id !== undefined) {
      this.subscribeToSaveResponse(
        this.tableDetailsService.update(tableDetails)
      );
    } else {
      this.subscribeToSaveResponse(
        this.tableDetailsService.create(tableDetails)
      );
    }
  }

  protected subscribeToSaveResponse(
    result: Observable<HttpResponse<ITableDetails>>
  ): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(tableDetails: ITableDetails): void {
    this.editForm.patchValue({
      id: tableDetails.id,
      formName: tableDetails.formName,
      type: tableDetails.type,
      subType: tableDetails.subType,
      tableName: tableDetails.tableName,
      description: tableDetails.description,
      descriptionParameter: tableDetails.descriptionParameter,
      freeField1: tableDetails.freeField1,
      freeField2: tableDetails.freeField2,
      freeField3: tableDetails.freeField3,
      createdDate: tableDetails.createdDate,
      createdBy: tableDetails.createdBy,
      lastModified: tableDetails.lastModified,
      lastModifiedBy: tableDetails.lastModifiedBy,
    });
  }

  protected createFromForm(): ITableDetails {
    return {
      ...new TableDetails(),
      id: this.editForm.get(["id"])!.value,
      formName: this.editForm.get(["formName"])!.value,
      type: this.editForm.get(["type"])!.value,
      subType: this.editForm.get(["subType"])!.value,
      tableName: this.editForm.get(["tableName"])!.value,
      description: this.editForm.get(["description"])!.value,
      descriptionParameter: this.editForm.get(["descriptionParameter"])!.value,
      freeField1: this.editForm.get(["freeField1"])!.value,
      freeField2: this.editForm.get(["freeField2"])!.value,
      freeField3: this.editForm.get(["freeField3"])!.value,
      createdDate: this.editForm.get(["createdDate"])!.value,
      createdBy: this.editForm.get(["createdBy"])!.value,
      lastModified: this.editForm.get(["lastModified"])!.value,
      lastModifiedBy: this.editForm.get(["lastModifiedBy"])!.value,
    };
  }
}
