package com.example.greenmarket.ui.altro

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenmarket.R

class AltroListAdapter(private var menuItems: List<AltroMenu>,
    private val itemClickListener: (Int) -> Unit) : RecyclerView.Adapter<AltroListAdapter.MenuViewHolder>(){

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuItemTextView: TextView = itemView.findViewById(R.id.sezione_altro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_altro_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val currentItem = menuItems[position]
        holder.menuItemTextView.text = currentItem.name
        holder.menuItemTextView.setOnClickListener {
            itemClickListener(position)
        }

    }

    override fun getItemCount(): Int {
        return menuItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(item: List<AltroMenu>) {
        this.menuItems = item
        notifyDataSetChanged()
    }

}