package com.example.room_and_compose.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "cities")
@Parcelize
data class Cities(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    var city_name: String? = null,
    var city_cep: String? = null,
    var city_uf: String? = null,
):Parcelable