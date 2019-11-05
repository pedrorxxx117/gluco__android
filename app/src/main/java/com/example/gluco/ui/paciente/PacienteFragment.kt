package com.example.gluco.ui.paciente

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gluco.R

class PacienteFragment : Fragment() {

    companion object {
        fun newInstance() = PacienteFragment()
    }

    private lateinit var viewModel: PacienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.paciente_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PacienteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
