package com.example.pizzaapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pizzaapp.adapter.PizzaListAdapter
import com.example.pizzaapp.data.MARGHERITA_PRICE
import com.example.pizzaapp.data.PEPPERONI_PRICE
import com.example.pizzaapp.model.OrderViewModel
import com.example.pizzaapp.model.Pizza
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class ListPizzasFragmentTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val context = mock(ListPizzasFragment::class.java)
    private val viewModel = mock(OrderViewModel::class.java)

    @Test
    fun adapter_size() {
        val data = listOf(
            Pizza(R.string.margherita, R.drawable.margherita, MARGHERITA_PRICE),
            Pizza(R.string.pepperoni, R.drawable.pepperoni, PEPPERONI_PRICE),
        )

        val adapter = PizzaListAdapter(context, data, viewModel)

        assertEquals("PizzaListAdapter size should be 2", data.size, adapter.itemCount)
    }
}