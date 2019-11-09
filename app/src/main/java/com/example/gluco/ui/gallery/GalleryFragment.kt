package com.example.gluco.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gluco.AgregarDieta
import com.example.gluco.Paciente
import com.example.gluco.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel

    var mAddBtn:FloatingActionButton? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        return root

            /*mAddBtn = view?.findViewById(R.id.adBtn)

            mAddBtn?.setOnClickListener {
                val  intent = Intent(getActivity(), Paciente::class.java)
                startActivity(intent)

            }*/
        }

    }


