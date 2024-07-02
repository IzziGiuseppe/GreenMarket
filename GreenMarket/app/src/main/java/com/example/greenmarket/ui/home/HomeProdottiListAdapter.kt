package com.example.greenmarket.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greenmarket.R
import com.example.greenmarket.db.model.ProdottoModel

class HomeProdottiListAdapter(
    private val itemClickListener: (ProdottoModel) -> Unit
): RecyclerView.Adapter<HomeProdottiListAdapter.MyViewHolder>() {

    private var prodHomeList = emptyList<ProdottoModel>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.image_nome)
        val textView2 = itemView.findViewById<TextView>(R.id.image_prezzo)
        val imageView = itemView.findViewById<ImageView>(R.id.stats_prod)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_prodotti_home, parent, false)
        )
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentProdotto = prodHomeList[position]
        holder.textView.text = currentProdotto.nome
        holder.textView2.text = "€" + currentProdotto.prezzo
        Glide.with(holder.itemView).load(currentProdotto.foto).into(holder.imageView)
        holder.itemView.setOnClickListener { itemClickListener(currentProdotto) }
    }

    override fun getItemCount(): Int {
        return prodHomeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(prodHome: List<ProdottoModel>) {
        this.prodHomeList = prodHome
        notifyDataSetChanged()
    }
}