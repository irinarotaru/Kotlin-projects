package org.example

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.io.File

fun String.removescript():String
{
    val remove={ str: String -> str.removeRange(str.indexOf("<script>"),str.indexOf("</script>"))}
    return remove(String())
}
fun main(args: Array<String>) = runBlocking(){
    var file= File("file.txt");
    var buf=file.bufferedReader()
    var cont=buf.readText()
    async{
        cont=cont.removescript()
    }
    println(cont)
    //println(cont.removeRange(cont.indexOf("<script>"),cont.indexOf("</script>")))
}

