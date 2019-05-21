/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CreateWoReferenceChangesAppTestModule } from '../../../test.module';
import { WoSalesAgentComponent } from 'app/entities/wo-sales-agent/wo-sales-agent.component';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent/wo-sales-agent.service';
import { WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

describe('Component Tests', () => {
  describe('WoSalesAgent Management Component', () => {
    let comp: WoSalesAgentComponent;
    let fixture: ComponentFixture<WoSalesAgentComponent>;
    let service: WoSalesAgentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [CreateWoReferenceChangesAppTestModule],
        declarations: [WoSalesAgentComponent],
        providers: []
      })
        .overrideTemplate(WoSalesAgentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesAgentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesAgentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesAgent(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesAgents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
