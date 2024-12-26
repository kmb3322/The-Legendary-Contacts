package com.example.thelegendarycontacts.data

object UserResponses {
    // (플레이리스트 이름, 곡 제목) -> "yes" 또는 "no"
    private val responseMap = mutableMapOf<Pair<String, String>, String>()

    fun setResponse(playlistName: String, trackTitle: String, response: String) {
        responseMap[Pair(playlistName, trackTitle)] = response
    }

    fun getResponse(playlistName: String, trackTitle: String): String? {
        return responseMap[Pair(playlistName, trackTitle)]
    }
}
