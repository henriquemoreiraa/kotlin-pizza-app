package com.example.pizzaapp.data

import com.example.pizzaapp.R
import com.example.pizzaapp.model.Pizza

const val MARGHERITA_PRICE = 12.0
const val PEPPERONI_PRICE = 13.0
const val CHEESE_PRICE = 10.0
const val VEGGIE_PRICE = 11.0

class Datasource {
    fun loadPizzas(): List<Pizza> {
        return listOf(
            Pizza(R.string.margherita, R.drawable.margherita, MARGHERITA_PRICE),
            Pizza(R.string.pepperoni, R.drawable.pepperoni, PEPPERONI_PRICE),
            Pizza(R.string.cheese, R.drawable.cheese, CHEESE_PRICE),
            Pizza(R.string.veggie, R.drawable.veggie, VEGGIE_PRICE)
        )
    }
}