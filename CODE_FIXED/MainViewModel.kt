package com.example.userprofiles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _joke = MutableLiveData<Joke>()
    val joke: LiveData<Joke> = _joke

    fun fetchJoke() {
        viewModelScope.launch {
            try {
                _joke.value = RetrofitInstance.api.getRandomJoke()
            } catch (e: Exception) {
                _joke.value = Joke(0, "error", "Failed to load joke.", "")
            }
        }
    }
}