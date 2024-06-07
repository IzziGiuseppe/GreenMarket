package com.example.greenmarket.ui.lista_spesa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentListaSpesaBinding
import com.example.greenmarket.ui.lista_spesa.conferma_ordine.ConfermaOrdineActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaSpesaFragment : Fragment() {

    private var _binding: FragmentListaSpesaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val listaSpesaViewModel =
            ViewModelProvider(this).get(ListaSpesaViewModel::class.java)

        _binding = FragmentListaSpesaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ListaSpesaListAdapter() {
            currentProdotto ->
            run {
                Toast.makeText(context, currentProdotto.prodotto + currentProdotto.quantita, Toast.LENGTH_SHORT).show()
            }
        }
        val recyclerView = binding.rvListaSpesa
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listaSpesaViewModel.readListaSpesa()
        listaSpesaViewModel.listaSpesa.observe(viewLifecycleOwner, Observer {
                prodListaSpesa -> adapter.setData(prodListaSpesa)
        })

        val deleteBT: FloatingActionButton = binding.deleteAll
        deleteBT.setOnClickListener {
            listaSpesaViewModel.deleteListaSpesa()
            listaSpesaViewModel.listaSpesa.observe(viewLifecycleOwner, Observer {
                    prodListaSpesa -> adapter.setData(prodListaSpesa)
            })
        }

        val confermaBT: Button = binding.confermaOrdine
        confermaBT.setOnClickListener {
            val intent = Intent(requireContext(), ConfermaOrdineActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}