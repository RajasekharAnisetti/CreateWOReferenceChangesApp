import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IWoSalesAgent, WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';

@Component({
  selector: 'jhi-wo-sales-agent-update',
  templateUrl: './wo-sales-agent-update.component.html'
})
export class WoSalesAgentUpdateComponent implements OnInit {
  woSalesAgent: IWoSalesAgent;
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    agentName: [null, [Validators.maxLength(255)]],
    promoCode: [null, [Validators.maxLength(255)]]
  });

  constructor(protected woSalesAgentService: WoSalesAgentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woSalesAgent }) => {
      this.updateForm(woSalesAgent);
      this.woSalesAgent = woSalesAgent;
    });
  }

  updateForm(woSalesAgent: IWoSalesAgent) {
    this.editForm.patchValue({
      id: woSalesAgent.id,
      agentName: woSalesAgent.agentName,
      promoCode: woSalesAgent.promoCode
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woSalesAgent = this.createFromForm();
    if (woSalesAgent.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesAgentService.update(woSalesAgent));
    } else {
      this.subscribeToSaveResponse(this.woSalesAgentService.create(woSalesAgent));
    }
  }

  private createFromForm(): IWoSalesAgent {
    const entity = {
      ...new WoSalesAgent(),
      id: this.editForm.get(['id']).value,
      agentName: this.editForm.get(['agentName']).value,
      promoCode: this.editForm.get(['promoCode']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesAgent>>) {
    result.subscribe((res: HttpResponse<IWoSalesAgent>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
