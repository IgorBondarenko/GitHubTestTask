package tech.gen.githubtesttask.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tech.gen.githubtesttask.R
import tech.gen.githubtesttask.core.database.enteties.UserHistory

class UsersHistoryAdapter(
    private val listOfUserHistory: MutableList<UserHistory>,
    val onItemClickListener: (htmlUrl: String) -> Unit
) :
    RecyclerView.Adapter<UsersHistoryAdapter.UserRepositoriesViewHolder>() {

    inner class UserRepositoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(userHistory: UserHistory, position: Int) {
            itemView.findViewById<TextView>(R.id.textRepositoryName).text = userHistory.name
            itemView.findViewById<TextView>(R.id.textRepositoryLanguage).text = userHistory.language
            itemView.findViewById<TextView>(R.id.textRepositoryStars).text = "${userHistory.stargazersCount}"

            itemView.setOnClickListener {
                onItemClickListener(userHistory.htmlUrl)
                notifyItemChanged(position)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoriesViewHolder =
        UserRepositoriesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_repository, parent, false)
        )

    override fun onBindViewHolder(holder: UserRepositoriesViewHolder, position: Int) {
        holder.bind(listOfUserHistory[position], position)
    }

    override fun getItemCount(): Int = listOfUserHistory.size

    fun update(listOfNewRepositories: List<UserHistory>) {
        listOfUserHistory.clear()
        listOfUserHistory.addAll(listOfNewRepositories)
        notifyDataSetChanged()
    }

}