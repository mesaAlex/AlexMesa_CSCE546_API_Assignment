package com.example.userprofiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userprofiles.ui.theme.UserProfilesTheme
import androidx.compose.ui.graphics.Color
import coil.imageLoader

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchQuote()

        setContent {
            UserProfilesTheme {
                val quote by viewModel.quote.observeAsState()

                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Alex's Mesa Random Quote Generator") })
                    }
                ) { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(16.dp)
                            .padding(),
                    ) {
                        Text(
                            text = quote?.q ?: "Loading...",
                            style = MaterialTheme.typography.titleLarge
                            .copy(color = Color.Gray)

                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "- ${quote?.a ?: ""}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(onClick = { viewModel.fetchQuote() }) {
                            Text("New Quote")
                        }
                    }
                }
            }
        }
    }
}