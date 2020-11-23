package leftshiftone.intentmarkup

import leftshiftone.intentmarkup.validation.IntentMarkupValidator
import leftshiftone.intentmarkup.validation.XmlValidation
import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import javax.xml.parsers.DocumentBuilderFactory

class IntentMarkupParser {

    companion object {
        // DocumentBuilderFactory and DocumentBuilder are not thread safe but resource intensive instances
        private val DOCUMENT_BUILDER = ThreadLocal.withInitial {
            val builder = DocumentBuilderFactory.newInstance()
            builder.newDocumentBuilder()
        }!!

        @JvmStatic
        fun getDocumentBuilder() = DOCUMENT_BUILDER.get()!!

        @JvmStatic
        private val validator = IntentMarkupValidator()

    }

    private fun parse(xml: String, validate: Boolean): IntentMarkup {
        val documentBuilder = getDocumentBuilder()
        val text = "<markup>$xml</markup>"
        if(validate){
            val validator = validator
            val validation = validator.validate(text)
            if (validation is XmlValidation.Failure) throw RuntimeException(validation.getMessage())
        }

        try {
            val document = documentBuilder.parse(ByteArrayInputStream(text.toByteArray(Charsets.UTF_8)))
            val root = document.find("markup")
            val intentNode= root.findAll("intent")
                    .firstOrNull()
            if(intentNode==null){
                return IntentMarkup(true, root.textContent, emptyList())
            }

            val musts = intentNode.findAll("must").map {
                MustWord(it.textContent, it.isTrue("fuzzy"))
            }
            return IntentMarkup(intentNode.isTrue("autocomplete", true), intentNode.textContent, musts)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun parse(stream: InputStream, validate: Boolean =true): IntentMarkup {
        return parse(stream.reader(StandardCharsets.UTF_8).readText(), validate)
    }

}

fun Node.find(tag: String): Node {
    val nodeList = this.childNodes
    for (i in 0 until nodeList.length) {
        if (nodeList.item(i).nodeName === tag) {
            return nodeList.item(i)
        }
    }
    throw RuntimeException("An error occurred while parsing GQL file. Check validity.")
}

fun Node.findAll(vararg tags: String): List<Node> {
    val result = ArrayList<Node>()
    val nodeList = this.childNodes
    for (i in 0..nodeList.length - 1) {
        val nodeName = nodeList.item(i).nodeName
        if (nodeName in tags) {
            result.add(nodeList.item(i))
        }
    }
    return result
}

fun Node.isTrue(name: String, defaultValue: Boolean = false): Boolean {
    val attrNode = this.attributes.getNamedItem(name)
    return attrNode?.textContent?.equals("true") ?: defaultValue
}