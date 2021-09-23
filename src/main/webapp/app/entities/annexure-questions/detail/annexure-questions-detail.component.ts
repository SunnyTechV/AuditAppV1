import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

import { IAnnexureQuestions } from "../annexure-questions.model";

@Component({
  selector: "jhi-annexure-questions-detail",
  templateUrl: "./annexure-questions-detail.component.html",
})
export class AnnexureQuestionsDetailComponent implements OnInit {
  annexureQuestions: IAnnexureQuestions | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ annexureQuestions }) => {
      this.annexureQuestions = annexureQuestions;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
