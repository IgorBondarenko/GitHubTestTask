package tech.gen.githubtesttask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import tech.gen.githubtesttask.databinding.ItemLoaderBinding

class LoaderStateAdapter : LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    inner class LoaderViewHolder(binding: ItemLoaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {

        }

    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder =
        LoaderViewHolder(
            ItemLoaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

}