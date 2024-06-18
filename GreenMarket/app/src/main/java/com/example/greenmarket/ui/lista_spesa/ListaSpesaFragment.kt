package com.example.greenmarket.ui.lista_spesa

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentListaSpesaBinding
import com.example.greenmarket.ui.lista_spesa.conferma_ordine.ConfermaOrdineActivity
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaSpesaFragment : Fragment() {

    private var _binding: FragmentListaSpesaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var listaSpesaViewModel: ListaSpesaViewModel

    @OptIn(UnstableApi::class)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        listaSpesaViewModel = ViewModelProvider(this).get(ListaSpesaViewModel::class.java)

        _binding = FragmentListaSpesaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ListaSpesaListAdapter(
            itemClickListener = {
                item -> item.nome.let { listaSpesaViewModel.readProdottoDettagliato(it) }
                listaSpesaViewModel.setQuantita(item.quantita) },
            imageClickListener = {
                item -> item.nome.let { listaSpesaViewModel.deleteProdByNome(it) }
            }
        )

        val recyclerView = binding.rvListaSpesa
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listaSpesaViewModel.readListaSpesa()
        listaSpesaViewModel.lista_spesa.observe(viewLifecycleOwner, Observer {
                prodListaSpesa -> adapter.setData(listaProdotti(prodListaSpesa.prodotti))
        })

        listaSpesaViewModel.lista_prodotti.observe(viewLifecycleOwner, Observer {
            prodListaSpesa -> adapter.setData(prodListaSpesa)
            listaSpesaViewModel.readPrezzoTotale()
        })

        listaSpesaViewModel.prodotto.observe(viewLifecycleOwner) {
            prodotto ->
            prodotto?.let {
                it.nome?.let { it1 ->
                    it.descrizione?.let { it2 ->
                        it.prezzo?.let { it3 ->
                            it.foto?.let { it4 ->
                                listaSpesaViewModel.quantita.value?.let { it5 ->
                                    startProdotto(
                                        it1,
                                        it2, it3, it4, it5
                                    )
                                }
                                listaSpesaViewModel.resetProdotto()
                            }
                        }
                    }
                }
            }
        }

        val deleteBT: FloatingActionButton = binding.deleteAll
        deleteBT.setOnClickListener {
            listaSpesaViewModel.deleteListaSpesa()
            /*listaSpesaViewModel.listaSpesa.observe(viewLifecycleOwner, Observer {
                    prodListaSpesa -> adapter.setData(listaProdotti(prodListaSpesa.prodotti))
            })*/
        }

        val confermaBT: Button = binding.confermaOrdine
        confermaBT.setOnClickListener {
            startConfermaOrdine(listaSpesaViewModel.prezzo_totale_view.value)
        }

        val totaleTV: TextView = binding.prezzoTotale
        //listaSpesaViewModel.readPrezzoTotale()
        listaSpesaViewModel.prezzo_totale_view.observe(viewLifecycleOwner) {
            totaleTV.text = it
        }

        return root
    }

    private fun startProdotto(nome: String, descrizione: String, prezzo: Float, foto: String, quantita: Float) {
        val intent = Intent(requireContext(), DettaglioProdottoActivity::class.java)
        intent.putExtra("to_modify", true)
        intent.putExtra("nome_prodotto", nome)
        intent.putExtra("prezzo_prodotto", prezzo)
        intent.putExtra("descrizione_prodotto", descrizione)
        intent.putExtra("foto_prodotto", foto)
        intent.putExtra("quantita_prodotto", quantita)
        startActivity(intent)
    }

    private fun startConfermaOrdine(prezzoTotale: String?) {
        if (prezzoTotale == "Totale: €0.0") {
            Toast.makeText(context, "Lista della spesa vuota!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            val intent = Intent(requireContext(), ConfermaOrdineActivity::class.java)
            if (prezzoTotale != null) {
                intent.putExtra("prezzo_totale", Regex("\\d+(\\.\\d+)?").find(prezzoTotale)?.value)
            }
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Rileggi la lista della spesa quando il frammento torna visibile
        listaSpesaViewModel.readListaSpesa()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listaProdotti(map: Map<String?, List<Float>?>) : List<ProdottoInListaModel>{

        val listaProdotti = mutableListOf<ProdottoInListaModel>()
        map.forEach { (key, value) ->
            val prodotto = key?.let { ProdottoInListaModel(it, value?.get(0) ?: 0.5f, value?.get(1) ?: 0f, value?.get(2) ?: 0f) }
            if (prodotto != null) {
                listaProdotti.add(prodotto)
            }
        }
        return listaProdotti
    }
}