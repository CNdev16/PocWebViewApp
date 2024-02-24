package com.example.pocwebviewapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.utils.Event
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    val navigation = MutableLiveData<Event<NavigateCommand>>()

    fun navigate(navigateCommand: NavigateCommand) {
        navigation.value = Event(navigateCommand)
    }

    fun navigateBack() {
        viewModelScope.launch {
            navigate(NavigateCommand.Back)
        }
    }
}
