package com.example.hackathon_perevalfstr.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.hackathon_perevalfstr.R

class PerevalListFragment : Fragment() {

    lateinit var appBarTextView: TextView

    lateinit var logoutButton: View

    lateinit var addPerevalButton: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pereval_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appBarTextView = requireView().findViewById(R.id.appBarTextView)
        logoutButton = requireView().findViewById(R.id.logoutButton)
        addPerevalButton = requireView().findViewById(R.id.addPerevalButton)

        val pref = requireContext().getSharedPreferences(
            getString(R.string.shared_preferences_key),
            Context.MODE_PRIVATE
        )

        val str = pref.getString(getString(R.string.first_name_key), "") + " " + pref.getString(
            getString(R.string.last_name_key), ""
        )

        appBarTextView.text = str

        logoutButton.setOnClickListener {
            pref.edit().clear().apply()
            parentFragmentManager.popBackStack()
        }

        addPerevalButton.setOnClickListener {
            val tr = parentFragmentManager.beginTransaction()
                .addToBackStack(null)
            tr.replace(R.id.fragmentRoot, AddPerevalFragment(), "add_pereval")
                .commit()
        }
    }
}
