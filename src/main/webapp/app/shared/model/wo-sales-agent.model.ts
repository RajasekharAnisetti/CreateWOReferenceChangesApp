import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

export interface IWoSalesAgent {
  id?: number;
  agentName?: string;
  promoCode?: string;
  woWorkOrders?: IWoWorkOrder[];
}

export class WoSalesAgent implements IWoSalesAgent {
  constructor(public id?: number, public agentName?: string, public promoCode?: string, public woWorkOrders?: IWoWorkOrder[]) {}
}
