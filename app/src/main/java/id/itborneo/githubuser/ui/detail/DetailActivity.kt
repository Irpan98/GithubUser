package id.itborneo.githubuser.ui.detail

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import id.itborneo.githubuser.R
import id.itborneo.githubuser.data.model.UserDetailModel
import id.itborneo.githubuser.data.model.UserModel
import id.itborneo.githubuser.data.networks.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_USER = "user"
    }

    private var getIntentData: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        retrieveData()

        if (getIntentData != null) {
            getDetailUser()
        }
    }

    private fun retrieveData() {
        getIntentData = intent.extras?.getParcelable(EXTRA_USER)
    }

    private fun getDetailUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val detailUser = ApiConfig.apiService.detailUser(getIntentData?.login ?: "")
            CoroutineScope(Dispatchers.Main).launch {
                updateUI(detailUser)
            }

        }


    }

    private fun updateUI(userDetail: UserDetailModel?) {

        findViewById<TextView>(R.id.tvName).text = userDetail?.name ?: "N/A"
        findViewById<TextView>(R.id.tvUsername).text = userDetail?.login
        findViewById<TextView>(R.id.tvAddress).text = userDetail?.location ?: "N/A"
        findViewById<TextView>(R.id.tvWorkplace).text = userDetail?.company ?: "N/A"
        findViewById<TextView>(R.id.tvFollower).text = userDetail?.followers.toString()
        findViewById<TextView>(R.id.tvFollowing).text = userDetail?.following.toString()
        findViewById<TextView>(R.id.tvRepository).text = userDetail?.publicRepos.toString()

        Picasso.get()
            .load(userDetail?.avatarUrl)
            .fit()
            .centerCrop()
            .into(findViewById<ImageView>(R.id.iv_avatar))
    }
}
