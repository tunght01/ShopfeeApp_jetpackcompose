package com.example.shopfeeapp.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

// Product data class
@Parcelize
data class Drink(
    val id: Int,
    val name: String,
    val Description: String,
    val Price: Int,
    val ImageUrl: ImageUrl?,
//    val order_details: List<OrderDetail>?,
    val rates: List<Rate>?,
    val status: Boolean,
    val sales:Int
//    val published_at: String,
//    val created_at: String,
//    val updated_at: String
):Parcelable

// ImageUrl data class
@Parcelize
data class ImageUrl(
    val id: Int,
    val name: String,
//    val alternativeText: String?,
//    val caption: String?,
//    val width: Int,
//    val height: Int,
    val formats: Formats,
//    val hash: String,
//    val ext: String,
//    val mime: String,
//    val size: Double,
    val url: String,
//    val previewUrl: String?,
//    val provider: String,
//    val provider_metadata: String?,
//    val created_at: String,
//    val updated_at: String
):Parcelable

// Formats data class
@Parcelize
data class Formats(
    val thumbnail: Thumbnail
):Parcelable

// Thumbnail data class
@Parcelize
data class Thumbnail(
    val name: String,
    val hash: String,
    val ext: String,
    val mime: String,
    val width: Int,
    val height: Int,
    val size: Double,
    val path: String?,
    val url: String
):Parcelable

// OrderDetail data class
//data class OrderDetail(
//    // Define the fields based on your actual JSON structure for order details
//)

// Rate data class
@Parcelize
data class Rate(
    val id: Int,
    val users_permissions_user: Int,
    val drink: Int,
    val Rating: Int,
    val Review: String,
    val published_at: String,
    val created_at: String,
    val updated_at: String
):Parcelable
