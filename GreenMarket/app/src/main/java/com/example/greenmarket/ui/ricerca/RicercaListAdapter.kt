package com.example.greenmarket.ui.ricerca

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.Prodotto

class RicercaListAdapter(
    private val itemClickListener: (Prodotto) -> Unit
): RecyclerView.Adapter<RicercaListAdapter.MyViewHolder>() {

    private var prodottiList = emptyArray<Prodotto>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_prodotto_item)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_prodotto_view, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProdotto = prodottiList[position]
        holder.textView.text = currentProdotto.nome + " $" + currentProdotto.prezzo
        holder.itemView.setOnClickListener { itemClickListener(currentProdotto) }
    }

    override fun getItemCount(): Int {
        return prodottiList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(prodotto: Array<Prodotto>) {
        this.prodottiList = prodotto
        notifyDataSetChanged()
    }

}