package org.example

import khttp.responses.Response

class GenericRequest(url:String, params:Map<String,String>) : Cloneable
{
    var url:String=url
    var params:Map<String,String> = params
    override fun clone():GenericRequest
    {
        return GenericRequest(url,params)
    }
}
interface HTTPGet
{
    fun getResponse(): Response
}
class GetRequest(url:String,params:Map<String,String>,timeout:Int) : HTTPGet
{
    var timeout:Int = timeout
    var genericReq:GenericRequest = GenericRequest(url,params)
    override fun getResponse(): Response {
        return khttp.get(genericReq.url,genericReq.params)
    }
    fun geturl():String=genericReq.url
}
class PostRequest(url:String,params:Map<String,String>)
{
    var genericReg:GenericRequest=GenericRequest(url,params)
    fun postData() : Response
    {
        return khttp.get(genericReg.url,genericReg.params)
    }
}
class CleanGetRequest(getReg:GetRequest) : HTTPGet
{
    var getReg:GetRequest=getReg
    var parentalConstrolDisallow:ArrayList<String> = ArrayList<String>()
    fun add(url:String)
    {
        parentalConstrolDisallow.add(url)
    }
    override fun getResponse(): Response {
        for (i in 0..parentalConstrolDisallow.size)
        {
            if(parentalConstrolDisallow[i]==getReg.geturl())
            {
                println("continut restrictionat")
            }
        }
        return getReg.getResponse()
    }
}
class KidsBrowser(cleanReq:CleanGetRequest,postReq:PostRequest)
{
    var cleanGet:CleanGetRequest=cleanReq
    var postReq:PostRequest?=postReq
    fun start(url:String):Response
    {
        return cleanGet.getResponse();
    }
    fun start2(url:String):Response
    {
        return postReq!!.postData()
    }
}

fun main() {
    var url1: String = "https://www.instagram.com/"
    val url2: String = "https://www.facebook.com/"
    val map: MutableMap<String, String> = mutableMapOf()
    var postReq = PostRequest(url2, map)
    var getReq = GetRequest(url2, map, 5)
    var cleanGetReq = CleanGetRequest(getReq)
    cleanGetReq.add(url2)
    var kidsBrowser = KidsBrowser(cleanGetReq, postReq)

    println(kidsBrowser.start(url1))
    println(kidsBrowser.start(url2))
    println(kidsBrowser.start2(url1))
    println(kidsBrowser.start2(url2))
}
