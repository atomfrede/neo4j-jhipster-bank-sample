import { IOperation } from 'app/shared/model/test-root/operation.model';

export interface ILabel {
  id?: string;
  labelName?: string;
  operations?: IOperation[];
}

export class Label implements ILabel {
  constructor(public id?: string, public labelName?: string, public operations?: IOperation[]) {}
}
