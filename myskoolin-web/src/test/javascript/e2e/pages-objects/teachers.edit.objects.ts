import {element, by, ElementFinder, browser, $} from 'protractor';
import E2EUtils from '../utils';
import {isBoolean, log} from 'util';

export class TeacherEditPageObjects {
    public submit_Button = element(by.id('submit'));
    public login_Input = element(by.id('login'));
    public firstName_Input = element(by.id('firstName'));
    public lastName_Input = element(by.id('lastName'));
    public teacherSex_male_Radio = element(by.id('teacherSex-0'));
    public teacherSex_female_Radio = element(by.id('teacherSex-1'));
    public nationality_Input = element(by.id('nationality'));
    public birthDate_Input = element(by.id('birthDate'));
    public homePhone_Input = element(by.id('homePhone'));
    public cellPhone_Input = element(by.id('cellPhone'));
    public proPhone_Input = element(by.id('proPhone'));
    public proCellPhone_Input = element(by.id('proCellPhone'));
    public email_Input = element(by.id('email'));
    public proEmail_Input = element(by.id('proEmail'));
    public addressName_Input = element(by.id('addressName'));
    public postalCode_Input = element(by.id('postalCode'));
    public city_Input = element(by.id('city'));
    public country_Input = element(by.id('country'));
    public substitute_Switch = element(by.id('substitute'));
    public employedDate_Input = element(by.id('employedDate'));
    public exitDate_Input = element(by.id('exitDate'));
    public exitReason_Input = element(by.id('exitReason'));
    public comment_Textarea = element(by.id('comment'));

    fillInfos(login: string, firstName: string, lastName: string, sex: string, nationality: string, birthDate: string,
              homePhone: string, cellPhone: string, proPhone: string, proCellPhone: string, email: string,
              proEmail: string, addressName: string, postalCode: string, city: string, country: string,
              substitute: boolean, substitutedTeachers: Array<string>, grades: Array<string>, taughtSubjects: Array<string>, employedDate: string,
              exitDate: string, exitReason: string, comment: string) {
        this.login_Input.sendKeys(login);
        this.firstName_Input.sendKeys(firstName);
        this.lastName_Input.sendKeys(lastName);
        switch (sex) {
            case 'male':
                this.teacherSex_male_Radio.click();
                break;
            case 'female':
                this.teacherSex_female_Radio.click();
                break;
        }
        this.nationality_Input.sendKeys(nationality);
        this.birthDate_Input.sendKeys(birthDate);
        this.homePhone_Input.sendKeys(homePhone);
        this.cellPhone_Input.sendKeys(cellPhone);
        this.proPhone_Input.sendKeys(proPhone);
        this.proCellPhone_Input.sendKeys(proCellPhone);
        this.email_Input.sendKeys(email);
        this.proEmail_Input.sendKeys(proEmail);
        this.addressName_Input.sendKeys(addressName);
        this.postalCode_Input.sendKeys(postalCode);
        this.city_Input.sendKeys(city);
        this.country_Input.sendKeys(country);
        if (substitute) {
            this.substitute_Switch.click();
        }
        const body = $('body');

        // grades
        E2EUtils.selectOptionByOptionValue('grades', grades);
        body.click();

        // taughtSubjects
        E2EUtils.selectOptionByOptionValue('taughtSubjects', taughtSubjects);
        body.click();

        this.employedDate_Input.sendKeys(employedDate);
        if (exitDate) {
            this.exitDate_Input.sendKeys(exitDate);
            this.exitReason_Input.sendKeys(exitReason);
        }
        this.comment_Textarea.sendKeys(comment);

    }
}
