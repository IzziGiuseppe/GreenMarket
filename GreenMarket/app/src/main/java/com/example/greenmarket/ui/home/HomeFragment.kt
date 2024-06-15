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
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.widget.ViewPager2
import com.example.greenmarket.R
import com.example.greenmarket.databinding.FragmentHomeBinding
import com.example.greenmarket.ui.altro.statistiche.StatsActivity
import com.example.greenmarket.ui.home.tessera_punti.TesseraPuntiActivity
import com.example.greenmarket.ui.login.UserProfileActivity

class HomeFragment : Fragment(), OnImageClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val imageList = listOf(R.drawable.mele, R.drawable.pere, R.drawable.banane, R.drawable.plus)

    private val prezzi = listOf(
        "€1.99",
        "€2.49",
        "€1.79",
        ""
    )

    private val nomi = listOf(
        "Mele",
        "Pere",
        "Banane",
        "Scopri di più"
    )

    private val nomiRicette = listOf(
        "Parmigiana",
        "Carbonara",
        "Amatriciana",
        "Scopri di più"
    )

    private lateinit var adapter: ImagePagerAdapter

    @RequiresApi(Build.VERSION_CODES.O)
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
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
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
        val immagineProfilo: ImageView = binding.iconaProfiloUtente
        homeViewModel.readNome(this, immagineProfilo)
        homeViewModel.text.observe(viewLifecycleOwner) {
            benvenuto.text = it
        }

        binding.iconaProfiloUtente.setOnClickListener {
            val intent = Intent(requireContext(), UserProfileActivity::class.java)
            startActivity(intent)
        }

        adapter = ImagePagerAdapter(imageList, this)
        binding.statsProd.adapter = adapter
        binding.statsProd.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.imagePrezzo.text = prezzi[position]
                binding.imageNome.text = nomi[position]
            }
        })

        binding.ricetteConsigliate.adapter = adapter
        binding.ricetteConsigliate.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.ricettaNome.text = nomiRicette[position]
            }
        })

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

    override fun onImageClick(position: Int) {
        if (position==3) {
            val intent = Intent(requireContext(), StatsActivity::class.java)
            startActivity(intent)
        }
        else
            Toast.makeText(requireContext(), "Image clicked: ${position+1}", Toast.LENGTH_SHORT).show()
    }
}