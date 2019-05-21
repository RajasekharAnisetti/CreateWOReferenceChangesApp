/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CreateWoReferenceChangesAppTestModule } from '../../../test.module';
import { WoSalesAgentDeleteDialogComponent } from 'app/entities/wo-sales-agent/wo-sales-agent-delete-dialog.component';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent/wo-sales-agent.service';

describe('Component Tests', () => {
  describe('WoSalesAgent Management Delete Component', () => {
    let comp: WoSalesAgentDeleteDialogComponent;
    let fixture: ComponentFixture<WoSalesAgentDeleteDialogComponent>;
    let service: WoSalesAgentService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CreateWoReferenceChangesAppTestModule],
        declarations: [WoSalesAgentDeleteDialogComponent]
      })
        .overrideTemplate(WoSalesAgentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesAgentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesAgentService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
