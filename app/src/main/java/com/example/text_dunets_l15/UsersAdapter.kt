package com.example.text_dunets_l15

import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter : RecyclerView.Adapter<UserViewHolder>() {

    private val users: MutableList<SpannableString> = mutableListOf()

    fun addUser(s: SpannableString) {
        users.add(s)
        notifyItemInserted(users.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_view_holder, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(s: SpannableString) {
        itemView.findViewById<TextView>(R.id.tvUser).text = s
    }
}