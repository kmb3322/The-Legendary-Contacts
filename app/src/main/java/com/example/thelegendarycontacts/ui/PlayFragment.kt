package com.example.thelegendarycontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thelegendarycontacts.R
import com.example.thelegendarycontacts.data.PlaylistRepository
import com.example.thelegendarycontacts.data.Track
import com.example.thelegendarycontacts.data.UserResponses
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class PlayFragment : Fragment() {

    companion object {
        private const val ARG_PLAYLIST_NAME = "arg_playlist_name"

        fun newInstance(playlistName: String): PlayFragment {
            val fragment = PlayFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PLAYLIST_NAME, playlistName)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var playlistName: String? = null
    private lateinit var trackList: List<Track>
    private var currentIndex = 0  // 현재 곡 인덱스

    private lateinit var youtubePlayerView: YouTubePlayerView
    private var youTubePlayer: YouTubePlayer? = null // 유튜브플레이어 인스턴스 (초기화 후 할당)

    private lateinit var textViewTrackTitle: TextView
    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playlistName = arguments?.getString(ARG_PLAYLIST_NAME)
        val name = playlistName ?: ""
        trackList = PlaylistRepository.playlists[name].orEmpty()

        // 만약 trackList가 비어있다면 처리 필요
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_play, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        youtubePlayerView = view.findViewById(R.id.youtubePlayerView)
        textViewTrackTitle = view.findViewById(R.id.textViewTrackTitle)
        buttonYes = view.findViewById(R.id.buttonYes)
        buttonNo = view.findViewById(R.id.buttonNo)

        // YouTubePlayerView 초기화 (LifeCycle에 맞춰 플레이어를 관리)
        lifecycle.addObserver(youtubePlayerView)

        // 플레이어 리스너 등록
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(player: YouTubePlayer) {
                super.onReady(player)
                // YouTubePlayer 인스턴스 저장
                youTubePlayer = player

                // 첫 곡 재생
                playCurrentTrack()
            }
        })

        // 첫 곡 제목 표시
        updateTrackUI()

        // YES / NO 버튼
        buttonYes.setOnClickListener {
            handleUserResponse("yes")
        }
        buttonNo.setOnClickListener {
            handleUserResponse("no")
        }
    }

    private fun playCurrentTrack() {
        val currentTrack = trackList.getOrNull(currentIndex) ?: return
        val videoId = currentTrack.youtubeId
        youTubePlayer?.loadVideo(videoId, 0f)
    }

    private fun updateTrackUI() {
        val currentTrack = trackList.getOrNull(currentIndex)
        if (currentTrack == null) {
            textViewTrackTitle.text = "재생할 곡이 없습니다."
            buttonYes.isEnabled = false
            buttonNo.isEnabled = false
        } else {
            textViewTrackTitle.text = currentTrack.title
            buttonYes.isEnabled = true
            buttonNo.isEnabled = true
        }
    }

    private fun handleUserResponse(response: String) {
        val currentTrack = trackList.getOrNull(currentIndex) ?: return
        val pname = playlistName ?: return

        // 응답 저장
        UserResponses.setResponse(pname, currentTrack.title, response)
        Toast.makeText(context, "[$response] 처리 완료!", Toast.LENGTH_SHORT).show()

        // 다음 곡으로 넘어가기
        currentIndex++
        if (currentIndex >= trackList.size) {
            // 더 이상 재생할 곡이 없다면
            Toast.makeText(context, "플레이리스트 재생 완료!", Toast.LENGTH_LONG).show()
            // Fragment 종료 or 뒤로가기
            parentFragmentManager.popBackStack()
        } else {
            updateTrackUI()
            playCurrentTrack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 혹시나 리소스 정리할 것이 있으면
        youTubePlayer = null
    }
}
