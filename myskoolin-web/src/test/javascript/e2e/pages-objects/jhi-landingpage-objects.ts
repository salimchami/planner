import {element, by, browser} from 'protractor';

export class LandingPage {
    // ########################### landingpage
    // navbar
    logoMenu = element(by.id('logoLink'));
    homeMenu = element(by.id('homeLink'));
    servicesMenu = element(by.id('servicesLink'));
    featuresMenu = element(by.id('featuresLink'));
    newsletterMenu = element(by.id('newsletterLink'));
    contactMenu = element(by.id('contactLink'));
    flagMenu = element(by.id('flagLink'));
    flagEnglishMenu = element(by.id('flagenLink'));
    flagFrenchMenu = element(by.id('flagfrLink'));

    // home buttons
    demoButton = element(by.id('flagenLink'));
    quotationButton = element(by.id('flagfrLink'));

    // login
    usernameInput = element(by.id('username'));
    passwordInput = element(by.id('password'));
    rememberMeCheckbox = element(by.id('rememberMe'));
    forgetPasswordLink = element(by.id('forgetPasswordLink'));
    signInButton = element(by.id('signIn'));
    newsletterInput = element(by.id('newsletterEmail'));
    newsletterButton = element(by.id('newsletterButton'));
    newsletterError = element(by.id('newsletterError'));
    newsletterConfirmation = element(by.id('newsletterConfirmation'));

    clickOnHomeMenu() {
        return this.homeMenu.click();
    }

    clickOnServicesMenu() {
        return this.servicesMenu.click();
    }

    clickOnFeaturesMenu() {
        return this.featuresMenu.click();
    }

    clickOnNewsletterMenu() {
        return this.newsletterMenu.click();
    }

    clickOnLogoMenu() {
        return this.logoMenu.click();
    }

    clickOnFlagMenu() {
        return this.flagMenu.click();
    }

    clickOnFlagEnglishMenu() {
        return this.flagEnglishMenu.click();
    }

    clickOnFlagFrenchMenu() {
        return this.flagFrenchMenu.click();
    }

    clickOnContactMenu() {
        return this.contactMenu.click();
    }

    clickOnDemoButton() {
        return this.demoButton.click();
    }

    clickOnQuotationButton() {
        return this.quotationButton.click();
    }

    clickOnUsernameInput() {
        return this.usernameInput.click();
    }

    typeOnUsernameInput(content) {
        this.usernameInput.sendKeys(content);
    }

    clickOnPasswordInput() {
        return this.passwordInput.click();
    }

    typeOnPasswordInput(content) {
        this.passwordInput.sendKeys(content);
    }

    clickOnRememberMeCheckbox() {
        return this.rememberMeCheckbox.click();
    }

    clickOnForgetPasswordLink() {
        return this.forgetPasswordLink.click();
    }

    clickOnSignInButton() {
        return this.signInButton.click();
    }

    clickOnNewsletterInput() {
        return this.newsletterInput.click();
    }

    typeOnNewsletterInput(content) {
        this.newsletterInput.sendKeys(content);
    }

    clickOnNewsletterButton() {
        return this.newsletterButton.click();
    }

    subscribeToNewsletter(email) {
        this.clickOnNewsletterMenu();
        this.clickOnNewsletterInput();
        this.typeOnNewsletterInput(email);
        browser.waitForAngular();
    }

    loginAdmin() {
        this.usernameInput.sendKeys('leboss');
        this.passwordInput.sendKeys('admin');
        this.clickOnConnectionButton();
    }

    private clickOnConnectionButton() {
        this.signInButton.click();
        browser.waitForAngular();
    }
}

export class NavBarPage {
    // entityMenu = element(by.id('entity-menu'));
    // accountMenu = element(by.id('account-menu'));
    // adminMenu: ElementFinder;
    // signIn = element(by.id('login'));
    // register = element(by.css('[routerLink="register"]'));
    // signOut = element(by.id('logout'));
    // passwordMenu = element(by.css('[routerLink="password"]'));
    // settingsMenu = element(by.css('[routerLink="settings"]'));
    //
    // constructor(asAdmin?: Boolean) {
    //     if (asAdmin) {
    //         this.adminMenu = element(by.id('admin-menu'));
    //     }
    // }
    //
    // clickOnEntityMenu() {
    //     // return this.entityMenu.click();
    // }
    //
    // clickOnAccountMenu() {
    //     return this.accountMenu.click();
    // }
    //
    // clickOnAdminMenu() {
    //     return this.adminMenu.click();
    // }
    //
    // clickOnSignIn() {
    //     return this.signIn.click();
    // }
    //
    // clickOnRegister() {
    //     return this.signIn.click();
    // }
    //
    // clickOnSignOut() {
    //     return this.signOut.click();
    // }
    //
    // clickOnPasswordMenu() {
    //     return this.passwordMenu.click();
    // }
    //
    // clickOnSettingsMenu() {
    //     return this.settingsMenu.click();
    // }
    //
    // clickOnEntity(entityName: string) {
    //     return element(by.css('[routerLink="' + entityName + '"]')).click();
    // }
    //
    // clickOnAdmin(entityName: string) {
    //     return element(by.css('[routerLink="' + entityName + '"]')).click();
    // }
    //
    // getSignInPage() {
    //     this.clickOnAccountMenu();
    //     this.clickOnSignIn();
    //     return new SignInPage();
    // }
    // getPasswordPage() {
    //     this.clickOnAccountMenu();
    //     this.clickOnPasswordMenu();
    //     return new PasswordPage();
    // }
    //
    // getSettingsPage() {
    //     this.clickOnAccountMenu();
    //     this.clickOnSettingsMenu();
    //     return new SettingsPage();
    // }
    //
    // goToEntity(entityName: string) {
    //     this.clickOnEntityMenu();
    //     return this.clickOnEntity(entityName);
    // }
    //
    // goToSignInPage() {
    //     this.clickOnAccountMenu();
    //     this.clickOnSignIn();
    // }
    //
    // goToPasswordMenu() {
    //     this.clickOnAccountMenu();
    //     this.clickOnPasswordMenu();
    // }
    //
    // autoSignOut() {
    //     this.clickOnAccountMenu();
    //     this.clickOnSignOut();
    // }
}

export class SignInPage {
    // username = element(by.id('username'));
    // password = element(by.id('password'));
    // loginButton = element(by.css('button[type=submit]'));
    //
    // setUserName(username) {
    //     this.username.sendKeys(username);
    // }
    //
    // getUserName() {
    //     return this.username.getAttribute('value');
    // }
    //
    // clearUserName() {
    //     this.username.clear();
    // }
    //
    // setPassword(password) {
    //     this.password.sendKeys(password);
    // }
    //
    // getPassword() {
    //     return this.password.getAttribute('value');
    // }
    //
    // clearPassword() {
    //     this.password.clear();
    // }
    //
    // autoSignInUsing(username: string, password: string) {
    //     this.setUserName(username);
    //     this.setPassword(password);
    //     return this.login();
    // }
    //
    // login() {
    //     return this.loginButton.click();
    // }
}

export class PasswordPage {
    // password = element(by.id('password'));
    // confirmPassword = element(by.id('confirmPassword'));
    // saveButton = element(by.css('button[type=submit]'));
    // title = element.all(by.css('h2')).first();
    //
    // setPassword(password) {
    //     this.password.sendKeys(password);
    // }
    //
    // getPassword() {
    //     return this.password.getAttribute('value');
    // }
    //
    // clearPassword() {
    //     this.password.clear();
    // }
    //
    // setConfirmPassword(confirmPassword) {
    //     this.confirmPassword.sendKeys(confirmPassword);
    // }
    //
    // getConfirmPassword() {
    //     return this.confirmPassword.getAttribute('value');
    // }
    //
    // clearConfirmPassword() {
    //     this.confirmPassword.clear();
    // }
    //
    // getTitle() {
    //     return this.title.getAttribute('jhiTranslate');
    // }
    //
    // save() {
    //     return this.saveButton.click();
    // }
}

export class SettingsPage {
    // firstName = element(by.id('firstName'));
    // lastName = element(by.id('lastName'));
    // email = element(by.id('email'));
    // saveButton = element(by.css('button[type=submit]'));
    // title = element.all(by.css('h2')).first();
    //
    // setFirstName(firstName) {
    //     this.firstName.sendKeys(firstName);
    // }
    //
    // getFirstName() {
    //     return this.firstName.getAttribute('value');
    // }
    //
    // clearFirstName() {
    //     this.firstName.clear();
    // }
    //
    // setLastName(lastName) {
    //     this.lastName.sendKeys(lastName);
    // }
    //
    // getLastName() {
    //     return this.lastName.getAttribute('value');
    // }
    //
    // clearLastName() {
    //     this.lastName.clear();
    // }
    //
    // setEmail(email) {
    //     this.email.sendKeys(email);
    // }
    //
    // getEmail() {
    //     return this.email.getAttribute('value');
    // }
    //
    // clearEmail() {
    //     this.email.clear();
    // }
    //
    // getTitle() {
    //     return this.title.getAttribute('jhiTranslate');
    // }
    //
    // save() {
    //     return this.saveButton.click();
    // }
}
