package com.example.pizzaapp.model

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.pizzaapp.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val SAME_DAY_DELIVERY_PRICE = 5.00
const val DEFAULT_DELIVERY_PRICE = 3.00
const val ADDITIONAL_PRICE = 1.00

class OrderViewModel : ViewModel() {
    private val _pizza = MutableLiveData<Pizza>()
    val pizza: LiveData<Pizza> = _pizza

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _deliveryDate = MutableLiveData<String>()
    val deliveryDate: LiveData<String> = _deliveryDate

    private val _deliveryPrice = MutableLiveData<Double>()
    val deliveryPrice: LiveData<String> = Transformations.map(_deliveryPrice) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private var _additional = mutableListOf<String>()
    val additional: List<String> = _additional

    val dateOptions = getDeliveryDates()

    fun setPrice(pizzaPrice: Double?) {
        _price.value = pizzaPrice!!
    }

    fun setDate(date: String) {
        _deliveryDate.value = date
        calculateAdditional()

        if (date == dateOptions[0]) {
            _deliveryPrice.value = SAME_DAY_DELIVERY_PRICE
            addPrice(_deliveryPrice.value!!)
            return
        }
        _deliveryPrice.value = DEFAULT_DELIVERY_PRICE
        addPrice(_deliveryPrice.value!!)
    }

    fun setAdditional(additionalChecked: CompoundButton) {
        val additionalName = additionalChecked.text.toString()
        if (additionalChecked.isChecked) {
            _additional.add(additionalName)
            addPrice(ADDITIONAL_PRICE)
            return
        }
        _additional.remove(additionalName)
        _price.value = _price.value?.minus(ADDITIONAL_PRICE)
    }

    fun setPizza(pizza: Pizza) {
        _pizza.value = pizza
    }

    fun cancelOrder(view: View?, action: Int) {
        resetOrder()
        view?.findNavController()?.navigate(action)
    }

    private fun resetOrder() {
        _price.value = 0.0
        _pizza.value = Pizza(0, 0, 0.0)
        _deliveryDate.value = dateOptions[0]
        _deliveryPrice.value = SAME_DAY_DELIVERY_PRICE
        _additional.clear()
    }

    private fun getDeliveryDates(): List<String> {
        val dates = mutableListOf<String>()
        val formatter = SimpleDateFormat("E, MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        repeat(4) {
            dates.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dates
    }

    private fun calculateAdditional() {
        val numberOfAdditional = _additional.size * ADDITIONAL_PRICE
        setPrice(_pizza.value?.pizzaPrice?.plus(numberOfAdditional))
    }

    private fun addPrice(price: Double) {
        _price.value = _price.value?.plus(price)
    }

    init {
        resetOrder()
    }
}