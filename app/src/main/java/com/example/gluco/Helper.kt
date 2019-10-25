package com.example.gluco

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth

fun Context.login(){
    val intent =
        Intent(this, BarraNavegacion::class.java).apply {
            flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    startActivity(intent)
}

/*un Context.logout(){
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
}*/