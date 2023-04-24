package com.wizeline.realtimestocks.ui.featuredstocks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.realtimestocks.databinding.StockItemBinding
import com.wizeline.realtimestocks.stock.Stock

class FeaturedStocksAdapter: RecyclerView.Adapter<FeaturedStocksAdapter.StockViewHolder>() {
    class StockViewHolder(private val binding: StockItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.tvStockTicker.text = stock.ticker
            binding.tvStockPrice.text = stock.price.toString()
            binding.tvStockCompannyName.text = stock.companyName
        }
    }

    private val callback: DiffUtil.ItemCallback<Stock> = object : DiffUtil.ItemCallback<Stock>() {
        override fun areItemsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem.ticker == newItem.ticker
        }

        override fun areContentsTheSame(oldItem: Stock, newItem: Stock): Boolean {
            return oldItem == newItem
        }
    }

    private val differ: AsyncListDiffer<Stock> = AsyncListDiffer(this, callback)

    var stocks: List<Stock>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val binding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        holder.bind(stocks[position])
    }
}
