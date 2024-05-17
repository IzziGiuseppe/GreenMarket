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

        Log.d("HomeFragment", "Ciao")
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("HomeFragment", "Ciao")

        val prod1 = Prodotto("Zucchine", "Ciao", 10f, "", "kg")
        Log.d("HomeFragment", "Ciao3")
        val prod2 = Prodotto("Melanzane", "Ciao", 10f, "", "kg")
        Log.d("HomeFragment", "Ciao4")
        val prod3 = Prodotto("Carne", "Ciao", 10f, "", "kg")

        Log.d("HomeFragment", "Ciao")

        homeViewModel.insert(prod1, prod2, prod3)

        Log.d("HomeFragment", "Ciao5")

        val variabile = homeViewModel.readAllStudents()

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val studentListObserver = Observer<Array<Prodotto>> {
            /*for(prod in it)
                Log.d("MainActivity","${prod.nome} ${prod.descrzione} ${prod.prezzo}")*/
            textView.text = it[0].nome + ", " + it[1].nome + ", " + it[2].nome
        }
        homeViewModel.listaProdotti.observe(viewLifecycleOwner, studentListObserver)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}