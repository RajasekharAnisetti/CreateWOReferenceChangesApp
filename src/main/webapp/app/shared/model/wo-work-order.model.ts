import { Moment } from 'moment';

export interface IWoWorkOrder {
  id?: number;
  customsBrokerName?: string;
  customsContactName?: string;
  customsCurrency?: string;
  customsPhoneNumber?: string;
  customsValue?: number;
  fromAirportCode?: string;
  jobContentDescr?: string;
  shipQuantity?: number;
  jobOriginalCost?: number;
  quotedAmount?: string;
  assignTo?: string;
  dateCreated?: Moment;
  jobNumber?: string;
  jobCustomer?: string;
  jobDeadlineTime?: number;
  jobTimeZone?: number;
  shippingDate?: Moment;
  jobDeadlineDate?: Moment;
  shippingTime?: number;
  isStorage?: boolean;
  isPickPack?: boolean;
  shipTotalWeight?: string;
  woRequestType?: number;
  fromLocationId?: number;
  toLocationId?: number;
  woSalesAgentId?: number;
  insuranceTypeId?: number;
}

export class WoWorkOrder implements IWoWorkOrder {
  constructor(
    public id?: number,
    public customsBrokerName?: string,
    public customsContactName?: string,
    public customsCurrency?: string,
    public customsPhoneNumber?: string,
    public customsValue?: number,
    public fromAirportCode?: string,
    public jobContentDescr?: string,
    public shipQuantity?: number,
    public jobOriginalCost?: number,
    public quotedAmount?: string,
    public assignTo?: string,
    public dateCreated?: Moment,
    public jobNumber?: string,
    public jobCustomer?: string,
    public jobDeadlineTime?: number,
    public jobTimeZone?: number,
    public shippingDate?: Moment,
    public jobDeadlineDate?: Moment,
    public shippingTime?: number,
    public isStorage?: boolean,
    public isPickPack?: boolean,
    public shipTotalWeight?: string,
    public woRequestType?: number,
    public fromLocationId?: number,
    public toLocationId?: number,
    public woSalesAgentId?: number,
    public insuranceTypeId?: number
  ) {
    this.isStorage = this.isStorage || false;
    this.isPickPack = this.isPickPack || false;
  }
}
