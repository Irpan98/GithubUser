package id.itborneo.githubuser.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.itborneo.githubuser.R
import id.itborneo.githubuser.data.model.UserModel


class MainAdapter(private val listener: (UserModel) -> Unit) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var data = listOf<UserModel>()

    fun set(data: List<UserModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        private val tvName = view.findViewById<TextView>(R.id.tv_name)
        private val tvSubtitle = view.findViewById<TextView>(R.id.tv_subtitle)
        private val ivImage = view.findViewById<ImageView>(R.id.iv_image)
        private val clItem = view.findViewById<CardView>(R.id.cl_item)
        fun bind(user: UserModel) {

            tvName.text = user.login
            tvSubtitle.text = user.htmlUrl?.removeRange(0, 8)
            Picasso.get()
                .load(user.avatarUrl)
                .into(ivImage)
            clItem.setOnClickListener {
                listener(user)
            }
        }
    }
}
