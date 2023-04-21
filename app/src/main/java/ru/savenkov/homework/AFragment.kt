package ru.savenkov.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback

class AFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        view.findViewById<Button>(R.id.enter_b_fragment_btn)
            .setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, BFragment())
                    .addToBackStack(null)
                    .commit()
            }

        requireActivity().onBackPressedDispatcher
            .addCallback(this) {
            requireActivity().finishAffinity()
        }
        return view
    }



}