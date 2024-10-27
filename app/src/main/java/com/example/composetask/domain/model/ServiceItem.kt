package com.example.composetask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ServiceItem(
    val id: Int,
    val name: String,
    val imageRes: Int,
    val category: Category
) : Parcelable

