package com.example.bevigil.model

import com.google.gson.annotations.SerializedName

data class ErrorData (
    @field:SerializedName("detail")
    var message: String? = null
)
