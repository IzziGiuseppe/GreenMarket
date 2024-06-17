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
import com.example.greenmarket.db.model.Prodotto
import com.example.greenmarket.ui.lista_spesa.ProdottoInListaModel
import com.example.greenmarket.ui.ricettario.RicettaModel
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.withContext

class HomeRicetteListAdapter(
    private val itemClickListener: (RicettaModel) -> Unit
): RecyclerView.Adapter<HomeRicetteListAdapter.MyViewHolder>() {

    private var ricetteHomeList = emptyList<RicettaModel>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_ricetta_home)
        //val textView2 = itemView.findViewById<TextView>(R.id.image_prezzo)
        val imageView = itemView.findViewById<ImageView>(R.id.foto_ricetta_home)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_ricette_home, parent, false)
        )
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRicetta = ricetteHomeList[position]
        holder.textView.text = currentRicetta.nome
        Glide.with(holder.itemView).load(currentRicetta.foto).into(holder.imageView)
        holder.itemView.setOnClickListener { itemClickListener(currentRicetta) }
    }

    override fun getItemCount(): Int {
        return ricetteHomeList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(ricetteHome: List<RicettaModel>) {
        this.ricetteHomeList = ricetteHome
        notifyDataSetChanged()
    }
}