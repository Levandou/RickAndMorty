package com.velagissellint.presentation.list_of_characters

import androidx.recyclerview.widget.DiffUtil
import com.velagissellint.domain.models.Result

class CharactersListDiffCallback : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(
        oldItem: Result,
        newItem: Result
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Result,
        newItem: Result
    ) = oldItem == newItem
}
