package com.example.thelegendarycontacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thelegendarycontacts.R

class PlaylistAdapter(
    private val playlistNames: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val name = playlistNames[position]
        holder.bind(name)
    }

    override fun getItemCount(): Int = playlistNames.size

    class PlaylistViewHolder(itemView: View, val onClick: (String) -> Unit)
        : RecyclerView.ViewHolder(itemView) {

        private val textViewPlaylistName = itemView.findViewById<TextView>(R.id.textViewPlaylistName)

        fun bind(playlistName: String) {
            textViewPlaylistName.text = playlistName
            itemView.setOnClickListener {
                onClick(playlistName)
            }
        }
    }
}
