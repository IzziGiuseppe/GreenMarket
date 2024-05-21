package com.example.greenmarket.ui.ricettario

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.ProdottiInRicette
import com.example.greenmarket.db.model.Ricetta

class RicettarioListAdapter: RecyclerView.Adapter<RicettarioListAdapter.MyViewHolder>() {

    private var ricetteList = emptyArray<ProdottiInRicette>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_ricetta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_ricetta_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRicetta = ricetteList[position]
        holder.textView.text = currentRicetta.ricetta
    }

    override fun getItemCount(): Int {
        return ricetteList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(ricetta: Array<ProdottiInRicette>) {
        this.ricetteList = ricetta
        notifyDataSetChanged()
    }
}