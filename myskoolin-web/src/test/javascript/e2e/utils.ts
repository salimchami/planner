import {browser, by, element} from 'protractor';
import {ILocation} from 'selenium-webdriver';

export default class E2EUtils {

    static scrollTo(scrollToElement) {
        const wd = browser.driver;
        return scrollToElement.getLocation().then((loc) => {
            return wd.executeScript('window.scrollTo(0,arguments[0]);', loc.y);
        });
    }

    static scrollToTop() {
        const wd = browser.driver;
        return wd.executeScript('window.scrollTo(0,arguments[0]);');
    }

    static mouseMoveToElement(item: ILocation) {
        browser.actions().mouseMove(item);
    }

    static selectOptionByOptionValue(selectFormFieldElementId, valuesToFind) {
        console.log('values to find : ' + valuesToFind);
        const formField = element(by.id(selectFormFieldElementId));
        formField.click().then(() => {
            formField.element(by.tagName('mat-select'))
                .getAttribute('aria-owns').then((optionIdsString: string) => {
                const optionIds = optionIdsString.split(' ');
                for (const optionId of optionIds) {
                    const option = element(by.id(optionId));
                    option.getText().then((text) => {
                        valuesToFind.forEach((value) => {
                            if (text === value) {
                                option.click();
                            }
                        });
                    });
                }
            });
        });
    }

    static changeLanguageTo(language: string) {
        element(by.id('flagLink')).click();
        element(by.linkText(language)).click();
        // element(by.cssContainingText('li', language)).element(by.css('a')).click();
        // element(by.id('flag' + language + 'Link')).click();
    }
}
