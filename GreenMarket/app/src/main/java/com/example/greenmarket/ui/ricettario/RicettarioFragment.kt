package com.example.greenmarket.ui.ricettario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentRicettarioBinding

class RicettarioFragment : Fragment() {

    private var _binding: FragmentRicettarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val ricettarioViewModel =
            ViewModelProvider(this).get(RicettarioViewModel::class.java)

        _binding = FragmentRicettarioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Recycler
        val adapter = RicettarioListAdapter()
        val recyclerView = binding.rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        ricettarioViewModel.readAllRicette()
        ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
            ricetta -> adapter.setData(ricetta)
        })

        binding.aggProdBt.setOnClickListener {
            val ricettaInput = binding.ricercaProdEditText.text.toString()
            ricettarioViewModel.ricetteByProdotto(ricettaInput)
            ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
                ricetta -> adapter.setData(ricetta)
            })
        }

        //ricettarioViewModel.deleteAllRicette()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}