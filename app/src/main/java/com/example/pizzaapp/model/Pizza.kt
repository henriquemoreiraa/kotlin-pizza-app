package com.example.pizzaapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Pizza(
    @StringRes val pizzaNameId: Int,
    @DrawableRes val pizzaImageId: Int,
    val pizzaPrice: Double
)

