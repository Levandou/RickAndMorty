package com.velagissellint.presentation.detailed_character

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.velagissellint.presentation.R
import com.velagissellint.presentation.ViewModelFactory
import com.velagissellint.presentation.containersDi.ContainerAppContainer
import com.velagissellint.presentation.databinding.FragmentDetailedCharacterBinding
import javax.inject.Inject

class DetailedCharacterFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    lateinit var detailedCharacterViewModel: DetailedCharacterViewModel

    private var _binding: FragmentDetailedCharacterBinding? = null
    private val binding: FragmentDetailedCharacterBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailedCharacterBinding == null")
    private var id: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as ContainerAppContainer).appContainer()
            ?.plusDetailedCharacterComponent()?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity?)?.supportActionBar?.title =
            getString(R.string.title_for_detailed_character)
        backPressed()
        val args: DetailedCharacterFragmentArgs by navArgs()
        id = args.myArg
        detailedCharacterViewModel =
            ViewModelProvider(this, factory).get(DetailedCharacterViewModel::class.java)
        detailedCharacterViewModel.loadDetailedCharacter(id)
        _binding = FragmentDetailedCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProgressBar(binding.progressBar, binding.businessBlock)
        observeDetailedCharacter()
    }

    @SuppressLint("SetTextI18n")
    private fun observeDetailedCharacter() {
        detailedCharacterViewModel.detailedCharacter.observe(viewLifecycleOwner, {
            loadAndSetImage(it.image, binding.ivLogo)
            binding.tvName.text = it.name
            binding.tvSpecies.text = DESCRIPTION_SPECIES + it.species
            binding.tvGender.text = DESCRIPTION_GENDER + it.gender
            binding.tvStatus.text = DESCRIPTION_STATUS + it.status
            binding.tvLocation.text = DESCRIPTION_LAST_LOCATION + it.location.name
            binding.tvNumberOfEpisodes.text =
                DESCRIPTION_NUMBER_EPISODES + it.episode.size.toString()
        })
    }

    private fun observeProgressBar(progressBar: ProgressBar, businessBlock: LinearLayout) {
        detailedCharacterViewModel.isLoading.observe(viewLifecycleOwner, {
            progressBar.isVisible = it
            businessBlock.isVisible = !it
        })
    }

    private fun loadAndSetImage(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }

    private fun backPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val DESCRIPTION_SPECIES = "Species: "
        private const val DESCRIPTION_GENDER = "Gender: "
        private const val DESCRIPTION_STATUS = "Status: "
        private const val DESCRIPTION_LAST_LOCATION = "Last location: "
        private const val DESCRIPTION_NUMBER_EPISODES = "Number of episodes: "
    }
}
