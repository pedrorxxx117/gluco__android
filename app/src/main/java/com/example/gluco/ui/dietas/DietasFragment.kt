package com.example.gluco.ui.dietas

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gluco.R

class DietasFragment : Fragment() {

    companion object {
        fun newInstance() = DietasFragment()
    }

    private lateinit var viewModel: DietasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dietas_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DietasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
