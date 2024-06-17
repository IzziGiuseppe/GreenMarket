package com.example.greenmarket.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.greenmarket.R
import com.example.greenmarket.databinding.FragmentHomeBinding
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.ui.altro.statistiche.StatsActivity
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiActivity
import com.example.greenmarket.ui.login.UserProfileActivity
import com.example.greenmarket.ui.ricerca.RicercaViewModel
import com.example.greenmarket.ui.ricerca.dettaglio_prodotti.DettaglioProdottoActivity
import com.example.greenmarket.ui.ricettario.RicettarioViewModel
import com.example.greenmarket.ui.ricettario.dettaglio_ricette.DettaglioRicettaActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val prodottiRV = listOf(
        Prodotto(nome = "Mele", descrizione = "Alta qualità e freschezza garantita.", prezzo = 1.99f, foto = "", "kg"),
        Prodotto(nome = "Pere", descrizione = "Gustose e nutrienti.", prezzo = 2.49f, foto = "",   "kg"),
        Prodotto(nome = "Noci", descrizione = "Frutta secca, fonte di grassi gustosa e adatta anche nelle insalate.", prezzo = 5.49f, foto = "",   "kg")
    )

    private lateinit var navController: NavController

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val ricettarioViewModel =
            ViewModelProvider(this).get(RicettarioViewModel::class.java)

        val prodottoViewModel =
            ViewModelProvider(this).get(RicercaViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        val adapter = HomeProdottiListAdapter {
            currentProdotto ->
            currentProdotto.nome.let {
                if (it != null) {
                    prodottoViewModel.readProdottoDettagliato(it)
                }
            }
        }

        val adapterRicette = HomeRicetteListAdapter {
            currentRicetta ->
            currentRicetta.nome.let {
                if (it != null) {
                    ricettarioViewModel.readRicettaDettagliata(it)
                }
            }
        }

        val rv = binding.recyclerViewProdHomeeee
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val rvRicetteHome = binding.rvRicetteHome
        rvRicetteHome.adapter = adapterRicette
        rvRicetteHome.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.readRicetteRandom()
        homeViewModel.listaRicette.observe(viewLifecycleOwner) {
            adapterRicette.setData(it)
        }

        homeViewModel.readProdottiRandom()
        homeViewModel.listaProdotti.observe(viewLifecycleOwner) {
            adapter.setData(it)
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

        prodottoViewModel.prodotto.observe(viewLifecycleOwner) { prodotto ->
            prodotto?.let {
                it.nome?.let { it1 ->
                    it.descrizione?.let { it2 ->
                        it.prezzo?.let { it3 ->
                            it.foto?.let { it4 ->
                                startProdotto(
                                    it1,
                                    it2, it3, it4
                                )
                                prodottoViewModel.resetProdotto()
                            }
                        }
                    }
                }
            }
        }

        /*val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        val stato: TextView = binding.apertoChiuso
        homeViewModel.updateStatusRegularly(lifecycleScope)
        homeViewModel.status.observe(viewLifecycleOwner) {
            stato.text = it
        }

        val orario: TextView = binding.orarioChiusura
        homeViewModel.updateStatusRegularly(lifecycleScope)
        homeViewModel.orari.observe(viewLifecycleOwner) {
            orario.text = it
        }

        val benvenuto: TextView = binding.textHome
        val immagineProfilo: ImageView = binding.iconaProfiloUtente
        homeViewModel.readNome(this, immagineProfilo)
        homeViewModel.text.observe(viewLifecycleOwner) {
            benvenuto.text = it
        }

        binding.iconaProfiloUtente.setOnClickListener {
            val intent = Intent(requireContext(), UserProfileActivity::class.java)
            startActivity(intent)
        }

        binding.tessPtBt.setOnClickListener {
            val intent = Intent(requireContext(), TesseraPuntiActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun startRicetta(nome: String, descrizione: String, foto: String, ingredienti: List<String>) {
        val intent = Intent(requireContext(), DettaglioRicettaActivity::class.java)
        intent.putExtra("nome_ricetta", nome)
        intent.putExtra("descrizione_ricetta", descrizione)
        intent.putExtra("foto_ricetta", foto)
        intent.putExtra("ingredienti_ricetta", ingredienti.toTypedArray())
        startActivity(intent)
    }

    fun startProdotto(nome: String, descrizione: String, prezzo: Float, foto: String) {
        val intent = Intent(requireContext(), DettaglioProdottoActivity::class.java)
        intent.putExtra("nome_prodotto", nome)
        intent.putExtra("prezzo_prodotto", prezzo)
        intent.putExtra("descrizione_prodotto", descrizione)
        intent.putExtra("foto_prodotto", foto)
        startActivity(intent)
    }
}