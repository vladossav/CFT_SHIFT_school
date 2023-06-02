package ru.savenkov.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import okhttp3.MediaType
import okhttp3.RequestBody

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fileName = "documentation_api.docx"
        val file = assets.open(fileName).readBytes()
        val responseRaw = findViewById<TextView>(R.id.response_raw)
        val responseBody = findViewById<TextView>(R.id.response_data)
        val responseError = findViewById<TextView>(R.id.response_error)
        val progressPercent = findViewById<TextView>(R.id.progress_percent)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val fileRequestBody = ProgressRequestBody(
            RequestBody.create(MediaType.parse("multipart/form-data"), file)
        ) { bytesWritten, contentLength ->
            runOnUiThread {
                val a = (bytesWritten * 100 / contentLength)
                val str = "$a%"
                progressBar.progress = a.toInt()
                progressPercent.text = str
            }
        }
        findViewById<Button>(R.id.upload_button).setOnClickListener {
            progressBar.isVisible = true
            progressPercent.isVisible = true
            viewModel.uploadFile(fileName, fileRequestBody)
        }

        viewModel.convertApiResponse.observe(this) { response ->
            responseRaw.text = response.responseRaw
            responseBody.text = response.responseBody.toString()
            if (response.responseError != "null") responseError.text = response.responseError
        }
    }
}