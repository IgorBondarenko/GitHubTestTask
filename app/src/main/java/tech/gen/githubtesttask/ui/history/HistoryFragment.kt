package tech.gen.githubtesttask.ui.history

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import tech.gen.githubtesttask.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel
    private var _binding: FragmentHistoryBinding? = null
    private var userHistoryAdapter: UsersHistoryAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyViewModel =
            ViewModelProvider(this).get(HistoryViewModel::class.java)

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val root = binding?.root

        initAdapter()

        historyViewModel
            .fetchHistory(requireContext())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    binding?.textNoHistoryHint?.isGone = it.isNotEmpty()
                    userHistoryAdapter?.update(it)
                },
                {
                    Toast.makeText(requireContext(), it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            )

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        userHistoryAdapter = null
    }

    private fun initAdapter() {
        userHistoryAdapter = UsersHistoryAdapter(mutableListOf()) { htmlUrl ->
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(htmlUrl)
                )
            )
        }
        binding?.recyclerViewHistory?.adapter = userHistoryAdapter
    }

}