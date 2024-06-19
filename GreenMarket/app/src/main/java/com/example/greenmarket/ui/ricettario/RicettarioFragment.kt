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
import com.example.greenmarket.InternetTest
import com.example.greenmarket.R
import com.example.greenmarket.databinding.FragmentNoInternetBinding
import com.example.greenmarket.databinding.FragmentRicettarioBinding
import com.example.greenmarket.ui.ricerca.RicercaListAdapter
import com.example.greenmarket.ui.ricettario.dettaglio_ricette.DettaglioRicettaActivity

class RicettarioFragment : Fragment() {

    private var _binding: FragmentRicettarioBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var _bindingRiserva: FragmentNoInternetBinding? = null

    private val bindingRiserva get() = _bindingRiserva!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val iT = InternetTest()

        val ricettarioViewModel = ViewModelProvider(this).get(RicettarioViewModel::class.java)

        val root: View

        if (iT.isInternetAvailable(requireContext())) {
            _binding = FragmentRicettarioBinding.inflate(inflater, container, false)
            root = binding.root

            val adapter = RicettarioListAdapter { currentRicetta ->
                if (iT.isInternetAvailable(requireContext())) {
                    currentRicetta.nome?.let { ricettarioViewModel.readRicettaDettagliata(it) }
                } else {
                    iT.toast(requireContext())
                }
            }

            val recyclerView = binding.rv
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            //ViewModel
            ricettarioViewModel.readAllRicette()
            ricettarioViewModel.listaRicette.observe(viewLifecycleOwner, Observer {
                    ricetta -> adapter.setData(ricetta)
            })

            binding.resetBt.setOnClickListener {
                if (iT.isInternetAvailable(requireContext())) {
                    binding.ricercaRicettaEditText.setText("")
                    ricettarioViewModel.readAllRicette()
                } else {
                    iT.toast(requireContext())
                }
            }

            binding.ricercaRicettaEditText.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    if (iT.isInternetAvailable(requireContext())) {
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
                    } else {
                        iT.toast(requireContext())
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
                                    ricettarioViewModel.resetRicetta()
                                }
                            }
                        }
                    }
                }
            }
        } else {
            _bindingRiserva = FragmentNoInternetBinding.inflate(inflater, container, false)
            iT.toast(requireContext())
            root = bindingRiserva.root

            bindingRiserva.noInternetText.visibility = View.VISIBLE
            bindingRiserva.noInternetImage.visibility = View.VISIBLE
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