package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
class GetSpecilityListResponse {

    @JsonProperty("code")
    @SerializedName("code")
    @Expose
    var code: String? = null
    @JsonProperty("status")
    @SerializedName("status")
    @Expose
    var status: String? = null
    @JsonProperty("result")
    @SerializedName("result")
    @Expose
    var result: Result? = null

}
