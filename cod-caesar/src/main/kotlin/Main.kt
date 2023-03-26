import java.io.BufferedReader
import java.io.File

fun CryptCaesar(key:Int,c:Char): Char
{
    var crypt:Char='a'
    if(c.isLowerCase())
    {
        if(c+key >'z')
        {
            crypt= c+key-25
        }
        else
        {
            crypt=c+key
        }
    }
    if(c.isUpperCase())
    {
        if(c+key >'Z')
        {
            crypt= c+key-25
        }
        else
        {
            crypt=c+key
        }
    }
    return crypt
}
fun main(args: Array<String>) {
    var bufferedReader: BufferedReader = File("text.txt").bufferedReader()
    var continut = bufferedReader.use { it.readText() }
    var list= listOf<String>()
    var list2= mutableListOf<String>()
    list=continut.split(" ")
    println(list)
    list.forEach {
        if(it.length in 4..7)
        {
            var chars=it.toCharArray()
            var chars2= mutableListOf<Char>()
            for (i in chars)
            {
                if(i.isLetter()) {
                    chars2.add(CryptCaesar(5,i))
                }
            }
            var cuv=String()
            for(i in 0..chars2.size-1) {
                cuv += chars2[i]
            }
            list2.add(cuv)
        }
        else {
            list2.add(it)
        }
    }
    println(list2)
}