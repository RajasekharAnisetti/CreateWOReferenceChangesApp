import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';
import { WoSalesAgentComponent } from './wo-sales-agent.component';
import { WoSalesAgentDetailComponent } from './wo-sales-agent-detail.component';
import { WoSalesAgentUpdateComponent } from './wo-sales-agent-update.component';
import { WoSalesAgentDeletePopupComponent } from './wo-sales-agent-delete-dialog.component';
import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

@Injectable({ providedIn: 'root' })
export class WoSalesAgentResolve implements Resolve<IWoSalesAgent> {
  constructor(private service: WoSalesAgentService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IWoSalesAgent> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<WoSalesAgent>) => response.ok),
        map((woSalesAgent: HttpResponse<WoSalesAgent>) => woSalesAgent.body)
      );
    }
    return of(new WoSalesAgent());
  }
}

export const woSalesAgentRoute: Routes = [
  {
    path: '',
    component: WoSalesAgentComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoSalesAgents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WoSalesAgentDetailComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoSalesAgents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WoSalesAgentUpdateComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoSalesAgents'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WoSalesAgentUpdateComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoSalesAgents'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const woSalesAgentPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: WoSalesAgentDeletePopupComponent,
    resolve: {
      woSalesAgent: WoSalesAgentResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'WoSalesAgents'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
