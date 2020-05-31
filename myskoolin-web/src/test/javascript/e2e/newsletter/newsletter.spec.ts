import {browser} from 'protractor';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';
import {PageUtils} from '../pages-objects/jhi-page-objects-utils';

describe('Newsletter e2e test', () => {

    let landingpage: LandingPage;
    // let contactComponentsPage: ContactComponentsPage;
    //
    beforeAll(() => {

        browser.get('/');
        browser.waitForAngular();
        landingpage = new LandingPage();
    });
    it('ignored newsletter tests', () => {
    });

    // it('should display error message because of empty newsletter input', () => {
    //     landingpage.clickOnNewsletterMenu();
    //     landingpage.clickOnNewsletterInput();
    //     PageUtils.clickElsewhere(landingpage.newsletterButton, 20);
    //     expect(landingpage.newsletterError.isPresent()).toBeTruthy();
    // });
    //
    // it('should display newsletter error message because of invalid email', () => {
    //     landingpage.clickOnNewsletterMenu();
    //     landingpage.clickOnNewsletterInput();
    //     landingpage.typeOnNewsletterInput('sssss@');
    //     expect(landingpage.newsletterError.isPresent()).toBeTruthy();
    // });
    //
    // it('should display newsletter confirmation ok', () => {
    //     landingpage.subscribeToNewsletter('salim.chami@gmail.com');
    //     expect(landingpage.newsletterConfirmation.isPresent()).toBeTruthy();
    // });

    // landingpage.typeOnNewsletterInput("salim.chami@protonmail.com");
    // it('should load create Contact dialog', () => {
    //     contactComponentsPage.clickOnCreateButton();
    //     contactDialogPage = new ContactDialogPage();
    //     expect(contactDialogPage.getModalTitle())
    //         .toMatch(/schoolmeApp.contact.home.createOrEditLabel/);
    //     contactDialogPage.close();
    // });
    //
    // it('should create and save Contacts', () => {
    //     contactComponentsPage.clickOnCreateButton();
    //     contactDialogPage.setNameInput('name');
    //     expect(contactDialogPage.getNameInput()).toMatch('name');
    //     contactDialogPage.save();
    //     expect(contactDialogPage.getSaveButton().isPresent()).toBeFalsy();
    // });

    afterAll(() => {
        // navBarPage.autoSignOut();
    });
});

export class ContactComponentsPage {
    // createButton = element(by.css('.jh-create-entity'));
    // title = element.all(by.css('jhi-contact div h2 span')).first();
    //
    // clickOnCreateButton() {
    //     return this.createButton.click();
    // }
    //
    // getTitle() {
    //     return this.title.getAttribute('jhiTranslate');
    // }
}
