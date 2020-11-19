package leftshiftone.intentmarkup

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class IntentMarkupParserTest {

    @Test
    fun testParse01() {
        val result = IntentMarkupParser().parse(IntentMarkupParserTest::class.java.getResourceAsStream("/test01.xml"))
        Assertions.assertFalse(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertTrue(result.musts.isEmpty())
    }

    @Test
    fun testParse02() {
        val result = IntentMarkupParser().parse(IntentMarkupParserTest::class.java.getResourceAsStream("/test02.xml"))
        Assertions.assertTrue(result.autocomplete)
        Assertions.assertEquals("Das ist ein Beispiel.", result.text)
        Assertions.assertFalse(result.musts.isEmpty())
        Assertions.assertTrue(result.musts[0].fuzzy)
        Assertions.assertEquals("Beispiel", result.musts[0].text)
    }

    @Test
    fun testParse03() {
        parseAndAssertTest03(IntentMarkupParser())
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
                    parseAndAssertTest03(parser)
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
    }

    private fun parseAndAssertTest03(parser: IntentMarkupParser, expectSuccess: Boolean = true) {
        try {
            val result = parser.parse(this.javaClass.getResourceAsStream("/test03.xml"))
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