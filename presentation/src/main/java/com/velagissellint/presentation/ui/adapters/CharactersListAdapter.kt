package com.velagissellint.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import com.velagissellint.domain.pojo.Result
import com.velagissellint.presentation.R
import com.velagissellint.presentation.ui.CharactersLIstViewHolder
import com.velagissellint.presentation.ui.CharactersListDiffCallback

class CharactersListAdapter(
    private val loadImage: (String, ImageView) -> Unit
) : PagingDataAdapter<Result, CharactersLIstViewHolder>(
    CharactersListDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersLIstViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_of_character,
            parent,
            false
        )
        return CharactersLIstViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersLIstViewHolder, position: Int) {
        getItem(position)?.apply {
            loadImage(image, holder.ivLogo)
            holder.tvName.text = name
            holder.tvGender.text = gender
            holder.tvSpecies.text = species
        }
    }
}
