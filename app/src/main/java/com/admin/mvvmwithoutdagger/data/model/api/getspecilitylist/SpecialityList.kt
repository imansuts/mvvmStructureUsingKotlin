package com.admin.mvvmwithoutdagger.data.model.api.getspecilitylist

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@JsonIgnoreProperties(ignoreUnknown = true)
class SpecialityList {

    @JsonProperty("type")
    @SerializedName("type")
    @Expose
    var type: String? = null
    @JsonProperty("id")
    @SerializedName("id")
    @Expose
    var id: String? = null
    @JsonProperty("name")
    @SerializedName("name")
    @Expose
    var name: String? = null
    @JsonProperty("image")
    @SerializedName("image")
    @Expose
    var image: String? = null
    @JsonProperty("is_active")
    @SerializedName("is_active")
    @Expose
    var isActive: String? = null
    @JsonProperty("is_deleted")
    @SerializedName("is_deleted")
    @Expose
    var isDeleted: String? = null
    @JsonProperty("created_at")
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @JsonProperty("modified_at")
    @SerializedName("modified_at")
    @Expose
    var modifiedAt: String? = null

}
