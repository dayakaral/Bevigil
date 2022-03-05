package com.example.bevigil.model

import com.google.gson.annotations.SerializedName

class ErrorData {
    @field:SerializedName("detail")
    var message: String? = null
}
