import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SampleNeo4JNoCacheSharedModule } from 'app/shared/shared.module';
import { BankAccountMySuffixComponent } from './bank-account-my-suffix.component';
import { BankAccountMySuffixDetailComponent } from './bank-account-my-suffix-detail.component';
import { BankAccountMySuffixUpdateComponent } from './bank-account-my-suffix-update.component';
import { BankAccountMySuffixDeleteDialogComponent } from './bank-account-my-suffix-delete-dialog.component';
import { bankAccountRoute } from './bank-account-my-suffix.route';

@NgModule({
  imports: [SampleNeo4JNoCacheSharedModule, RouterModule.forChild(bankAccountRoute)],
  declarations: [
    BankAccountMySuffixComponent,
    BankAccountMySuffixDetailComponent,
    BankAccountMySuffixUpdateComponent,
    BankAccountMySuffixDeleteDialogComponent
  ],
  entryComponents: [BankAccountMySuffixDeleteDialogComponent]
})
export class SampleNeo4JNoCacheBankAccountMySuffixModule {}
