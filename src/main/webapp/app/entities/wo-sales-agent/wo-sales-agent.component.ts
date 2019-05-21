import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { AccountService } from 'app/core';
import { WoSalesAgentService } from './wo-sales-agent.service';

@Component({
  selector: 'jhi-wo-sales-agent',
  templateUrl: './wo-sales-agent.component.html'
})
export class WoSalesAgentComponent implements OnInit, OnDestroy {
  woSalesAgents: IWoSalesAgent[];
  currentAccount: any;
  eventSubscriber: Subscription;

  constructor(
    protected woSalesAgentService: WoSalesAgentService,
    protected jhiAlertService: JhiAlertService,
    protected eventManager: JhiEventManager,
    protected accountService: AccountService
  ) {}

  loadAll() {
    this.woSalesAgentService
      .query()
      .pipe(
        filter((res: HttpResponse<IWoSalesAgent[]>) => res.ok),
        map((res: HttpResponse<IWoSalesAgent[]>) => res.body)
      )
      .subscribe(
        (res: IWoSalesAgent[]) => {
          this.woSalesAgents = res;
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInWoSalesAgents();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IWoSalesAgent) {
    return item.id;
  }

  registerChangeInWoSalesAgents() {
    this.eventSubscriber = this.eventManager.subscribe('woSalesAgentListModification', response => this.loadAll());
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
