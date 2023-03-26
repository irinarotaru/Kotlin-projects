interface AbstractFactory
{
    fun yourJob(opt:Int):Persoana
}
class Facultate:AbstractFactory
{
    override fun yourJob(opt:Int):Student {
        if(opt==1)
            return Integralist()
        else
            return Restantier()
    }
}
interface Persoana{}
interface Student:Persoana
{
    fun spune()
}
class Integralist: Student {
    override fun spune()
    {
        println("Sunt student si sunt integralist")
    }
}
class Restantier:Student
{
    override fun spune()
    {
        println("Sunt student si sunt restantier")
    }
}
/*class Doctorat:AbstractFactory
{
    override fun yourJob(opt:Int): Persoana {
        return Profesor(opt:Int)
    }
}
class Job:AbstractFactory
{
    override fun yourJob(opt:Int): Persoana {
        return Secretar(opt:Int)
    }
}

class Profesor(var opt:Int):Persoana
{
    override fun spune() {
        println("Eu sunt profesor")
    }
}
class Secretar(var opt:Int):Persoana
{
    override fun spune() {
        println("Eu sunt secretar")
    }
}
*/
fun main(args: Array<String>) {
    var facultate=Facultate()
    //var doctorat=Doctorat()
    //var job=Job()
    var student=facultate.yourJob(1)
    var student2=facultate.yourJob(2)
    //var profesor=doctorat.yourJob()
    //var secretar=job.yourJob()
    student.spune()
    student2.spune()
    //profesor.spune()
    //secretar.spune()
}