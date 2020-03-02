import { browser, ExpectedConditions as ec } from 'protractor';
import { NavBarPage, SignInPage } from '../../../page-objects/jhi-page-objects';

import { LabelComponentsPage } from './label.page-object';

const expect = chai.expect;

describe('Label e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let labelComponentsPage: LabelComponentsPage;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Labels', async () => {
    await navBarPage.goToEntity('label');
    labelComponentsPage = new LabelComponentsPage();
    await browser.wait(ec.visibilityOf(labelComponentsPage.title), 5000);
    expect(await labelComponentsPage.getTitle()).to.eq('sampleNeo4JNoCacheApp.testRootLabel.home.title');
    await browser.wait(ec.or(ec.visibilityOf(labelComponentsPage.entities), ec.visibilityOf(labelComponentsPage.noResult)), 1000);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
