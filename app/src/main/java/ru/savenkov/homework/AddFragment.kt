package ru.savenkov.homework

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.core.view.isVisible


class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        val editText = view.findViewById<EditText>(R.id.input_text)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        view.findViewById<Button>(R.id.enter_button).setOnClickListener { enterButton ->
            if (editText.text.isEmpty()) enterButton.makeWiggle()
            else {
                enterButton.isVisible = false
                editText.isVisible = false
                progressBar.apply {
                    isVisible = true
                    postDelayed({ try {
                        parentFragmentManager.popBackStack()
                    } catch (err: Exception) {
                        Log.e("err", err.message.toString())
                    }
                    },1000)
                    fadeIn()
                }
            }
        }

        return view
    }
}