import {Subscription} from 'rxjs';
import {NgcCookieConsentService, NgcStatusChangeEvent} from 'ngx-cookieconsent';
import {CookieService} from 'ngx-cookie';
import {APP_CONSTANTS} from '../../../app.constants';
import {EncryptService} from '../security/encrypt.service';
import {Injectable} from '@angular/core';
import {addMonth} from 'ts-date/esm/locale/en';

@Injectable()
export class CookieConsentService {
    private popupCloseSubscription: Subscription;

    constructor(private encryptService: EncryptService) {
    }

    initCookieConsent(ccService: NgcCookieConsentService, cookieService: CookieService, subscriptions: any) {
        const cookieConsent = cookieService.get(APP_CONSTANTS.COOKIE_CONSENT_KEY);
        if (cookieConsent && this.encryptService.decryptString(cookieConsent) === 'true') {
            ccService.getConfig().enabled = false;
        }

        subscriptions.statusChangeSubscription = ccService.statusChange$.subscribe(
            (event: NgcStatusChangeEvent) => {
                if (event.status === 'dismiss') {
                    const consentEncrypt = this.encryptService.encryptString('true');
                    const now = new Date();
                    cookieService.put(APP_CONSTANTS.COOKIE_CONSENT_KEY, consentEncrypt, {expires: addMonth(now, 1)});
                }
            });
    }

    unsubscribes(subscriptions) {
        // unsubscribe to cookieconsent observables to prevent memory leaks
        if (subscriptions.popupCloseSubscription) {
            subscriptions.popupCloseSubscription.unsubscribe();
        }
    }

    translate(translateService, ccService) {
        translateService//
            .get(['landing.cookie-consent.message', 'landing.cookie-consent.dismiss'])
            .subscribe((data) => {
                ccService.getConfig().content = ccService.getConfig().content || {};
                // Override default messages with the translated ones
                ccService.getConfig().content.message = data['landing.cookie-consent.message'];
                ccService.getConfig().content.dismiss = data['landing.cookie-consent.dismiss'];
                ccService.destroy();
                ccService.init(ccService.getConfig()); // update config with translated messages
            });
    }
}
