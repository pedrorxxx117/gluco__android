package com.example.gluco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro_en_app.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private  val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
            //al IniciarSeccion = findViewById<Button>(R.id.IniciarSession)

            IniciarSession.setOnClickListener{
                val correo = txt_correo_login.text.toString().trim()
                val contraseña = txt_contraseña_login.text.toString().trim()

                if (correo.isEmpty()){
                    txt_correo_login.error = "Ingrese un correo."
                    txt_correo_login.requestFocus()
                    return@setOnClickListener
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                    txt_correo_login.error = "Correo invalido"
                    txt_correo_login.requestFocus()
                    return@setOnClickListener
                }
                if (contraseña.isEmpty() ||  contraseña.length <= 6) {
                    txt_contraseña_login.error = "Revise su contraseña."
                    txt_contraseña_login.requestFocus()
                    return@setOnClickListener
                }

                loginUser(correo, contraseña)

                /*val intent = Intent(this, BarraNavegacion::class.java)
                startActivity(intent)*/
            }

            val RegistroUsuario = findViewById<Button>(R.id.btnresgistrarce)
            RegistroUsuario.setOnClickListener{
                val intent = Intent(this, Registro_en_app::class.java)
                startActivity(intent)
            }
        }

    private fun loginUser(correo: String, contraseña: String) {
        progressbar.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(correo, contraseña)
            .addOnCompleteListener(this) { task ->
                progressbar.visibility = View.GONE
                if (task.isSuccessful){
                    login()
                }else{
                    Log.w(TAG,"signUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Algo salio mal intentalo de nuevo.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    override fun onStart(){
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }
}

