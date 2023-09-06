package com.example.pizzaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pizzaapp.data.Datasource
import com.example.pizzaapp.databinding.FragmentPizzaDetailBinding
import com.example.pizzaapp.model.OrderViewModel
import com.example.pizzaapp.model.Pizza
import com.example.pizzaapp.model.SAME_DAY_DELIVERY_PRICE
import java.text.NumberFormat

class PizzaDetailFragment : Fragment() {
    companion object {
        const val PIZZA_NAME = "pizzaName"
    }

    private var binding: FragmentPizzaDetailBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private lateinit var pizza: Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pizza = Datasource().loadPizzas().filter { currentPizza ->
                context?.resources?.getString(currentPizza.pizzaNameId)
                    ?.startsWith(it.getString(PIZZA_NAME).toString(), ignoreCase = true) ?: false
            }[0]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentPizzaDetailBinding
            .inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imageView2?.setImageResource(pizza.pizzaImageId)
        binding?.pizzaName?.text = context?.resources?.getString(pizza.pizzaNameId)
        binding?.pizzaPrice?.text = NumberFormat.getCurrencyInstance().format(pizza.pizzaPrice)

        binding?.apply {
            pizzaDetailFragment = this@PizzaDetailFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun onClickBuy() {
        sharedViewModel.setPizza(pizza)
        sharedViewModel.setDate(
            sharedViewModel.deliveryDate.value ?: sharedViewModel.dateOptions[0]
        )

        findNavController().navigate(R.id.action_pizzaDetailFragment_to_summaryFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}