package com.example.a10augportfolio.network

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class PaxabayPOJO {
    @SerializedName("total")
    @Expose
    var total: Int? = null

    @SerializedName("totalHits")
    @Expose
    var totalHits: Int? = null

    @SerializedName("hits")
    @Expose
    var hits: List<HitPOJO>? = null

}