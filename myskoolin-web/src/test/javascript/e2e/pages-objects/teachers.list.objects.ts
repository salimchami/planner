import {element, by, ElementFinder, browser, $} from 'protractor';
import E2EUtils from '../utils';
import {isBoolean, log} from 'util';

export class TeacherListPageObjects {
    public new_Button = element(by.id('newTeacher'));

    createNewTeacher() {
        this.new_Button.click();
    }
}
