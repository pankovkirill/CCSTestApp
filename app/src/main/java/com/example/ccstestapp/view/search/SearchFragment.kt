package com.example.ccstestapp.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ccstestapp.CURRENCY_LIST
import com.example.ccstestapp.R
import com.example.ccstestapp.databinding.FragmentSearchBinding
import com.example.ccstestapp.view.main.MainFragment

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding

    private val displayList = arrayListOf<String>()

    private val adapter: SearchAdapter by lazy {
        SearchAdapter(
            onListItemClickListener,
            displayList
        )
    }

    private val onListItemClickListener = object :
        SearchAdapter.OnListItemClickListener {
        override fun onItemClick(data: String) {
            parentFragmentManager.popBackStack()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.container, MainFragment.newInstance(data))
                .commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        displayList.addAll(CURRENCY_LIST)

        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favoriteRecyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(onQueryTextListener)
    }

    private val onQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            p0?.let {
                if (it.isNotEmpty()) {
                    displayList.clear()
                    CURRENCY_LIST.forEach { item ->
                        if (item.contains(it.uppercase()))
                            displayList.add(item)
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    displayList.clear()
                    displayList.addAll(CURRENCY_LIST)
                    adapter.notifyDataSetChanged()
                }
            }

            return true
        }
    }
}