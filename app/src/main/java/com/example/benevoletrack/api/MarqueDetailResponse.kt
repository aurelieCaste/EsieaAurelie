package com.example.benevoletrack.api

data class MarqueDetailResponse(
        val Results: List<MarqueModels>
)

data class MarqueModels(
        val Model_ID: Int,
        val Model_Name: String
)