package com.velagissellint.presentation.list_of_characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import com.velagissellint.domain.models.Result
import com.velagissellint.presentation.R
import com.velagissellint.presentation.list_of_characters.CharactersLIstViewHolder
import com.velagissellint.presentation.list_of_characters.CharactersListDiffCallback

class CharactersListAdapter(
    private val loadImage: (String, ImageView) -> Unit,
    private val clickOnItem: (id: String) -> Unit
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
            holder.itemView.setOnClickListener {
                clickOnItem(id.toString())
            }
        }
    }
}
