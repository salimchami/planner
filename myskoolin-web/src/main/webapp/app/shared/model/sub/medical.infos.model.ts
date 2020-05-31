export class MedicalInfos {

    constructor(
        public healthInsurance?: boolean,
        public healthInsuranceName?: string,
        public healthInsuranceReference?: string,
        public healthInsuranceContact?: string,
        public complementaryHealthInsurance?: boolean,
        public complementaryHealthInsuranceName?: string,
        public complementaryHealthInsuranceReference?: string,
        public complementaryHealthInsuranceContact?: string,
        public healthComment?: string,
        public allergies?: string,
        public knownDiseases?: string
    ) {
    }
}
