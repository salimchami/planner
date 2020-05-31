import {animate, AUTO_STYLE, state, style, transition, trigger} from '@angular/animations';

export const NAVBAR_ANIMATIONS = [
    trigger('notificationBottom', [
        state('an-off, void',
            style({
                overflow: 'hidden',
                height: '0px',
            })
        ),
        state('an-animate',
            style({
                overflow: 'hidden',
                height: AUTO_STYLE,
            })
        ),
        transition('an-off <=> an-animate', [
            animate('400ms ease-in-out')
        ])
    ]),
    trigger('slideInOut', [
        state('in', style({
            width: '300px',
            // transform: 'translate3d(0, 0, 0)'
        })),
        state('out', style({
            width: '0',
            // transform: 'translate3d(100%, 0, 0)'
        })),
        transition('in => out', animate('400ms ease-in-out')),
        transition('out => in', animate('400ms ease-in-out'))
    ]),
    trigger('mobileHeaderNavRight', [
        state('nav-off, void',
            style({
                overflow: 'hidden',
                height: '0px',
            })
        ),
        state('nav-on',
            style({
                height: AUTO_STYLE,
            })
        ),
        transition('nav-off <=> nav-on', [
            animate('400ms ease-in-out')
        ])
    ]),
    trigger('fadeInOutTranslate', [
        transition(':enter', [
            style({opacity: 0}),
            animate('400ms ease-in-out', style({opacity: 1}))
        ]),
        transition(':leave', [
            style({transform: 'translate(0)'}),
            animate('400ms ease-in-out', style({opacity: 0}))
        ])
    ])
];
