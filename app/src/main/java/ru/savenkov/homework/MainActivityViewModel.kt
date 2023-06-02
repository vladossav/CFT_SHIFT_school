package ru.savenkov.homework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import ru.savenkov.homework.api.ConvertApi
import ru.savenkov.homework.api.ConvertApiResponse
import ru.savenkov.homework.api.FileResponse

class MainActivityViewModel: ViewModel() {
    private val _convertApiResponse = MutableLiveData<ConvertApiResponse>()
    val convertApiResponse: LiveData<ConvertApiResponse>
    get() = _convertApiResponse

    fun uploadFile(fileName: String, fileRequestBody: ProgressRequestBody) {
        val convertApi = ConvertApi.create()
        val filePart = MultipartBody.Part.createFormData("FileData", fileName, fileRequestBody)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = convertApi.uploadFile(filePart)
                val responseObj = ConvertApiResponse(
                    response.raw().toString(),
                    response.errorBody().toString(),
                    response.body() as FileResponse
                )
                _convertApiResponse.postValue(responseObj)
            } catch (e: Exception) {
                val responseObj = ConvertApiResponse(
                    null,
                    e.message,
                    null
                )
                _convertApiResponse.postValue(responseObj)
            }
        }
    }
}