import { NgModule } from '@angular/core';

import { CreateWoReferenceChangesAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
  imports: [CreateWoReferenceChangesAppSharedLibsModule],
  declarations: [JhiAlertComponent, JhiAlertErrorComponent],
  exports: [CreateWoReferenceChangesAppSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class CreateWoReferenceChangesAppSharedCommonModule {}
