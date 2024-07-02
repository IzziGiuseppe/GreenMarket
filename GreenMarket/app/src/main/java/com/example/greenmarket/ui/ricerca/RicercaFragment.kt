package com.example.greenmarket.ui.ricerca

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.databinding.FragmentNoInternetBinding
import com.example.greenmarket.databinding.FragmentRicercaBinding
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity

class RicercaFragment : Fragment() {

    private var _binding: FragmentRicercaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    //Nel caso non ci sia connessione
    private var _bindingRiserva: FragmentNoInternetBinding? = null

    private val bindingRiserva get() = _bindingRiserva!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val iT = InternetTest()

        val ricercaViewModel = ViewModelProvider(this).get(RicercaViewModel::class.java)

        val root: View

        if (context?.let { iT.isInternetAvailable(it) } == true) {

            _binding = FragmentRicercaBinding.inflate(inflater, container, false)
            root = binding.root

            val adapter = RicercaListAdapter { currentProdotto ->
                if (context?.let { it1 -> iT.isInternetAvailable(it1) } == true) {
                    currentProdotto.nome?.let { ricercaViewModel.readProdottoDettagliato(it) }
                } else{
                    iT.toast(requireContext())
                }
            }

            val recyclerView = binding.rvRicercaProd
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())

            ricercaViewModel.readAllProdotti()
            ricercaViewModel.listaProdotti.observe(viewLifecycleOwner, Observer { prodotti ->
                adapter.setData(prodotti)
            })

            binding.resetProd.setOnClickListener {
                if (context?.let { it1 -> iT.isInternetAvailable(it1) } == true) {
                    binding.ricercaProdottoEditText.setText("")
                    ricercaViewModel.readAllProdotti()
                } else {
                    iT.toast(requireContext())
                }
            }

            binding.ricercaProdottoEditText.setOnTouchListener { _, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_UP) {
                    if (context?.let { it1 -> iT.isInternetAvailable(it1) } == true) {
                        val drawableEnd = binding.ricercaProdottoEditText.compoundDrawables[2]
                        drawableEnd?.let {
                            val bounds = it.bounds
                            val x = motionEvent.rawX.toInt()
                            if (x >= (binding.ricercaProdottoEditText.right - bounds.width())) {
                                val ricettaInput = binding.ricercaProdottoEditText.text.toString()
                                ricercaViewModel.prodottiByNome(ricettaInput)
                                return@setOnTouchListener true
                            }
                        }
                    } else {
                        iT.toast(requireContext())
                    }
                }
                false
            }

            ricercaViewModel.prodotto.observe(viewLifecycleOwner) { prodotto ->
                prodotto?.let {
                    it.nome?.let { it1 ->
                        it.descrizione?.let { it2 ->
                            it.prezzo?.let { it3 ->
                                it.foto?.let { it4 ->
                                    startProdotto(
                                        it1,
                                        it2, it3, it4
                                    )
                                    ricercaViewModel.resetProdotto()
                                }
                            }
                        }
                    }
                }
            }

        }else {
            _bindingRiserva = FragmentNoInternetBinding.inflate(inflater, container, false)
            iT.toast(requireContext())
            root = bindingRiserva.root

            bindingRiserva.noInternetText.visibility = View.VISIBLE
            bindingRiserva.noInternetImage.visibility = View.VISIBLE
        }

        return root
    }

    fun startProdotto(nome: String, descrizione: String, prezzo: Float, foto: String) {
        val intent = Intent(requireContext(), DettaglioProdottoActivity::class.java)
        /*MOMENTANEAMENTE COMMENTATO
        intent.putExtra("nome_prezzo_prodotto", "$nome \n$$prezzo")
         */
        intent.putExtra("nome_prodotto", nome)
        intent.putExtra("prezzo_prodotto", prezzo)
        intent.putExtra("descrizione_prodotto", descrizione)
        intent.putExtra("foto_prodotto", foto)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}