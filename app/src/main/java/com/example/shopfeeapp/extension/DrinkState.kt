package com.example.shopfeeapp.extension

import com.example.shopfeeapp.model.Drink

data class DrinkState(
    val drink: Drink,
    val isColdSelected: Boolean = false,
    val isHotSelected: Boolean = false,
    val isSizeSmallSelected: Boolean = false,
    val isSizeMediumSelected: Boolean = false,
    val isSizeLargeSelected: Boolean = false,
    val isSugarNormalSelected: Boolean = false,
    val isSugarReducedSelected: Boolean = false,
    val isIceNormalSelected: Boolean = false,
    val isIceReducedSelected: Boolean = false,
    val toppings: Map<String, Boolean> = mapOf(
        "Trân châu đen" to false,
        "Trân châu trắng" to false,
        "Dừa khô" to false,
        "Thạch 7 màu" to false
    ),
    val note: String = "",
    val quantity: Int = 1
) {
    val basePrice = drink.Price
    val totalPrice: Int
        get() {
            var price = basePrice
            if (isSizeMediumSelected) price += 10000
            if (isSizeLargeSelected) price += 15000
            if (toppings["Trân châu đen"] == true) price += 5000
            if (toppings["Trân châu trắng"] == true) price += 6000
            if (toppings["Dừa khô"] == true) price += 3000
            if (toppings["Thạch 7 màu"] == true) price += 7000
            return price * quantity
        }
}







