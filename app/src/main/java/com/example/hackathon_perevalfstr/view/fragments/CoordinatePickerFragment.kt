package com.example.hackathon_perevalfstr.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.hackathon_perevalfstr.PerevalConstructor
import com.example.hackathon_perevalfstr.R
import java.lang.Exception

class CoordinatePickerFragment(val constructor: PerevalConstructor) : Fragment() {

    private val latitudeEditFirst: EditText by lazy {
        requireView().findViewById(R.id.latitudeEditFirst)
    }
    private val latitudeEditSecond: EditText by lazy {
        requireView().findViewById(R.id.latitudeEditSecond)
    }
    private val longitudeEditFirst: EditText by lazy {
        requireView().findViewById(R.id.longitudeEditFirst)
    }
    private val longitudeEditSecond: EditText by lazy {
        requireView().findViewById(R.id.longitudeEditSecond)
    }
    private val heightEdit: EditText by lazy {
        requireView().findViewById(R.id.heightEdit)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coordinate_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        latitudeEditFirst.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                constructor.latitude = getLatitude()
            }
        })

        latitudeEditSecond.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                constructor.latitude = getLatitude()
            }
        })
        
        longitudeEditFirst.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                constructor.longitude = getLongitude()
            }
        })


        longitudeEditSecond.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                constructor.longitude = getLongitude()
            }
        })

        heightEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                constructor.height = getHeight()
            }
        })

    }

    fun getLatitude(): Double {
        var firstEl = 0.0
        var secondEl = 0.0
        try {
            firstEl = latitudeEditFirst.text.toString().toDouble()
        } catch (e: Exception) {}
        try {
            secondEl = latitudeEditSecond.text.toString().toDouble()/100
        } catch (e: Exception) {}
        return firstEl+secondEl
    }

    fun getLongitude(): Double {
        var firstEl = 0.0
        var secondEl = 0.0
        try {
            firstEl = longitudeEditFirst.text.toString().toDouble()
        } catch (e: Exception) {}
        try {
            secondEl = longitudeEditSecond.text.toString().toDouble()/100
        } catch (e: Exception) {}
        return firstEl+secondEl
    }

    fun getHeight(): Int {
        var result = 0
        try {
            result = heightEdit.text.toString().toInt()
        } catch (e: Exception) {}
        return result
    }

    override fun onDetach() {
        Toast.makeText(requireContext(), "${constructor.longitude} ${constructor.latitude} ${constructor.height}", Toast.LENGTH_SHORT).show()
        super.onDetach()
    }
}