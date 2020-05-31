import {element, by} from 'protractor';

export class MenuItemsObjects {

    static menuItem_click(linkText: string) {
        // element(by.xpath('span[@class="pcoded-mtext" and text()="' + name + '"]')).click();
        // element(by.cssContainingText('.pcoded-mtext', name)).click();
        element(by.linkText(linkText)).click();

    }
}
