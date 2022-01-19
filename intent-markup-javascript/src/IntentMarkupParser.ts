import {IntentMarkup, MustWord} from "./IntentMarkup";

export class IntentMarkupParser {

    public static parse(xml: string) {
        const parser = new DOMParser()
        const document = parser.parseFromString("<markup>"+xml+"</markup>", "text/xml");

        const root: any = document.firstChild
        const intentNode = root.firstElementChild
        if( intentNode== null)
            return new IntentMarkup(true, root.textContent, [], false)
        const autocomplete = intentNode.hasAttribute("autocomplete") ?
            intentNode.getAttribute("autocomplete") == "true" : true

        const keyword = intentNode.hasAttribute("keyword-only") ?
            intentNode.getAttribute("keyword-only") == "true" : false

        const result = [];
        const mustWords =intentNode.getElementsByTagName("must");
        for (var i = 0; i < mustWords.length; i++) {
            const mustWord = mustWords.item(i);
            const fuzzy = mustWord.hasAttribute("fuzzy") ?
                mustWord.getAttribute("fuzzy") == "true" : false
            result.push(new MustWord(mustWord.textContent, fuzzy));
        }

        return new IntentMarkup(autocomplete, intentNode.textContent, result, keyword);
    }
}
