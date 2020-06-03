"use strict";
exports.__esModule = true;
// export namespace ContactBuilderPattern {
//     export class ContactBuilder {
//         private _name: string;
//         private _email: string;
//         private _message: string;
//
//         private _type: string;
//         private _sex: string;
//         private _langKey: string;
//         private _phone: string;
//         private _translatedGender: string;
//         private _gender: string;
//
//         constructor(name: string, email: string, message) {
//             this._name = name;
//             this._email = email;
//             this._message = message;
//         }
//
//         get gender(): string {
//             return this._gender;
//         }
//
//         get translatedGender(): string {
//             return this._translatedGender;
//         }
//
//         get phone(): string {
//             return this._phone;
//         }
//
//         get langKey(): string {
//             return this._langKey;
//         }
//
//         get message(): string {
//             return this._message;
//         }
//
//         get email(): string {
//             return this._email;
//         }
//
//         get sex(): string {
//             return this._sex;
//         }
//
//         get type(): string {
//             return this._type;
//         }
//
//         get name(): string {
//             return this._name;
//         }
//
//         withType(type: string): ContactBuilder {
//             this._type = type;
//             return this;
//         }
//
//         withSex(sex: string): ContactBuilder {
//             this._sex = sex;
//             return this;
//         }
//
//         withLangKey(langKey: string): ContactBuilder {
//             this._langKey = langKey;
//             return this;
//         }
//
//         withPhone(phone: string): ContactBuilder {
//             this._phone = phone;
//             return this;
//         }
//
//         withTranslatedGender(translatedGender: string): ContactBuilder {
//             this._translatedGender = translatedGender;
//             return this;
//         }
//
//         withGender(gender: string): ContactBuilder {
//             this._gender = gender;
//             return this;
//         }
//
//         build(): Contact {
//             return new Contact(this);
//         }
//
//     }
//
var Contact = /** @class */ (function () {
    function Contact(name, email, message, type, sex, langKey) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.type = type;
        this.sex = sex;
        this.langKey = langKey;
    }
    Contact.genderFromSex = function (sex) {
        var result;
        switch (sex) {
            case 'global.constants.sexes.male':
                result = 'global.constants.genders.mr';
                break;
            case 'global.constants.sexes.female':
                result = 'global.constants.genders.mrs';
                break;
        }
        return result;
    };
    Contact.prototype.setGender = function (value) {
        this.gender = value;
    };
    Contact.prototype.setTranslatedGender = function (value) {
        this.translatedGender = value;
    };
    Contact.prototype.setPhone = function (value) {
        this.phone = value;
    };
    return Contact;
}());
exports.Contact = Contact;
// }
//# sourceMappingURL=contact.model.js.map