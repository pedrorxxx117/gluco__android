package com.example.gluco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gluco.ui.dietas.DietasFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Dietas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dietas_activity)

        var mAddBtn: FloatingActionButton
        mAddBtn = findViewById(R.id.addDieta)

        mAddBtn.setOnClickListener {
            val intent = Intent(this, AgregarDieta::class.java)
            startActivity(intent)
        }


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DietasFragment.newInstance())
                .commitNow()
        }
    }
}
