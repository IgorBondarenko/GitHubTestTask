package tech.gen.githubtesttask.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import tech.gen.githubtesttask.R
import tech.gen.githubtesttask.core.network.models.Repository
import tech.gen.githubtesttask.databinding.ItemUserRepositoryBinding

class RepositoriesPageAdapter(
    val onItemClickListener: (userRepository: Repository) -> Unit
) :
    PagingDataAdapter<Repository, RepositoriesPageAdapter.UserRepositoriesViewHolder>(COMPARATOR) {


    inner class UserRepositoriesViewHolder(binding: ItemUserRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repository: Repository, position: Int) {
            itemView.findViewById<TextView>(R.id.textRepositoryName).text = repository.name
            itemView.findViewById<TextView>(R.id.textRepositoryLanguage).text = repository.language
            itemView.findViewById<TextView>(R.id.textRepositoryStars).text = "${repository.stargazersCount}"

            itemView.isSelected = repository.isWatched
            itemView.setOnClickListener {
                onItemClickListener(repository)
                repository.isWatched = true
                notifyItemChanged(position)
            }
        }

    }

    override fun onBindViewHolder(holder: UserRepositoriesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoriesViewHolder =
        UserRepositoriesViewHolder(
            ItemUserRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {

        private val COMPARATOR = object : DiffUtil.ItemCallback<Repository>() {

            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                (oldItem.isWatched == newItem.isWatched) and
                        (oldItem.name == newItem.name) and
                        (oldItem.stargazersCount == newItem.stargazersCount)

        }

    }

}