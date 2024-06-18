package com.example.greenmarket.ui.altro.storico

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import com.example.greenmarket.db.model.Scontrino
import com.example.greenmarket.ui.altro.storico.dettaglio_scontrini.ScontrinoModel

class StoricoListAdapter(
    private val itemClickListener: (ScontrinoModel) -> Unit
): RecyclerView.Adapter<StoricoListAdapter.MyViewHolder>() {

    private var listScontrini = emptyList<ScontrinoModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.data_item)
        val textView2 = itemView.findViewById<TextView>(R.id.num_prodotti)
        val textView3 = itemView.findViewById<TextView>(R.id.prezzo_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_storico_view, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentScontrino = listScontrini[position]
        holder.textView.text = currentScontrino.data
        val prod = if (currentScontrino.prodotti.size == 1) {
            "prodotto"
        } else {
            "prodotti"
        }
        val cs = if (currentScontrino.codiceSconto == "-") {
            ""
        } else {
            " + 1 \uD83C\uDFAB"
        }
        holder.textView2.text = currentScontrino.prodotti.size.toString() + " $prod $cs"
        holder.textView3.text = "€" + currentScontrino.totale.toString()
        holder.itemView.setOnClickListener { itemClickListener(currentScontrino) }
    }

    override fun getItemCount(): Int {
        return listScontrini.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(scontrino: List<ScontrinoModel>) {
        this.listScontrini = scontrino
        notifyDataSetChanged()
    }


}