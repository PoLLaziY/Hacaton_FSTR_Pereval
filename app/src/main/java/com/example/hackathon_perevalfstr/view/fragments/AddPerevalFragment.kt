package com.example.hackathon_perevalfstr.view.fragments

import android.app.DatePickerDialog
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.hackathon_perevalfstr.PerevalConstructor
import com.example.hackathon_perevalfstr.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddPerevalFragment : Fragment() {

    var bm: Bitmap? = null
    set(value) {
        field = value
        val root = requireView().findViewById<ViewGroup>(R.id.pictureRoot)
        root.removeAllViews()
        LayoutInflater.from(requireContext()).inflate(R.layout.added_photo, root, true)
        root.findViewById<ImageView>(R.id.imageViewAdded).setImageBitmap(value)
    }

    val camReq = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        bm = it
    }

    val gallReq = registerForActivityResult(ActivityResultContracts.GetContent()) {
        Glide.with(requireContext())
            .asBitmap()
            .load(it)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    bm = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}

            })

    }

    val perevalConstructor by lazy {
        PerevalConstructor(this)
    }

    lateinit var perevalName: EditText

    lateinit var lvlButtons: List<View>

    var checkedLvlButtonIndex: Int = -1

    lateinit var passStateButtons: List<View>

    var passStateIndex: Int = -1

    lateinit var datePicker: ViewGroup

    lateinit var datePickerText: TextView

    lateinit var todayButton: Button

    lateinit var coordinatePickerButton: View

    lateinit var latitudeText: TextView

    lateinit var longitudeText: TextView

    lateinit var heightText: TextView

    lateinit var galereyButton: View

    lateinit var photoButton: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pereval, container, false).apply {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findViews()
        setListeners()



    }

    fun onLatitudeChanged() {
        latitudeText.text = perevalConstructor.latitude.toString()
    }

    fun onLongitudeChanged() {
        longitudeText.text = perevalConstructor.longitude.toString()
    }

    fun onHeightChanged() {
        heightText.text = perevalConstructor.height.toString()
    }

    fun findViews() {
        perevalName = requireView().findViewById(R.id.perevalName)

        lvlButtons =
            listOf(
                requireView().findViewById(R.id.lvl_0),
                requireView().findViewById(R.id.lvl_1A),
                requireView().findViewById(R.id.lvl_1B),
                requireView().findViewById(R.id.lvl_2A),
                requireView().findViewById(R.id.lvl_2B),
                requireView().findViewById(R.id.lvl_3A),
                requireView().findViewById(R.id.lvl_3B)
            )

        passStateButtons =
            listOf(
                requireView().findViewById(R.id.weather),
                requireView().findViewById(R.id.notSure),
                requireView().findViewById(R.id.notPass)
            )

        datePicker = requireView().findViewById(R.id.datePicker)

        datePickerText = datePicker.findViewById(R.id.datePickerText)

        todayButton = requireView().findViewById(R.id.todayButton)

        coordinatePickerButton = requireView().findViewById(R.id.coordinatePickerButton)

        latitudeText = coordinatePickerButton.findViewById(R.id.latitudeText)

        longitudeText = coordinatePickerButton.findViewById(R.id.longitudeText)

        heightText = requireView().findViewById<View>(R.id.heightButton).findViewById(R.id.heightText)

        galereyButton = requireView().findViewById(R.id.galereyButton)

        photoButton = requireView().findViewById(R.id.photoButton)
    }

    fun setListeners() {
        perevalName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                perevalConstructor.perevalName = p0?.toString()
                if (p0.isNullOrEmpty()) Toast.makeText(
                    requireContext(),
                    "${perevalConstructor.perevalName}",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        lvlButtons.forEachIndexed { index, view ->
            view.setOnClickListener {
                if (checkedLvlButtonIndex != index) {
                    view.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.add_pereval_button_pressed
                    )
                    checkedLvlButtonIndex = index
                    perevalConstructor.level = index
                    lvlButtons.forEachIndexed { index, view ->
                        if (checkedLvlButtonIndex != index) {
                            view.background = AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.add_pereval_button_checked
                            )
                        }
                    }
                }
            }
        }

        passStateButtons.forEachIndexed { index, view ->
            view.setOnClickListener {
                if (passStateIndex != index) {
                    view.background = AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.add_pereval_button_pressed
                    )
                    passStateIndex = index
                    perevalConstructor.passState = index
                    passStateButtons.forEachIndexed { index, view ->
                        if (passStateIndex != index) {
                            view.background = AppCompatResources.getDrawable(
                                requireContext(),
                                R.drawable.add_pereval_button_checked
                            )
                        }
                    }
                }
            }
        }

        datePicker.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext())
            datePicker.setOnDateSetListener { _, year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, day)
                val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                datePickerText.text = dateFormat.format(calendar.time)
                perevalConstructor.date = dateFormat.format(calendar.time)
            }
            datePicker.show()
        }

        todayButton.apply {
            val currentDate = Date()
            val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            val dateText: String = dateFormat.format(currentDate)
            this.text = "${getString(R.string.today)}\n$dateText"

            setOnClickListener {
                datePickerText.text = dateText
                perevalConstructor.date = dateText
            }
        }

        coordinatePickerButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragmentRoot, CoordinatePickerFragment(perevalConstructor))
                .addToBackStack(null)
                .commit()
        }

        galereyButton.setOnClickListener {
            gallReq.launch("image/*")
        }

        photoButton.setOnClickListener {
            camReq.launch(null)
           }
        }
    }
