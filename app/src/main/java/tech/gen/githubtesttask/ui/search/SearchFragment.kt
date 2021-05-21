package tech.gen.githubtesttask.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.gen.githubtesttask.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel
    private var _binding: FragmentSearchBinding? = null
    private var repositoriesAdapter: RepositoriesPageAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val rootView = binding?.root

        initAdapter()

        binding?.buttonSearch?.setOnClickListener {
            val searchQuery = binding?.editTextSearch?.text.toString()

            lifecycleScope.launch {
                searchViewModel
                    .fetchUsersRepositories(requireContext(), searchQuery)
                    .collectLatest {
                        repositoriesAdapter?.submitData(it)
                    }
            }
        }

        return rootView
    }

    private fun initAdapter() {
        repositoriesAdapter =
            RepositoriesPageAdapter { selectedUserRepository ->
                if (!selectedUserRepository.isWatched)
                    searchViewModel
                        .insert(requireContext(), selectedUserRepository)
                        .subscribeOn(Schedulers.io())
                        .subscribe()

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(selectedUserRepository.htmlUrl)
                    )
                )
            }

        repositoriesAdapter?.addLoadStateListener { loadState ->
            binding?.textSearchHint?.isGone =
                !(loadState.refresh is LoadState.NotLoading && repositoriesAdapter?.itemCount == 0)
        }

        binding?.recyclerViewUsers?.adapter =
            repositoriesAdapter?.withLoadStateFooter(LoaderStateAdapter())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        repositoriesAdapter = null
    }

}