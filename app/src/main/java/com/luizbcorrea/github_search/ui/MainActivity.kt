package com.luizbcorrea.github_search.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.luizbcorrea.github_search.R
import com.luizbcorrea.github_search.core.createDialog
import com.luizbcorrea.github_search.core.createProgressDialog
import com.luizbcorrea.github_search.core.hideSoftKeyboard
import com.luizbcorrea.github_search.databinding.ActivityMainBinding
import com.luizbcorrea.github_search.presentation.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter

        viewModel.getRepoList("LuizCorrea-Dev")

        binding.buttonClean.setOnClickListener {
            binding.rvRepos.adapter = null
            binding.buttonClean.isEnabled = false
        }





        viewModel.repos.observe(this) {
            when (it) {

                MainViewModel.State.Loading -> dialog.show()

                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)

                }
            }
        }

    }

    // menu de search
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)



        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    // escutando o botão de pesquisa
    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it) }
        binding.root.hideSoftKeyboard()

        binding.rvRepos.adapter = adapter
        binding.buttonClean.isEnabled = true
        return true
    }

    // escutando o teclado e armazenando em newText
    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange>>>>>>>>>>>: $newText")
        return false
    }

    companion object {
        private const val TAG = "TAG"
    }


}
