package com.example.thelegendarycontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelegendarycontacts.R
import com.example.thelegendarycontacts.data.PlaylistRepository

class DashboardFragment : Fragment() {

    private val playlistNames = PlaylistRepository.playlists.keys.toList()
    // 예: ["My Favorite Songs", "Another Playlist", ...]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewPlaylist)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = PlaylistAdapter(playlistNames) { selectedPlaylist ->
            // 플레이리스트 선택 -> PlayFragment 로 이동
            val fragment = PlayFragment.newInstance(selectedPlaylist)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit()
        }

        recyclerView.adapter = adapter
    }
}
