export class IntentMarkup {
    readonly autocomplete: boolean
    readonly text: string
    readonly musts: MustWord[]

    constructor(autocomplete: boolean, text: string, musts: MustWord[]) {
        this.autocomplete = autocomplete
        this.text = text
        this.musts = musts
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
