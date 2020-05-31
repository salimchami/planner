import {Currency} from 'app/shared/model/currency.model';
import {Feature} from 'app/shared/model/feature.model';

export class Pricing {

    constructor(
        public  id?: string,
        public  titleCode?: string,
        public  amount?: number,
        public  intAmount?: string,
        public  decAmount?: string,
        public  currency?: Currency,
        public  period?: string,
        public  features?: Array<Feature>,
        public  schoolOffice?: number,
        public  teachers?: number,
        public  administration?: number,
        public  infirmary?: number,
        public  timeTables?: number,
        public  color?: string,
        public  icon?: string,
        public  position?: number,
    ) {
    }
}
