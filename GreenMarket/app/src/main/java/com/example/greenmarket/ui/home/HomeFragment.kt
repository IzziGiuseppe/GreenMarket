package com.example.greenmarket.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.greenmarket.databinding.FragmentHomeBinding
import com.example.greenmarket.db.GMDatabase
import com.example.greenmarket.db.model.Prodotto

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        /*homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        homeViewModel.readAllProdotti()
        homeViewModel.listaProdotti.observe(viewLifecycleOwner) {
            textView.text = it.size.toString() + ", " + it[it.size-1].nome + ", " + it[it.size-2].nome +
                    ", " + it[it.size-3].nome + ", " + it[it.size-4].nome + ", " + it[it.size-5].nome +
                    ", " + it[it.size-6].nome + ", " + it[it.size-7].nome + ", " + it[it.size-8].nome
        }

        //homeViewModel.deleteAllProdotti()

        /*val studentListObserver = Observer<Array<Prodotto>> {
            /*for(prod in it)
                Log.d("MainActivity","${prod.nome} ${prod.descrizione} ${prod.prezzo}")*/
            val ultimo = it.size - 1
            textView.text = it[ultimo].nome
        }
        homeViewModel.listaProdotti.observe(viewLifecycleOwner, studentListObserver)*/

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}