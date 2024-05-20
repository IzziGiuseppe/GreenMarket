package com.example.greenmarket.ui.altro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.greenmarket.databinding.FragmentAltroBinding

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
                position -> altroViewModel.sceltaListener(position)
            }
            val recyclerView = binding.rvAltro
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}