const fs= require("fs")
const path= require("path")
import {IntentMarkupParser} from "../IntentMarkupParser";

describe("IntentMarkupParser Test", () => {
    test('intent_markup_with_autocomplete_false', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_markup_with_autocomplete_false.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeFalsy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 0).toBeTruthy()
    });
    test('intent_markup_with_autocomplete_implicit_and_fuzzy', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_markup_with_autocomplete_implicit_and_fuzzy.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 1).toBeTruthy()
        expect(intent.musts[0].text).toBe("Beispiel")
        expect(intent.musts[0].fuzzy).toBeTruthy()
    });
    test('intent_markup_with_autocomplete_explicit_and_fuzzy.xml', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_markup_with_autocomplete_explicit_and_fuzzy.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 1).toBeTruthy()
        expect(intent.musts[0].text).toBe("Beispiel")
        expect(intent.musts[0].fuzzy).toBeTruthy()
    });
    test('intent_with_no_markup', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_with_no_markup.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
    });
    test('intent_markup_with_keyword_false.xml', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_markup_with_keyword_false.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("vpn")
        expect(intent.musts.length == 0).toBeTruthy()
        expect(intent.keyword).toBeFalsy()
    });
    test('intent_markup_with_keyword_true.xml', () => {
        let filePath = path.join(__dirname+'/../../build/testResources', 'intent_markup_with_keyword_true.xml');
        let xml = fs.readFileSync(filePath).toString()
        const intent = IntentMarkupParser.parse(xml)
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("vpn")
        expect(intent.musts.length == 0).toBeTruthy()
        expect(intent.keyword).toBeTruthy()
    });


})

