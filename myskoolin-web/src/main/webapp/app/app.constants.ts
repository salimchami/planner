// These constants are injected via webpack environment variables.
// You can add more variables in webpack.common.js or in profile specific webpack.<dev|prod>.js files.
// If you change the values in the webpack config files, you need to re run webpack to update the application

import {NgcCookieConsentConfig} from 'ngx-cookieconsent';

export const VERSION = process.env.VERSION;
export const DEBUG_INFO_ENABLED: boolean = !!process.env.DEBUG_INFO_ENABLED;
export const SERVER_API_URL = process.env.SERVER_API_URL;
export const BUILD_TIMESTAMP = process.env.BUILD_TIMESTAMP;
export const WEEKDAYS = ['sunday', 'monday', 'tuesday', 'wednesday', 'thursday', 'friday', 'saturday'];
export const COOKIE_CONSENT_CONFIG: NgcCookieConsentConfig = {
    'cookie': {
        'domain': 'www.myschoolstory.net'
    },
    'position': 'bottom-right',
    'theme': 'block',
    'palette': {
        'popup': {
            'background': '#cecece',
            'text': '#444444',
            'link': '#ffffff'
        },
        'button': {
            'background': '#C75B5B',
            'text': '#ffffff',
            'border': 'transparent'
        }
    },
    'type': 'info',
    'content': {
        'message': 'This website uses cookies to ensure you get the best experience on our website.',
        'dismiss': 'Got it!',
        'deny': 'Refuse cookies',
        'link': '',
        'href': ''
    }
};
const DAYS_SELECT_OPTIONS: Array<any> = [
    {
        value: 'SUNDAY',
        label: 'global.enums.days.sunday'
    },
    {
        value: 'MONDAY',
        label: 'global.enums.days.monday'
    },
    {
        value: 'TUESDAY',
        label: 'global.enums.days.tuesday'
    },
    {
        value: 'WEDNESDAY',
        label: 'global.enums.days.wednesday'
    },
    {
        value: 'THURSDAY',
        label: 'global.enums.days.thursday'
    },
    {
        value: 'FRIDAY',
        label: 'global.enums.days.friday'
    },
    {
        value: 'SATURDAY',
        label: 'global.enums.days.saturday'
    }
];

export const APP_CONSTANTS = {
    COOKIE_CONSENT_KEY: 'myschoolstory-cookie-consent',
    SCHOOLME_URL: 'http://www.myschoolstory.net',
    CONTACT_EMAIL: 'contact@myschoolstory.net',
    CONTACT_PHONE: '+33 (6) 19 13 43 50',
    APP_TITLE: 'Myskoolin',
    DATE_PATTERNS: {
        FRA: 'dd-MM-yyyy',
        ENG: 'MM-dd-yyyy'
    },
    CONTACT_TYPES: [
        'global.constants.contact-reasons.invoice',
        'global.constants.contact-reasons.complaint',
        'global.constants.contact-reasons.myAccount',
        'global.constants.contact-reasons.softwareDysfunction',
        'global.constants.contact-reasons.other'
    ],
    SEXES: [
        'global.constants.sexes.male',
        'global.constants.sexes.female'
    ],
    FAMILY_SITUATIONS: [
        'global.form.family-situations.single',
        'global.form.family-situations.married',
        'global.form.family-situations.separated',
        'global.form.family-situations.widower',
        'global.form.family-situations.other'
    ],
    CONTACTS_BY: [
        {
            name: 'PRIMARY_EMAIL',
            code: 'global.enums.contacts-by.primary-email'
        },
        {
            name: 'SECONDARY_EMAIL',
            code: 'global.enums.contacts-by.secondary-email'
        },
        {
            name: 'PROFESSIONAL_EMAIL',
            code: 'global.enums.contacts-by.professional-email'
        },
        {
            name: 'MOBILE_PHONE',
            code: 'global.enums.contacts-by.mobile-phone'
        },
        {
            name: 'PROFESSIONAL_MOBILE_PHONE',
            code: 'global.enums.contacts-by.professional-mobile-phone'
        },
        {
            name: 'PROFESSIONAL_PHONE',
            code: 'global.enums.contacts-by.professional-phone'
        },
        {
            name: 'PHONE',
            code: 'global.enums.contacts-by.phone'
        },
        {
            name: 'SMS',
            code: 'global.enums.contacts-by.sms'
        },
        {
            name: 'PROFESSIONAL_SMS',
            code: 'global.enums.contacts-by.professional-sms'
        },
        {
            name: 'SCHOOLME_EMAIL',
            code: 'global.enums.contacts-by.schoolme-email'
        },
        {
            name: 'POSTAL_MAIL',
            code: 'global.enums.contacts-by.postal-mail'
        }
    ],
    CURRENCIES: [
        {
            name: 'EURO',
            code: 'global.enums.currency.singular.euro',
            'pluralCode': 'global.enums.currency.plural.euros',
            'symbol': '€'
        },
        {
            name: 'DOLLAR',
            code: 'global.enums.currency.singular.dollar',
            'pluralCode': 'global.enums.currency.plural.dollars',
            'symbol': '$'
        },
        {
            name: 'DIRHAM',
            code: 'global.enums.currency.singular.dirham',
            'pluralCode': 'global.enums.currency.plural.dirhams',
            'symbol': 'MDH'
        }
    ],
    DAYS_SELECT_OPTIONS,
    DAYS: [
        {
            name: 'SUNDAY',
            code: 'global.enums.days.sunday',
            position: 0
        },
        {
            name: 'MONDAY',
            code: 'global.enums.days.monday',
            position: 1
        },
        {
            name: 'TUESDAY',
            code: 'global.enums.days.tuesday',
            position: 2
        },
        {
            name: 'WEDNESDAY',
            code: 'global.enums.days.wednesday',
            position: 3
        },
        {
            name: 'THURSDAY',
            code: 'global.enums.days.thursday',
            'position': 4
        },
        {
            name: 'FRIDAY',
            code: 'global.enums.days.friday',
            'position': 5
        },
        {
            name: 'SATURDAY',
            code: 'global.enums.days.saturday',
            'position': 6
        }
    ],
    DISTANCES: [
        {
            name: 'METER',
            code: 'global.enums.distances.meter'
        },
        {
            name: 'KILOMETER',
            code: 'global.enums.distances.kilometer'
        },
        {
            name: 'INCH',
            code: 'global.enums.distances.inch'
        },
        {
            name: 'FOOT',
            code: 'global.enums.distances.foot'
        },
        {
            name: 'YARD',
            code: 'global.enums.distances.yard'
        },
        {
            name: 'MILE',
            code: 'global.enums.distances.mile'
        }
    ],
    FILES_TYPES: [
        {
            name: 'CLIENT_LOGO',
            code: 'global.enums.files.client-logo'
        }
    ],
    GENDERS: [
        {
            name: 'MR',
            code: 'global.gender.mr'
        },
        {
            name: 'MRS',
            code: 'global.gender.mrs'
        },
        {
            name: 'MISS',
            code: 'global.gender.miss'
        }
    ],
    PARTS_OF_DAYS: [
        {
            name: 'AM',
            code: 'global.enums.parts-of-day.am'
        },
        {
            name: 'PM',
            code: 'global.enums.parts-of-day.pm'
        }
    ],
    PERIODS: [
        {
            name: 'YEAR',
            code: 'global.enums.periods.year'
        },
        {
            name: 'MONTH',
            code: 'global.enums.periods.month'
        },
        {
            name: 'WEEK',
            code: 'global.enums.periods.week'
        },
        {
            name: 'DAY',
            code: 'global.enums.periods.day'
        },
        {
            name: 'HOUR',
            code: 'global.enums.periods.hour'
        },
        {
            name: 'MINUTE',
            code: 'global.enums.periods.minute'
        },
        {
            name: 'SECOND',
            code: 'global.enums.periods.second'
        }
    ],
    SCHOOL_CLASSES_NOTATIONS: [
        {
            name: 'MONTHLY',
            code: 'global.enums.school-class-notation.monthly',
            position: 1,
            defaultPeriodsNbPerYear: 9
        },
        {
            name: 'QUARTER',
            code: 'global.enums.school-class-notation.quarter',
            'position': 2,
            'defaultPeriodsNbPerYear': 3
        },
        {
            name: 'SEMESTER',
            code: 'global.enums.school-class-notation.semester',
            'position': 3,
            'defaultPeriodsNbPerYear': 2
        },
        {
            name: 'ANNUAL',
            code: 'global.enums.school-class-notation.annual',
            'position': 4,
            'defaultPeriodsNbPerYear': 1
        }
    ],
    SCHOOL_ROOMS_TYPES: [
        {
            name: 'NORMAL',
            code: 'schoolRoom.enums.types.normal'
        },
        {
            name: 'SCIENCES',
            code: 'schoolRoom.enums.types.sciences'
        },
        {
            name: 'MUSIC',
            code: 'schoolRoom.enums.types.music'
        },
        {
            name: 'SPORT',
            code: 'schoolRoom.enums.types.sport'
        },
        {
            name: 'IT',
            code: 'schoolRoom.enums.types.it'
        },
        {
            name: 'MULTIPURPOSE',
            code: 'schoolRoom.enums.types.multipurpose'
        },
        {
            name: 'STEPPED',
            code: 'schoolRoom.enums.types.stepped'
        },
        {
            name: 'AMPHITHEATER',
            code: 'schoolRoom.enums.types.amphitheater'
        }
    ],
    SURFACES: [
        {
            name: 'SQUARE_METER',
            code: 'global.enums.surfaces.squareMeter'
        },
        {
            name: 'SQUARE_KILOMETER',
            code: 'global.enums.surfaces.squareKilometer'
        },
        {
            name: 'SQUARE_INCH',
            code: 'global.enums.surfaces.squareInch'
        },
        {
            name: 'SQUARE_FOOT',
            code: 'global.enums.surfaces.squareFoot'
        },
        {
            name: 'SQUARE_MILE',
            code: 'global.enums.surfaces.squareMile'
        }
    ],
    TIMETABLES_GENERATION_STATES: [
        {
            name: 'NO_TIMETABLE',
            code: 'global.enums.tt-generation-states.no-tt'
        },
        {
            name: 'TT_PARTIALLY_GENERATED',
            code: 'global.enums.contacts-by.secondary-email'
        },
        {
            name: 'GENERATION_COMPLETE',
            code: 'global.enums.contacts-by.professional-email'
        }
    ]

};
