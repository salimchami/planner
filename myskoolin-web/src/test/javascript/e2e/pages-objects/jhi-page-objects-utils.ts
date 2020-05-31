import {browser} from 'protractor';

export class PageUtils {

    static clickElsewhere(elm, offset) {
        return browser.actions()
            .mouseMove(elm, offset)
            .click()
            .perform();
    }

    static getInputText(elm, callback) {
        elm.getAttribute('value').then (function(text){
            callback(text);
        });
    }

    static sleep(ms) {
        browser.driver.sleep(ms);
    }
}
