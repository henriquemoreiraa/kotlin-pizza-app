package com.example.pizzaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.ListPizzasFragment
import com.example.pizzaapp.R
import com.example.pizzaapp.model.Pizza
import java.text.NumberFormat
import com.example.pizzaapp.ListPizzasFragmentDirections
import com.example.pizzaapp.model.OrderViewModel


class PizzaListAdapter(
    private val context: ListPizzasFragment,
    private val dataset: List<Pizza>,
    private val viewModel: OrderViewModel
) :
    RecyclerView.Adapter<PizzaListAdapter.PizzaItemViewHolder>() {

    class PizzaItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val pizzaName: TextView = view.findViewById(R.id.pizza_name)
        val pizzaPrice: TextView = view.findViewById(R.id.pizza_price)
        val pizzaImage: ImageView = view.findViewById(R.id.pizza_image)
        val pizzaCard: CardView = view.findViewById(R.id.pizza_card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pizza_card, parent, false)

        return PizzaItemViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: PizzaItemViewHolder, position: Int) {
        val pizza = dataset[position]
        val pizzaName = context.resources.getString(pizza.pizzaNameId)

        holder.pizzaName.text = pizzaName
        holder.pizzaPrice.text = NumberFormat.getCurrencyInstance().format(pizza.pizzaPrice)
        holder.pizzaImage.setImageResource(pizza.pizzaImageId)

        holder.pizzaCard.setOnClickListener {
            viewModel.setPrice(pizza.pizzaPrice)
            val action = ListPizzasFragmentDirections
                .actionListPizzasFragmentToPizzaDetailFragment(pizzaName)

            holder.view.findNavController().navigate(action)
        }
    }
}