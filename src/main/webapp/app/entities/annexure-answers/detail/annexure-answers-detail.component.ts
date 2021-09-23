import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IAnnexureAnswers } from "../annexure-answers.model";

@Component({
  selector: "jhi-annexure-answers-detail",
  templateUrl: "./annexure-answers-detail.component.html",
})
export class AnnexureAnswersDetailComponent implements OnInit {
  annexureAnswers: IAnnexureAnswers | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ annexureAnswers }) => {
      this.annexureAnswers = annexureAnswers;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
