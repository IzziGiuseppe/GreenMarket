package com.example.greenmarket.ui.altro

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentAltroBinding
import com.example.greenmarket.ui.altro.chi_siamo.ChiSiamoActivity

class AltroFragment : Fragment() {

    private var _binding: FragmentAltroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val altroViewModel =
            ViewModelProvider(this).get(AltroViewModel::class.java)

        _binding = FragmentAltroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        altroViewModel.menuItems.observe(viewLifecycleOwner) { it ->
            //recycler
            val adapter = AltroListAdapter(it) {
                position -> sceltaListener(position)
            }
            val recyclerView = binding.rvAltro
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return root
    }

    fun sceltaListener(position: Int) {
        when (position) {
            0 -> profiloUtente()
            1 -> storico()
            2 -> chiSiamo()
            3 -> stats()
            4 -> invito()
            5 -> terminiCondizioni()
            6 -> donazione()
            else ->
                Toast.makeText(requireContext(), "No action", Toast.LENGTH_SHORT).show()
        }
    }

    fun profiloUtente() {
        Toast.makeText(requireContext(), "Profilo utente da implementare", Toast.LENGTH_LONG).show()
    }

    fun storico() {
        Toast.makeText(requireContext(), "Storico da implementare", Toast.LENGTH_LONG).show()
    }

    fun chiSiamo() {
        val intent = Intent(requireContext(), ChiSiamoActivity::class.java)
        startActivity(intent)
    }

    fun stats() {
        Toast.makeText(requireContext(), "Statistiche da implementare", Toast.LENGTH_LONG).show()
    }

    fun invito() {
        Toast.makeText(requireContext(), "Invito da implementare", Toast.LENGTH_LONG).show()
    }

    fun terminiCondizioni() {
        Toast.makeText(requireContext(), "Termini e condizioni da implementare", Toast.LENGTH_LONG).show()
    }

    fun donazione() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse("https://www.gofundme.com/it-it/"))
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}