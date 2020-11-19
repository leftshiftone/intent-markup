package leftshiftone.intentmarkup.validation

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class XmlValidatorTest {

    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        val validator = IntentMarkupValidator()
        val errorCounter= AtomicInteger(0)
        (0..24).forEach {
            Thread {
                try{
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    val validation = validator.validate("<intent autocomplete=\"false\">Das ist ein Beispiel.</intent>")
                    if(validation is XmlValidation.Failure) errorCounter.incrementAndGet()
                } catch (ex: Exception){
                    errorCounter.incrementAndGet()
                }
                finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
        assertThat(errorCounter.get()).isEqualTo(0)
    }

    @Test
    fun testSuccess() {
        val validator = IntentMarkupValidator()
        val validation = validator.validate("<intent autocomplete=\"false\">Das ist ein Beispiel.</intent>")
        if(validation is XmlValidation.Failure) fail<String>("Markup should be successfully validated")
    }


    @Test
    fun testFailure() {
        val validator = IntentMarkupValidator()
        val validation = validator.validate("<markup><container><block>abc</container></markup>")
        if(validation is XmlValidation.Success) fail<String>("Markup validation should fail!!!")
    }

}
