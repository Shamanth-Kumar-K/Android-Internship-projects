package com.example.cupcake.data

/**
 * Data class that represents the current UI state in terms of [quantity], [flavor],
 * [dateOptions], [selectedDate] and [price]
 */
data class OrderUiState(
    /** Selected cupcake quantity (1, 6, 12) */
    val quantity: Int = 0,
    /** Flavor of the cupcakes in the order (such as "Vanilla", "Chocolate", etc..) */
    val flavor: String = "",
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: String = "",
    /** Available lookup options for the pickup date */
    val pickupOptions: List<String> = listOf()
)