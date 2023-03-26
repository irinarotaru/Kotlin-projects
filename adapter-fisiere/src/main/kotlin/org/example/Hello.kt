package org.example
import kotlinx.coroutines.*
import java.io.File

interface FileConverter
{
    fun convertFile(file:String)
}
class FiletotxtConverter:FileConverter
{
    override fun convertFile(file: String) {
        println("converteste fisierul")
    }
}
class FileAdapter(var totxt:FiletotxtConverter):FileConverter
{
    override fun convertFile(file: String) {
        println("adapteaza fisierul")
    }
}
fun main(args: Array<String>)= runBlocking {
    var lista = mutableListOf<File>()
    var f=File("file.txt")
    var f2=File("file2.txt")
    lista.add(f)
    lista.add(f2)
    var textconv=async{
        FiletotxtConverter()
    }
    textconv.await().convertFile("file.txt")
    var adapter=FileAdapter(textconv.await())
    adapter.convertFile("file.txt")
}

