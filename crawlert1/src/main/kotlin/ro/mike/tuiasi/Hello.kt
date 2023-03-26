package ro.mike.tuiasi
import khttp.responses.Response
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import khttp.get
import org.json.JSONArray
import org.json.JSONObject
import org.json.XML
import com.google.gson.Gson

class Crawler(link:String)
{
    var link:String=link
    fun getResource(): Response
    {
        var resp= get(link)
        return resp
    }
    fun processContent(contentType: String)
    {
        var res=this.getResource().toString()
        var jsn = ro.mike.tuiasi.JsonParser()
        var xml = ro.mike.tuiasi.XmlParser()
        var yaml = ro.mike.tuiasi.YamlParser()
        when(contentType){
            "Json" -> jsn.parse(res)
            "Xml" -> xml.parse(res)
            "Yaml" -> yaml.parse(res)
        }
    }
}

interface Parser
{
    fun parse(text: String): Map<*, *>
}

fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
    when (val value = this[it])
    {
        is JSONArray ->
        {
            val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
            JSONObject(map).toMap().values.toList()
        }
        is JSONObject -> value.toMap()
        else -> value
    }
}

class JsonParser: Parser
{
    override fun parse(text: String): Map<String, *>
    {
        return JSONObject(text).toMap()
    }
}

class XmlParser: Parser
{
    override fun parse(text: String): Map<*, *>
    {
        var json = JSONObject(text)
        var xml = XML.toString(json)
        var mapper = XmlMapper();
        var map = mapper.readValue(xml, Map::class.java)
        return map
    }
}

class YamlParser: Parser
{
    override fun parse(text: String): Map<*, *>
    {
        val gson = Gson()
        var sirg: String = gson.fromJson(text, String::class.java)
        sirg = sirg.replace("\\{".toRegex(), "")
        sirg = sirg.replace("\\}".toRegex(), "")
        val map = mapOf<String,String>()
        return map
    }
}

fun main()
{
    val url: String = "https://time.com/"
    var crw=Crawler(url)
    crw.processContent("json")
    crw.processContent("xml")
    crw.processContent("yaml")
}
