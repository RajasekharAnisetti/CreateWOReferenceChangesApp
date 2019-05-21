import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CreateWoReferenceChangesAppSharedModule } from 'app/shared';
import {
  WoSalesAgentComponent,
  WoSalesAgentDetailComponent,
  WoSalesAgentUpdateComponent,
  WoSalesAgentDeletePopupComponent,
  WoSalesAgentDeleteDialogComponent,
  woSalesAgentRoute,
  woSalesAgentPopupRoute
} from './';

const ENTITY_STATES = [...woSalesAgentRoute, ...woSalesAgentPopupRoute];

@NgModule({
  imports: [CreateWoReferenceChangesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    WoSalesAgentComponent,
    WoSalesAgentDetailComponent,
    WoSalesAgentUpdateComponent,
    WoSalesAgentDeleteDialogComponent,
    WoSalesAgentDeletePopupComponent
  ],
  entryComponents: [
    WoSalesAgentComponent,
    WoSalesAgentUpdateComponent,
    WoSalesAgentDeleteDialogComponent,
    WoSalesAgentDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CreateWoReferenceChangesAppWoSalesAgentModule {}
