export class IntentMarkup {
    readonly autocomplete: boolean
    readonly keyword: boolean
    readonly text: string
    readonly musts: MustWord[]

    constructor(autocomplete: boolean, text: string, musts: MustWord[], keyword: boolean) {
        this.autocomplete = autocomplete
        this.text = text
        this.musts = musts
        this.keyword = keyword
    }
}

export class MustWord {
    readonly text: string
    readonly fuzzy: boolean

    constructor(text: string, fuzzy: boolean) {
        this.text = text
        this.fuzzy = fuzzy
    }
}
