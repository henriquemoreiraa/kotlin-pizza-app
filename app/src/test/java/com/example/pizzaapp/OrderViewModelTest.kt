package com.example.pizzaapp

import android.content.Context
import android.widget.CompoundButton
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pizzaapp.data.Datasource
import com.example.pizzaapp.model.OrderViewModel
import com.example.pizzaapp.model.Pizza
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import java.text.NumberFormat

class OrderViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: OrderViewModel

    @Before
    fun initiate_viewModel() {
        viewModel = OrderViewModel()
    }

    @Test
    fun set_ten_to_price() {
        viewModel.price.observeForever {}
        viewModel.setPrice(10.00)

        assertEquals(
            "price should be 10",
            NumberFormat.getCurrencyInstance().format(10.00),
            viewModel.price.value
        )
    }

    @Test
    fun set_date_to_deliveryDate() {
        viewModel.deliveryDate.observeForever {}
        viewModel.setDate(viewModel.dateOptions[0])

        assertEquals(
            "delivery date should be ${viewModel.dateOptions[0]}",
            viewModel.dateOptions[0],
            viewModel.deliveryDate.value
        )
    }

    @Test
    fun set_cheese_to_additional() {
        val additionalString = R.string.cheese_additional.toString()

        viewModel.setAdditional(additionalString, true)

        assertEquals(
            "additional should have cheese additional",
            true,
            viewModel.additional.contains(additionalString)
        )
    }

    @Test
    fun remove_cheese_from_additional() {
        val additionalString = R.string.cheese_additional.toString()

        viewModel.setAdditional(additionalString, true)
        viewModel.setAdditional(additionalString, false)

        assertEquals(
            "additional should be empty",
            true,
            viewModel.additional.isEmpty()
        )
    }

    @Test
    fun set_pizza() {
        val pizza = Datasource().loadPizzas()[0]

        viewModel.pizza.observeForever {}
        viewModel.setPizza(pizza)

        assertEquals(
            "pizzaNameId should be ${pizza.pizzaNameId}",
            pizza.pizzaNameId,
            viewModel.pizza.value?.pizzaNameId
        )

        assertEquals(
            "pizzaImageId should be ${pizza.pizzaImageId}",
            pizza.pizzaImageId,
            viewModel.pizza.value?.pizzaImageId
        )

        assertEquals(
            "pizzaPrice should be ${pizza.pizzaPrice}",
            pizza.pizzaPrice,
            viewModel.pizza.value?.pizzaPrice
        )
    }
}