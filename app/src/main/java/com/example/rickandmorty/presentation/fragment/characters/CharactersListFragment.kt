package com.example.rickandmorty.presentation.fragment.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
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
        viewModel.requestList()
    }

    private fun setAdapter() {
        val recyclerView = binding.listRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        adapter = CharactersAdapter()
        recyclerView.adapter = adapter
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

        viewModel.dataErrorResponse.observe(viewLifecycleOwner) {
            showErrorMessage()
        }
    }

    private fun showEmptyState(isEmptyStateVisible: Boolean) {
        binding.emptyStateTextView.visibility = if (isEmptyStateVisible) View.VISIBLE else View.GONE
    }

    private fun showLoader(isLoaderVisible: Boolean) {
        binding.progressBar.visibility = if (isLoaderVisible) View.VISIBLE else View.GONE
    }
}