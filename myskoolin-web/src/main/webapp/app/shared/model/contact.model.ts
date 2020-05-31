export class Contact {

    static genderFromSex(sex: string): string {
        let result;

        switch (sex) {
            case 'global.constants.sexes.male':
                result = 'global.constants.genders.mr';
                break;
            case 'global.constants.sexes.female':
                result = 'global.constants.genders.mrs';
                break;
        }
        return result;
    }

    constructor(public name: string,
                public email: string,
                public message: string,
                public type: string,
                public sex: string,
                public langKey: string,
                public phone: string,
                public translatedGender: string,
                public gender: string
    ) {
    }
}

// }
