package id.itborneo.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.itborneo.githubuser.R
import id.itborneo.githubuser.data.model.UserModel
import id.itborneo.githubuser.data.networks.ApiConfig
import id.itborneo.githubuser.ui.detail.DetailActivity
import id.itborneo.githubuser.ui.detail.DetailActivity.Companion.EXTRA_USER
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var rvHome: RecyclerView
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        initData()
    }

    private fun initData() {
        CoroutineScope(Dispatchers.IO).launch {

            val listUser = ApiConfig.apiService.users()
            CoroutineScope(Dispatchers.Main).launch {
                adapter.set(listUser)
            }
        }

    }

    private fun initView() {
        rvHome = findViewById(R.id.rv_home)
    }

    private fun initRecyclerView() {
        rvHome.layoutManager = GridLayoutManager(this, 2)
        adapter = MainAdapter {
            actionToDetail(it)
        }
        rvHome.adapter = adapter


    }

    private fun actionToDetail(user: UserModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_USER, user)
        startActivity(intent)
    }
}