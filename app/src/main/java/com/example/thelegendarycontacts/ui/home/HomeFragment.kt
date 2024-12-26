package com.example.thelegendarycontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thelegendarycontacts.R

class HomeFragment : Fragment() {

    // 더미 데이터 예시 (원하는 만큼 늘리셔도 됨)
    private val contactList = listOf(
        "유재석", "강호동", "이영자", "김종국", "아이유",
        "장범준", "BTS", "뉴진스", "김연아", "손흥민",
        "이정재", "정우성", "박보검", "송강호", "전지현",
        "김태리", "손예진", "박서준", "V(뷔)", "RM"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // 레이아웃 매니저를 반드시 설정해야 목록이 보입니다!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewHome)

        // 레이아웃 매니저 설정
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 어댑터 설정
        val adapter = HomeContactAdapter(contactList) { contactName ->
            Toast.makeText(context, "$contactName 님의 탑스터 보기", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter
    }
}
