package presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import data.remote.api.CurrencyApiService

class HomeScreen:Screen {
    @Composable
    override fun Content() {
        println("=D")
        LaunchedEffect(Unit){
            CurrencyApiService().getLatestExchangeRates()
        }
    }
}