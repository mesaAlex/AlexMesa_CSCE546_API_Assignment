package com.example.userprofiles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    fun fetchQuote() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getRandomQuote()
                _quote.value = response.first()
            } catch (e: Exception) {
                _quote.value = Quote("| ERROR RECEIVING QUOTE |", "Restart Program")
            }
        }
    }
}