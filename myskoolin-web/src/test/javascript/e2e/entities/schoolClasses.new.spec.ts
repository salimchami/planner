import {browser} from 'protractor';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';
import {MenuItemsObjects} from '../pages-objects/menu-items.objects';
import {SchoolClassesListPageObjects} from '../pages-objects/schoolClasses.list.objects';
import {SchoolClassEditPageObjects} from '../pages-objects/schoolClasses.edit.objects';
import E2EUtils from '../utils';

describe('SchoolClasses Tests - ', () => {

    let schoolClassListPage: SchoolClassesListPageObjects;
    let newSchoolClassPage: SchoolClassEditPageObjects;
    let landingpage: LandingPage;

    beforeAll(() => {
        landingpage = new LandingPage();
        schoolClassListPage = new SchoolClassesListPageObjects();
        newSchoolClassPage = new SchoolClassEditPageObjects();

        browser.manage().window().maximize();

        browser.get('/');
        browser.waitForAngular();
        landingpage.loginAdmin();
        MenuItemsObjects.menuItem_click('Classes');
        schoolClassListPage.createNewSchoolClass();
    });

    it('should create a school class', () => {

        const councilsDates = [{date: '15/12/2018', time: '18:30'}, {date: '15/03/2019', time: '18:30'}, {date: '12/06/2019', time: '18:30'}];

        newSchoolClassPage.fillInfos('Sixième 1', '6°1',
            'Sixième (6°)', null,
            'Trimestrielle', '01/09/2018', '02/07/2019',
            ['Angelina JOLIE (27/02/1978)'], councilsDates);
        E2EUtils.scrollToTop();
        newSchoolClassPage.submit_Button.click();
        browser.sleep(800000);
    });
});
