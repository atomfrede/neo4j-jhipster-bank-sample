import { Moment } from 'moment';
import { IOperation } from 'app/shared/model/test-root/operation.model';
import { BankAccountType } from 'app/shared/model/enumerations/bank-account-type.model';

export interface IBankAccountMySuffix {
  id?: string;
  name?: string;
  guid?: string;
  bankNumber?: number;
  agencyNumber?: number;
  lastOperationDuration?: number;
  meanOperationDuration?: number;
  meanQueueDuration?: number;
  balance?: number;
  openingDay?: Moment;
  lastOperationDate?: Moment;
  active?: boolean;
  accountType?: BankAccountType;
  attachmentContentType?: string;
  attachment?: any;
  description?: any;
  userLogin?: string;
  userId?: string;
  operations?: IOperation[];
}

export class BankAccountMySuffix implements IBankAccountMySuffix {
  constructor(
    public id?: string,
    public name?: string,
    public guid?: string,
    public bankNumber?: number,
    public agencyNumber?: number,
    public lastOperationDuration?: number,
    public meanOperationDuration?: number,
    public meanQueueDuration?: number,
    public balance?: number,
    public openingDay?: Moment,
    public lastOperationDate?: Moment,
    public active?: boolean,
    public accountType?: BankAccountType,
    public attachmentContentType?: string,
    public attachment?: any,
    public description?: any,
    public userLogin?: string,
    public userId?: string,
    public operations?: IOperation[]
  ) {
    this.active = this.active || false;
  }
}
