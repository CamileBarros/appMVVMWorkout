package com.camile.workoutmvvm

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.camile.workoutmvvm.adapters.MainAdapter
import com.camile.workoutmvvm.databinding.ActivityMainBinding
import com.camile.workoutmvvm.repositories.MainRepository
import com.camile.workoutmvvm.rest.RetrofitService
import com.camile.workoutmvvm.viewmodel.main.MainViewModel
import com.camile.workoutmvvm.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter {
        openLink(it.link)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )

        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer {
            adapter.setLiveList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}
