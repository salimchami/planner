import {Sanction} from './sanction.model';
import {Absence} from './absence.model';
import {SchoolClass} from '../schoolclass.model';

export class SchoolingInfos {

    constructor(
        public individualizedReceptionPlan?: boolean,
        public individualizedReceptionPlanContent?: string,
        public personalizedSchoolingPlan?: boolean,
        public personalizedSchoolingPlanContent?: string,
        public entryDate?: Date,
        public exitDate?: Date,
        public exitReason?: string,
        public nationalNumber?: string,
        public originEstablishment?: string,
        public redoubling?: boolean,
        public sanctions?: Array<Sanction>,
        public absences?: Array<Absence>,
    ) {
    }
}
