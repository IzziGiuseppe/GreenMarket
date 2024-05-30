package com.example.greenmarket.ui.altro.termini_condizioni

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class TermCondListAdapter: RecyclerView.Adapter<TermCondListAdapter.MyViewHolder>() {

    private var termCondTxt = emptyArray<String>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.sezione_tc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tc_view, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val tcTxt = termCondTxt[position]
        holder.textView.text = tcTxt
    }

    override fun getItemCount(): Int {
        return termCondTxt.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(txt: Array<String>) {
        this.termCondTxt = txt
        notifyDataSetChanged()
    }

}