package com.ksusha.e_commerceapi.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.ksusha.e_commerceapi.ProductsListFragmentUiState
import com.ksusha.e_commerceapi.databinding.FragmentProductsListBinding
import com.ksusha.e_commerceapi.epoxy.UiProductEpoxyController
import com.ksusha.e_commerceapi.model.ui.UIFilter
import com.ksusha.e_commerceapi.model.ui.UIProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class ProductsListFragment: Fragment() {

    private var _binding: FragmentProductsListBinding? = null
    private val binding by lazy { _binding!! }

    private val viewModel: ProductsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = UiProductEpoxyController(viewModel)
        binding.epoxyRecyclerView.setController(controller)
        //controller.setData(emptyList())

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favouriteProductsId },
            viewModel.store.stateFlow.map { it.expandedProductsId },
            viewModel.store.stateFlow.map { it.productFilterInfo }
        ) { listOfProducts, setOfFavoriteIds, setOfExpandedIds, productFilterInfo ->
            val uiProducts = listOfProducts.map { product ->
                UIProduct(
                    product = product,
                    isFavourite = setOfFavoriteIds.contains(product.id),
                    isExpanded = setOfExpandedIds.contains(product.id)
                )
            }

            val uiFilters = productFilterInfo.filters.map { filter ->
                UIFilter(
                    filter = filter,
                    isSelected = productFilterInfo.selectedFilter?.equals(filter) == true
                )
            }.toSet()

            val filteredProducts = if (productFilterInfo.selectedFilter == null) {
                uiProducts
            } else {
                uiProducts.filter { it.product.category == productFilterInfo.selectedFilter.value }
            }
            return@combine ProductsListFragmentUiState(uiFilters, filteredProducts)

        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner) { uiState ->
            controller.setData(uiState)
        }

        viewModel.refreshProducts()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}