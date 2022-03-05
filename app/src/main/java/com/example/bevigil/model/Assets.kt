package com.example.bevigil.model

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


data class Assets (

  @field:SerializedName("CloudFront URL")
  var CloudFrontURL : ArrayList<String>? = null,

  @field:SerializedName("email")
  var email    : ArrayList<String>? = null,

  @field:SerializedName("filename")
  var filename : ArrayList<String>? = null,

  @field:SerializedName("file_path")
  var filePath : ArrayList<String>? = null,

  @field:SerializedName("Firebase URL")
  var firebaseURL  : ArrayList<String>? = null,

  @field:SerializedName("host")
  var host: ArrayList<String>? = null,

  @field:SerializedName("IP Address disclosure")
  var ipAddressDisclosure : ArrayList<String>? = null,

  @field:SerializedName("ip_url" )
  var ipUrl :ArrayList<String>? = null,

  @field:SerializedName("relative_endpoint" )
  var relati:ArrayList<String>? = null,

  @field:SerializedName("rest_api")
  var restAp:ArrayList<String>? = null,

  @field:SerializedName("url")
  var url   :ArrayList<String>? = null

)