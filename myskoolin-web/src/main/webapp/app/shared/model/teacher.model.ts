import {BaseEntity} from './../../shared';
import {Address} from './sub/address.model';
import {InfirmaryStatistics} from './sub/infirmary.statistics.model';
import {MedicalInfos} from './sub/medical.infos.model';
import {Subject} from './subject.model';
import {Absence} from './sub/absence.model';
import {FormGroup} from '@angular/forms';
import {Grade} from './grade.model';
import {Timeslot} from './sub';

export class Teacher implements BaseEntity {

    constructor(
        public id?: string,
        public login?: string,
        public imageUrl?: string,
        public clientId?: string,
        public firstName?: string,
        public lastName?: string,
        public gender?: string,
        public nationality?: string,
        public familySituation?: string, // ------
        public birthDate?: Date,
        public homePhone?: string,
        public cellPhone?: string,
        public proPhone?: string,
        public proCellPhone?: string,
        public email?: string,
        public proEmail?: string,
        public address?: Address,
        public substitute?: boolean,
        public grades?: Array<Grade>,
        public taughtSubjects?: Array<Subject>,
        public infirmaryStatistics?: InfirmaryStatistics,
        public medicalInfos?: MedicalInfos,
        public employedDate?: Date,
        public exitDate?: Date,
        public exitReason?: string,
        public timetable?: Array<Timeslot>,
        public absences?: Array<Absence>,
        public substitutedTeachers?: Array<Teacher>,
        public comment?: string,
        public langKey?: string,
    ) {
        this.substitute = false;
        this.absences = [];
        this.address = new Address();
        this.substitutedTeachers = [];
        this.grades = [];
        this.taughtSubjects = [];
        this.infirmaryStatistics = new InfirmaryStatistics();
        this.medicalInfos = new MedicalInfos();
    }

    update(teacherUserForm: FormGroup, currentLang: string) {
        if (!this.langKey) {
            this.langKey = currentLang;
        }
        this.login = teacherUserForm.controls.login.value;
        this.firstName = teacherUserForm.controls.firstName.value;
        this.lastName = teacherUserForm.controls.lastName.value;
        this.gender = teacherUserForm.controls.gender.value;
        this.nationality = teacherUserForm.controls.nationality.value;
        this.birthDate = teacherUserForm.controls.birthDate.value;
        this.homePhone = teacherUserForm.controls.homePhone.value;
        this.cellPhone = teacherUserForm.controls.cellPhone.value;
        this.familySituation = teacherUserForm.controls.familySituation.value;
        this.proPhone = teacherUserForm.controls.proPhone.value;
        this.proCellPhone = teacherUserForm.controls.proCellPhone.value;
        this.email = teacherUserForm.controls.email.value;
        this.proEmail = teacherUserForm.controls.proEmail.value;
        this.address.name = teacherUserForm.controls.addressName.value;
        this.address.postalCode = teacherUserForm.controls.postalCode.value;
        this.address.city = teacherUserForm.controls.city.value;
        this.address.country = teacherUserForm.controls.country.value;
        this.substitute = teacherUserForm.controls.substitute.value;
        this.grades = teacherUserForm.controls.grades.value;
        this.taughtSubjects = teacherUserForm.controls.taughtSubjects.value;
        this.employedDate = teacherUserForm.controls.employedDate.value;
        this.exitDate = teacherUserForm.controls.exitDate.value;
        this.exitReason = teacherUserForm.controls.exitReason.value;
        this.substitutedTeachers = teacherUserForm.controls.substitutedTeachers.value;
        this.comment = teacherUserForm.controls.comment.value;
    }
}
