package com.example.publickeypinningkmmwithktor.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.publickeypinningkmmwithktor.Greeting
import com.example.publickeypinningkmmwithktor.httpclient.CorrectPinningService
import com.example.publickeypinningkmmwithktor.httpclient.IncorrectPinningService

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel =
        MainViewModel(CorrectPinningService(), IncorrectPinningService())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        GreetingView(Greeting().greet())
                        MainView()
                    }
                }
            }
        }
    }


    @Composable
    fun GreetingView(text: String) {
        Text(text = text)
    }

    @Preview
    @Composable
    fun DefaultPreview() {
        MyApplicationTheme {
            GreetingView("Hello, Android!")
        }
    }

    @Preview
    @Composable
    fun MainView() {
        val scroll = rememberScrollState(0)
        val gameUiState = viewModel.viewState.collectAsState()
        val textColor = if (gameUiState.value.data != null) MaterialTheme.colors.secondary else {
            MaterialTheme.colors.error
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .padding(8.dp)
            ) {
                Button(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1F),
                    onClick = { viewModel.fetchDataWithCorrectPins() }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.connect_with_correct_pinning)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(modifier = Modifier
                    .fillMaxHeight()
                    .weight(1F),
                    onClick = { viewModel.fetchDataWithIncorrectPins() }) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.connect_with_incorrect_pins)
                    )
                }
            }

            Text(
                text = gameUiState.value.data ?: gameUiState.value.exception?.message ?: "No data",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp)
                    .background(MaterialTheme.colors.onSurface)
                    .verticalScroll(scroll)
                    .padding(8.dp),
                color = textColor
            )
        }
    }
}
