package com.example.harrypottercaracters.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.harrypottercaracters.CharacterAdapter
import com.example.harrypottercaracters.MainRepository
import com.example.harrypottercaracters.MyViewModelFactory
import com.example.harrypottercaracters.databinding.ActivityMainBinding
import com.example.harrypottercaracters.R
import com.example.harrypottercaracters.networking.RetrofitService
import com.example.harrypottercaracters.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

	lateinit var viewModel: MainViewModel
	private val adapter = CharacterAdapter()
	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val retrofitService = RetrofitService.getInstance()
		val mainRepository = MainRepository(retrofitService)
		binding.recyclerview.adapter = adapter

		viewModel = ViewModelProvider(this, MyViewModelFactory(mainRepository)).get(MainViewModel::class.java)


		viewModel.characterList.observe(this, {
			adapter.setCharacter(it)
		})

		viewModel.errorMessage.observe(this, {
			Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
		})

		viewModel.loading.observe(this, Observer {
			if (it) {
				binding.progressDialog.visibility = View.VISIBLE
			} else {
				binding.progressDialog.visibility = View.GONE
			}
		})

		viewModel.getAllCharacters()

	}
}