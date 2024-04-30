import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*
import io.ktor.http.*

class Greeting {
    private val platform = getPlatform()
    //private val client = HttpClient()
    val client = HttpClient(CIO)

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun greeting(): String {
       // val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/todos/1")

        val response: HttpResponse = client.get("https://ktor.io") {
            headers {
                append(HttpHeaders.Accept, "text/html")
                append(HttpHeaders.Authorization, "abc123")
                append(HttpHeaders.UserAgent, "ktor client")
            }
        }
        //val response = client.get("https://ktor.io/docs/")
        client.close()
        return response.bodyAsText()
    }


}