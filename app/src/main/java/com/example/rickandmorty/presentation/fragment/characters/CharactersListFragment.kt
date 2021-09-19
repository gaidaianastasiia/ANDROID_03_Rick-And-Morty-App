package com.example.rickandmorty.presentation.fragment.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.base.BaseFragment
import kotlin.reflect.KClass


class CharactersListFragment : BaseFragment<
        CharactersListViewModel,
        CharactersListViewModel.Factory,
        FragmentCharactersListBinding
        >() {
    private lateinit var adapter: CharactersAdapter
    override val viewModelClass: KClass<CharactersListViewModel> = CharactersListViewModel::class

    override fun createViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ): FragmentCharactersListBinding =
        FragmentCharactersListBinding.inflate(inflater, parent, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObserve()
        setListeners()
        viewModel.requestList()
    }

    private fun setAdapter() {
        val recyclerView = binding.listRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = CharactersAdapter()
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition: Int =
                    (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                viewModel.onScrolled(lastVisibleItemPosition)
            }
        })
    }

    private fun setObserve() {
        viewModel.charactersList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.showEmptyState.observe(viewLifecycleOwner) { isEmptyStateVisible ->
            showEmptyState(isEmptyStateVisible)
        }

        viewModel.showLoader.observe(viewLifecycleOwner) { isLoaderVisible ->
            showLoader(isLoaderVisible)
        }

        viewModel.showRefreshing.observe(viewLifecycleOwner) { isRefreshingVisible ->
            showRefreshing(isRefreshingVisible)
        }

        viewModel.dataErrorResponse.observe(viewLifecycleOwner) {
            showErrorMessage()
        }
    }

    private fun setListeners() {
        binding.swiperefresh.setOnRefreshListener { viewModel.onRefresh() }
    }

    private fun showRefreshing(isRefreshingVisible: Boolean) {
        binding.swiperefresh.isRefreshing = isRefreshingVisible
    }

    private fun showEmptyState(isEmptyStateVisible: Boolean) {
        binding.emptyStateTextView.visibility = if (isEmptyStateVisible) View.VISIBLE else View.GONE
    }

    private fun showLoader(isLoaderVisible: Boolean) {
        binding.progressBar.visibility = if (isLoaderVisible) View.VISIBLE else View.GONE
    }
}