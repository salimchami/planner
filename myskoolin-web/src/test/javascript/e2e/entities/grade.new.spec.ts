import {browser} from 'protractor';
import {GradeEditObjects} from '../pages-objects/grade.edit.objects';
import {PageUtils} from '../pages-objects/jhi-page-objects-utils';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';

describe('Newsletter e2e test', () => {

    let newGrade: GradeEditObjects;
    let landingpage: LandingPage;
    // let contactComponentsPage: ContactComponentsPage;
    //
    beforeAll(() => {
        landingpage = new LandingPage();
        browser.get('/');
        landingpage.loginAdmin();
        browser.get('#/grades/new');
        browser.waitForAngular();
        newGrade = new GradeEditObjects();
    });

    it('should create a grade with series', () => {
        newGrade.fillBasicItems('the grade 1', 'G1', 58, 0);
        newGrade.clickAddSerie1();

        browser.waitForAngular();

        newGrade.typeSerieName(0, 'AAAA');
        newGrade.typeSerieDiminutive(0, 'AA');
        newGrade.typeSerieOption(0, 'the option');

        newGrade.clickAddSerie2();

        newGrade.typeSerieName(1, 'BAAAA');
        newGrade.typeSerieDiminutive(1, 'BAA');
        newGrade.typeSerieOption(1, 'the option 2');

        expect(newGrade.submitButton.isEnabled).toBeTruthy();
        newGrade.clickSubmitButton();
        // PageUtils.getInputText(newGrade.nameInput, (text) => {
        //     expect(text).toEqual('the grade 1');
        // });
        PageUtils.sleep(10000);
    });
});
