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


        //val prod1 = Prodotto("Zucchine", "Ciao", 10f, "", "kg")
        //val prod2 = Prodotto("Melanzane", "Ciao", 10f, "", "kg")
        //val prod3 = Prodotto("Carne", "Ciao", 10f, "", "kg")

        homeViewModel.deleteAllProdotti()

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val studentListObserver = Observer<Array<Prodotto>> {
            /*for(prod in it)
                Log.d("MainActivity","${prod.nome} ${prod.descrzione} ${prod.prezzo}")*/
            val ultimo = it.size - 1
            textView.text = it.toString()
        }
        homeViewModel.listaProdotti.observe(viewLifecycleOwner, studentListObserver)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}