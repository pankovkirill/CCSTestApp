package com.example.ccstestapp.view.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccstestapp.App
import com.example.ccstestapp.R
import com.example.ccstestapp.databinding.FragmentFavoriteBinding
import com.example.ccstestapp.model.room.FavoriteEntity
import com.example.ccstestapp.view.main.MainFragment
import com.example.ccstestapp.viewmodel.FavoriteViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {
    private lateinit var binding: FragmentFavoriteBinding

    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: FavoriteViewModel

    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            onListItemClickListener,
            onLongListItemClickListener
        )
    }

    private val onListItemClickListener = object :
        FavoriteAdapter.OnListItemClickListener {
        override fun onItemClick(data: String) {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(data))
                .commit()
        }
    }

    private val onLongListItemClickListener = object :
        FavoriteAdapter.OnLongListItemClickListener {
        override fun onItemClick(
            itemView: View,
            layoutPosition: Int,
            id: Int,
            list: MutableList<FavoriteEntity>
        ): Boolean {
            showPopupMenu(itemView, layoutPosition, id, list)
            return true
        }
    }

    private fun showPopupMenu(
        itemView: View,
        layoutPosition: Int,
        id: Int,
        list: MutableList<FavoriteEntity>
    ) {
        val popupMenu = PopupMenu(context, itemView)
        popupMenu.apply {
            inflate(R.menu.favorite_popup_menu)

            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.delete -> {
                        viewModel.deleteById(id)
                        list.removeAt(layoutPosition)
                        binding.favoriteRecyclerView.adapter?.notifyItemRemoved(layoutPosition)
                    }
                }
                true
            }
            show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        App.instance.component.inject(this)
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)

        initViewModel()
        initView()
    }

    private fun initView() {
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)

        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(
            resources.getDrawable(R.drawable.recycler_divider, null)
        )
        binding.favoriteRecyclerView.addItemDecoration(dividerItemDecoration)

        binding.favoriteRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = viewModelFactory.create(FavoriteViewModel::class.java)
        lifecycleScope.launchWhenStarted {
            viewModel.data.collect {
                renderData(it)
            }
        }
        viewModel.gatData()
    }

    private fun renderData(list: List<FavoriteEntity>) {
        if (list.isEmpty()) {
            Toast.makeText(context, getString(R.string.no_data_available), Toast.LENGTH_SHORT)
                .show()
        } else
            adapter.setData(list as ArrayList<FavoriteEntity>)
    }
}