package com.example.pizzaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.pizzaapp.adapter.PizzaListAdapter
import com.example.pizzaapp.data.Datasource
import com.example.pizzaapp.databinding.FragmentListPizzasBinding
import com.example.pizzaapp.model.OrderViewModel

class ListPizzasFragment : Fragment() {
    private var binding: FragmentListPizzasBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()
    private var adapter: PizzaListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentListPizzasBinding
            .inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pizzasData = Datasource().loadPizzas()
        adapter = PizzaListAdapter(this, pizzasData, sharedViewModel)

        binding?.floatingActionButton?.visibility =
            if (sharedViewModel.pizza.value?.pizzaNameId?.equals(0) == true)
                View.INVISIBLE
            else View.VISIBLE

        binding?.recyclerView?.adapter = adapter
        binding?.apply {
            listPizzasFragment = this@ListPizzasFragment
        }
    }

    fun navigateToSummary() {
        findNavController().navigate(R.id.action_listPizzasFragment_to_summaryFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}