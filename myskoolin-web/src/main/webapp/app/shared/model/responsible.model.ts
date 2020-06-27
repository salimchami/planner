import {BaseEntity} from '../../shared';
import {Address} from './sub/address.model';

export class Responsible implements BaseEntity {

    constructor(
        public respAccount: boolean,
        public id?: any,
        public firstName?: string,
        public lastName?: string,
        public gender?: string,
        public birthdate?: Date,
        public nationality?: string,
        public email?: string,
        public phone?: string,
        public familySituation?: string,
        public studentRelationship?: string,
        public dependentChildren?: number,
        public profession?: string,
        public secondaryEmail?: string,
        public proEmail?: string,
        public cellPhone?: string,
        public workPhone?: string,
        public workCellPhone?: string,
        public address?: Address,
        public parentsAssociation?: boolean,
        public parentsAssociationPosition?: string,
        public comment?: string,
    ) {
        this.parentsAssociation = false;
        this.address = new Address();
    }
}
