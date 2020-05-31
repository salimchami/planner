import {Interlocutor} from './interlocutor.model';
import {AcademicYear} from '../academic-years/academic-years.model';

export class SchoolmeSubscription {
    private schoolName: string;
    private dunsNumber: string;
    private address: string;
    private website: string;
    private phone: string;
    private email: string;
    contractualised: boolean;
    private langKey: string;
    private interlocutor: Interlocutor;
    date: Date;
    private academicYear: AcademicYear;

    constructor(academicYear: AcademicYear, schoolName: string, dunsNumber: string, address: string,
                website: string, phone: string, email: string, langKey: string, interlocutor: Interlocutor) {
        this.academicYear = academicYear;
        this.schoolName = schoolName;
        this.dunsNumber = dunsNumber;
        this.address = address;
        this.website = website;
        this.phone = phone;
        this.email = email;
        this.langKey = langKey;
        this.interlocutor = interlocutor;
    }
}
