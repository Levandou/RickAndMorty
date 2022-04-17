package com.velagissellint.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.velagissellint.presentation.R
import com.velagissellint.presentation.ViewModelFactory
import com.velagissellint.presentation.containersDi.ContainerAppContainer
import com.velagissellint.presentation.ui.adapters.CharactersListAdapter
import com.velagissellint.presentation.ui.adapters.CharactersListLoadStateAdapter
import javax.inject.Inject

class ListOfCharactersFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var listOfCharactersViewModel: ListOfCharactersViewModel

    private lateinit var adapter: CharactersListAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ContainerAppContainer).appContainer()
            ?.plusListOfCharactersComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)?.supportActionBar?.title =
            getString(R.string.title_for_list_of_characters)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)
        listOfCharactersViewModel =
            ViewModelProvider(this, factory).get(ListOfCharactersViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list_of_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        observeCharactersList()
    }

    private fun setupRecyclerView(view: View) {
        val rv = view.findViewById<RecyclerView>(R.id.rv_characters)
        rv.addItemDecoration(DividerItemDecoration(activity?.applicationContext))
        adapter = CharactersListAdapter { url, imageView ->
            listOfCharactersViewModel.loadImage(url, imageView)
        }
        rv.adapter = adapter.withLoadStateFooter(CharactersListLoadStateAdapter { adapter.retry() })
    }

    private fun observeCharactersList() {
        listOfCharactersViewModel.pagesOfCharacters.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }
}
