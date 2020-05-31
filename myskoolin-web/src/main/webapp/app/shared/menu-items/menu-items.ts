import {Injectable} from '@angular/core';

export interface BadgeItem {
    type: string;
    value: string;
}

export interface ChildrenItems {
    state: string;
    target?: boolean;
    name: string;
    type?: string;
    children?: ChildrenItems[];
}

export interface MainMenuItems {
    state: string;
    short_label?: string;
    main_state?: string;
    target?: boolean;
    name: string;
    type: string;
    icon: string;
    badge?: BadgeItem[];
    children?: ChildrenItems[];
}

export interface Menu {
    label: string;
    main: MainMenuItems[];
}

const MENUITEMS = [
        {
            label: '',
            main: [
                {
                    state: '',
                    name: 'menu.dashboards.home',
                    type: 'link',
                    icon: 'mdi mdi-home mdi-18px',
                },
                {
                    state: '',
                    name: 'menu.dashboards.title',
                    type: 'sub',
                    icon: 'mdi mdi-view-dashboard mdi-18px',
                    children: [
                        {
                            state: 'students-dashboard',
                            name: 'menu.students.title',
                            icon: 'mdi mdi-human-child mdi-18px',
                        },
                        {
                            state: 'school-classes-dashboard',
                            name: 'menu.school-classes.title',
                            icon: 'mdi mdi-google-classroom mdi-18px',
                        },
                        {
                            state: 'teachers-dashboard',
                            name: 'menu.teachers.title',
                            icon: 'mdi mdi-teach mdi-18px',
                        },
                        {
                            state: 'timetables-dashboard',
                            name: 'menu.timetables.title',
                            icon: 'mdi mdi-timetable mdi-18px',
                        },
                        {
                            state: 'school-rooms-dashboard',
                            name: 'menu.institution.school-rooms',
                            icon: 'mdi mdi-door-open mdi-18px',
                        },
                        {
                            state: 'infirmary-dashboard',
                            name: 'menu.institution.infirmary',
                            icon: 'mdi mdi-hospital mdi-18px',
                        }
                    ]
                },
            ]
        },
        {
            label: '',
            main: [
                {
                    state: 'school-classes',
                    name: 'menu.school-classes.title',
                    type: 'link',
                    icon: 'mdi mdi-google-classroom mdi-18px',
                },
                {
                    state: 'school-classes-more',
                    name: 'menu.school-classes.title-more',
                    type: 'sub',
                    icon: 'mdi mdi-google-classroom mdi-18px',
                    children: [
                        {
                            state: 'timetables',
                            name: 'menu.school-classes.timetables',
                            icon: 'mdi mdi-timetable mdi-18px'
                        },
                        {
                            state: 'daily-book',
                            name: 'menu.school-classes.daily-book',
                            icon: 'mdi mdi-book-open mdi-18px'
                        },
                        {
                            state: 'continual-assessment',
                            name: 'menu.school-classes.continual-assessment',
                            icon: 'mdi mdi-pen mdi-18px'
                        },
                        {
                            state: 'absences',
                            name: 'menu.school-classes.absences',
                            icon: 'mdi mdi-alert-box mdi-18px'
                        },
                        {
                            state: 'delays',
                            name: 'menu.school-classes.delays',
                            icon: 'mdi mdi-clock-alert mdi-18px'
                        },
                        {
                            state: 'sanctions',
                            name: 'menu.school-classes.sanctions',
                            icon: 'mdi mdi-alert mdi-18px'
                        },
                        {
                            state: 'reports',
                            name: 'menu.school-classes.school-reports',
                            icon: 'mdi mdi-note-multiple-outline mdi-18px'
                        },
                    ]
                },
                {
                    state: 'students',
                    name: 'menu.students.title',
                    type: 'link',
                    icon: 'mdi mdi-human-child mdi-18px',
                },
                {
                    state: 'students-more',
                    name: 'menu.students.title-more',
                    type: 'sub',
                    icon: 'mdi mdi-human-child mdi-18px',
                    children: [
                        {
                            state: 'continual-assessment',
                            name: 'menu.students.continual-assessment',
                            icon: 'mdi mdi-pen mdi-18px'
                        },
                        {
                            state: 'absences',
                            name: 'menu.students.absences',
                            icon: 'mdi mdi-alert-box mdi-18px'
                        },
                        {
                            state: 'delays',
                            name: 'menu.students.delays',
                            icon: 'mdi mdi-clock-alert mdi-18px'
                        },
                        {
                            state: 'sanctions',
                            name: 'menu.students.sanctions',
                            icon: 'mdi mdi-alert mdi-18px'
                        },
                        {
                            state: 'reports',
                            name: 'menu.students.school-reports',
                            icon: 'mdi mdi-note-multiple-outline mdi-18px'
                        },
                        {
                            state: 'orientation',
                            name: 'menu.students.orientation',
                            icon: 'mdi mdi-arrow-expand-all mdi-18px'
                        },
                    ]
                },
                {
                    state: 'teachers',
                    name: 'menu.teachers.title',
                    type: 'link',
                    icon: 'mdi mdi-teach mdi-18px',
                },
                {
                    state: 'subjects',
                    name: 'menu.subjects.title',
                    type: 'link',
                    icon: 'mdi mdi-developer-board mdi-18px',
                },
                {
                    state: 'grades',
                    name: 'menu.grades.title',
                    type: 'link',
                    icon: 'mdi mdi-blur-linear mdi-18px'
                }
            ]
        },
        {
            label: '',
            main: [
                {
                    state: 'timetables',
                    name: 'menu.timetables.title',
                    type: 'link',
                    icon: 'mdi mdi-timetable mdi-18px'
                }
            ]
        },
        {
            label: '',
            main: [
                {
                    state: 'infirmary',
                    name: 'menu.institution.infirmary',
                    type: 'link',
                    icon: 'mdi mdi-hospital mdi-18px',
                },
                {
                    state: 'canteen',
                    name: 'menu.institution.canteen',
                    type: 'link',
                    icon: 'mdi mdi-food mdi-18px',
                },
                {
                    state: 'school-rooms',
                    name: 'menu.institution.school-rooms',
                    type: 'link',
                    icon: 'mdi mdi-door-open mdi-18px',
                },
                {
                    state: 'staff',
                    name: 'menu.institution.staff',
                    type: 'link',
                    icon: 'mdi mdi-human-male mdi-18px',
                }
            ]
        },
        {
            label: '',
            main: [
                {
                    state: 'tuition-fees',
                    name: 'menu.finance.tuition-fees',
                    type: 'link',
                    icon: 'mdi mdi-cash mdi-18px',
                }
            ]
        },
        {
            label: '',
            main: [
                {
                    state: 'school-account',
                    name: 'menu.settings.school-account-settings',
                    type: 'link',
                    icon: 'mdi mdi-tune mdi-18px',
                }
            ]
        }
    ]
;

@Injectable()
export class MenuItems {
    private items: Menu[];

    constructor() {
    }

    // menuItemsByAuthority() {
    //     principal.
    // }
    //
    getAll(): Menu[] {
        return MENUITEMS;
    }
}
