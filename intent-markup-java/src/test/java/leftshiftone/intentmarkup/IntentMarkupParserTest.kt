package leftshiftone.intentmarkup

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class IntentMarkupParserTest {

    @Test
    fun intent_markup_with_autocomplete_false() {
        val result = IntentMarkupParser().parse(IntentMarkupParserTest::class.java.getResourceAsStream("/intent_markup_with_autocomplete_false.xml"))
        Assertions.assertFalse(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertTrue(result.musts.isEmpty())
    }

    @Test
    fun intent_markup_with_autocomplete_implicit_and_fuzzy() {
        val result = IntentMarkupParser().parse(IntentMarkupParserTest::class.java.getResourceAsStream("/intent_markup_with_autocomplete_implicit_and_fuzzy.xml"))
        Assertions.assertTrue(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertFalse(result.musts.isEmpty())
        Assertions.assertTrue(result.musts[0].fuzzy)
        Assertions.assertEquals("Beispiel", result.musts[0].text)
    }

    @Test
    fun intent_markup_with_autocomplete_explicit_and_fuzzy() {
        intent_markup_with_autocomplete_explicit_and_fuzzy(IntentMarkupParser())
    }


    @Test
    fun intent_with_no_markup() {
        val result = IntentMarkupParser().parse(IntentMarkupParserTest::class.java.getResourceAsStream("/intent_with_no_markup.xml"))
        Assertions.assertTrue(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
    }

    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        val parser = IntentMarkupParser()
        (0..24).forEach {
            Thread {
                try{
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    (parser)
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
    }

    private fun intent_markup_with_autocomplete_explicit_and_fuzzy(parser: IntentMarkupParser, expectSuccess: Boolean = true) {
        try {
            val result = parser.parse(this.javaClass.getResourceAsStream("/intent_markup_with_autocomplete_explicit_and_fuzzy.xml"))
            Assertions.assertTrue(result.autocomplete)
            Assertions.assertEquals("Das ist ein Beispiel.", result.text)
            Assertions.assertFalse(result.musts.isEmpty())
            Assertions.assertTrue(result.musts[0].fuzzy)
            Assertions.assertEquals("Beispiel", result.musts[0].text)
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '/test03.xml' should not throw a validation exception", e)
            }
        }
    }

}