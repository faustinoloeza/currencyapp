package data.remote.api

import data.local.Preference
import domain.ICurrencyApiservice
import domain.IPreferenceRepository
import domain.model.ApiResponse
import domain.model.Currency
import domain.model.RequestState
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class CurrencyApiService(
    private val Preferences: IPreferenceRepository
) : ICurrencyApiservice {
    companion object {
        const val ENDPOINT_CURRENCY = "https://api.currencyapi.com/v3/latest";
        const val API_KEY_CURRENCY = "SUPERSECRET";
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true

            })
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(DefaultRequest) {
            headers {
                append("apikey", API_KEY_CURRENCY)
            }
        }
    }

    override suspend fun getLatestExchangeRates(): RequestState<List<Currency>> {
        return try {
            val response = httpClient.get(ENDPOINT_CURRENCY)
            if (response.status.value == 200) {
                println("API RESPONSE: ${response.body<String>()}")
                val apiResponse = Json.decodeFromString<ApiResponse>(response.body())

                val lastupdated = apiResponse.meta.last_updated_at
                Preferences.saveLastUpdated(lastupdated)
                RequestState.Success(data = apiResponse.data.values.toList())
            } else {
                RequestState.Error(message = "Error Code: ${response.status}")
            }
        } catch (e: Exception) {
            println("Error ${e.message.toString()}")
            RequestState.Error(message = e.message.toString())
        }
    }
}