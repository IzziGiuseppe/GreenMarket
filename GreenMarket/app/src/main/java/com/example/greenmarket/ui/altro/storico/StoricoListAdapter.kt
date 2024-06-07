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

class StoricoListAdapter(
    private val itemClickListener: (Scontrino) -> Unit
): RecyclerView.Adapter<StoricoListAdapter.MyViewHolder>() {

    private var listScontrini = emptyArray<Scontrino>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.data_item)
        val textView2 = itemView.findViewById<TextView>(R.id.num_prodotti)
        val textView3 = itemView.findViewById<TextView>(R.id.prezzo_item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_storico_view, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentScontrino = listScontrini[position]
        holder.textView.text = currentScontrino.data
        holder.textView2.text = "5 prodotti"
        holder.textView3.text = "$30"
        holder.itemView.setOnClickListener { itemClickListener(currentScontrino) }
    }

    override fun getItemCount(): Int {
        return listScontrini.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(scontrino: Array<Scontrino>) {
        this.listScontrini = scontrino
        notifyDataSetChanged()
    }


}