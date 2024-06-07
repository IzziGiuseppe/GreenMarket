package com.example.greenmarket.ui.lista_spesa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.ComposizioneScontrini

class ListaSpesaListAdapter(
    private val itemClickListener: (ComposizioneScontrini) -> Unit
): RecyclerView.Adapter<ListaSpesaListAdapter.MyViewHolder>() {

    private var prodListaSpesaList = emptyArray<ComposizioneScontrini>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_prod_lista_spesa_item)
        val textView2 = itemView.findViewById<TextView>(R.id.quantita_prod_lista_spesa)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lista_spesa_view, parent, false))
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProdotto = prodListaSpesaList[position]
        holder.textView.text = currentProdotto.prodotto
        holder.textView2.text = "Quantità: "+ String.format("%.0f", currentProdotto.quantita).removeSuffix(".0")
        holder.itemView.setOnClickListener { itemClickListener(currentProdotto) }
    }

    override fun getItemCount(): Int {
        return prodListaSpesaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(prodListaSpesa: Array<ComposizioneScontrini>) {
        this.prodListaSpesaList = prodListaSpesa
        //notifyDataSetChanged()
    }


}