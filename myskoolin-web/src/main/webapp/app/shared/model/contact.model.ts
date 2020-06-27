export class Contact {

    static genderFromGender(gender: string): string {
        let result;

        switch (gender) {
            case 'global.constants.genders.male':
                result = 'global.constants.genders.mr';
                break;
            case 'global.constants.genders.female':
                result = 'global.constants.genders.mrs';
                break;
        }
        return result;
    }

    constructor(public name: string,
                public email: string,
                public message: string,
                public type: string,
                public langKey: string,
                public phone: string,
                public gender: string
    ) {
    }
}

// }
