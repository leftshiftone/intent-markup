import {IntentMarkup, MustWord} from "./IntentMarkup";

export default class IntentMarkupParser {

    public static parse(xml: string) {
        const parser = new DOMParser()
        const document = parser.parseFromString(xml, "text/xml");

        const root: any = document.firstChild
        const autocomplete = root.hasAttribute("autocomplete") ?
            root.getAttribute("autocomplete") == "true" : true

        const result = [];
        const mustWords = root.getElementsByTagName("must");
        for (var i = 0; i < mustWords.length; i++) {
            const mustWord = mustWords.item(i);
            const fuzzy = mustWord.hasAttribute("fuzzy") ?
                mustWord.getAttribute("fuzzy") == "true" : false
            result.push(new MustWord(mustWord.textContent, fuzzy));
        }

        return new IntentMarkup(autocomplete, root.textContent, result);
    }
}
