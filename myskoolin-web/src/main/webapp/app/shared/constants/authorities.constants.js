"use strict";
exports.__esModule = true;
var Authorities;
(function (Authorities) {
    Authorities["ROLE_ADMINISTRATION"] = "ROLE_ADMINISTRATION";
    Authorities["ROLE_INFIRMARY"] = "ROLE_INFIRMARY";
    Authorities["ROLE_SCHOOL_LIFE"] = "ROLE_SCHOOL_LIFE";
    Authorities["ROLE_SCHOOLME_ADMIN"] = "ROLE_SCHOOLME_ADMIN";
    Authorities["ROLE_STUDENTS"] = "ROLE_STUDENTS";
    Authorities["ROLE_STUDENT_RESPONSIBLE"] = "ROLE_STUDENT_RESPONSIBLE";
    Authorities["ROLE_TEACHERS"] = "ROLE_TEACHERS";
    Authorities["ROLE_ANONYMOUS"] = "ROLE_ANONYMOUS";
})(Authorities = exports.Authorities || (exports.Authorities = {}));
exports.AUTHORITIES_ALL = [
    Authorities.ROLE_ADMINISTRATION,
    Authorities.ROLE_INFIRMARY,
    Authorities.ROLE_SCHOOL_LIFE,
    Authorities.ROLE_SCHOOLME_ADMIN,
    Authorities.ROLE_STUDENTS,
    Authorities.ROLE_STUDENT_RESPONSIBLE,
    Authorities.ROLE_TEACHERS,
    Authorities.ROLE_ANONYMOUS
];
exports.AUTHORITIES_AUTHENTICATED_USERS = [
    Authorities.ROLE_ADMINISTRATION,
    Authorities.ROLE_INFIRMARY,
    Authorities.ROLE_SCHOOL_LIFE,
    Authorities.ROLE_SCHOOLME_ADMIN,
    Authorities.ROLE_STUDENTS,
    Authorities.ROLE_STUDENT_RESPONSIBLE,
    Authorities.ROLE_TEACHERS
];
exports.AUTHORITIES_ADMIN_USERS = [
    Authorities.ROLE_ADMINISTRATION,
    Authorities.ROLE_SCHOOL_LIFE,
    Authorities.ROLE_SCHOOLME_ADMIN
];
//# sourceMappingURL=authorities.constants.js.map