export class ClientContractRequest {
    constructor(
        public id: string,
        public vatNumber?: string,
        public startDateMin?: Date,
        public startDateMax?: Date,
        public endDate?: Date,
        public wantedPricingId?: string,
    ) {
    }

}
