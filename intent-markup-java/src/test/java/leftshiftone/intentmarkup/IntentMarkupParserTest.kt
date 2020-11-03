package leftshiftone.intentmarkup

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntentMarkupParserTest {

    @Test
    fun testParse01() {
        val result = IntentMarkupParser.parse(IntentMarkupParserTest::class.java.getResourceAsStream("/test01.xml"))
        Assertions.assertFalse(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertTrue(result.musts.isEmpty())
    }

    @Test
    fun testParse02() {
        val result = IntentMarkupParser.parse(IntentMarkupParserTest::class.java.getResourceAsStream("/test02.xml"))
        Assertions.assertTrue(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertFalse(result.musts.isEmpty())
        Assertions.assertTrue(result.musts[0].fuzzy)
        Assertions.assertEquals("Beispiel", result.musts[0].text)
    }

    @Test
    fun testParse03() {
        val result = IntentMarkupParser.parse(IntentMarkupParserTest::class.java.getResourceAsStream("/test03.xml"))
        Assertions.assertTrue(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertFalse(result.musts.isEmpty())
        Assertions.assertTrue(result.musts[0].fuzzy)
        Assertions.assertEquals("Beispiel", result.musts[0].text)
    }

}