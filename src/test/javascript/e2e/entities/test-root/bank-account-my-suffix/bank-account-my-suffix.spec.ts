import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { BankAccountComponentsPage, BankAccountDeleteDialog, BankAccountUpdatePage } from './bank-account-my-suffix.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('BankAccount e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let bankAccountComponentsPage: BankAccountComponentsPage;
  let bankAccountUpdatePage: BankAccountUpdatePage;
  let bankAccountDeleteDialog: BankAccountDeleteDialog;
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load BankAccounts', async () => {
    await navBarPage.goToEntity('bank-account-my-suffix');
    bankAccountComponentsPage = new BankAccountComponentsPage();
    await browser.wait(ec.visibilityOf(bankAccountComponentsPage.title), 5000);
    expect(await bankAccountComponentsPage.getTitle()).to.eq('sampleNeo4JNoCacheApp.testRootBankAccount.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(bankAccountComponentsPage.entities), ec.visibilityOf(bankAccountComponentsPage.noResult)),
      1000
    );
  });

  it('should load create BankAccount page', async () => {
    await bankAccountComponentsPage.clickOnCreateButton();
    bankAccountUpdatePage = new BankAccountUpdatePage();
    expect(await bankAccountUpdatePage.getPageTitle()).to.eq('sampleNeo4JNoCacheApp.testRootBankAccount.home.createOrEditLabel');
    await bankAccountUpdatePage.cancel();
  });

  it('should create and save BankAccounts', async () => {
    const nbButtonsBeforeCreate = await bankAccountComponentsPage.countDeleteButtons();

    await bankAccountComponentsPage.clickOnCreateButton();

    await promise.all([
      bankAccountUpdatePage.setNameInput('name'),
      bankAccountUpdatePage.setGuidInput('64c99148-3908-465d-8c4a-e510e3ade974'),
      bankAccountUpdatePage.setBankNumberInput('5'),
      bankAccountUpdatePage.setAgencyNumberInput('5'),
      bankAccountUpdatePage.setLastOperationDurationInput('5'),
      bankAccountUpdatePage.setMeanOperationDurationInput('5'),
      bankAccountUpdatePage.setMeanQueueDurationInput('PT12S'),
      bankAccountUpdatePage.setBalanceInput('5'),
      bankAccountUpdatePage.setOpeningDayInput('2000-12-31'),
      bankAccountUpdatePage.setLastOperationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      bankAccountUpdatePage.accountTypeSelectLastOption(),
      bankAccountUpdatePage.setAttachmentInput(absolutePath),
      bankAccountUpdatePage.setDescriptionInput('description'),
      bankAccountUpdatePage.userSelectLastOption()
    ]);

    expect(await bankAccountUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await bankAccountUpdatePage.getGuidInput()).to.eq(
      '64c99148-3908-465d-8c4a-e510e3ade974',
      'Expected Guid value to be equals to 64c99148-3908-465d-8c4a-e510e3ade974'
    );
    expect(await bankAccountUpdatePage.getBankNumberInput()).to.eq('5', 'Expected bankNumber value to be equals to 5');
    expect(await bankAccountUpdatePage.getAgencyNumberInput()).to.eq('5', 'Expected agencyNumber value to be equals to 5');
    expect(await bankAccountUpdatePage.getLastOperationDurationInput()).to.eq(
      '5',
      'Expected lastOperationDuration value to be equals to 5'
    );
    expect(await bankAccountUpdatePage.getMeanOperationDurationInput()).to.eq(
      '5',
      'Expected meanOperationDuration value to be equals to 5'
    );
    expect(await bankAccountUpdatePage.getMeanQueueDurationInput()).to.contain('12', 'Expected meanQueueDuration value to be equals to 12');
    expect(await bankAccountUpdatePage.getBalanceInput()).to.eq('5', 'Expected balance value to be equals to 5');
    expect(await bankAccountUpdatePage.getOpeningDayInput()).to.eq('2000-12-31', 'Expected openingDay value to be equals to 2000-12-31');
    expect(await bankAccountUpdatePage.getLastOperationDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastOperationDate value to be equals to 2000-12-31'
    );
    const selectedActive = bankAccountUpdatePage.getActiveInput();
    if (await selectedActive.isSelected()) {
      await bankAccountUpdatePage.getActiveInput().click();
      expect(await bankAccountUpdatePage.getActiveInput().isSelected(), 'Expected active not to be selected').to.be.false;
    } else {
      await bankAccountUpdatePage.getActiveInput().click();
      expect(await bankAccountUpdatePage.getActiveInput().isSelected(), 'Expected active to be selected').to.be.true;
    }
    expect(await bankAccountUpdatePage.getAttachmentInput()).to.endsWith(
      fileNameToUpload,
      'Expected Attachment value to be end with ' + fileNameToUpload
    );
    expect(await bankAccountUpdatePage.getDescriptionInput()).to.eq(
      'description',
      'Expected Description value to be equals to description'
    );

    await bankAccountUpdatePage.save();
    expect(await bankAccountUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await bankAccountComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last BankAccount', async () => {
    const nbButtonsBeforeDelete = await bankAccountComponentsPage.countDeleteButtons();
    await bankAccountComponentsPage.clickOnLastDeleteButton();

    bankAccountDeleteDialog = new BankAccountDeleteDialog();
    expect(await bankAccountDeleteDialog.getDialogTitle()).to.eq('sampleNeo4JNoCacheApp.testRootBankAccount.delete.question');
    await bankAccountDeleteDialog.clickOnConfirmButton();

    expect(await bankAccountComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
