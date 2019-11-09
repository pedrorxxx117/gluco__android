package com.example.gluco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_registro_en_app.*
import java.util.regex.Pattern


class Registro_en_app : AppCompatActivity() {
    private lateinit var mAuth : FirebaseAuth
    private  val TAG = ""


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_en_app)



       mAuth = FirebaseAuth.getInstance()


            btnRegistroNuevoUsuario.setOnClickListener {
                val nombre = editText_nombre.text.toString().trim()
                val correo = editText_correo.text.toString().trim()
                val contraseña = editText_contraseña.text.toString().trim()
                val cfcontraseña = editText_cfcontraseña.text.toString().trim()


                if (nombre.isEmpty()) {
                    editText_nombre.error = "Ingrese su Nombre."
                    editText_nombre.requestFocus()
                    return@setOnClickListener
                }

                if (correo.isEmpty()) {
                    editText_correo.error = "Ingrese un correo."
                    editText_correo.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    editText_correo.error = "Correo invalido"
                    editText_correo.requestFocus()
                    return@setOnClickListener
                }
                if (contraseña.isEmpty() ||  contraseña.length <= 6) {
                    editText_contraseña.error = "Revise su contraseña."
                    editText_contraseña.requestFocus()
                    return@setOnClickListener

                }
                if (cfcontraseña.isEmpty() || cfcontraseña.length <= 6) {
                    editText_cfcontraseña.error = "Revise su contraseña."
                    editText_cfcontraseña.requestFocus()
                    return@setOnClickListener
                }

                if(cfcontraseña != contraseña){
                    editText_cfcontraseña.error = "No coincide la contraseña."
                    editText_cfcontraseña.requestFocus()
                    return@setOnClickListener
                }

                registroUsuario(correo, contraseña)
            }
        }

    private fun registroUsuario(correo: String, contraseña: String) {
            mAuth.createUserWithEmailAndPassword(correo, contraseña)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Registro exitoso
                        val intent =
                            Intent(this@Registro_en_app, CompletarRegistro::class.java).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        val txtnombre = findViewById<EditText>(R.id.editText_nombre)
                        val nombreUsuario = txtnombre.text.toString()
                        intent.putExtra("idnombre", nombreUsuario)
                        startActivity(intent)

                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Algo salio mal intentalo de nuevo.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
    }
    /*override fun onStart(){
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }*/
}

