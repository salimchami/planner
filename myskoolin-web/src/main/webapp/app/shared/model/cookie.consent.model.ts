import {Subscription} from 'rxjs';

export class CookieConsent {
    popupOpenSubscription: Subscription;
    popupCloseSubscription: Subscription;
    initializeSubscription: Subscription;
    statusChangeSubscription: Subscription;
    revokeChoiceSubscription: Subscription;
    noCookieLawSubscription: Subscription;

    constructor() {
    }
}
