import {browser} from 'protractor';
import {StudentsEditObjects} from '../pages-objects/students.edit.objects';
import {LandingPage} from '../pages-objects/jhi-landingpage-objects';
import E2EUtils from '../utils';

describe('Students - new student form test', () => {

    let newStudentPage: StudentsEditObjects;
    let landingpage: LandingPage;

    beforeAll(() => {
        browser.manage().window().maximize();

        landingpage = new LandingPage();
        browser.get('/');
        landingpage.loginAdmin();
        browser.get('#/students/new');
        browser.waitForAngular();
        newStudentPage = new StudentsEditObjects();
    });

    it('should create a student', () => {
        newStudentPage.fillBasicInfos('firstname.lastname', 'firstname', 'lastname', 'female',
            'francaise', 'email@email.com', '0613435085', '5888779966',
            '05/12/2000', 'the address', '75010', 'Paris', 'France', 'RAS');
        // next
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_0_Button.click();
        // scholar infos
        newStudentPage.fillScholarInfos('454456666', false, '01/09/2014', 'L\'ancien Lycée');
        newStudentPage.fillScholarInfosPlans(false, '', false, '');
        // next
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_1_Button.click();
        // responsible 1
        newStudentPage.fillResponsible_firtPart(1, 'rolf', 'hegdal', 'male', 'américaine', '22/05/1965', 'ljan terrasse 346',
            '3095', 'vear', 'rogaland', '66976498', '40652479', 'rolf.hegdal@example.com',
            'rolf.hegdal@example2.com');
        E2EUtils.scrollToTop();
        newStudentPage.fillRespLogin('the.resp.login');
        newStudentPage.fillResponsible_secondPart(1, 0, 'Père', 2, 'Ingénieur', 'proemail@example.com',
            '0225888787897', true, 'Président', 'RAS');
        // add responsible
        newStudentPage.getAddResponsible_Button(1).click();
        // responsible 2
        newStudentPage.fillResponsible_firtPart(2, 'rolfa', 'hegdal', 'male', 'américaine', '22/05/1965', 'ljan terrasse 346',
            '3095', 'vear', 'rogaland', '66976498', '40652479', 'rolf.hegdal@example.com',
            'rolf.hegdal@example2.com');
        E2EUtils.scrollToTop();
        newStudentPage.fillResponsible_secondPart(2, 0, 'Père', 2, 'Ingénieur', 'proemail@example.com',
            '0225888787897', true, 'Président', 'RAS');
        // next
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_2_Button.click();
        newStudentPage.fillMedicalInfos('RAS', 'RAS', 'RAS', true, 'insurance',
            'the contact', 'reference',
            true, 'insurance', 'the contact', 'reference');
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_3_Button.click();
        newStudentPage.fillCanteenInfos(true, true, true, true, ['Lundi', 'Mardi', 'Mercredi', 'Jeudi', 'Vendredi'],
            '01/02/2019', '31/07/2019');
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_4_Button.click();
        newStudentPage.fillResidentialSchoolInfos(true, '100225', '02/01/2019', '01/09/2019');
        E2EUtils.scrollToTop();
        newStudentPage.nextStep_5_Button.click();
        newStudentPage.submit_Button.click();
        browser.sleep(800000);
    });

});
