import {browser} from 'protractor';
import {TeacherEditPageObjects} from '../pages-objects/teachers.edit.objects';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';
import E2EUtils from '../utils';
import {MenuItemsObjects} from '../pages-objects/menu-items.objects';
import {TeacherListPageObjects} from '../pages-objects/teachers.list.objects';

describe('Teachers - new teacher form test', () => {

    let teacherListPage: TeacherListPageObjects;
    let newTeacherPage: TeacherEditPageObjects;
    let landingpage: LandingPage;

    beforeAll(() => {
        landingpage = new LandingPage();
        teacherListPage = new TeacherListPageObjects();
        newTeacherPage = new TeacherEditPageObjects();

        browser.manage().window().maximize();

        browser.get('/');
        browser.waitForAngular();
        landingpage.loginAdmin();
        MenuItemsObjects.menuItem_click('Professeurs');
        teacherListPage.createNewTeacher();
    });

    it('should create a teacher', () => {
        newTeacherPage.fillInfos('jacques.leprof', 'jacques', 'leprof', 'male', 'francaise',
            '15/05/1980', '02158574', '06215487', '01022336', '0104201589',
            'jacquesleprf@email.com', 'jacquesleprf@proemail.com', 'ladresse de jacques', '75000',
            'Paris', 'France', false, [], ['Terminale (Tle)'], ['(Terminale) EPS'], '01/08/2015',
            null, null, 'RAS');
        E2EUtils.scrollToTop();
        newTeacherPage.submit_Button.click();
        browser.sleep(800000);
    });
});
