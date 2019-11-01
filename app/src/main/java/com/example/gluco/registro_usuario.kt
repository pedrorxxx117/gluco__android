package com.example.gluco

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.*
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro_en_app.*
import kotlinx.android.synthetic.main.activity_registro_usuario.*
import java.lang.IllegalStateException
import java.util.*

class registro_usuario : AppCompatActivity() {
    private lateinit var mAuth1 : FirebaseAuth
    private lateinit var mAuth2 : FirebaseAuth
    private val TAG = "RegistroUsuario"


    var Genero = ""
    var Diabetes = ""
    var idDoc = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)

        val db = FirebaseFirestore.getInstance()

        val items = arrayOf("Hombre", "Mujer")
        spinnerGenero.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)

        val tiposdeabetes = arrayOf("diabetes Tipo 1", "diabetes Tipo 2")
        spinnerdiabetes.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,tiposdeabetes)

        btnRegistroPaciente.setOnClickListener {
            val nombre = edit_nombrePaciente.text.toString().trim()
            val correo = edit_correoPaciente.text.toString().trim()
            val contraseña = edit_contraseñaPaciente.text.toString().trim()
            val cfcontraseña = edit_contraseñaPciente2.text.toString().trim()
            val fehca = dateTv.text.toString().trim()

            if (nombre.isEmpty()){
                edit_nombrePaciente.error = "Ingresa tu nombre."
                edit_nombrePaciente.requestFocus()
                return@setOnClickListener
            }
            if (correo.isEmpty()){
                edit_correoPaciente.error = "Ingresa tu correo."
                edit_correoPaciente.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                edit_correoPaciente.error = "Correo invalido"
                edit_correoPaciente.requestFocus()
                return@setOnClickListener
            }
            if (contraseña.isEmpty() ||  contraseña.length <= 6) {
                edit_correoPaciente.error = "Revise su contraseña."
                edit_correoPaciente.requestFocus()
                return@setOnClickListener

            }
            if (cfcontraseña.isEmpty() || cfcontraseña.length <= 6) {
                edit_contraseñaPciente2.error = "Revise su contraseña."
                edit_contraseñaPciente2.requestFocus()
                return@setOnClickListener
            }

            if(cfcontraseña != contraseña){
                editText_cfcontraseña.error = "No coincide la contraseña."
                editText_cfcontraseña.requestFocus()
                return@setOnClickListener
            }

            val user = FirebaseAuth.getInstance().currentUser
            if (user != null ){
                idDoc = user.email.toString()
            }

            val pacienteinfo = hashMapOf(
                "diabetes" to Diabetes,
                "doctor" to idDoc,
                "genero" to Genero,
                "email" to correo,
                "nacimineto" to fehca,
                "nombre" to nombre

            )
            db.collection("paciente").document(correo).set(pacienteinfo)
                .addOnSuccessListener {
                    Log.d(TAG, "Se añadio correctamente!")
                    val intent = Intent(this, MainActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                }
                .addOnFailureListener {e ->
                    Log.w(TAG, "Algo salio mal.")
                    Toast.makeText(this,"Algo salio mal",Toast.LENGTH_SHORT).show()
                }

            registroPaciente(correo, contraseña)



        }

        spinnerGenero.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long)  {
                Genero = parent.getItemAtPosition(pos).toString().trim()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        spinnerdiabetes.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, pos: Int, id: Long) {
                Diabetes = parent.getItemAtPosition(pos).toString().trim()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

        mAuth1 = FirebaseAuth.getInstance()

         var firebaseOptions:FirebaseOptions = FirebaseOptions.Builder()
            .setDatabaseUrl("https://gluco-87a10.firebaseio.com")
            .setApiKey("AIzaSyBxjR_-OlC1dJA3WwqfwzNs2mbp2wwpDUY")
            .setApplicationId("gluco-87a10").build()

        try { var myApp = FirebaseApp.initializeApp(getApplicationContext(), firebaseOptions, "gluco")
            mAuth2 = FirebaseAuth.getInstance(myApp)
        }catch (e: IllegalStateException){
            mAuth2 = FirebaseAuth.getInstance(FirebaseApp.getInstance("gluco"))
        }
    }

    private fun registroPaciente(correo: String, contraseña: String) {
        mAuth2.createUserWithEmailAndPassword(correo, contraseña)
            .addOnCompleteListener(this, object: OnCompleteListener<AuthResult>{
               override fun onComplete(@NonNull task:Task<AuthResult>) {
                    if (!task.isSuccessful()){
                        val ex = task.getException().toString()
                        Toast.makeText(this@registro_usuario, "Registro Fallido" + ex,
                            Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this@registro_usuario, "Registro Exitoso",
                            Toast.LENGTH_LONG).show()
                        mAuth2.signOut()
                    }
                }

            })

    }


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





