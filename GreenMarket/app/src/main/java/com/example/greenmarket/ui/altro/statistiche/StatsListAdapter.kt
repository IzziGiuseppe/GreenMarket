package com.example.greenmarket.ui.altro.statistiche

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.db.model.Scontrino

class StatsListAdapter(
    private val itemClickListener: (Prodotto) -> Unit
): RecyclerView.Adapter<StatsListAdapter.MyViewHolder>() {

    private var listProdStats = emptyArray<Prodotto>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_prodotto_stats_item)
        val textView2 = itemView.findViewById<TextView>(R.id.quantita_prodotto_stats)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_stats_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProdotto = listProdStats[position]
        holder.textView.text = currentProdotto.nome
        holder.textView2.text = position.toString()
        holder.itemView.setOnClickListener { itemClickListener(currentProdotto) }
    }

    override fun getItemCount(): Int {
        return listProdStats.size
    }

    fun setData(prodotto: Array<Prodotto>) {
        this.listProdStats = prodotto
        //notifyDataSetChanged()
    }
}