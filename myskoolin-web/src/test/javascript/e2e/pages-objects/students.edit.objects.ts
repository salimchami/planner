import {element, by, ElementFinder, browser} from 'protractor';
import E2EUtils from '../utils';

export class StudentsEditObjects {
    public submit_Button = element(by.id('submit'));
    public cancel_Button = element(by.id('cancel'));
    public nextStep_0_Button = element(by.id('nextStep-0'));
    public nextStep_1_Button = element(by.id('nextStep-1'));
    public nextStep_2_Button = element(by.id('nextStep-2'));
    public nextStep_3_Button = element(by.id('nextStep-3'));
    public nextStep_4_Button = element(by.id('nextStep-4'));
    public nextStep_5_Button = element(by.id('nextStep-5'));
    public nextStep_6_Button = element(by.id('nextStep-6'));
    public login_Input = element(by.id('login'));
    public firstName_Input = element(by.id('firstName'));
    public lastName_Input = element(by.id('lastName'));
    public studentGender_male_Radio = element(by.id('studentSex-0'));
    public studentGender_female_Radio = element(by.id('studentSex-1'));
    public email_Input = element(by.id('email'));
    public cellPhone_Input = element(by.id('cellPhone'));
    public homePhone_Input = element(by.id('homePhone'));
    public birthDate_Input = element(by.id('birthDate'));
    public nationality_Input = element(by.id('nationality'));
    public addressName_Input = element(by.id('addressName'));
    public postalCode_Input = element(by.id('postalCode'));
    public city_Input = element(by.id('city'));
    public country_Input = element(by.id('country'));
    public comment_Textarea = element(by.id('comment'));
    public nationalNumber_Input = element(by.id('nationalNumber'));
    public redoubling_Switch = element(by.id('redoubling'));
    public entryDate_Input = element(by.id('entryDate'));
    public originEstablishment_Input = element(by.id('originEstablishment'));
    public individualizedReceptionPlan_Switch = element(by.id('individualizedReceptionPlan'));
    public individualizedReceptionPlanContent_Input = element(by.id('individualizedReceptionPlanContent'));
    public personalizedSchoolingPlan_Switch = element(by.id('personalizedSchoolingPlan'));
    public personalizedSchoolingPlanContent_Input = element(by.id('personalizedSchoolingPlanContent'));
    public exitDate_Input = element(by.id('exitDate'));
    public exitReason_Input = element(by.id('exitReason'));
    ///////////////////////////////////
    ///////////////////////////////////
    public knownDiseases_Textarea = element(by.id('knownDiseases'));
    public allergies_Textarea = element(by.id('allergies'));
    public medicalInfosComment_Textarea = element(by.id('medicalInfosComment'));
    public healthInsurance_Switch = element(by.id('healthInsurance'));
    public healthInsuranceName_Input = element(by.id('healthInsuranceName'));
    public healthInsuranceReference_Input = element(by.id('healthInsuranceReference'));
    public healthInsuranceContact_Input = element(by.id('healthInsuranceContact'));
    public complementaryHealthInsurance_Switch = element(by.id('complementaryHealthInsurance'));
    public complementaryHealthInsuranceName_Input = element(by.id('complementaryHealthInsuranceName'));
    public complementaryHealthInsuranceReference_Input = element(by.id('complementaryHealthInsuranceReference'));
    public complementaryHealthInsuranceContact_Input = element(by.id('complementaryHealthInsuranceContact'));
    public canteenRecorded_Switch = element(by.id('canteenRecorded'));
    public canteenBreakfast_Switch = element(by.id('canteenBreakfast'));
    public canteenLunch_Switch = element(by.id('canteenLunch'));
    public canteenDinner_Switch = element(by.id('canteenDinner'));
    public canteenSubscriptionPeriodStart_Input = element(by.id('canteenSubscriptionPeriodStart'));
    public canteenSubscriptionPeriodEnd_Input = element(by.id('canteenSubscriptionPeriodEnd'));
    public residentialSchoolRegistered_Switch = element(by.id('residentialSchoolRegistered'));
    public residentialSchoolName_Input = element(by.id('residentialSchoolName'));
    public residentialSchoolPeriodStart_Input = element(by.id('residentialSchoolPeriodStart'));
    public residentialSchoolPeriodEnd_Input = element(by.id('residentialSchoolPeriodEnd'));
    public respLogin_Input = element(by.id('respLogin'));
    public finalFirstName = element(by.id('_finalFirstName'));
    public finalLastName = element(by.id('_finalLastName'));
    public finalSex = element(by.id('_finalSex'));
    public finalEmail = element(by.id('_finalEmail'));
    public finalBirthDate = element(by.id('_finalBirthDate'));
    public finalAddressName = element(by.id('_finalAddressName'));
    public finalPostalCode = element(by.id('_finalPostalCode'));
    public finalCity = element(by.id('_finalCity'));
    public finalCountry = element(by.id('_finalCountry'));
    public finalComment = element(by.id('_finalComment'));
    public finalNationalNumber = element(by.id('_finalNationalNumber'));
    public finalRedoublingYes = element(by.id('_finalRedoublingYes'));
    public finalRedoublingNo = element(by.id('_finalRedoublingNo'));
    public finalEntryDate = element(by.id('_finalEntryDate'));
    public finalOriginEstablishment = element(by.id('_finalOriginEstablishment'));
    public finalIndividualizedReceptionPlanYes = element(by.id('_finalIndividualizedReceptionPlanYes'));
    public finalIndividualizedReceptionPlanNo = element(by.id('_finalIndividualizedReceptionPlanNo'));
    public finalIndividualizedReceptionPlan = element(by.id('_finalIndividualizedReceptionPlan'));
    public finalPersonalizedSchoolingPlan = element(by.id('_finalPersonalizedSchoolingPlan'));
    public finalPersonalizedSchoolingPlanContent = element(by.id('_finalPersonalizedSchoolingPlanContent'));
    public finalExitDate = element(by.id('_finalExitDate'));
    public finalExitReason = element(by.id('_finalExitReason'));
    public finalResponsibleFirstName_1 = element(by.id('finalResponsibleFirstName-0'));
    public finalResponsibleLastName_1 = element(by.id('finalResponsibleLastName-0'));
    public finalResponsibleSex_1 = element(by.id('finalResponsibleSex-0'));
    public finalResponsibleBirthDate_1 = element(by.id('finalResponsibleBirthDate-0'));
    public finalResponsibleAddressName_1 = element(by.id('finalResponsibleAddressName-0'));
    public finalResponsiblePostalCode_1 = element(by.id('finalResponsiblePostalCode-0'));
    public finalResponsibleCity_1 = element(by.id('finalResponsibleCity-0'));
    public finalResponsibleCountry_1 = element(by.id('finalResponsibleCountry-0'));
    public finalResponsiblePhone_1 = element(by.id('finalResponsiblePhone-0'));
    public finalResponsibleCellPhone_1 = element(by.id('finalResponsibleCellPhone-0'));
    public finalResponsibleEmail_1 = element(by.id('finalResponsibleEmail-0'));
    public finalResponsibleSecondaryEmail_1 = element(by.id('finalResponsibleSecondaryEmail-0'));
    public finalResponsibleFamilySituation_1 = element(by.id('finalResponsibleFamilySituation-0'));
    public finalResponsibleStudentRelationship_1 = element(by.id('finalResponsibleStudentRelationship-0'));
    public finalResponsibleDependentChildren_1 = element(by.id('finalResponsibleDependentChildren-0'));
    public finalResponsibleProfession_1 = element(by.id('finalResponsibleProfession-0'));
    public finalResponsibleProEmail_1 = element(by.id('finalResponsibleProEmail-0'));
    public finalResponsibleWorkCellPhone_1 = element(by.id('finalResponsibleWorkCellPhone-0'));
    public finalResponsibleParentsAssociation_1 = element(by.id('finalResponsibleParentsAssociation-0'));
    public finalResponsibleParentsAssociationPosition_1 = element(by.id('finalResponsibleParentsAssociationPosition-0'));
    public finalResponsibleComment_1 = element(by.id('finalResponsibleComment-0'));
    public finalResponsibleFirstName_2 = element(by.id('finalResponsibleFirstName-1'));
    public finalResponsibleLastName_2 = element(by.id('finalResponsibleLastName-1'));
    public finalResponsibleSex_2 = element(by.id('finalResponsibleSex-1'));
    public finalResponsibleBirthDate_2 = element(by.id('finalResponsibleBirthDate-1'));
    public finalResponsibleAddressName_2 = element(by.id('finalResponsibleAddressName-1'));
    public finalResponsiblePostalCode_2 = element(by.id('finalResponsiblePostalCode-1'));
    public finalResponsibleCity_2 = element(by.id('finalResponsibleCity-1'));
    public finalResponsibleCountry_2 = element(by.id('finalResponsibleCountry-1'));
    public finalResponsiblePhone_2 = element(by.id('finalResponsiblePhone-1'));
    public finalResponsibleCellPhone_2 = element(by.id('finalResponsibleCellPhone-1'));
    public finalResponsibleEmail_2 = element(by.id('finalResponsibleEmail-1'));
    public finalResponsibleSecondaryEmail_2 = element(by.id('finalResponsibleSecondaryEmail-1'));
    public finalResponsibleFamilySituation_2 = element(by.id('finalResponsibleFamilySituation-1'));
    public finalResponsibleStudentRelationship_2 = element(by.id('finalResponsibleStudentRelationship-1'));
    public finalResponsibleDependentChildren_2 = element(by.id('finalResponsibleDependentChildren-1'));
    public finalResponsibleProfession_2 = element(by.id('finalResponsibleProfession-1'));
    public finalResponsibleProEmail_2 = element(by.id('finalResponsibleProEmail-1'));
    public finalResponsibleWorkCellPhone_2 = element(by.id('finalResponsibleWorkCellPhone-1'));
    public finalResponsibleParentsAssociation_2 = element(by.id('finalResponsibleParentsAssociation-1'));
    public finalResponsibleParentsAssociationPosition_2 = element(by.id('finalResponsibleParentsAssociationPosition-1'));
    public finalResponsibleComment_2 = element(by.id('finalResponsibleComment-1'));
    public finalCanteenRecordedYes = element(by.id('_finalCanteenRecordedYes'));
    public finalCanteenRecordedNo = element(by.id('_finalCanteenRecordedNo'));
    public finalCanteenBreakfastYes = element(by.id('_finalCanteenBreakfastYes'));
    public finalCanteenBreakfastNo = element(by.id('_finalCanteenBreakfastNo'));
    public finalCanteenLunchYes = element(by.id('_finalCanteenLunchYes'));
    public finalCanteenLunchNo = element(by.id('_finalCanteenLunchNo'));
    public finalCanteenDinnerYes = element(by.id('_finalCanteenDinnerYes'));
    public finalCanteenDinnerNo = element(by.id('_finalCanteenDinnerNo'));
    public finalCanteenWeekDays = element(by.id('_finalCanteenWeekDays'));
    public finalSubscriptionPeriodStart = element(by.id('_finalSubscriptionPeriodStart'));
    public finalSubscriptionPeriodEnd = element(by.id('_finalSubscriptionPeriodEnd'));
    public finalResidentialSchoolRegisteredYes = element(by.id('_finalResidentialSchoolRegisteredYes'));
    public finalResidentialSchoolRegisteredNo = element(by.id('_finalResidentialSchoolRegisteredNo'));
    public finalResidentialSchoolName = element(by.id('_finalResidentialSchoolName'));
    public finalResidentialSchoolPeriodStart = element(by.id('_finalResidentialSchoolPeriodStart'));
    public finalResidentialSchoolPeriodEnd = element(by.id('_finalResidentialSchoolPeriodEnd'));

    getCanteenWeekDay_Checkbox(dayName: string): ElementFinder {
        return element(by.id('canteenWeekDays-label-' + (dayName)));
    }

    getAddResponsible_Button(responsibleNumber: number): ElementFinder {
        return element(by.id('addResponsible-' + (responsibleNumber - 1)));
    }

    getRemoveResponsible_Button(responsibleNumber: number): ElementFinder {
        return element(by.id('remove-responsible-' + (responsibleNumber - 1)));
    }

    getResponsibleFirstName_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleFirstName-' + (responsibleNumber - 1)));
    }

    getResponsibleNationality_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleNationality-' + (responsibleNumber - 1)));
    }

    getRespAccount_Switch(responsibleNumber: number): ElementFinder {
        return element(by.id('respAccount-' + (responsibleNumber - 1)));
    }

    getResponsibleLastName_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleLastName-' + (responsibleNumber - 1)));
    }

    getResponsibleSex_Male_Radio(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleSexOption-' + (responsibleNumber - 1) + '-0'));
    }

    getResponsibleSex_Female_Radio(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleSexOption-' + (responsibleNumber - 1) + '-1'));
    }

    getResponsibleBirthDate_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleBirthDate-' + (responsibleNumber - 1)));
    }

    getResponsibleAddressName_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleAddressName-' + (responsibleNumber - 1)));
    }

    getResponsiblePostalCode_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsiblePostalCode-' + (responsibleNumber - 1)));
    }

    getResponsibleCity_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleCity-' + (responsibleNumber - 1)));
    }

    getResponsibleCountry_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleCountry-' + (responsibleNumber - 1)));
    }

    getResponsiblePhone_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsiblePhone-' + (responsibleNumber - 1)));
    }

    getResponsibleCellPhone_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleCellPhone-' + (responsibleNumber - 1)));
    }

    getResponsibleEmail_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleEmail-' + (responsibleNumber - 1)));
    }

    getResponsibleSecondaryEmail_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('ResponsibleSecondaryEmail-' + (responsibleNumber - 1)));
    }

    getResponsibleProEmail_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleProEmail-' + (responsibleNumber - 1)));
    }

    getResponsibleWorkCellPhone_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleWorkCellPhone-' + (responsibleNumber - 1)));
    }

    getResponsibleParentsAssociation_Switch(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleParentsAssociation-' + (responsibleNumber - 1)));
    }

    getResponsibleComment_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('responsibleComment-' + (responsibleNumber - 1)));
    }

    getParentsAssociationPosition_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('parentsAssociationPosition-' + (responsibleNumber - 1)));
    }

    getDependentChildren_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('dependentChildren-' + (responsibleNumber - 1)));
    }

    getStudentRelationship_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('studentRelationship-' + (responsibleNumber - 1)));
    }

    getResponsibleProfession_Input(responsibleNumber: number): ElementFinder {
        return element(by.id('profession-' + (responsibleNumber - 1)));
    }

    getFamilySituation_SelectOption(responsibleNumber: number, optionNumber: number): ElementFinder {
        return element(by.id('familySituationOption-' + (responsibleNumber - 1) + '-' + optionNumber));
    }

    getFamilySituation_Select(responsibleNumber: number): ElementFinder {
        return element(by.id('familySituation-' + (responsibleNumber - 1)));
    }

    fillBasicInfos(login: string, firstName: string, lastName: string, gender: string, nationality: string, email: string,
                   cellPhone: string, homePhone: string,
                   birthDate: string, addressName: string, postalCode: string, city: string,
                   country: string, comment: string) {
        this.login_Input.sendKeys(login);
        this.firstName_Input.sendKeys(firstName);
        this.lastName_Input.sendKeys(lastName);
        switch (gender) {
            case 'male':
                this.studentGender_male_Radio.click();
                break;
            case 'female':
                this.studentGender_female_Radio.click();
                break;
        }
        this.nationality_Input.sendKeys(nationality);
        this.email_Input.sendKeys(email);
        this.cellPhone_Input.sendKeys(cellPhone);
        this.homePhone_Input.sendKeys(homePhone);
        this.birthDate_Input.sendKeys(birthDate);
        this.addressName_Input.sendKeys(addressName);
        this.postalCode_Input.sendKeys(postalCode);
        this.city_Input.sendKeys(city);
        this.country_Input.sendKeys(country);
        this.comment_Textarea.sendKeys(comment);
    }

    fillScholarInfos(nationalNumber: string, redoubling: boolean, entryDate: string, originEstablishment: string, exitDate?: string, exitReason?: string) {
        this.nationalNumber_Input.sendKeys(nationalNumber);
        if (redoubling) {
            this.redoubling_Switch.click();
        }
        E2EUtils.scrollTo(this.entryDate_Input);
        this.entryDate_Input.sendKeys(entryDate);
        this.originEstablishment_Input.sendKeys(originEstablishment);
        if (exitDate) {
            E2EUtils.scrollTo(this.exitDate_Input);
            this.exitDate_Input.sendKeys(exitDate);
            this.exitReason_Input.sendKeys(exitReason);
        }
    }

    fillScholarInfosPlans(individualizedReceptionPlan: boolean,
                          individualizedReceptionPlanContent: string, personalizedSchoolingPlan: boolean,
                          personalizedSchoolingPlanContent: string) {
        if (individualizedReceptionPlan) {
            this.individualizedReceptionPlan_Switch.click();
            this.individualizedReceptionPlanContent_Input.sendKeys(individualizedReceptionPlanContent);
        }
        if (personalizedSchoolingPlan) {
            this.personalizedSchoolingPlan_Switch.click();
            this.personalizedSchoolingPlanContent_Input.sendKeys(personalizedSchoolingPlanContent);
        }
    }

    fillResponsible_firtPart(responsibleNumber: number, firstName: string, lastName: string, gender: string, nationality: string,
                             birthDate: string, address: string,
                             postalCode: string, city: string, country: string, phone: string, cellPhone: string, email: string,
                             secondaryEmail: string) {

        this.getResponsibleNationality_Input(responsibleNumber).sendKeys(nationality);
        this.getResponsibleFirstName_Input(responsibleNumber).sendKeys(firstName);
        this.getResponsibleLastName_Input(responsibleNumber).sendKeys(lastName);
        switch (gender) {
            case 'male':
                this.getResponsibleSex_Male_Radio(responsibleNumber).click();
                break;
            case 'female':
                this.getResponsibleSex_Female_Radio(responsibleNumber).click();
                break;
        }
        this.getResponsibleBirthDate_Input(responsibleNumber).sendKeys(birthDate);
        this.getResponsibleAddressName_Input(responsibleNumber).sendKeys(address);
        this.getResponsiblePostalCode_Input(responsibleNumber).sendKeys(postalCode);
        this.getResponsibleCity_Input(responsibleNumber).sendKeys(city);
        this.getResponsibleCountry_Input(responsibleNumber).sendKeys(country);
        this.getResponsiblePhone_Input(responsibleNumber).sendKeys(phone);
        this.getResponsibleCellPhone_Input(responsibleNumber).sendKeys(cellPhone);
        this.getResponsibleEmail_Input(responsibleNumber).sendKeys(email);
        this.getResponsibleSecondaryEmail_Input(responsibleNumber).sendKeys(secondaryEmail);
    }

    fillResponsible_secondPart(responsibleNumber: number, familySituation: number, studentRelationship: string, dependentChildren: number, profession: string,
                               proEmail: string, responsibleWorkCellPhone: string, responsibleParentsAssociation: boolean, parentsAssociationPosition: string, comment: string) {
        this.getFamilySituation_Select(responsibleNumber).click();
        this.getFamilySituation_SelectOption(responsibleNumber, familySituation).click();
        this.getStudentRelationship_Input(responsibleNumber).sendKeys(studentRelationship);
        this.getDependentChildren_Input(responsibleNumber).sendKeys(dependentChildren);
        this.getResponsibleProfession_Input(responsibleNumber).sendKeys(profession);
        this.getResponsibleProEmail_Input(responsibleNumber).sendKeys(proEmail);
        this.getResponsibleWorkCellPhone_Input(responsibleNumber).sendKeys(responsibleWorkCellPhone);
        if (responsibleParentsAssociation) {
            this.getResponsibleParentsAssociation_Switch(responsibleNumber).click();
            this.getParentsAssociationPosition_Input(responsibleNumber).sendKeys(parentsAssociationPosition);
        }
        this.getResponsibleComment_Input(responsibleNumber).sendKeys(comment);
    }

    fillMedicalInfos(knownDiseases: string, allergies: string, comment: string, healthInsurance: boolean, healthInsuranceName: string,
                     healthInsuranceContact: string, healthInsuranceReference: string, complementaryHealthInsurance: boolean,
                     complementaryHealthInsuranceName: string, complementaryHealthInsuranceContact: string, complementaryHealthInsuranceReference: string) {
        this.knownDiseases_Textarea.sendKeys(knownDiseases);
        this.allergies_Textarea.sendKeys(allergies);
        this.medicalInfosComment_Textarea.sendKeys(comment);
        if (healthInsurance) {
            this.healthInsurance_Switch.click();
            this.healthInsuranceName_Input.sendKeys(healthInsuranceName);
            this.healthInsuranceContact_Input.sendKeys(healthInsuranceContact);
            this.healthInsuranceReference_Input.sendKeys(healthInsuranceReference);
        }
        if (complementaryHealthInsurance) {
            this.complementaryHealthInsurance_Switch.click();
            this.complementaryHealthInsuranceName_Input.sendKeys(complementaryHealthInsuranceName);
            this.complementaryHealthInsuranceContact_Input.sendKeys(complementaryHealthInsuranceContact);
            this.complementaryHealthInsuranceReference_Input.sendKeys(complementaryHealthInsuranceReference);
        }
    }

    fillCanteenInfos(canteen: boolean, canteenBreakfast: boolean, canteenLunch: boolean, canteenDinner: boolean, daysName: Array<string>,
                     canteenSubscriptionPeriodStart: string, canteenSubscriptionPeriodEnd: string) {
        if (canteen) {
            this.canteenRecorded_Switch.click();
            if (canteenBreakfast) {
                this.canteenBreakfast_Switch.click();
            }
            if (canteenLunch) {
                this.canteenLunch_Switch.click();
            }
            if (canteenDinner) {
                this.canteenDinner_Switch.click();
            }
            daysName.forEach((dayName) => {
                this.getCanteenWeekDay_Checkbox(dayName).click();
            });
            this.canteenSubscriptionPeriodStart_Input.sendKeys(canteenSubscriptionPeriodStart);
            this.canteenSubscriptionPeriodEnd_Input.sendKeys(canteenSubscriptionPeriodEnd);
        }
    }

    fillResidentialSchoolInfos(registered: boolean, name: string, periodStart: string, periodEnd: string) {
        if (registered) {
            this.residentialSchoolRegistered_Switch.click();
            this.residentialSchoolName_Input.sendKeys(name);
            this.residentialSchoolPeriodStart_Input.sendKeys(periodStart);
            this.residentialSchoolPeriodEnd_Input.sendKeys(periodEnd);
        }

    }

    fillRespLogin(login: string) {
        this.respLogin_Input.sendKeys(login);
    }
}
