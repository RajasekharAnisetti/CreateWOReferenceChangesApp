export interface ILocationType {
  id?: number;
  name?: string;
}

export class LocationType implements ILocationType {
  constructor(public id?: number, public name?: string) {}
}
