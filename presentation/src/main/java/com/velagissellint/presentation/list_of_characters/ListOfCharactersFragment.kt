package com.velagissellint.presentation.list_of_characters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.velagissellint.presentation.R
import com.velagissellint.presentation.ViewModelFactory
import com.velagissellint.presentation.containersDi.ContainerAppContainer
import com.velagissellint.presentation.list_of_characters.adapters.CharactersListAdapter
import com.velagissellint.presentation.list_of_characters.adapters.CharactersListLoadStateAdapter
import javax.inject.Inject

class ListOfCharactersFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var listOfCharactersViewModel: ListOfCharactersViewModel

    private lateinit var navController: NavController
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
        navController = NavHostFragment.findNavController(this)
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
        adapter = CharactersListAdapter({ url, imageView ->
            listOfCharactersViewModel.loadImage(url, imageView)
        }, { id ->
            val action =
                ListOfCharactersFragmentDirections
                    .actionListOfCharactersFragmentToDetailedCharacterFragment(id)
            navController.navigate(action)
        })
        rv.adapter = adapter.withLoadStateFooter(CharactersListLoadStateAdapter { adapter.retry() })
    }

    private fun observeCharactersList() {
        listOfCharactersViewModel.pagesOfCharacters.observe(viewLifecycleOwner, {
            adapter.submitData(lifecycle, it)
        })
    }
}
