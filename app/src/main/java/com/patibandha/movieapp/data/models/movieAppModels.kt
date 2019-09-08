package com.patibandha.movieapp.data.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class FlickerImageListPRQ(
    @SerializedName("page") var page: String
)

data class FlickerImageListRS(
    @SerializedName("description") var description: String? = "",
    @SerializedName("generator") var generator: String? = "",
    @SerializedName("items") var items: List<Movie>? = arrayListOf(),
    @SerializedName("link") var link: String? = "",
    @SerializedName("modified") var modified: String? = "",
    @SerializedName("title") var title: String? = ""
)

@Parcelize
@Entity(tableName = "movies")
data class Movie(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id") var id: Int = 0,

    @ColumnInfo(name = "author")
    @SerializedName("author") var author: String? = "",

    @ColumnInfo(name = "author_id")
    @SerializedName("author_id") var authorId: String? = "",

    @ColumnInfo(name = "date_taken")
    @SerializedName("date_taken") var dateTaken: String? = "",

    @ColumnInfo(name = "description")
    @SerializedName("description") var description: String? = "",

    @ColumnInfo(name = "link")
    @SerializedName("link") var link: String? = "",

    @Ignore
    @SerializedName("media") var media: Media? = null,

    @ColumnInfo(name = "published")
    @SerializedName("published") var published: String? = "",

    @ColumnInfo(name = "tags")
    @SerializedName("tags") var tags: String? = "",

    @ColumnInfo(name = "title")
    @SerializedName("title") var title: String? = "",

    @ColumnInfo(name = "imageUrl")
    @SerializedName("imageUrl") var imageUrl: String? = ""


) : Serializable, Parcelable

@Parcelize
data class Media(
    @SerializedName("m") var imageUrl: String? = ""
) : Parcelable
