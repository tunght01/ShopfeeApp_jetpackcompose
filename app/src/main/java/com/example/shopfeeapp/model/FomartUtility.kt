package com.example.shopfeeapp.model

import java.text.DecimalFormat

class FomartUtility {
    fun format(price:Int):(String){
        return DecimalFormat("#,### vnđ").format(price)
    }
    fun calculateAverageRating(rates: List<Rate>?): Float {
        // Kiểm tra nếu rates là null hoặc rỗng
        if (rates == null || rates.isEmpty()) {
            return 0f // Trả về 0 nếu không có đánh giá nào
        }

        var totalRating = 0
        for (rate in rates) {
            totalRating += rate.Rating // sử dụng lowercase vì naming convention của Kotlin
        }

        // Kiểm tra nếu totalRating bằng 0 (trường hợp đặc biệt)
        if (totalRating == 0) {
            return 0f
        }

        val averageRating = totalRating.toFloat() / rates.size

        // Làm tròn averageRating về 1 chữ số thập phân và chuyển về Float
        val roundedAverage = (averageRating * 10).toInt() / 10f

        return roundedAverage
    }
}
