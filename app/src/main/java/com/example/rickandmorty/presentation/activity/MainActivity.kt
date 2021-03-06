package com.example.rickandmorty.presentation.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.ActivityMainBinding
import com.example.rickandmorty.presentation.base.BaseActivity
import com.example.rickandmorty.presentation.fragment.characters.CharactersListFragment
import kotlin.reflect.KClass

class MainActivity : BaseActivity<MainViewModel, MainViewModel.Factory, ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.onCreate()
        }

        viewModel.startEvent.observe(this) {
            startToDoFragment()
        }
    }

    override val viewModelClass: KClass<MainViewModel> = MainViewModel::class

    override fun createViewBinding(inflater: LayoutInflater): ActivityMainBinding =
        ActivityMainBinding.inflate(inflater)

    private fun startToDoFragment() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<CharactersListFragment>(R.id.fragmentContainer)
        }
    }
}