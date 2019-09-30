package com.example.gluco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            val RegistroUsuario = findViewById<Button>(R.id.btnresgistrarce)
            RegistroUsuario.setOnClickListener(View.OnClickListener {
                val intent = Intent(this,CompletarRegistro::class.java)
                startActivity(intent)
            })
            val IniciarSeccion = findViewById<Button>(R.id.IniciarSession)
            IniciarSeccion.setOnClickListener(View.OnClickListener {
                val intent = Intent(this, BarraNavegacion::class.java)
                startActivity(intent)
            })

        }
    }

