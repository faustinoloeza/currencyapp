import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.engine.cio.*

class Greeting {
    private val platform = getPlatform()
    //private val client = HttpClient()
    val client = HttpClient(CIO)

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun greeting(): String {
        val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/todos/1")
        //val response = client.get("https://ktor.io/docs/")
        client.close()
        return response.bodyAsText()
    }


}