package com.example.greenmarket.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import com.bumptech.glide.Glide
import com.example.greenmarket.InternetTest
import com.example.greenmarket.NoInternetActivity
import com.example.greenmarket.R
import com.example.greenmarket.databinding.FragmentAltroBinding
import com.example.greenmarket.databinding.FragmentHomeBinding
import com.example.greenmarket.databinding.FragmentNoInternetBinding
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.ui.altro.statistiche.StatsActivity
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiActivity
import com.example.greenmarket.ui.lista_spesa.ListaSpesaViewModel
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

    private var _bindingRiserva: FragmentNoInternetBinding? = null

    private val bindingRiserva get() = _bindingRiserva!!

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var immagineProfilo: ImageView

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val iT = InternetTest()
        val intentNoInternet = Intent(requireContext(), NoInternetActivity::class.java)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        val ricettarioViewModel =
            ViewModelProvider(this).get(RicettarioViewModel::class.java)

        val prodottoViewModel =
            ViewModelProvider(this).get(RicercaViewModel::class.java)

        val root: View

        if (context?.let { iT.isInternetAvailable(it) } == true) {
            _binding = FragmentHomeBinding.inflate(inflater, container, false)
            root = binding.root

            val adapter = HomeProdottiListAdapter {
                    currentProdotto ->
                if (iT.isInternetAvailable(requireContext())) {
                    currentProdotto.nome.let {
                        if (it != null) {
                            prodottoViewModel.readProdottoDettagliato(it)
                        }
                    }
                } else {
                    iT.toast(requireContext())
                }
            }

            val adapterRicette = HomeRicetteListAdapter {
                    currentRicetta ->
                if (iT.isInternetAvailable(requireContext())) {
                    currentRicetta.nome.let {
                        if (it != null) {
                            ricettarioViewModel.readRicettaDettagliata(it)
                        }
                    }
                } else {
                    iT.toast(requireContext())
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
            immagineProfilo = binding.iconaProfiloUtente
            homeViewModel.readNome(this, immagineProfilo)
            homeViewModel.text.observe(viewLifecycleOwner) {
                benvenuto.text = it
            }
            homeViewModel.foto.observe(viewLifecycleOwner) {
                Glide.with(this)
                    .load(it)
                    .into(immagineProfilo)
            }

            binding.iconaProfiloUtente.setOnClickListener {
                val intent = Intent(requireContext(), UserProfileActivity::class.java)
                if (context?.let { it1 -> iT.isInternetAvailable(it1) } == true) {
                    startActivity(intent)
                } else {
                    //startActivity(intentNoInternet)
                    iT.toast(requireContext())
                }
            }

            binding.tessPtBt.setOnClickListener {
                val intent = Intent(requireContext(), TesseraPuntiActivity::class.java)
                if (context?.let { it1 -> iT.isInternetAvailable(it1) } == true) {
                    startActivity(intent)
                } else {
                    iT.toast(requireContext())
                    startActivity(intentNoInternet)
                }
            }
        }
        else {
            _bindingRiserva = FragmentNoInternetBinding.inflate(inflater, container, false)
            iT.toast(requireContext())
            root = bindingRiserva.root

            bindingRiserva.noInternetText.visibility = View.VISIBLE
            bindingRiserva.noInternetImage.visibility = View.VISIBLE
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        homeViewModel.readNome(this, immagineProfilo)
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