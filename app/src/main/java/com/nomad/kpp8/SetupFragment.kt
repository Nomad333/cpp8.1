package com.nomad.kpp8

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class SetupFragment : Fragment(R.layout.fragment_setup) {
    private lateinit var startButton: Button
    private lateinit var minEditText: EditText
    private lateinit var maxEditText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startButton = view.findViewById(R.id.btnStart  )
        minEditText = view.findViewById(R.id.etRangeMin)
        maxEditText = view.findViewById(R.id.etRangeMax)

        startButton.setOnClickListener {

        }
    }

    private fun checkFields(){
    }
}