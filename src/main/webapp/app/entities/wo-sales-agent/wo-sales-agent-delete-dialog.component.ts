import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';

@Component({
  selector: 'jhi-wo-sales-agent-delete-dialog',
  templateUrl: './wo-sales-agent-delete-dialog.component.html'
})
export class WoSalesAgentDeleteDialogComponent {
  woSalesAgent: IWoSalesAgent;

  constructor(
    protected woSalesAgentService: WoSalesAgentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.woSalesAgentService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'woSalesAgentListModification',
        content: 'Deleted an woSalesAgent'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-wo-sales-agent-delete-popup',
  template: ''
})
export class WoSalesAgentDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ woSalesAgent }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(WoSalesAgentDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.woSalesAgent = woSalesAgent;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/wo-sales-agent', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/wo-sales-agent', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
