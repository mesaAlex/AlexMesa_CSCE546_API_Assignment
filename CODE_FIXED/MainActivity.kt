package com.example.userprofiles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userprofiles.ui.theme.UserProfilesTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchJoke()

        setContent {
            UserProfilesTheme {
                val joke by viewModel.joke.observeAsState()

                @OptIn(ExperimentalMaterial3Api::class)
                Scaffold(
                    topBar = {
                        TopAppBar(title = { Text("Alex's Funny App") })
                    }
                ) { padding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            if (joke != null) {
                                Text(
                                    text = joke!!.setup,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = joke!!.punchline,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            } else {
                                Text(
                                    text = "Loading…",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(onClick = { viewModel.fetchJoke() }) {
                                Text("Tell me another joke")
                            }
                        }
                    }
                }
            }
        }
    }
}