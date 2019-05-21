import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'wo-work-order',
        loadChildren: './wo-work-order/wo-work-order.module#CreateWoReferenceChangesAppWoWorkOrderModule'
      },
      {
        path: 'location-type',
        loadChildren: './location-type/location-type.module#CreateWoReferenceChangesAppLocationTypeModule'
      },
      {
        path: 'wo-sales-agent',
        loadChildren: './wo-sales-agent/wo-sales-agent.module#CreateWoReferenceChangesAppWoSalesAgentModule'
      },
      {
        path: 'insurance-type',
        loadChildren: './insurance-type/insurance-type.module#CreateWoReferenceChangesAppInsuranceTypeModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CreateWoReferenceChangesAppEntityModule {}
