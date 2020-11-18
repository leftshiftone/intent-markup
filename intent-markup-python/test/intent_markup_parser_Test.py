from unittest import TestCase
import os

from intent_markup.intent_markup_parser import IntentMarkupParser


class IntentMarkupParserTest(TestCase):
    root_dir = os.path.dirname(os.path.realpath(__file__))

    def test_intent_markup_parser_01(self):
        with open(f"{self.root_dir}/test01.xml", 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertFalse(intent.autocomplete)
            self.assertEqual("Das ist ein Beispiel.", intent.text)
            self.assertTrue(len(intent.musts) == 0)

    def test_intent_markup_parser_02(self):
        with open(f"{self.root_dir}/test02.xml", 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertTrue(intent.autocomplete)
            self.assertEqual("Das ist ein Beispiel.", intent.text)
            self.assertFalse(len(intent.musts) == 0)
            self.assertTrue(intent.musts[0].fuzzy)
            self.assertEqual("Beispiel", intent.musts[0].text)

    def test_intent_markup_parser_03(self):
        with open(f"{self.root_dir}/test03.xml", 'r') as f:
            intent = IntentMarkupParser.parse(f.read())
            self.assertTrue(intent.autocomplete)
            self.assertEqual("Das ist ein Beispiel.", intent.text)
            self.assertFalse(len(intent.musts) == 0)
            self.assertTrue(intent.musts[0].fuzzy)
            self.assertEqual("Beispiel", intent.musts[0].text)
