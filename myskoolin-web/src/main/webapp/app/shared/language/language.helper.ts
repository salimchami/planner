import {Injectable, RendererFactory2, Renderer2} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Router, ActivatedRouteSnapshot} from '@angular/router';
import {TranslateService, LangChangeEvent} from '@ngx-translate/core';

import {LANGUAGES_ARRAY} from './language.constants';
import {APP_CONSTANTS} from '../../app.constants';

@Injectable()
export class JhiLanguageHelper {
    renderer: Renderer2 = null;

    constructor(
        private translateService: TranslateService,
        // tslint:disable-next-line: no-unused-variable
        private rootRenderer: RendererFactory2,
        private titleService: Title,
        private router: Router
    ) {
        this.renderer = rootRenderer.createRenderer(document.querySelector('html'), null);
        this.init();
    }

    getAll(): Promise<any> {
        return Promise.resolve(LANGUAGES_ARRAY);
    }

    /**
     * Update the window title using params in the following
     * order:
     * 1. titleKey parameter
     * 2. $state.$current.data.pageTitle (current state page title)
     * 3. 'global.title'
     */
    updateTitle(titleKey?: string) {
        if (!titleKey) {
            titleKey = this.getPageTitle(this.router.routerState.snapshot.root);
        }

        this.translateService.get(titleKey).subscribe((title) => {
            let finalTitle;
            if (title.includes(APP_CONSTANTS.APP_TITLE)) {
                finalTitle = title;
            } else if (!!title.trim().length) {
                finalTitle = APP_CONSTANTS.APP_TITLE + ' - ' + title;
            } else {
                finalTitle = APP_CONSTANTS.APP_TITLE;
            }
            this.titleService.setTitle(finalTitle);
        });
    }

    private init() {
        this.translateService.onLangChange.subscribe((event: LangChangeEvent) => {
            this.renderer.setAttribute(document.querySelector('html'), 'lang', this.translateService.currentLang);
            this.updateTitle();
        });
    }

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'schoolmeApp';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    translate(key: string) {
        return this.translateService.get(key);
    }
}
