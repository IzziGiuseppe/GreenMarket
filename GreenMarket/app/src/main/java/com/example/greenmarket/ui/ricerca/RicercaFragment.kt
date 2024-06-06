package com.example.greenmarket.ui.ricerca

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentRicercaBinding
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity

class RicercaFragment : Fragment() {

    private var _binding: FragmentRicercaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ricercaViewModel =
            ViewModelProvider(this).get(RicercaViewModel::class.java)

        _binding = FragmentRicercaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //recycler
        val adapter = RicercaListAdapter() {
            currentProdotto ->
            run {
                val prodotto = currentProdotto.nome
                var nome = ""
                var descrizione = ""
                var prezzo = 0f
                var foto = ""
                ricercaViewModel.readProdottoDettagliato(prodotto)
                ricercaViewModel.prodotto.observe(viewLifecycleOwner) {
                    nome = it.nome
                    descrizione = it.descrizione
                    prezzo = it.prezzo
                    foto = it.foto
                }
                startProdotto(nome, descrizione, prezzo, foto)
            }
        }
        val recyclerView = binding.rvRicercaProd
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //viewmodel
        ricercaViewModel.readAllProdotti()
        ricercaViewModel.listaProdotti.observe(viewLifecycleOwner, Observer {
            prodotto -> adapter.setData(prodotto)
        })

        binding.resetProd.setOnClickListener {
            binding.ricercaProdottoEditText.setText("")
            ricercaViewModel.readAllProdotti()
            ricercaViewModel.listaProdotti.observe(viewLifecycleOwner, Observer {
                prodotto -> adapter.setData(prodotto)
            })
        }

        binding.ricercaProdottoEditText.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val drawableEnd = binding.ricercaProdottoEditText.compoundDrawables[2]

                if (drawableEnd != null) {
                    val bounds = drawableEnd.bounds
                    val x = motionEvent.rawX.toInt()

                    if (x >= (binding.ricercaProdottoEditText.right - bounds.width())) {
                        val ricettaInput = binding.ricercaProdottoEditText.text.toString()
                        ricercaViewModel.prodottiByNome(ricettaInput)
                        ricercaViewModel.listaProdotti.observe(viewLifecycleOwner, Observer {
                                prodotto -> adapter.setData(prodotto)
                        })
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        return root
    }

    fun startProdotto(nome: String, descrizione: String, prezzo: Float, foto: String) {
        val intent = Intent(requireContext(), DettaglioProdottoActivity::class.java)
        intent.putExtra("nome_prezzo_prodotto", "$nome \n$$prezzo")
        intent.putExtra("descrizione_prodotto", descrizione)
        intent.putExtra("foto_prodotto", foto)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}