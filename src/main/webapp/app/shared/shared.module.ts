import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import {
  CreateWoReferenceChangesAppSharedLibsModule,
  CreateWoReferenceChangesAppSharedCommonModule,
  JhiLoginModalComponent,
  HasAnyAuthorityDirective
} from './';

@NgModule({
  imports: [CreateWoReferenceChangesAppSharedLibsModule, CreateWoReferenceChangesAppSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [CreateWoReferenceChangesAppSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CreateWoReferenceChangesAppSharedModule {
  static forRoot() {
    return {
      ngModule: CreateWoReferenceChangesAppSharedModule
    };
  }
}
