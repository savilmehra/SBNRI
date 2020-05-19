package com.`in`.sbnri

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.`in`.sbnri.PaginationListener.PAGE_START
import com.`in`.sbnri.databinding.ActivityMainBinding
import com.`in`.sbnri.entities.MainEntity
import com.`in`.sbnri.mvvm.ViewModelFactory
import com.`in`.sbnri.retrofit.ApiClient
import com.`in`.sbnri.retrofit.Webservices
import kotlinx.android.synthetic.main.content_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var adapter: AdapterRecyler? = null
    private var currentPage: Int = 1
    private var isLastPage = false
    private lateinit var feedViewModel: GenericViewModel
    private var pageSize = 10
    var itemCount = 0
    private var loading = true
    var pastVisiblesItems = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    private var list: MutableList<MainEntity>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val layoutManager = LinearLayoutManager(this)

        binding!!.lytMain.rv.hasFixedSize()
        binding!!.lytMain.rv.setLayoutManager(layoutManager)
        adapter = AdapterRecyler(this)
        binding!!.lytMain.rv.adapter = adapter
        setViewModel()
        getData()
        binding!!.lytMain.rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount()
                    totalItemCount = layoutManager.getItemCount()
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
                    if (!loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            currentPage++
                            getData()
                        }
                    }
                }
            }
        })


    }

    private fun setViewModel() {
        feedViewModel =
            ViewModelProvider(this, ViewModelFactory(application!!)).get<GenericViewModel>(
                GenericViewModel::class.java
            )

        feedViewModel.getAllListOfChatters().observe(this, Observer<List<MainEntity>> { data ->

            if (data != null && data.isNotEmpty()) {
                if (list != null && list!!.isNotEmpty())
                    adapter!!.setData(data, list!!.size)
                else
                    adapter!!.setData(data, 0)

                list = data as MutableList<MainEntity>?
            }


        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getData() {
        val BASE_URL = "https://api.github.com/"
        val apiService = ApiClient.getChatterList(BASE_URL).create(Webservices::class.java)
        loading = true
        binding!!.lytMain.pb.visibility = View.VISIBLE
        val call = apiService.getData(currentPage, pageSize)
        call.enqueue(object : Callback<List<MainEntity>> {
            override fun onResponse(
                call: Call<List<MainEntity>>,
                response: Response<List<MainEntity>>
            ) {
                loading = false
                binding!!.lytMain.pb.visibility = View.GONE
                if (response != null && response.body() != null) {
                    val data = response.body() as List<MainEntity>
                    if (data != null && data.isNotEmpty()) {
                        feedViewModel.insertData(data)

                    }

                }


            }

            override fun onFailure(call: Call<List<MainEntity>>, t: Throwable) {
                loading = false
                binding!!.lytMain.pb.visibility = View.GONE
                Toast.makeText(this@MainActivity, " Call Failed Try Again", Toast.LENGTH_SHORT)
                    .show()
            }

        })
    }
}
