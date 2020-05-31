export class GradeSerie {
    constructor(
        public name?: string,
        public diminutive?: string,
        public option?: string,
    ) {
    }

    public update(name: string) {
        this.name = name;
        return this;
    }

    public convert(json: any) {
        this.name = json.name;
        this.diminutive = json.diminutive;
        this.option = json.option;
        return this;
    }
}
