import {Injectable} from '@angular/core';
import {JhiLanguageService} from 'ng-jhipster';

import {LANGUAGES, Principal} from '..';
import {AuthServerProvider} from '../auth/auth-jwt.service';
import {JhiTrackerService} from '../tracker/tracker.service';
import {registerLocaleData} from '@angular/common';
import localeEn from '@angular/common/locales/en';
import localeFr from '@angular/common/locales/fr';

@Injectable()
export class LoginService {

    constructor(
        private languageService: JhiLanguageService,
        private principal: Principal,
        private trackerService: JhiTrackerService,
        private authServerProvider: AuthServerProvider
    ) {
    }

    login(credentials, callback?) {
        const cb = callback || function() {
        };

        return new Promise((resolve, reject) => {
            this.authServerProvider.login(credentials).subscribe((data) => {
                this.principal.identity(true).then((account) => {
                    // After the login the language will be changed to
                    // the language selected by the user during his registration
                    if (account !== null) {
                        if (account.langKey) {
                            this.languageService.changeLanguage(account.langKey);

                        } else {
                            this.languageService.changeLanguage(this.languageService.currentLang);
                        }
                        if (this.languageService.currentLang === LANGUAGES.FR) {
                            registerLocaleData(localeFr, LANGUAGES.FR);
                        } else {
                            registerLocaleData(localeEn, LANGUAGES.EN);
                        }
                    }
                    this.trackerService.sendActivity();
                    resolve(data);
                });
                return cb();
            }, (err) => {
                this.logout();
                reject(err);
                return cb(err);
            });
        });
    }

    loginWithToken(jwt, rememberMe) {
        return this.authServerProvider.loginWithToken(jwt, rememberMe);
    }

    logout() {
        this.authServerProvider.logout().subscribe();
        this.principal.authenticate(null);
    }
}
