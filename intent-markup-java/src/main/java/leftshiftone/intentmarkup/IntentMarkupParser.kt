package leftshiftone.intentmarkup

import org.w3c.dom.Node
import java.io.ByteArrayInputStream
import java.io.FileInputStream
import java.io.InputStream
import javax.xml.XMLConstants
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.stream.StreamSource
import javax.xml.validation.SchemaFactory

class IntentMarkupParser {

    companion object {

        private val parser = ThreadLocal.withInitial {
            val builder = DocumentBuilderFactory.newInstance()
            builder.newDocumentBuilder()
        }!!

        private val validator = ThreadLocal.withInitial {
            val root = System.getProperty("user.dir")
            val xsdStream = FileInputStream(root + "/../src/main/resources/intent-markup.xsd")

            val factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
            val schema = factory.newSchema(StreamSource(xsdStream))
            schema.newValidator()
        }!!

        fun parse(stream: InputStream): IntentMarkup {
            val documentBuilder = parser.get()
            val validator = validator.get()

            try {
                val bytes = stream.readBytes()
                validator.validate(StreamSource(ByteArrayInputStream(bytes)))
                val document = documentBuilder.parse(ByteArrayInputStream(bytes))

                val root = document.find("intent")
                val musts = root.findAll("must").map {
                    MustWord(it.textContent, it.isTrue("fuzzy"))
                }
                return IntentMarkup(root.isTrue("autocomplete", true), root.textContent, musts)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

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
    for (i in 0 until nodeList.length - 1) {
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