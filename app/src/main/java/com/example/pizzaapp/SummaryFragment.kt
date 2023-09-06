package com.example.pizzaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.pizzaapp.adapter.AdditionalAdapter
import com.example.pizzaapp.databinding.FragmentSummaryBinding
import com.example.pizzaapp.model.OrderViewModel
import java.text.NumberFormat

class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private var adapter: AdditionalAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentSummaryBinding
            .inflate(inflater, container, false)

        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdditionalAdapter(this, sharedViewModel.additional)

        binding?.pizzaName?.text = context?.resources
            ?.getString(sharedViewModel.pizza.value?.pizzaNameId!!)
        binding?.pizzaPrice?.text = NumberFormat
            .getCurrencyInstance().format(sharedViewModel.pizza.value?.pizzaPrice)
        binding?.recyclerView2?.adapter = adapter

        binding?.apply {
            summaryFragment = this@SummaryFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    fun onClickBuyButton() {
        Toast.makeText(context, "Sending order...", Toast.LENGTH_SHORT).show()
    }

    fun onClickCancelButton() {
        sharedViewModel.cancelOrder(view, R.id.action_summaryFragment_to_listPizzasFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}