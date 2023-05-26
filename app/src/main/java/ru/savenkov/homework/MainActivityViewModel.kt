package ru.savenkov.homework

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.data.ListItem

class MainActivityViewModel(
    private val repository: Repository
): ViewModel() {

    var studentsList = MutableLiveData<List<ListItem>>()

    fun getStudents() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val list = repository.getData()
            studentsList.postValue(list)
        } catch (er: Exception) {
            Log.e("err", er.message.toString())
        }

    }
}