"use strict";
exports.__esModule = true;
var address_model_1 = require("./sub/address.model");
var Responsible = /** @class */ (function () {
    function Responsible(respAccount, id, firstName, lastName, sex, birthdate, nationality, email, phone, familySituation, studentRelationship, dependentChildren, profession, secondaryEmail, proEmail, cellPhone, workPhone, workCellPhone, address, parentsAssociation, parentsAssociationPosition, comment) {
        this.respAccount = respAccount;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.birthdate = birthdate;
        this.nationality = nationality;
        this.email = email;
        this.phone = phone;
        this.familySituation = familySituation;
        this.studentRelationship = studentRelationship;
        this.dependentChildren = dependentChildren;
        this.profession = profession;
        this.secondaryEmail = secondaryEmail;
        this.proEmail = proEmail;
        this.cellPhone = cellPhone;
        this.workPhone = workPhone;
        this.workCellPhone = workCellPhone;
        this.address = address;
        this.parentsAssociation = parentsAssociation;
        this.parentsAssociationPosition = parentsAssociationPosition;
        this.comment = comment;
        this.parentsAssociation = false;
        this.address = new address_model_1.Address();
    }
    return Responsible;
}());
exports.Responsible = Responsible;
//# sourceMappingURL=responsible.model.js.map