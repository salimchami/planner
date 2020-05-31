import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRouteSnapshot, NavigationEnd} from '@angular/router';
import {
    JhiLanguageHelper,
    LoginModalService,
    LoginService,
    MenuItems,
    NAVBAR_ANIMATIONS,
    Principal
} from '../../shared';
import {JhiLanguageService} from 'ng-jhipster';
import {ProfileService} from '../profiles/profile.service';
import {VERSION} from '../../app.constants';
import {NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-main',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.scss'],
    animations: NAVBAR_ANIMATIONS
})
export class JhiMainComponent implements OnInit {
    inProduction: boolean;
    languages: any[];
    swaggerEnabled: boolean;
    version: string;
    modalRef: NgbModalRef;
    public navType: string;
    public themeLayout: string;
    public verticalPlacement: string;
    public verticalLayout: string;
    public pcodedDeviceType: string;
    public verticalNavType: string;
    public verticalEffect: string;
    public vnavigationView: string;
    public freamType: string;
    public sidebarImg: string;
    public sidebarImgType: string;
    public layoutType: string;

    public headerTheme: string;
    public pcodedHeaderPosition: string;

    public liveNotification: string;
    public liveNotificationClass: string;

    public languageNotification: string;
    public languageNotificationClass: string;

    public profileNotification: string;
    public profileNotificationClass: string;

    public mailsNotification: string;
    public mailsNotificationClass: string;

    public chatSlideInOut: string;
    public innerChatSlideInOut: string;

    public searchWidth: number;
    public searchWidthString: string;

    public navRight: string;
    public windowWidth: number;
    public chatTopPosition: string;

    public toggleOn: boolean;
    public navBarTheme: string;
    public activeItemTheme: string;
    public pcodedSidebarPosition: string;

    public menuTitleTheme: string;
    public dropDownIcon: string;
    public subItemIcon: string;

    public configOpenRightBar: string;
    public displayBoxLayout: string;
    public isVerticalLayoutChecked: boolean;
    public isSidebarChecked: boolean;
    public isHeaderChecked: boolean;
    public headerFixedMargin: string;
    public sidebarFixedHeight: string;
    public itemBorderStyle: string;
    public subItemBorder: boolean;
    public itemBorder: boolean;

    public config: any;

    constructor(private jhiLanguageHelper: JhiLanguageHelper,
                private router: Router,
                public menuItems: MenuItems,
                private loginService: LoginService,
                private languageService: JhiLanguageService,
                private languageHelper: JhiLanguageHelper,
                private principal: Principal,
                private loginModalService: LoginModalService,
                private profileService: ProfileService,
    ) {
        this.version = VERSION ? 'v' + VERSION : '';
        this.navType = 'st2';
        this.themeLayout = 'vertical';
        this.verticalPlacement = 'left';
        this.verticalLayout = 'wide';
        this.pcodedDeviceType = 'desktop';
        this.verticalNavType = 'expanded';
        this.verticalEffect = 'shrink';
        this.vnavigationView = 'view1';
        this.freamType = 'theme1';
        this.sidebarImg = 'false';
        this.sidebarImgType = 'img1';
        this.layoutType = 'light';

        this.headerTheme = 'theme1';
        this.pcodedHeaderPosition = 'fixed';

        this.languageNotification = 'an-off';
        this.liveNotification = 'an-off';
        this.profileNotification = 'an-off';
        this.mailsNotification = 'an-off';

        this.chatSlideInOut = 'out';
        this.innerChatSlideInOut = 'out';

        this.searchWidth = 0;

        this.navRight = 'nav-on';

        this.windowWidth = window.innerWidth;
        this.setHeaderAttributes(this.windowWidth);

        this.toggleOn = true;
        this.navBarTheme = 'theme1';
        this.activeItemTheme = 'theme10';
        this.pcodedSidebarPosition = 'fixed';
        this.menuTitleTheme = 'theme6';
        this.dropDownIcon = 'style3';
        this.subItemIcon = 'style7';

        this.displayBoxLayout = 'd-none';
        this.isVerticalLayoutChecked = false;
        this.isSidebarChecked = true;
        this.isHeaderChecked = true;
        this.headerFixedMargin = '56px';
        this.sidebarFixedHeight = 'calc(100vh - 56px)';
        this.itemBorderStyle = 'none';
        this.subItemBorder = true;
        this.itemBorder = true;

        this.setMenuAttributes(this.windowWidth);
        this.setHeaderAttributes(this.windowWidth);
    }

    ngOnInit() {
        // this.setBackgroundPattern('pattern1');
        this.router.events.subscribe((event) => {
            if (event instanceof NavigationEnd) {
                this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));
            }
        });
        this.languageHelper.getAll().then((languages) => {
            this.languages = languages;
        });
        this.profileService.getProfileInfo().then((profileInfo) => {
            this.inProduction = profileInfo.inProduction;
            this.swaggerEnabled = profileInfo.swaggerEnabled;
        });
        window.scrollTo(0, 0);
    }

    private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
        let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'schoolmeApp';
        if (routeSnapshot.firstChild) {
            title = this.getPageTitle(routeSnapshot.firstChild) || title;
        }
        return title;
    }

    currentLanguage() {
        return this.languageService.currentLang;
    }

    changeLanguage(languageKey: string) {
        this.languageService.changeLanguage(languageKey);
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    logout() {
        this.loginService.logout();
        this.router.navigate(['']);
    }

    onResize(event) {
        this.windowWidth = event.target.innerWidth;
        this.setHeaderAttributes(this.windowWidth);

        let reSizeFlag = true;
        if (this.pcodedDeviceType === 'tablet' && this.windowWidth >= 768 && this.windowWidth <= 1024) {
            reSizeFlag = false;
        } else if (this.pcodedDeviceType === 'mobile' && this.windowWidth < 768) {
            reSizeFlag = false;
        }
        /* for check device */
        if (reSizeFlag) {
            this.setMenuAttributes(this.windowWidth);
        }
    }

    setHeaderAttributes(windowWidth) {
        if (windowWidth < 992) {
            this.navRight = 'nav-off';
        } else {
            this.navRight = 'nav-on';
        }
    }

    setMenuAttributes(windowWidth) {
        if (windowWidth >= 768 && windowWidth <= 1024) {
            this.pcodedDeviceType = 'tablet';
            this.verticalNavType = 'offcanvas';
            this.verticalEffect = 'overlay';
        } else if (windowWidth < 768) {
            this.pcodedDeviceType = 'mobile';
            this.verticalNavType = 'offcanvas';
            this.verticalEffect = 'overlay';
        } else {
            this.pcodedDeviceType = 'desktop';
            this.verticalNavType = 'expanded';
            this.verticalEffect = 'shrink';
        }
    }

    toggleHeaderNavRight() {
        this.navRight = this.navRight === 'nav-on' ? 'nav-off' : 'nav-on';
        this.chatTopPosition = this.chatTopPosition === 'nav-on' ? '112px' : '';
        if (this.navRight === 'nav-off' && this.innerChatSlideInOut === 'in') {
            this.toggleInnerChat();
        }
        if (this.navRight === 'nav-off' && this.chatSlideInOut === 'in') {
            this.toggleChat();
        }
    }

    toggleLiveNotification() {
        this.liveNotification = this.liveNotification === 'an-off' ? 'an-animate' : 'an-off';
        this.liveNotificationClass = this.liveNotification === 'an-animate' ? 'active' : '';

        if (this.liveNotification === 'an-animate' && this.innerChatSlideInOut === 'in') {
            this.toggleInnerChat();
        }
        if (this.liveNotification === 'an-animate' && this.chatSlideInOut === 'in') {
            this.toggleChat();
        }
    }

    toggleLanguageNotification() {
        this.languageNotification = this.languageNotification === 'an-off' ? 'an-animate' : 'an-off';
        this.languageNotificationClass = this.languageNotification === 'an-animate' ? 'active' : '';
    }

    toggleMailsNotification() {
        this.mailsNotification = this.mailsNotification === 'an-off' ? 'an-animate' : 'an-off';
        this.mailsNotificationClass = this.mailsNotification === 'an-animate' ? 'active' : '';

        if (this.mailsNotification === 'an-animate' && this.innerChatSlideInOut === 'in') {
            this.toggleInnerChat();
        }
        if (this.mailsNotification === 'an-animate' && this.chatSlideInOut === 'in') {
            this.toggleChat();
        }
    }

    toggleProfileNotification() {
        this.profileNotification = this.profileNotification === 'an-off' ? 'an-animate' : 'an-off';
        this.profileNotificationClass = this.profileNotification === 'an-animate' ? 'active' : '';

        if (this.profileNotification === 'an-animate' && this.innerChatSlideInOut === 'in') {
            this.toggleInnerChat();
        }
        if (this.profileNotification === 'an-animate' && this.chatSlideInOut === 'in') {
            this.toggleChat();
        }
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    notificationOutsideClick(ele: string) {
        if (ele === 'live' && this.liveNotification === 'an-animate') {
            this.toggleLiveNotification();
        } else if (ele === 'profile' && this.profileNotification === 'an-animate') {
            this.toggleProfileNotification();
        } else if (ele === 'mails' && this.mailsNotification === 'an-animate') {
            this.toggleMailsNotification();
        } else if (ele === 'language' && this.languageNotification === 'an-animate') {
            this.toggleLanguageNotification();
        }
    }

    toggleChat() {
        this.chatSlideInOut = this.chatSlideInOut === 'out' ? 'in' : 'out';
        if (this.innerChatSlideInOut === 'in') {
            this.innerChatSlideInOut = 'out';
        }
    }

    toggleInnerChat() {
        this.innerChatSlideInOut = this.innerChatSlideInOut === 'out' ? 'in' : 'out';
    }

    searchOn() {
        document.querySelector('#main-search').classList.add('open');
        const searchInterval = setInterval(() => {
            if (this.searchWidth >= 200) {
                clearInterval(searchInterval);
                return false;
            }
            this.searchWidth = this.searchWidth + 15;
            this.searchWidthString = this.searchWidth + 'px';
        }, 35);
    }

    searchOff() {
        const searchInterval = setInterval(() => {
            if (this.searchWidth <= 0) {
                document.querySelector('#main-search').classList.remove('open');
                clearInterval(searchInterval);
                return false;
            }
            this.searchWidth = this.searchWidth - 15;
            this.searchWidthString = this.searchWidth + 'px';
        }, 35);
    }

    toggleOpened() {
        if (this.windowWidth < 992) {
            this.toggleOn = this.verticalNavType === 'offcanvas' ? true : this.toggleOn;
            if (this.navRight === 'nav-on') {
                this.toggleHeaderNavRight();
            }
        }
        this.verticalNavType = this.verticalNavType === 'expanded' ? 'offcanvas' : 'expanded';
    }

    onClickedOutsideSidebar(e: Event) {
        if ((this.windowWidth < 992 && this.toggleOn && this.verticalNavType !== 'offcanvas') || this.verticalEffect === 'overlay') {
            this.toggleOn = true;
            this.verticalNavType = 'offcanvas';
        }
    }

    toggleRightbar() {
        this.configOpenRightBar = this.configOpenRightBar === 'open' ? '' : 'open';
    }

    setNavBarTheme(theme: string) {
        if (theme === 'themelight1') {
            this.navBarTheme = 'themelight1';
            this.menuTitleTheme = 'theme1';
            this.sidebarImg = 'false';
        } else {
            this.menuTitleTheme = 'theme6';
            this.navBarTheme = 'theme1';
            this.sidebarImg = 'false';
        }
    }

    setLayoutType(type: string) {
        this.layoutType = type;
        if (type === 'dark') {
            this.headerTheme = 'theme1';
            this.sidebarImg = 'false';
            this.navBarTheme = 'theme1';
            this.menuTitleTheme = 'theme6';
            document.querySelector('body').classList.add('dark');
        } else if (type === 'light') {
            this.sidebarImg = 'false';
            this.headerTheme = 'theme5';
            this.navBarTheme = 'themelight1';
            this.menuTitleTheme = 'theme1';
            document.querySelector('body').classList.remove('dark');
        } else if (type === 'img') {
            this.sidebarImg = 'true';
            this.headerTheme = 'theme1';
            this.navBarTheme = 'theme1';
            this.menuTitleTheme = 'theme6';
            document.querySelector('body').classList.remove('dark');
        }
    }

    setVerticalLayout() {
        this.isVerticalLayoutChecked = !this.isVerticalLayoutChecked;
        if (this.isVerticalLayoutChecked) {
            this.verticalLayout = 'box';
            this.displayBoxLayout = '';
        } else {
            this.verticalLayout = 'wide';
            this.displayBoxLayout = 'd-none';
        }
    }

    setBackgroundPattern(pattern: string) {
        document.querySelector('body').setAttribute('themebg-pattern', pattern);
    }

    setSidebarPosition() {
        this.isSidebarChecked = !this.isSidebarChecked;
        this.pcodedSidebarPosition = this.isSidebarChecked === true ? 'fixed' : 'absolute';
        this.sidebarFixedHeight = this.isHeaderChecked === true ? 'calc(100vh + 56px)' : 'calc(100vh - 56px)';
    }

    setHeaderPosition() {
        this.isHeaderChecked = !this.isHeaderChecked;
        this.pcodedHeaderPosition = this.isHeaderChecked === true ? 'fixed' : 'relative';
        this.headerFixedMargin = this.isHeaderChecked === true ? '56px' : '';
    }

    contentMarginLeft() {
        if (!this.isAuthenticated() || this.verticalNavType === 'offcanvas') {
            return 0;
        } else if (this.verticalNavType === 'expanded') {
            return 235;
        }
    }

    isItemMenuActive(item) {
        return this.router.routerState.snapshot.url.includes(item) ? 'active' : '';
    }
}
