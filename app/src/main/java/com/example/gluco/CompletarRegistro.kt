package com.example.gluco

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
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
  //  var timeFormat = SimpleDateFormat("hh:mm a", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completar_registro)

         val db = FirebaseFirestore.getInstance()


         var idNombre = intent.getStringExtra("idnombre")
         var iduser = idNombre

        val hora_entrada = findViewById<Button>(R.id.hora_entrada)
        val txthora_entrada = findViewById<TextView>(R.id.horaentrada)

        hora_entrada.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                txthora_entrada.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        val hora_salida = findViewById<Button>(R.id.hora_salida)
        val txthora_salida = findViewById<TextView>(R.id.horaentrada2)

        hora_salida.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                txthora_salida.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

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
            val hora1 = txthora_entrada.text.toString().trim()
            val hora2 = txthora_salida.text.toString().trim()
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
                "direccion" to direccion,
                "horaEntrada" to hora1,
                "horaSalida" to  hora2
            )
            val tipo = hashMapOf(
                "tipo" to "doctor"
            )

            db.collection("doc").document(idNombre).set(docinfo)
                .addOnSuccessListener {
                    //Log.d(TAG,"Se añadio correctamente!")
                    //agregar tipo de usuario doctor
                    db.collection("user").document(idNombre).set(tipo)
                        .addOnSuccessListener {
                            Log.d(TAG,"Se añadio correctamente!")
                        }
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }
                .addOnFailureListener {e ->
                    Log.w(TAG, "Algo salio mal.")
                    Toast.makeText(this,"Algo salio mal",Toast.LENGTH_SHORT).show()
                }




        }
    }
}
