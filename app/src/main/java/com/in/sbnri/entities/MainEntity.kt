package com.`in`.sbnri.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.`in`.sbnri.typeConverterForRoom.ObjectToStringTypeConverter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "main_table")
class MainEntity : Serializable {

    @PrimaryKey(autoGenerate = true)
    var count = 0
    @SerializedName("description")
    @Expose
    @ColumnInfo(name = "description")
    var description: String? = null

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var name: String? = null

    @SerializedName("open_issues_count")
    @Expose
    @ColumnInfo(name = "open_issues_count")
    var openIssuesCount: Int? = null

    @SerializedName("license")
    @Expose
    @TypeConverters(ObjectToStringTypeConverter::class)
    @ColumnInfo(name = "license")
    var license: License? = null

    @SerializedName("permissions")
    @Expose
    @TypeConverters(ObjectToStringTypeConverter::class)
    @ColumnInfo(name = "permissions")
    var permissions: Permissions? = null

}