package com.example.hackathon_perevalfstr.view.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.example.hackathon_perevalfstr.R
import com.example.hackathon_perevalfstr.UserConstructor

class UserProfileFragment : Fragment() {

    private val button: View by lazy {
        requireView().findViewById(R.id.button)
    }

    private val lastNameEdit: EditText by lazy {
        requireView().findViewById(R.id.lastNameEdit)
    }

    private val firstNameEdit: EditText by lazy {
        requireView().findViewById(R.id.firstNameEdit)
    }

    private val patronymicEdit: EditText by lazy {
        requireView().findViewById(R.id.patronymicEdit)
    }

    private val emailEdit: EditText by lazy {
        requireView().findViewById(R.id.emailEdit)
    }

    private val phoneEdit: EditText by lazy {
        requireView().findViewById(R.id.phoneEdit)
    }

    private val linkEdit: EditText by lazy {
        requireView().findViewById(R.id.socialLinkEdit)
    }

    private val userConstructor: UserConstructor by lazy {
        UserConstructor(this)
    }

    val pref by lazy {
        requireContext().getSharedPreferences(
            getString(R.string.shared_preferences_key),
            Context.MODE_PRIVATE
        )
    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_user_profile, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            button.setOnClickListener {
                Toast.makeText(requireContext(), "${pref.getString(getString(R.string.soc_link_key), "0")}", Toast.LENGTH_SHORT).show()
                pref.edit()
                    .putString(getString(R.string.last_name_key), lastNameEdit.text.toString())
                    .putString(getString(R.string.first_name_key), firstNameEdit.text.toString())
                    .putString(getString(R.string.patronymic_key), patronymicEdit.text.toString())
                    .putString(getString(R.string.email_key), emailEdit.text.toString())
                    .putString(getString(R.string.phone_key), phoneEdit.text.toString())
                    .putString(getString(R.string.soc_link_key), linkEdit.text.toString())
                    .apply()

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentRoot, PerevalListFragment())
                    .commit()
            }

            lastNameEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    userConstructor.lastName = p0
                }
                override fun afterTextChanged(p0: Editable?) {}
            })


            firstNameEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    userConstructor.firstName = p0
                }
                override fun afterTextChanged(p0: Editable?) {}
            })


            patronymicEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    userConstructor.patronymic = p0
                }
                override fun afterTextChanged(p0: Editable?) {}
            })

            emailEdit.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    userConstructor.email = p0
                }
                override fun afterTextChanged(p0: Editable?) {}
            })

            lastNameEdit.setText(pref.getString(getString(R.string.last_name_key), null))
            firstNameEdit.setText(pref.getString(getString(R.string.first_name_key), null))
            patronymicEdit.setText(pref.getString(getString(R.string.patronymic_key), null))
            emailEdit.setText(pref.getString(getString(R.string.email_key), null))
            phoneEdit.setText(pref.getString(getString(R.string.phone_key), null))
            linkEdit.setText(pref.getString(getString(R.string.soc_link_key), null))
        }

        fun onUserIsReady() {
            button.isEnabled = true
            button.isClickable = true
            button.background = AppCompatResources.getDrawable(requireContext(), R.color.blue)
        }

        fun lastNameStateChanged(value: Boolean) {
            if (value) lastNameEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_complied)
            else lastNameEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_error)

        }

        fun firstNameStateChanged(value: Boolean) {
            if (value) firstNameEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_complied)
            else firstNameEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_error)
        }

        fun patronymicStateChanged(value: Boolean) {
            if (value) patronymicEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_complied)
            else patronymicEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_error)
        }

        fun emailStateChanged(value: Boolean) {
            if (value) emailEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_complied)
            else emailEdit.background = AppCompatResources.getDrawable(requireContext(), R.drawable.edittext_background_error)
        }
    }

