package com.example.greenmarket.ui.altro.storico.dettaglio_scontrini

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.ComposizioneScontrini

class DettaglioScontriniListAdapter(): RecyclerView.Adapter<DettaglioScontriniListAdapter.MyViewHolder>() {

    private var prodDettScontrList = emptyArray<ComposizioneScontrini>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_prod_dett_scontr_item)
        val textView2 = itemView.findViewById<TextView>(R.id.quantita_prod_dett_scontrino)
        val textView3 = itemView.findViewById<TextView>(R.id.prezzo_dett_scontrino)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_dettaglio_scontrino_view, parent, false)
        )
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val currentProdotto = prodDettScontrList[position]
        holder.textView.text = currentProdotto.prodotto
        holder.textView2.text = "Quantità: "+ String.format("%.0f", currentProdotto.quantita).removeSuffix(".0")
        holder.textView3.text = "5"
    }

    override fun getItemCount(): Int {
        return prodDettScontrList.size
    }

    fun setData(prodDettScontr: Array<ComposizioneScontrini>) {
        this.prodDettScontrList = prodDettScontr
        //notifyDataSetChanged()
    }


}