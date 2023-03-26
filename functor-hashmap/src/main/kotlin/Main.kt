class Myfunctor<K,V>(val hash:HashMap<K,V>){
    fun map(function:(V)->(V)):Myfunctor<K,V>
    {
        val hm=HashMap<K,V>()
        for((k,v) in hash)
        {
            hm[k]=function(v)
        }
        return Myfunctor(hm)
    }

}

fun linear(x:Int):Int
{
   return 3*x-1
}

fun main(args: Array<String>) {
    val value= hashMapOf<Int,Int>(1 to 5,2 to 7,3 to 8,4 to 11,5 to 24)
    println(Myfunctor(value).map(::linear))
}