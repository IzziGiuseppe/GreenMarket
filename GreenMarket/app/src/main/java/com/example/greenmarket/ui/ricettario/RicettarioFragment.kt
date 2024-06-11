package com.example.greenmarket.ui.ricettario

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.R
import com.example.greenmarket.databinding.FragmentRicettarioBinding
import com.example.greenmarket.ui.ricerca.RicercaListAdapter
import com.example.greenmarket.ui.ricettario.dettaglio_ricette.DettaglioRicettaActivity

class RicettarioFragment : Fragment() {

    private var _binding: FragmentRicettarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val ricettarioViewModel = ViewModelProvider(this).get(RicettarioViewModel::class.java)

        _binding = FragmentRicettarioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = RicettarioListAdapter { currentRicetta ->
            currentRicetta.nome?.let { ricettarioViewModel.readRicettaDettagliata(it) }
        }

        val recyclerView = binding.rv
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /*val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)*/

        //ViewModel
        ricettarioViewModel.readAllRicette()
        ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
            ricetta -> adapter.setData(ricetta)
        })

        binding.resetBt.setOnClickListener {
            binding.ricercaRicettaEditText.setText("")
            ricettarioViewModel.readAllRicette()
            /* NON SO SE SERVE
            ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
                    ricetta -> adapter.setData(ricetta)
            })
            */
        }

        binding.ricercaRicettaEditText.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                val drawableEnd = binding.ricercaRicettaEditText.compoundDrawables[2]

                if (drawableEnd != null) {
                    val bounds = drawableEnd.bounds
                    val x = motionEvent.rawX.toInt()

                    if (x >= (binding.ricercaRicettaEditText.right - bounds.width())) {
                        val ricettaInput = binding.ricercaRicettaEditText.text.toString()
                        ricettarioViewModel.ricetteByProdotto(ricettaInput)
                        ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
                                ricetta -> adapter.setData(ricetta)
                        })
                        return@setOnTouchListener true
                    }
                }
            }
            false
        }

        ricettarioViewModel.ricetta.observe(viewLifecycleOwner) { ricetta ->
            ricetta?.let {
                it.nome?.let { it1 ->
                    it.descrizione?.let { it2 ->
                        it.foto?.let { it3 ->
                            it.ingredienti?.let { it4 ->
                                startRicetta(
                                    it1,
                                    it2, it3, it4
                                )
                            }
                        }
                    }
                }
            }
        }

        return root
    }

    fun startRicetta(nome: String, descrizione: String, foto: String, ingredienti: List<String>) {
        val intent = Intent(requireContext(), DettaglioRicettaActivity::class.java)
        intent.putExtra("nome_ricetta", nome)
        intent.putExtra("descrizione_ricetta", descrizione)
        intent.putExtra("foto_ricetta", foto)
        intent.putExtra("ingredienti_ricetta", ingredienti.toTypedArray())
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}