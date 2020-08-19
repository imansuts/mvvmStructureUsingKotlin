package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.ArrayList


@JsonIgnoreProperties(ignoreUnknown = true)
class Result {
    @JsonProperty("status")
    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @JsonProperty("code")
    @SerializedName("code")
    @Expose
    var code: Int? = null
    @JsonProperty("message")
    @SerializedName("message")
    @Expose
    var message: String? = null
    @JsonProperty("speciality_list")
    @SerializedName("speciality_list")
    @Expose
    var specialityList: ArrayList<SpecialityList>? = null

}
