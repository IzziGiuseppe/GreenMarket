package com.example.greenmarket.ui.altro

import android.content.ActivityNotFoundException
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
import com.example.greenmarket.ui.internet.InternetTest
import com.example.greenmarket.databinding.FragmentAltroBinding
import com.example.greenmarket.ui.altro.chi_siamo.ChiSiamoActivity
import com.example.greenmarket.ui.altro.donazioni.DonazioniActivity
import com.example.greenmarket.ui.altro.statistiche.StatsActivity
import com.example.greenmarket.ui.altro.storico.StoricoActivity
import com.example.greenmarket.ui.altro.termini_condizioni.TermCondActivity
import com.example.greenmarket.ui.login.profilo_utente.UserProfileActivity

class AltroFragment : Fragment() {

    private var _binding: FragmentAltroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val iT = InternetTest()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val altroViewModel =
            ViewModelProvider(this).get(AltroViewModel::class.java)

        _binding = FragmentAltroBinding.inflate(inflater, container, false)
        val root: View = binding.root

        altroViewModel.menuItems.observe(viewLifecycleOwner) {
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

    private fun sceltaListener(position: Int) {
        when (position) {
            0 -> profiloUtente()
            1 -> storico()
            2 -> chiSiamo()
            3 -> stats()
            4 -> invito()
            5 -> terminiCondizioni()
            6 -> assistenza()
            7 -> donazione()
            else ->
                Toast.makeText(requireContext(), "No action", Toast.LENGTH_SHORT).show()
        }
    }

    private fun assistenza() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:domenico.laporta.dlp@gmail.com")
        }

        try {
            this.startActivity(Intent.createChooser(emailIntent, "Scegli un'app per email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Non ci sono app di email installate.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun profiloUtente() {
        val intent = Intent(requireContext(), UserProfileActivity::class.java)
        if (iT.isInternetAvailable(requireContext())) {
            startActivity(intent)
        } else {
            iT.toast(requireContext())
        }
    }

    private fun storico() {
        val intent = Intent(requireContext(), StoricoActivity::class.java)
        if (iT.isInternetAvailable(requireContext())) {
            startActivity(intent)
        } else {
            iT.toast(requireContext())
        }
    }

    private fun chiSiamo() {
        val intent = Intent(requireContext(), ChiSiamoActivity::class.java)
        startActivity(intent)
    }

    private fun stats() {
        val intent = Intent(requireContext(), StatsActivity::class.java)
        if (iT.isInternetAvailable(requireContext())) {
            startActivity(intent)
        } else {
            iT.toast(requireContext())
        }
    }

    private fun invito() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Scarica anche tu GreenMarket!")
            setPackage("com.whatsapp")
        }

        try {
            this.startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp non è installato.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun terminiCondizioni() {
        val intent = Intent(requireContext(), TermCondActivity::class.java)
        startActivity(intent)
    }

    private fun donazione() {
        val intent = Intent(requireContext(), DonazioniActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}