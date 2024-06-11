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
import com.example.greenmarket.databinding.FragmentRicercaBinding
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity

class RicercaFragment : Fragment() {

    private var _binding: FragmentRicercaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val ricercaViewModel = ViewModelProvider(this).get(RicercaViewModel::class.java)

        _binding = FragmentRicercaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = RicercaListAdapter { currentProdotto ->
            currentProdotto.nome?.let { ricercaViewModel.readProdottoDettagliato(it) }
        }

        val recyclerView = binding.rvRicercaProd
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        /*val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        val dividerDrawable = ColorDrawable(ContextCompat.getColor(requireContext(), R.color.black))
        dividerItemDecoration.setDrawable(dividerDrawable)
        recyclerView.addItemDecoration(dividerItemDecoration)*/

        ricercaViewModel.readAllProdotti()
        ricercaViewModel.listaProdotti.observe(viewLifecycleOwner, Observer { prodotti ->
            adapter.setData(prodotti)
        })

        binding.resetProd.setOnClickListener {
            binding.ricercaProdottoEditText.setText("")
            ricercaViewModel.readAllProdotti()
        }

        binding.ricercaProdottoEditText.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
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
                            }
                        }
                    }
                }
            }
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