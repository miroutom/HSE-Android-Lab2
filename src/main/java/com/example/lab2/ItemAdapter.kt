package com.example.lab2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(private val context: Context,
                  private val dataset: MutableList<ShoppingItem>
                  ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.itemTitle)
        val deleteButton: View = view.findViewById(R.id.buttonDelete)
        val editButton: View = view.findViewById(R.id.buttonEdit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private var onDeleteClickListener: ((Int) -> Unit)? = null
    private var onEditClickListener: ((Int) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (Int) -> Unit){
        this.onDeleteClickListener = listener
    }

    fun setOnEditClickListener(listener: (Int) -> Unit){
        this.onEditClickListener = listener
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.name

        holder.itemView.findViewById<View>(R.id.buttonDelete)?.setOnClickListener {
            onDeleteClickListener?.invoke(position)
        }

        holder.itemView.findViewById<View>(R.id.buttonEdit)?.setOnClickListener {
            onEditClickListener?.invoke(position)
        }
    }

    fun addItem(item: ShoppingItem){
        dataset.add(item)
        notifyItemInserted(dataset.size - 1)
    }

    fun editItem(position: Int, newItem: ShoppingItem){
        dataset[position] = newItem
        notifyItemChanged(position)
    }

    fun removeItem(position: Int){
        if (dataset.size > position && position >= 0) {
            dataset.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, dataset.size)
        }
    }

}