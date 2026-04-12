package com.example.wooftheme.data

import androidx.annotation.StringRes
import com.example.wooftheme.R

/**
 * A data class to represent the information presented in the dog card
 */
data class Dog(
    val imageUrl: String,
    @StringRes val name: Int,
    val age: Int,
    @StringRes val hobbies: Int
)

val dogs = listOf(
    Dog("https://images.unsplash.com/photo-1517849845537-4d257902454a?auto=format&fit=crop&q=80&w=400", R.string.dog_name_1, 2, R.string.dog_description_1),
    Dog("https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?auto=format&fit=crop&q=80&w=400", R.string.dog_name_2, 16, R.string.dog_description_2),
    Dog("https://images.unsplash.com/photo-1543466835-00a7907e9de1?auto=format&fit=crop&q=80&w=400", R.string.dog_name_3, 2, R.string.dog_description_3),
    Dog("https://images.unsplash.com/photo-1537151608828-ea2b11777ee8?auto=format&fit=crop&q=80&w=400", R.string.dog_name_4, 8, R.string.dog_description_4),
    Dog("https://images.unsplash.com/photo-1583337130417-3346a1be7dee?auto=format&fit=crop&q=80&w=400", R.string.dog_name_5, 8, R.string.dog_description_5),
    Dog("https://images.unsplash.com/photo-1598133894008-61f7fdb8cc3a?auto=format&fit=crop&q=80&w=400", R.string.dog_name_6, 8, R.string.dog_description_6),
    Dog("https://images.unsplash.com/photo-1518717758536-85ae29035b6d?auto=format&fit=crop&q=80&w=400", R.string.dog_name_7, 4, R.string.dog_description_7),
    Dog("https://images.unsplash.com/photo-1530281700549-e82e7bf110d6?auto=format&fit=crop&q=80&w=400", R.string.dog_name_8, 1, R.string.dog_description_8)
)
