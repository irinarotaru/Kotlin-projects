package com.sd.laborator.services

import com.sd.laborator.interfaces.AgeSearchInterface
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class  AgeSearchService: AgeSearchInterface {
    override fun getAge(name: String): Int {
// codificare parametru URL (deoarece poate conţine caracterespeciale)
        val encodedName = URLEncoder.encode(name,StandardCharsets.UTF_8.toString())
// construire obiect de tip URL
        val nameUrl =
            URL("https://www.agify.io/?name=$encodedName")
// preluare raspuns HTTP (se face cerere GET şi se preia conţinutul răspunsului sub formă de text)
        val rawResponse: String = nameUrl.readText()
// parsare obiect JSON
        val responseRootObject = JSONObject("{\"data\": ${rawResponse}}")val responseContentObject =
            responseRootObject.getJSONArray("data").takeUnless { it.isEmpty}?.getJSONObject(0)
        return responseContentObject?.getInt("woeid") ?: -1
    }
}