import java.io.BufferedReader
import java.io.File

class Student(var strategy:Strategy)
{
    fun useStrategy()=strategy.use()
    fun spuneBanc(banc:String)
    {
        println("Iti voi spune un banc")
        println(banc)
    }
    fun asculta()
    {
        println("Ti-am ascultat bancul\n")
    }
    fun reactie(react:String)
    {
        when(react)
        {
            "chicot" ->{
                println("Studentul a chicotit")
            }
            "ras" -> {
                println("Studentul a ras")
            }
            "nimic" -> {
                println("Studentul nu a zis nimic")
            }
            else -> {
                println("Am terminat de zis bancuri")
                System.exit(0)
            }
        }
    }
}
interface Strategy
{
    fun use()
}
class StrategyA:Strategy
{
    override fun use()
    {
        println("folosim strategia A-banc *")
    }
}
class StrategyB:Strategy
{
    override fun use()
    {
        println("folosim strategia B-banc **")
    }
}
class StrategyC:Strategy
{
    override fun use() {
        println("folosim strategia C-banc ***")
    }
}
fun main(args: Array<String>) {
    var buffer:BufferedReader= File("bancuri.txt").bufferedReader()
    var input=buffer.use { it.readText() }
    var bancuri=input.split("---")
    var Student=Student(StrategyA())
    var Student2=Student(StrategyA())
    for(i in bancuri)
    {
        var ver=i.toCharArray()
        if(ver[2]=='*') {
            Student = Student(StrategyC())
            Student.spuneBanc(i)
            Student2.asculta()
            var reactie= readLine()!!
            Student2.reactie(reactie)
        }
        else
        {
            if(ver[1]=='*') {
                Student = Student(StrategyB())
                Student.spuneBanc(i)
                Student2.asculta()
                var reactie= readLine()!!
                Student2.reactie(reactie)
            }
            else{
                Student.spuneBanc(i)
                Student2.asculta()
                var reactie= readLine()!!
                Student2.reactie(reactie)
            }
        }

    }
}