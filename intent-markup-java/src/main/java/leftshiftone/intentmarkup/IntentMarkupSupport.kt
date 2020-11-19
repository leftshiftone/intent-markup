package leftshiftone.intentmarkup

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class IntentMarkupSupport {

    companion object {
        private fun getResourceAsStream(resource: String): InputStream? = IntentMarkupSupport::class.java.getResourceAsStream(resource)

        fun getIntentMarkupVersion(): String {
            val prop = Properties()
            prop.load( this::class.java.getResourceAsStream("/config.properties"))
            return prop.getOrDefault("version","1.9.0") as String
        }

    }

}