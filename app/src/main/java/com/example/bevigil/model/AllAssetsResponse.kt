package com.example.bevigil.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class AllAssetsResponse (

  @field:SerializedName("package_id")
  var packageId : String? = null,
  @field:SerializedName("assets")
  var assets    : Assets? = null

)