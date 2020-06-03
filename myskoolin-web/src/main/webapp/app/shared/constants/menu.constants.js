"use strict";
exports.__esModule = true;
var animations_1 = require("@angular/animations");
exports.NAVBAR_ANIMATIONS = [
    animations_1.trigger('notificationBottom', [
        animations_1.state('an-off, void', animations_1.style({
            overflow: 'hidden',
            height: '0px'
        })),
        animations_1.state('an-animate', animations_1.style({
            overflow: 'hidden',
            height: animations_1.AUTO_STYLE
        })),
        animations_1.transition('an-off <=> an-animate', [
            animations_1.animate('400ms ease-in-out')
        ])
    ]),
    animations_1.trigger('slideInOut', [
        animations_1.state('in', animations_1.style({
            width: '300px'
        })),
        animations_1.state('out', animations_1.style({
            width: '0'
        })),
        animations_1.transition('in => out', animations_1.animate('400ms ease-in-out')),
        animations_1.transition('out => in', animations_1.animate('400ms ease-in-out'))
    ]),
    animations_1.trigger('mobileHeaderNavRight', [
        animations_1.state('nav-off, void', animations_1.style({
            overflow: 'hidden',
            height: '0px'
        })),
        animations_1.state('nav-on', animations_1.style({
            height: animations_1.AUTO_STYLE
        })),
        animations_1.transition('nav-off <=> nav-on', [
            animations_1.animate('400ms ease-in-out')
        ])
    ]),
    animations_1.trigger('fadeInOutTranslate', [
        animations_1.transition(':enter', [
            animations_1.style({ opacity: 0 }),
            animations_1.animate('400ms ease-in-out', animations_1.style({ opacity: 1 }))
        ]),
        animations_1.transition(':leave', [
            animations_1.style({ transform: 'translate(0)' }),
            animations_1.animate('400ms ease-in-out', animations_1.style({ opacity: 0 }))
        ])
    ])
];
//# sourceMappingURL=menu.constants.js.map