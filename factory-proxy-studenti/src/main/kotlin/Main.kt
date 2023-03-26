import java.io.BufferedReader
import java.io.File
import java.io.InputStream
interface Persoana
{
    fun stareStudent()
}
class Student(var note:MutableList<String>):Persoana
{
    override fun stareStudent() {
        var s=0
        for(i in note)
        {
            var numar=i.toInt()
            if(numar<5)
                s++
        }
        if(s>4)
            println("repetent")
        if(s<4&&s>2)
            println("restantier")
        if(s==0)
            println("integralist")
    }
}
class Proxy(var student:Student)
{
    fun stareStudent()
    {
        var parola= readLine()!!
        if(parola=="student")
            student.stareStudent()
    }
}
interface Creator
{
    fun facultate():Persoana
}
class Facultate(var note:MutableList<String>):Creator
{
    override fun facultate(): Persoana {
        return Student(note)
    }

}
fun main(args: Array<String>) {
    val buffer:BufferedReader=File("fisier.txt").bufferedReader()
    val input=buffer.use{it.readLine()}
    val sir=input.substring(20)
    println(input)
    println(sir)
    val list=sir.split(",")
    println(list)
    val note= mutableListOf<String>()
    var k=0
    for (i in list)
    {
        var cuv=i.split("to")
        note.add(cuv.first())
    }
    println(note)
    var facultate=Facultate(note)
    facultate.facultate().stareStudent()
    var student=Student(note)
    var proxy:Proxy=Proxy(student)
    proxy.stareStudent()
}


