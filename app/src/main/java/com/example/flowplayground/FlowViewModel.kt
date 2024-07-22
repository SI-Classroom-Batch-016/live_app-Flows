package com.example.flowplayground

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

class FlowViewModel : ViewModel() {

    val repository = Repository()

    val x = "X"


    private val _errorMessage = MutableSharedFlow<String>()
    val errorMessage: SharedFlow<String> = _errorMessage


    private val _apiData = MutableStateFlow<String>("X")
    val apiData: StateFlow<String> = _apiData

    private val _apiDataNumber = MutableStateFlow<Int>(1)
    val apiDataNumber: StateFlow<Int> = _apiDataNumber

    val combinedFlow = apiData.combine(apiDataNumber) { firstFlowData, secondFlowData ->

        var result = ""
        for (i in 1..secondFlowData) {
            result += firstFlowData
        }

        result
    }


    fun loadData() {
        viewModelScope.launch {
            try {
                val result = repository.apiCall()
                _apiData.emit(result)

            } catch (ex: Exception) {
                Log.e("FlowViewModel", ex.message.toString())
                _errorMessage.emit(ex.message.toString())
            }
        }
    }

    fun loadDataNumber() {
        viewModelScope.launch {
            try {
                val result = repository.apiCallNumber()
                _apiDataNumber.emit(result)

            } catch (ex: Exception) {
                Log.e("FlowViewModel", ex.message.toString())
                _errorMessage.emit(ex.message.toString())
            }
        }
    }


//    private val _apiData = MutableLiveData<String>()
//    val apiData: LiveData<String> = _apiData
//
//    fun loadData() {
//        viewModelScope.launch {
//            try {
//                val result = repository.apiCall()
//                _apiData.postValue(result)
//            } catch (ex:Exception) {
//                Log.e("FlowViewModel", ex.message.toString())
//            }
//        }
//    }


}