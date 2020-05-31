import {browser} from 'protractor';
import {PageUtils} from '../pages-objects/jhi-page-objects-utils';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';
import {SubjectEditObjects} from '../pages-objects/subject.edit.objects';

describe('Newsletter e2e test', () => {

    let newSubject: SubjectEditObjects;
    let landingpage: LandingPage;
    // let contactComponentsPage: ContactComponentsPage;
    //
    beforeAll(() => {
        landingpage = new LandingPage();
        browser.get('/');
        landingpage.loginAdmin();
        browser.get('#/subjects/new');
        browser.waitForAngular();
        newSubject = new SubjectEditObjects();
    });

    it('should create a grade with series', () => {
        newSubject.fillBasicData('the subject', 'subj1', '#ffffff', '#000000', 4, 240, 480, 4);
        newSubject.selectGrade(6);
        newSubject.selectGradeSerie(0);

        browser.waitForAngular();

        expect(newSubject.submitButton.isEnabled).toBeTruthy();
        newSubject.clickSubmitButton();
        // PageUtils.getInputText(newGrade.nameInput, (text) => {
        //     expect(text).toEqual('the grade 1');
        // });
        browser.waitForAngular();

        browser.get('#/subjects/view/the subject');

        PageUtils.sleep(10000);
    });
});
