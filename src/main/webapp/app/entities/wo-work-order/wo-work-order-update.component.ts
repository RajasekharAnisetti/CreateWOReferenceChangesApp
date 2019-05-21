import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IWoWorkOrder, WoWorkOrder } from 'app/shared/model/wo-work-order.model';
import { WoWorkOrderService } from './wo-work-order.service';
import { ILocationType } from 'app/shared/model/location-type.model';
import { LocationTypeService } from 'app/entities/location-type';
import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent';
import { IInsuranceType } from 'app/shared/model/insurance-type.model';
import { InsuranceTypeService } from 'app/entities/insurance-type';

@Component({
  selector: 'jhi-wo-work-order-update',
  templateUrl: './wo-work-order-update.component.html'
})
export class WoWorkOrderUpdateComponent implements OnInit {
  woWorkOrder: IWoWorkOrder;
  isSaving: boolean;

  locationtypes: ILocationType[];

  wosalesagents: IWoSalesAgent[];

  insurancetypes: IInsuranceType[];
  shippingDateDp: any;
  jobDeadlineDateDp: any;

  editForm = this.fb.group({
    id: [],
    customsBrokerName: [null, [Validators.maxLength(255)]],
    customsContactName: [null, [Validators.maxLength(255)]],
    customsCurrency: [null, [Validators.maxLength(255)]],
    customsPhoneNumber: [null, [Validators.maxLength(255)]],
    customsValue: [],
    fromAirportCode: [null, [Validators.maxLength(255)]],
    jobContentDescr: [null, [Validators.maxLength(255)]],
    shipQuantity: [null, [Validators.max(11)]],
    jobOriginalCost: [null, [Validators.max(11)]],
    quotedAmount: [null, [Validators.maxLength(255)]],
    assignTo: [null, [Validators.maxLength(255)]],
    dateCreated: [],
    jobNumber: [null, [Validators.maxLength(255)]],
    jobCustomer: [null, [Validators.maxLength(255)]],
    jobDeadlineTime: [],
    jobTimeZone: [],
    shippingDate: [],
    jobDeadlineDate: [],
    shippingTime: [],
    isStorage: [],
    isPickPack: [],
    shipTotalWeight: [null, [Validators.maxLength(255)]],
    woRequestType: [null, [Validators.max(11)]],
    fromLocationId: [],
    toLocationId: [],
    woSalesAgentId: [],
    insuranceTypeId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected woWorkOrderService: WoWorkOrderService,
    protected locationTypeService: LocationTypeService,
    protected woSalesAgentService: WoSalesAgentService,
    protected insuranceTypeService: InsuranceTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ woWorkOrder }) => {
      this.updateForm(woWorkOrder);
      this.woWorkOrder = woWorkOrder;
    });
    this.locationTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ILocationType[]>) => mayBeOk.ok),
        map((response: HttpResponse<ILocationType[]>) => response.body)
      )
      .subscribe((res: ILocationType[]) => (this.locationtypes = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.woSalesAgentService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IWoSalesAgent[]>) => mayBeOk.ok),
        map((response: HttpResponse<IWoSalesAgent[]>) => response.body)
      )
      .subscribe((res: IWoSalesAgent[]) => (this.wosalesagents = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.insuranceTypeService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IInsuranceType[]>) => mayBeOk.ok),
        map((response: HttpResponse<IInsuranceType[]>) => response.body)
      )
      .subscribe((res: IInsuranceType[]) => (this.insurancetypes = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(woWorkOrder: IWoWorkOrder) {
    this.editForm.patchValue({
      id: woWorkOrder.id,
      customsBrokerName: woWorkOrder.customsBrokerName,
      customsContactName: woWorkOrder.customsContactName,
      customsCurrency: woWorkOrder.customsCurrency,
      customsPhoneNumber: woWorkOrder.customsPhoneNumber,
      customsValue: woWorkOrder.customsValue,
      fromAirportCode: woWorkOrder.fromAirportCode,
      jobContentDescr: woWorkOrder.jobContentDescr,
      shipQuantity: woWorkOrder.shipQuantity,
      jobOriginalCost: woWorkOrder.jobOriginalCost,
      quotedAmount: woWorkOrder.quotedAmount,
      assignTo: woWorkOrder.assignTo,
      dateCreated: woWorkOrder.dateCreated != null ? woWorkOrder.dateCreated.format(DATE_TIME_FORMAT) : null,
      jobNumber: woWorkOrder.jobNumber,
      jobCustomer: woWorkOrder.jobCustomer,
      jobDeadlineTime: woWorkOrder.jobDeadlineTime,
      jobTimeZone: woWorkOrder.jobTimeZone,
      shippingDate: woWorkOrder.shippingDate,
      jobDeadlineDate: woWorkOrder.jobDeadlineDate,
      shippingTime: woWorkOrder.shippingTime,
      isStorage: woWorkOrder.isStorage,
      isPickPack: woWorkOrder.isPickPack,
      shipTotalWeight: woWorkOrder.shipTotalWeight,
      woRequestType: woWorkOrder.woRequestType,
      fromLocationId: woWorkOrder.fromLocationId,
      toLocationId: woWorkOrder.toLocationId,
      woSalesAgentId: woWorkOrder.woSalesAgentId,
      insuranceTypeId: woWorkOrder.insuranceTypeId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const woWorkOrder = this.createFromForm();
    if (woWorkOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.woWorkOrderService.update(woWorkOrder));
    } else {
      this.subscribeToSaveResponse(this.woWorkOrderService.create(woWorkOrder));
    }
  }

  private createFromForm(): IWoWorkOrder {
    const entity = {
      ...new WoWorkOrder(),
      id: this.editForm.get(['id']).value,
      customsBrokerName: this.editForm.get(['customsBrokerName']).value,
      customsContactName: this.editForm.get(['customsContactName']).value,
      customsCurrency: this.editForm.get(['customsCurrency']).value,
      customsPhoneNumber: this.editForm.get(['customsPhoneNumber']).value,
      customsValue: this.editForm.get(['customsValue']).value,
      fromAirportCode: this.editForm.get(['fromAirportCode']).value,
      jobContentDescr: this.editForm.get(['jobContentDescr']).value,
      shipQuantity: this.editForm.get(['shipQuantity']).value,
      jobOriginalCost: this.editForm.get(['jobOriginalCost']).value,
      quotedAmount: this.editForm.get(['quotedAmount']).value,
      assignTo: this.editForm.get(['assignTo']).value,
      dateCreated:
        this.editForm.get(['dateCreated']).value != null ? moment(this.editForm.get(['dateCreated']).value, DATE_TIME_FORMAT) : undefined,
      jobNumber: this.editForm.get(['jobNumber']).value,
      jobCustomer: this.editForm.get(['jobCustomer']).value,
      jobDeadlineTime: this.editForm.get(['jobDeadlineTime']).value,
      jobTimeZone: this.editForm.get(['jobTimeZone']).value,
      shippingDate: this.editForm.get(['shippingDate']).value,
      jobDeadlineDate: this.editForm.get(['jobDeadlineDate']).value,
      shippingTime: this.editForm.get(['shippingTime']).value,
      isStorage: this.editForm.get(['isStorage']).value,
      isPickPack: this.editForm.get(['isPickPack']).value,
      shipTotalWeight: this.editForm.get(['shipTotalWeight']).value,
      woRequestType: this.editForm.get(['woRequestType']).value,
      fromLocationId: this.editForm.get(['fromLocationId']).value,
      toLocationId: this.editForm.get(['toLocationId']).value,
      woSalesAgentId: this.editForm.get(['woSalesAgentId']).value,
      insuranceTypeId: this.editForm.get(['insuranceTypeId']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoWorkOrder>>) {
    result.subscribe((res: HttpResponse<IWoWorkOrder>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackLocationTypeById(index: number, item: ILocationType) {
    return item.id;
  }

  trackWoSalesAgentById(index: number, item: IWoSalesAgent) {
    return item.id;
  }

  trackInsuranceTypeById(index: number, item: IInsuranceType) {
    return item.id;
  }
}
