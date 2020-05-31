import {element, by, ElementFinder, browser, $} from 'protractor';
import E2EUtils from '../utils';
import {isBoolean, log} from 'util';
import {SchoolClassesListPageObjects} from './schoolClasses.list.objects';

export class SchoolClassEditPageObjects {
    public submit_Button = element(by.id('submit'));
    public name_Input = element(by.id('name'));
    public customName_Input = element(by.id('customName'));
    public grades_Select = element(by.id('grades'));
    public gradeSeries_Select = element(by.id('gradeSeries'));
    public notation_Select = element(by.id('notation'));
    public coursesStartDate_Input = element(by.id('coursesStartDate'));
    public coursesEndDate_Input = element(by.id('coursesEndDate'));
    public headTeachers_Select = element(by.id('headTeachers'));
    public addCouncilDate_Button = element(by.id('addCouncilDate'));

    getCouncilDate_Input(councilDateIndex: number): ElementFinder {
        return element(by.id('councilDate-' + (councilDateIndex)));
    }

    getCouncilTime_Input(councilTimeIndex: number): ElementFinder {
        return element(by.id('councilTime-' + (councilTimeIndex)));
    }

    fillInfos(name: string, customName: string, gradeName: string, gradeSerieName: string,
              notation: string, coursesStartDate: string, coursesEndDate: string, headTeachers: Array<string>,
              councilDates: Array<any>) {
        this.name_Input.sendKeys(name);
        this.customName_Input.sendKeys(customName);

        const body = $('body');

        E2EUtils.selectOptionByOptionValue('grades', [gradeName]);
        body.click();
        if (gradeSerieName) {
            E2EUtils.selectOptionByOptionValue('gradeSeries', [gradeSerieName]);
            body.click();
        }
        E2EUtils.selectOptionByOptionValue('notation', [notation]);
        body.click();
        this.coursesStartDate_Input.sendKeys(coursesStartDate);
        this.coursesEndDate_Input.sendKeys(coursesEndDate);
        browser.sleep(120000);
        E2EUtils.selectOptionByOptionValue('headTeachers', headTeachers);
        body.click();
        councilDates.forEach((councilDate, index) => {
            if (index > 0) {
                this.addCouncilDate_Button.click();
            }
            const councilDateElement = this.getCouncilDate_Input(index);
            councilDateElement.sendKeys(councilDate.date);
            const councilTimeElement = this.getCouncilTime_Input(index);
            councilTimeElement.sendKeys(councilDate.time);
        });
    }
}
