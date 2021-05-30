package com.example.benevoletrack.api

import com.example.benevoletrack.list.Marque


data  class MarqueListResponse(

      val Count: Int,
      val Results: List<Marque>
)

