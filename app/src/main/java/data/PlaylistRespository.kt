package com.example.thelegendarycontacts.data

object PlaylistRepository {

    // "플레이리스트 이름" -> "Track 리스트" 매핑
    val playlists: Map<String, List<Track>> = mapOf(
        "My Favorite Songs" to listOf(
            Track("음악 1", "grmbM0dDBPk"),
            // 위 링크 예: https://youtu.be/grmbM0dDBPk?si=0RLcbMrQGesoqbiO
            // 동영상 ID만 "grmbM0dDBPk" 로 추출하여 사용

            // 더미 곡 2,3 ... 원하시면 여기 추가
            Track("음악 2", "grmbM0dDBPk"),
            Track("음악 3", "grmbM0dDBPk")
        ),
        "Another Playlist" to listOf(
            Track("다른 음악 1", "grmbM0dDBPk"),
            Track("다른 음악 2", "grmbM0dDBPk")
        )
        // ...
    )
}
