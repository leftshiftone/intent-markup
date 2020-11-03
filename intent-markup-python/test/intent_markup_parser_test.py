from unittest import TestCase

from intent_markup.intent_markup_parser import IntentMarkupParser


class IntentMarkupParserTest(TestCase):

    def test_intent_markup_parser_01(self):
        with open('test01.xml', 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertFalse(intent.autocomplete)
            self.assertEquals("Das ist ein Beispiel.", intent.text)
            self.assertTrue(len(intent.musts) == 0)

    def test_intent_markup_parser_02(self):
        with open('test02.xml', 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertTrue(intent.autocomplete)
            self.assertEquals("Das ist ein Beispiel.", intent.text)
            self.assertFalse(len(intent.musts) == 0)
            self.assertTrue(intent.musts[0].fuzzy)
            self.assertEquals("Beispiel", intent.musts[0].text)

    def test_intent_markup_parser_03(self):
        with open('test03.xml', 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertTrue(intent.autocomplete)
            self.assertEquals("Das ist ein Beispiel.", intent.text)
            self.assertFalse(len(intent.musts) == 0)
            self.assertTrue(intent.musts[0].fuzzy)
            self.assertEquals("Beispiel", intent.musts[0].text)
