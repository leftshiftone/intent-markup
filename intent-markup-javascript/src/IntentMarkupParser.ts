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

/*
    def parse_must_words(element: xml.Element):
        text = element.text
    fuzzy = element.attrib["fuzzy"] == "true" if "fuzzy" in element.attrib else False
    return MustWord(text, fuzzy)

    def element_to_string(element):
        s = str(element.text) or ""
    for sub_element in element:
        s += element_to_string(sub_element)
    if element.tail is not None:
        s += element.tail
    return s

    parsed_xml = xml.fromstring(raw_xml)

    autocomplete = parsed_xml.attrib["autocomplete"] == "true" if "autocomplete" in parsed_xml.attrib else True
    value = element_to_string(parsed_xml)
    must_words = list(map(parse_must_words, parsed_xml.findall("must")))

    return IntentMarkup(autocomplete, value, must_words)
}*/
