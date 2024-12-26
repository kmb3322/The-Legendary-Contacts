package com.example.thelegendarycontacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thelegendarycontacts.R

class HomeContactAdapter(
    private val contacts: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<HomeContactAdapter.HomeContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contact, parent, false)
        return HomeContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeContactViewHolder, position: Int) {
        val contactName = contacts[position]
        holder.bind(contactName)

        // 클릭 시 해당 인물 이름을 onClick 람다로 전달
        holder.itemView.setOnClickListener {
            onClick(contactName)
        }
    }

    override fun getItemCount() = contacts.size

    inner class HomeContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewContactName: TextView = itemView.findViewById(R.id.textViewContactName)

        fun bind(name: String) {
            textViewContactName.text = name
        }
    }
}
