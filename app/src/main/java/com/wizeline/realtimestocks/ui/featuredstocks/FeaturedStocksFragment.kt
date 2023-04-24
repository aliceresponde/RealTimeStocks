package com.wizeline.realtimestocks.ui.featuredstocks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.realtimestocks.databinding.FragmentFeaturedStocksBinding
import com.wizeline.realtimestocks.databinding.StockItemBinding
import com.wizeline.realtimestocks.stock.Stock

class FeaturedStocksFragment : Fragment() {

    private var _binding: FragmentFeaturedStocksBinding? = null
    private val binding get() = _binding!!
    private val stocksRecycler get() = binding.rvFeaturedStocks

    private val viewModel : StocksViewModel by viewModels()
    private val featuredStocksAdapter = FeaturedStocksAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeaturedStocksBinding.inflate(inflater, container, false)
        stocksRecycler.layoutManager = LinearLayoutManager(requireContext())

        stocksRecycler.adapter = featuredStocksAdapter

        viewModel.getStocks()

        viewModel.stocks.observe(viewLifecycleOwner) {
            it?.let {
                featuredStocksAdapter.stocks = it
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
