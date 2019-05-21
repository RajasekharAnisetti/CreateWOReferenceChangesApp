import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CreateWoReferenceChangesAppSharedModule } from 'app/shared';
import {
  LocationTypeComponent,
  LocationTypeDetailComponent,
  LocationTypeUpdateComponent,
  LocationTypeDeletePopupComponent,
  LocationTypeDeleteDialogComponent,
  locationTypeRoute,
  locationTypePopupRoute
} from './';

const ENTITY_STATES = [...locationTypeRoute, ...locationTypePopupRoute];

@NgModule({
  imports: [CreateWoReferenceChangesAppSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    LocationTypeComponent,
    LocationTypeDetailComponent,
    LocationTypeUpdateComponent,
    LocationTypeDeleteDialogComponent,
    LocationTypeDeletePopupComponent
  ],
  entryComponents: [
    LocationTypeComponent,
    LocationTypeUpdateComponent,
    LocationTypeDeleteDialogComponent,
    LocationTypeDeletePopupComponent
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CreateWoReferenceChangesAppLocationTypeModule {}
