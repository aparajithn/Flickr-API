package com.example.apprenticeassignment2.data


import com.google.gson.annotations.SerializedName

//Response sample
//{"photos":{"page":1,"pages":249849,"perpage":3,"total":749547,
// "photo":[
// {"id":"51814235755","owner":"55583186@N02","secret":"719737ffe7","server":"65535","farm":66,"title":"Early Morning Bald Eagle","ispublic":1,"isfriend":0,"isfamily":0},
// {"id":"51813564333","owner":"20198552@N06","secret":"bf86883099","server":"65535","farm":66,"title":"Northern Pintail","ispublic":1,"isfriend":0,"isfamily":0},
// {"id":"51812498877","owner":"20198552@N06","secret":"fcb9056f0b","server":"65535","farm":66,"title":"Northern Pintail","ispublic":1,"isfriend":0,"isfamily":0}
// ]},"stat":"ok"}
data class PhotosResponse(
    @SerializedName("photos")
    val photos: ImageSearchResponse
)

data class ImageSearchResponse(
    @SerializedName("page")
    val page: Int = 1,
    @SerializedName("perpage")
    val perPage: Int,
    val photo: MutableList<Photo>,
    @SerializedName("total")
    val totalResults: Int
) {
    val nextPage: Int = page.plus(1)
}