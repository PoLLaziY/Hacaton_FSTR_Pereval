package com.example.hackathon_perevalfstr

import com.example.hackathon_perevalfstr.view.fragments.AddPerevalFragment
import com.google.gson.annotations.SerializedName

class PerevalConstructor(val fragment: AddPerevalFragment) {

    var perevalName: String? = null

    var level: Int = -1

    var passState: Int = -1

    var date: String? = null

    var latitude : Double = 0.0
    set(value) {
        if (field != value) {
            field = value
            fragment.onLatitudeChanged()
        }
    }
    var longitude : Double = 0.0
        set(value) {
            if (field != value) {
                field = value
                fragment.onLongitudeChanged()
            }
        }

    var height : Int = 0
        set(value) {
            if (field != value) {
                field = value
                fragment.onHeightChanged()
            }
        }
}