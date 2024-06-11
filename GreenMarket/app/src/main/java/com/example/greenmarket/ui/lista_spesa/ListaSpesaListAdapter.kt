package com.example.greenmarket.ui.lista_spesa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R
import java.math.BigDecimal
import java.math.RoundingMode

class ListaSpesaListAdapter(
    private val itemClickListener: (ProdottoInListaModel) -> Unit,
    private val imageClickListener: (ProdottoInListaModel) -> Unit
): RecyclerView.Adapter<ListaSpesaListAdapter.MyViewHolder>() {

    private var prodListaSpesaList = emptyList<ProdottoInListaModel>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.nome_prod_lista_spesa_item)
        val textView2 = itemView.findViewById<TextView>(R.id.quantita_prod_lista_spesa)
        val imageView = itemView.findViewById<ImageView>(R.id.delete_prod_bt)
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
        holder.textView.text = currentProdotto.nome + " €" + currentProdotto.prezzo
        holder.textView2.text = "Quantità: " + formatFloat(currentProdotto.quantita) + "kg\n" +
                "Prezzo totale: €${"%.2f".format(currentProdotto.prezzoTotale)}"
        holder.imageView.setOnClickListener{ imageClickListener(currentProdotto)}
        holder.itemView.setOnClickListener { itemClickListener(currentProdotto) }
    }

    override fun getItemCount(): Int {
        return prodListaSpesaList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(prodListaSpesa: List<ProdottoInListaModel>) {
        this.prodListaSpesaList = prodListaSpesa
        notifyDataSetChanged()
    }

    //Gestisce il formato delle quantità con valore .0
    private fun formatFloat(value: Float): String {
        return if(value == value.toInt().toFloat()){
            value.toInt().toString()
        }else{
            value.toString()
        }
    }

}