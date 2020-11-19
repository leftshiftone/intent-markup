package leftshiftone.intentmarkup.validation

import leftshiftone.intentmarkup.IntentMarkupSupport
import java.io.InputStream
import java.nio.charset.StandardCharsets

open class IntentMarkupValidator {

    companion object {
        // XMLValidator is not thread safe but resource intensive instances
        private val VALIDATOR =  ThreadLocal.withInitial {
            XmlValidator(IntentMarkupValidator::class.java.getResourceAsStream("/intent-markup.xsd"))
        }

        @JvmStatic
        fun getValidator() = VALIDATOR.get()!!
    }

    /**
     * Provides the intent-markup version used in the validation
     * @return version of intent-markup
     */
    fun getVersion(): String {
        return IntentMarkupSupport.getIntentMarkupVersion()
    }

    /**
     * Validates the given input stream.
     *
     * @param stream the input stream
     * @return boolean
     */
    fun validate(stream: InputStream): XmlValidation {
        return getValidator().validate(stream)
    }

    /**
     * Validates the given markup text.
     *
     * @param markup the markup text
     * @return boolean
     */
    fun validate(markup: String) = validate(markup.byteInputStream(StandardCharsets.UTF_8))


}
