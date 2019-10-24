package com.example.gluco

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TimePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_completar_registro.*
import kotlinx.android.synthetic.main.activity_registro_en_app.*
import java.text.SimpleDateFormat
import java.util.*

class CompletarRegistro : AppCompatActivity() {


    private val TAG = "CompletarRegistro"
    var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_registro)

         val db = FirebaseFirestore.getInstance()


         var idNombre = intent.getStringExtra("idnombre")
         var iduser = idNombre
        //var nombre = intent.getStringExtra("idnombre")
        //val txtnombre = findViewById<EditText>(R.id.editText_nombre)

       /* hora_entrada.setOnClickListener {
            val now = Calendar.getInstance()
            val timepicker = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val selectTime = Calendar.getInstance()
                    selectTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectTime.set(Calendar.MINUTE, minute)
                    hora_entrada.text = timeFormat.format(selectTime)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timepicker.show()
        }*/
        /*hora_salida.setOnClickListener {
            val now = Calendar.getInstance()
            val timepicker = TimePickerDialog(
                this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    val selectTime = Calendar.getInstance()
                    selectTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    selectTime.set(Calendar.MINUTE, minute)
                    horaentrada2.text = timeFormat.format(selectTime)
                },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false
            )
            timepicker.show()
        }*/

        btnTerminarRegistro.setOnClickListener {
            val direccion = editText_direccion.text.toString().trim()
            val  telefono = editText_telefono.text.toString().trim()
            /*val horaEntrada = hora_entrada.text.toString().trim()
            val  horaSalida = hora_salida.text.toString().trim()*/
           /* val hora1 = editText_hora1.text.toString().trim()
            val  hora2 = editText_hora2.text.toString().trim()*/
            //var nombre = txtnombre.text.toString()

            if (direccion.isEmpty()){
                editText_direccion.error = "Ingresa la dirección."
                editText_direccion.requestFocus()
                return@setOnClickListener
            }
            if (telefono.isEmpty()){
                editText_telefono.error = "Ingresa tu telefonó."
                editText_telefono.requestFocus()
                return@setOnClickListener
            }
            /*if (hora1.isEmpty() || hora2.isEmpty()){
                editText_hora1.error
                editText_hora2.error
                editText_hora1.requestFocus()
                editText_hora2.requestFocus()
            }*/

            val user = FirebaseAuth.getInstance().currentUser
            if ( user != null){
                 idNombre = user.email
                //nombre = user.displayName.toString()
            }
            //al nombre = user?.displayName.toString()


            val docinfo = hashMapOf(
                "telefono" to telefono,
                "nombre" to iduser,
                "direccion" to direccion
                /*"horaEntrada" to horaEntrada,
                "horaSalida" to  horaSalida*/
            )

            db.collection("doc").document(idNombre).set(docinfo)
                .addOnSuccessListener {
                    Log.d(TAG,"Se añadio correctamente!")
                }
                .addOnFailureListener {e ->
                    Log.w(TAG, "Algo salio mal.")
                }
        }
    }
}
