package com.example.bevigil.model

import com.example.bevigil.utils.Constants.AMAZON_EXECUTE_API
import com.example.bevigil.utils.Constants.AWS_URL
import com.example.bevigil.utils.Constants.CLOUD_FRONT_URL
import com.example.bevigil.utils.Constants.EMAIL
import com.example.bevigil.utils.Constants.FILE_NAME
import com.example.bevigil.utils.Constants.FILE_PATH
import com.example.bevigil.utils.Constants.FIREBASE_URL
import com.example.bevigil.utils.Constants.HOST
import com.example.bevigil.utils.Constants.IP_ADDRESS_DISCLOSURE
import com.example.bevigil.utils.Constants.IP_URL
import com.example.bevigil.utils.Constants.RELATIVE_END_POINT
import com.example.bevigil.utils.Constants.REST_API
import com.example.bevigil.utils.Constants.URL
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


data class Assets (

  @field:SerializedName(CLOUD_FRONT_URL)
  var CloudFrontURL : ArrayList<String>? = null,

  @field:SerializedName(EMAIL)
  var email    : ArrayList<String>? = null,

  @field:SerializedName(FILE_NAME)
  var filename : ArrayList<String>? = null,

  @field:SerializedName(FILE_PATH)
  var filePath : ArrayList<String>? = null,

  @field:SerializedName(FIREBASE_URL)
  var firebaseURL  : ArrayList<String>? = null,

  @field:SerializedName(HOST)
  var host: ArrayList<String>? = null,

  @field:SerializedName(IP_ADDRESS_DISCLOSURE)
  var ipAddressDisclosure : ArrayList<String>? = null,

  @field:SerializedName(IP_URL )
  var ipUrl :ArrayList<String>? = null,

  @field:SerializedName(RELATIVE_END_POINT )
  var relativeEndPoint:ArrayList<String>? = null,

  @field:SerializedName(REST_API)
  var restApi:ArrayList<String>? = null,

  @field:SerializedName(URL)
  var url   :ArrayList<String>? = null,

  @field:SerializedName(AWS_URL)
  var awsUrl : ArrayList<String>? = null,

  @field:SerializedName(AMAZON_EXECUTE_API)
  var amazonExecuteApi: ArrayList<String>? = null

)