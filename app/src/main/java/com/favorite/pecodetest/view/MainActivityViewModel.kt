package com.favorite.pecodetest.view

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    var numberOfFragments = mutableStateOf(1)
    var visible by mutableStateOf(numberOfFragments.value > 1)
}