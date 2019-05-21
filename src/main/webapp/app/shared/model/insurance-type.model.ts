import { IWoWorkOrder } from 'app/shared/model/wo-work-order.model';

export interface IInsuranceType {
  id?: number;
  name?: string;
  woWorkOrders?: IWoWorkOrder[];
}

export class InsuranceType implements IInsuranceType {
  constructor(public id?: number, public name?: string, public woWorkOrders?: IWoWorkOrder[]) {}
}
