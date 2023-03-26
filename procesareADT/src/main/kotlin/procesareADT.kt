fun inmulteste(vector:ArrayList<Int>,alpha:Int)
{
    for (i in vector.indices)
    {
        vector[i]*=alpha
    }
}
fun ordoneaza(vector:ArrayList<Int>)
{
    for (i in vector.indices)
    {
        for (j in vector.indices)
        {
            if (vector[i]>vector[j])
            {
                vector[i]=vector[j].also { vector[j]=vector[i] }
            }
        }
    }
}
fun afiseaza(vector:ArrayList<Int>)
{
    for (i in vector.indices)
    {
        println(vector[i])
    }
}
class Inmulteste(vector: ArrayList<Int>, alpha: Int) : Runnable {
    @Volatile
    var vector: ArrayList<Int>
    var alpha: Int

    init {
        this.vector = vector
        this.alpha = alpha
    }

    override fun run() {
        for (i in vector.indices){
            vector[i] *= alpha
        }
    }
}

class Ordoneaza(vector: ArrayList<Int>) : Runnable {
    @Volatile
    var vector: ArrayList<Int>

    init {
        this.vector = vector
    }

    override fun run() {
        for (i in vector.indices)
        {
            for (j in vector.indices)
            {
                if (vector[i]>vector[j])
                {
                    vector[i]=vector[j].also { vector[j]=vector[i] }
                }
            }
        }
    }
}

class Afiseaza(vector: ArrayList<Int>) : Runnable {
    @Volatile
    var vector: ArrayList<Int>

    init {
        this.vector = vector
    }

    override fun run() {
        for (i in vector.indices)
        {
            println(vector[i])
        }
    }
}

fun main()
{
    var vector= arrayListOf<Int>(1,5,2,8,3,10,4,0)
    runBlocking<Unit>
    {
        lauch{inmulteste(vector,2)}
        lauch{ordoneaza(vector)}
        launch{afiseaza(vector)}
    }
    vector= arrayListOf<Int>(1,5,2,8,3,10,4,0)
    val inm= Thread(Inmulteste(vector, 2))
    val ord = Thread(Ordoneaza(vector))
    val afis = Thread(Afiseaza(vector))

    inm.run()
    ord.run()
    afis.run()
}