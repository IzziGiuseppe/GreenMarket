package com.example.greenmarket.ui.home.tessera_punti

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class CodiciScontoListAdapter(
): RecyclerView.Adapter<CodiciScontoListAdapter.MyViewHolder>() {

    private var listCodiciSconto = emptyList<String>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.codice_sconto)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_codice_sconto_view, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentCodiceSconto = listCodiciSconto[position]
        holder.textView.text = currentCodiceSconto
    }

    override fun getItemCount(): Int {
        return listCodiciSconto.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(cs: List<String>) {
        this.listCodiciSconto = cs
        notifyDataSetChanged()
    }


}