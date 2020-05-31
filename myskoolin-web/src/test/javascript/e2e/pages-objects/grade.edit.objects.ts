import {element, by} from 'protractor';
import {PageUtils} from '../pages-objects/jhi-page-objects-utils';

export class GradeEditObjects {

    nameInput = element(by.id('name'));
    diminutiveInput = element(by.id('diminutive'));
    notationSelect = element(by.id('notation'));
    maxMinutesPerDayInput = element(by.id('maxMinutesPerDay'));
    addSerie1Button = element(by.id('add-serie-1'));
    addSerie2Button = element(by.id('add-serie-2'));
    submitButton = element(by.id('submit'));

    typeOnNameInput(content) {
        this.nameInput.sendKeys(content);
    }

    typeOnDiminutiveInput(content) {
        this.diminutiveInput.sendKeys(content);
    }

    typeOnMaxMinutesPerDayInput(content: number) {
        this.maxMinutesPerDayInput.sendKeys(content);
    }

    selectNotation(index) {
        this.notationSelect.element(by.id('notation-' + index)).click();
    }

    fillBasicItems(name, diminutive, maxMinutesPerDay, notation) {
        this.typeOnNameInput(name);
        this.typeOnDiminutiveInput(diminutive);
        this.typeOnMaxMinutesPerDayInput(maxMinutesPerDay);
        this.selectNotation(notation);
    }

    clickSubmitButton() {
        this.submitButton.click();
    }

    clickAddSerie1() {
        this.addSerie1Button.click();
    }

    clickAddSerie2() {
        this.addSerie2Button.click();
    }

    typeSerieName(index, text) {
        const serieName1 = element(by.id('serie-name-' + index));
        serieName1.sendKeys(text);
    }

    typeSerieDiminutive(index, text) {
        const serieName1 = element(by.id('serie-diminutive-' + index));
        serieName1.sendKeys(text);
    }

    typeSerieOption(index, text) {
        const serieName1 = element(by.id('serie-option-' + index));
        serieName1.sendKeys(text);
    }

}
