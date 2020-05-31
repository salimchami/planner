import {element, by, browser} from 'protractor';
import {PageUtils} from '../pages-objects/jhi-page-objects-utils';

export class SubjectEditObjects {

    nameInput = element(by.id('name'));
    customNameInput = element(by.id('customName'));
    colorInput = element(by.id('color'));
    bgColorInput = element(by.id('bgColor'));
    gradeSelect = element(by.id('grade'));
    gradeSerieSelect = element(by.id('gradeSerie'));
    coefficientInput = element(by.id('coefficient'));
    foreignLanguageSelect = element(by.id('foreignLanguage'));

    maxMinutesPerDayInput = element(by.id('maxMinutesPerDay'));
    minutesPerWeekInput = element(by.id('minutesPerWeek'));
    coursesFrequencyPerWeekInput = element(by.id('coursesFrequencyPerWeek'));

    submitButton = element(by.id('submit'));

    typeOnNameInput(content) {
        this.nameInput.sendKeys(content);
    }

    typeOnCustomNameInput(content) {
        this.customNameInput.sendKeys(content);
    }

    typeOnColorInput(content) {
        this.colorInput.sendKeys(content);
    }

    typeOnBgColorInput(content) {
        this.bgColorInput.sendKeys(content);
    }

    selectGrade(index) {
        this.gradeSelect.element(by.id('grade-' + index)).click();
    }

    selectGradeSerie(index) {
        this.gradeSerieSelect.element(by.id('gradeSerie-' + index)).click();
    }

    typeOnCoefficientInput(content) {
        this.coefficientInput.sendKeys(content);
    }

    clickOnForeignLanguageSelector() {
        this.foreignLanguageSelect.click();
    }

    typeOnMaxMinutesByDayInput(content) {
        this.maxMinutesPerDayInput.sendKeys(content);
    }

    typeOnMinutesPerWeekInput(content) {
        this.minutesPerWeekInput.sendKeys(content);
    }

    typeOnCoursesFrequencyPerWeekInput(content) {
        this.coursesFrequencyPerWeekInput.sendKeys(content);
    }

    clickSubmitButton() {
        this.submitButton.click();
    }

    fillBasicData(name, customName, color, bgColor, coefficient, maxMinutesPerDay, minutesPerWeek, coursesFrequencyPerWeek) {
        this.typeOnNameInput(name);
        this.typeOnCustomNameInput(customName);
        this.typeOnColorInput(color);
        this.typeOnBgColorInput(bgColor);
        this.typeOnCoefficientInput(coefficient);
        this.typeOnMaxMinutesByDayInput(maxMinutesPerDay);
        this.typeOnMinutesPerWeekInput(minutesPerWeek);
        this.typeOnCoursesFrequencyPerWeekInput(coursesFrequencyPerWeek);

    }
}
