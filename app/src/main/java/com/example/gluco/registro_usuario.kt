package com.example.gluco

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_registro_usuario.*
import java.util.*

class registro_usuario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)



        val items = arrayOf("Hombre", "Mujer")
        spinnerGenero.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)

        val tiposdeabetes = arrayOf("diabetes Tipo 1", "diabetes Tipo 2")
        spinnerdiabetes.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,tiposdeabetes)


    }


       /* @SuppressLint("SetTextI18n")
        fun funDate(view: View) {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val dpd = DatePickerDialog(
                this,
                android.R.style.Theme_Holo_Dialog,
                DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
                    dateTv.text = "$dayOfMonth $monthOfYear $year"
                },
                year,
                month,
                day
            )

            //show datepicker
            dpd.show()
        }*/
       @RequiresApi(Build.VERSION_CODES.N)
       fun clickDataPicker(view: View) {
           val c = Calendar.getInstance()
           val year = c.get(Calendar.YEAR)
           val month = c.get(Calendar.MONTH)
           val day = c.get(Calendar.DAY_OF_MONTH)

           val dpd = DatePickerDialog(this,android.R.style.Theme_Holo_Dialog, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
               dateTv.text = "$dayOfMonth-${monthOfYear+1}-$year"

           }, year, month, day)
           dpd.show()
       }


}
