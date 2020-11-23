import IntentMarkupParser from "../IntentMarkupParser";

describe("IntentMarkupParser Test", () => {
    test('test parse xml 1', () => {
        const intent = IntentMarkupParser.parse('<intent autocomplete="false">Das ist ein Beispiel.</intent>')
        expect(intent.autocomplete).toBeFalsy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 0).toBeTruthy()
    });
    test('test parse xml 2', () => {
        const intent = IntentMarkupParser.parse('<intent>Das ist ein <must fuzzy="true">Beispiel</must>.</intent>')
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 1).toBeTruthy()
        expect(intent.musts[0].text).toBe("Beispiel")
        expect(intent.musts[0].fuzzy).toBeTruthy()
    });
    test('test parse xml 3', () => {
        const intent = IntentMarkupParser.parse('<intent autocomplete="true">Das ist ein <must fuzzy="true">Beispiel</must>.</intent>')
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
        expect(intent.musts.length == 1).toBeTruthy()
        expect(intent.musts[0].text).toBe("Beispiel")
        expect(intent.musts[0].fuzzy).toBeTruthy()
    });
    test('test parse xml 4', () => {
        const intent = IntentMarkupParser.parse('Das ist ein Beispiel.')
        expect(intent.autocomplete).toBeTruthy()
        expect(intent.text).toBe("Das ist ein Beispiel.")
    });
})
