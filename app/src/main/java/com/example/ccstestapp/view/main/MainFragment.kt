package com.example.ccstestapp.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccstestapp.App
import com.example.ccstestapp.model.interactor.MainInteractorImpl.Companion.ASCENDING
import com.example.ccstestapp.model.interactor.MainInteractorImpl.Companion.A_TO_Z_ORDER
import com.example.ccstestapp.model.interactor.MainInteractorImpl.Companion.DESCENDING
import com.example.ccstestapp.model.interactor.MainInteractorImpl.Companion.Z_TO_A_ORDER
import com.example.ccstestapp.viewmodel.MainViewModel
import com.example.ccstestapp.R
import com.example.ccstestapp.databinding.FragmentMainBinding
import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.data.RatesModel
import com.example.ccstestapp.view.search.SearchFragment
import javax.inject.Inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    private lateinit var baseRate: String

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: RatesModel) {
                viewModel.addRateToFavoriteList(data.name)
                Toast.makeText(context, "add", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.instance.component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        baseRate = arguments?.getString(ARG_OBJECT) ?: BASE_RATE

        initViewModel()
        initAdapter()

        binding.search.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, SearchFragment())
                .commit()
        }

        binding.filter.setOnClickListener {
            showPopupMenu(it)
        }

    }

    private fun initAdapter() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.recycler_divider, null))
        binding.mainRecyclerView.addItemDecoration(dividerItemDecoration)

        binding.mainRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(MainViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            viewModel.data.collect {
                renderData(it)
            }
        }
        viewModel.getData(A_TO_Z_ORDER, baseRate)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                appState.data?.let {
                    if (it.rates.isEmpty()) {
                        Toast.makeText(
                            context,
                            getString(R.string.no_data_available),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else
                        showData(appState)
                }
            }
            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.INVISIBLE
                Toast.makeText(context, appState.error.message, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    private fun showData(appState: AppState.Success) {
        binding.base.text = appState.data?.base

        appState.data?.let { adapter.setData(it.rates) }
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.apply {
            inflate(R.menu.main_popup_menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.fromAtoZ -> {
                        viewModel.getData(A_TO_Z_ORDER, baseRate)
                    }
                    R.id.fromZtoA -> {
                        viewModel.getData(Z_TO_A_ORDER, baseRate)
                    }
                    R.id.ascending -> {
                        viewModel.getData(ASCENDING, baseRate)
                    }
                    R.id.descending -> {
                        viewModel.getData(DESCENDING, baseRate)
                    }
                }
                true
            }
            show()
        }
    }

    companion object {
        private const val BASE_RATE = "EUR"
        private const val ARG_OBJECT = "item"

        fun newInstance(item: String) = MainFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_OBJECT, item)
            }
        }
    }


}