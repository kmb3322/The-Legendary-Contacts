package com.example.thelegendarycontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thelegendarycontacts.R

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editTextAlbumName = view.findViewById<EditText>(R.id.editTextAlbumName)
        val buttonAddTopster = view.findViewById<Button>(R.id.buttonAddTopster)

        buttonAddTopster.setOnClickListener {
            val albumName = editTextAlbumName.text.toString().trim()
            if (albumName.isNotEmpty()) {
                Toast.makeText(context, "내 탑스터에 [$albumName] 추가 완료", Toast.LENGTH_SHORT).show()
                editTextAlbumName.text.clear()
            } else {
                Toast.makeText(context, "앨범명을 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
