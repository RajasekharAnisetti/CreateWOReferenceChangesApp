import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CreateWoReferenceChangesAppSharedModule } from 'app/shared';
import {
  InsuranceTypeComponent,
  InsuranceTypeDetailComponent,
  InsuranceTypeUpdateComponent,
  InsuranceTypeDeletePopupComponent,
  InsuranceTypeDeleteDialogComponent,
  insuranceTypeRoute,
  insuranceTypePopupRoute
} from './';

const ENTITY_STATES = [...insuranceTypeRoute, ...insuranceTypePopupRoute];

@NgModule({
  imports: [CreateWoReferenceChangesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    InsuranceTypeComponent,
    InsuranceTypeDetailComponent,
    InsuranceTypeUpdateComponent,
    InsuranceTypeDeleteDialogComponent,
    InsuranceTypeDeletePopupComponent
  ],
  entryComponents: [
    InsuranceTypeComponent,
    InsuranceTypeUpdateComponent,
    InsuranceTypeDeleteDialogComponent,
    InsuranceTypeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CreateWoReferenceChangesAppInsuranceTypeModule {}
