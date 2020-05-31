import { Routes } from '@angular/router';

import { AUTHORITIES_ALL } from '../../shared';
import { NewsletterComponent } from './newsletter.component';

export const newsletterRoute: Routes = [
    {
        path: 'newsletter/finish',
        component: NewsletterComponent,
        data: {
            authorities: AUTHORITIES_ALL,
            pageTitle: 'schoolmeApp.newsletter.home.title'
        }

    }
];
