import {element, by, ElementFinder, browser, $} from 'protractor';
import E2EUtils from '../utils';
import {isBoolean, log} from 'util';

export class SchoolClassesListPageObjects {
    public new_Button = element(by.id('newSchoolClass'));

    createNewSchoolClass() {
        this.new_Button.click();
    }
}
